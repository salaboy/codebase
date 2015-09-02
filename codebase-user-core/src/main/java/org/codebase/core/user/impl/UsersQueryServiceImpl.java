/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.core.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.codebase.model.user.User;
import org.codebase.core.user.api.UsersQueryService;
import org.codebase.core.exceptions.ServiceException;
import org.codebase.core.util.PersistenceManager;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.Unit;
import org.hibernate.search.query.engine.spi.FacetManager;
import org.hibernate.search.query.facet.Facet;
import org.hibernate.search.query.facet.FacetSelection;
import org.hibernate.search.query.facet.FacetSortOrder;
import org.hibernate.search.query.facet.FacetingRequest;
import org.hibernate.search.spatial.DistanceSortField;

/**
 *
 * @author salaboy
 */
@ApplicationScoped
public class UsersQueryServiceImpl implements UsersQueryService {

    @Inject
    private PersistenceManager pm;

    private final static Logger log = Logger.getLogger(UsersQueryServiceImpl.class.getName());

    public UsersQueryServiceImpl() {
    }

    @PostConstruct
    public void init() throws InterruptedException {
        Search.getFullTextEntityManager(pm.getEm()).createIndexer().startAndWait();
    }


    @Override
    public List<User> search( Integer offset, Integer limit, List<String> excludesEmails) throws ServiceException {
        FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(pm.getEm());
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
