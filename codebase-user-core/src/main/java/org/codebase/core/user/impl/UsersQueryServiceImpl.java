/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.core.user.impl;

import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.lucene.search.Query;
import org.codebase.model.user.User;
import org.codebase.core.user.api.UsersQueryService;
import org.codebase.shared.exceptions.ServiceException;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 *
 * @author salaboy
 */
@ApplicationScoped
public class UsersQueryServiceImpl implements UsersQueryService {

    @PersistenceContext
    private EntityManager pm;

    private final static Logger log = Logger.getLogger(UsersQueryServiceImpl.class.getName());

    public UsersQueryServiceImpl() {
    }

    @PostConstruct
    public void init() throws InterruptedException {
        Search.getFullTextEntityManager(pm).createIndexer().startAndWait();
    }


    @Override
    public List<User> search( Integer offset, Integer limit, List<String> excludesEmails) throws ServiceException {
        FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(pm);
        QueryBuilder qb = fullTextEm.getSearchFactory().buildQueryBuilder().forEntity(User.class).get();
        String excludesString = "";
        for (String e : excludesEmails) {
            excludesString += e + " ";
        }
        System.out.println(">>> Excluding emails : " + excludesString);
        BooleanJunction<BooleanJunction> bool = qb.bool();
        bool.must(qb.keyword().onField("live").matching("true").createQuery());
        bool.must(qb.keyword().onField("email").matching(excludesString).createQuery()).not();

        Query query = bool.createQuery();

        FullTextQuery fullTextQuery = fullTextEm.createFullTextQuery(query, User.class);
        fullTextQuery.setSort(org.apache.lucene.search.Sort.RELEVANCE);

       
        if (offset != null && limit != null) {
            fullTextQuery.setFirstResult(offset);
            fullTextQuery.setMaxResults(limit);
        } else {
            fullTextQuery.setFirstResult(0);
            fullTextQuery.setMaxResults(20);
        }    

        List resultList = fullTextQuery.getResultList();

        System.out.println("Results size for query between page (" + fullTextQuery.getFirstResult() + "-to -" + fullTextQuery.getMaxResults() + ") = " + resultList.size());
        return resultList;
    }

    

}
