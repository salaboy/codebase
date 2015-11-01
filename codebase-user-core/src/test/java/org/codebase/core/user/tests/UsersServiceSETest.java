/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.core.user.tests;

import javax.inject.Inject;

import org.codebase.model.user.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.codebase.core.user.api.UsersService;
import org.codebase.shared.exceptions.ServiceException;

/**
 *
 * @author gabi
 */
@RunWith(Arquillian.class)
public class UsersServiceSETest {

    @Inject
    private UsersService usersService;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.codebase")
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/users.xml", "users.xml")
                .addAsManifestResource("META-INF/servicekey.xml", "servicekey.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void removeUserTest() throws ServiceException, Exception {

        User u = new User("gabriela@gmail.com", "123456");

        Long newUserId = usersService.newUser(u);

        Assert.assertNotNull(newUserId);

        usersService.updateLive(newUserId, "true");

        usersService.removeUser(newUserId);

        User byId = usersService.getById(newUserId);
        Assert.assertTrue(byId == null);
    }

    

    

}
