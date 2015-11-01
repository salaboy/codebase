/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.endpoints.school;

import org.codebase.services.endpoints.school.config.AuthRESTResponseFilter;
import org.codebase.services.endpoints.school.impl.PublicSchoolEndpointServiceImpl;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.jpa.JPAFraction;

/**
 *
 * @author salaboy
 */
public class App {

    public static void main(String[] args) throws Exception {
        Container container = new Container();

        container.fraction(new DatasourcesFraction()
                .jdbcDriver("h2", (d) -> {
                    d.driverDatasourceClassName("org.h2.Driver");
                    d.xaDatasourceClass("org.h2.jdbcx.JdbcDataSource");
                    d.driverModuleName("com.h2database.h2");
                })
                .dataSource("MyDS", (ds) -> {
                    ds.driverName("h2");
                    ds.connectionUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
                    ds.userName("sa");
                    ds.password("sa");
                })
        );

        container.fraction(new JPAFraction()
                .inhibitDefaultDatasource()
                .defaultDatasource("jboss/datasources/MyDS")
        );

        container.start();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.setContextRoot("/api");  
        deployment.addPackages(true, "org.codebase");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("META-INF/persistence.xml", App.class.getClassLoader()), "classes/META-INF/persistence.xml");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("META-INF/users.xml", App.class.getClassLoader()), "classes/META-INF/users.xml");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("META-INF/courses.xml", App.class.getClassLoader()), "classes/META-INF/courses.xml");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("META-INF/schoolclasses.xml", App.class.getClassLoader()), "classes/META-INF/schoolclasses.xml");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("META-INF/schools.xml", App.class.getClassLoader()), "classes/META-INF/schools.xml");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("META-INF/teachers.xml", App.class.getClassLoader()), "classes/META-INF/teachers.xml");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("META-INF/students.xml", App.class.getClassLoader()), "classes/META-INF/students.xml");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("META-INF/years.xml", App.class.getClassLoader()), "classes/META-INF/years.xml");
        
//        deployment.addResource(PublicSchoolEndpointServiceImpl.class);
        deployment.addClass(PublicSchoolEndpointServiceImpl.class);
        deployment.addClass(AuthRESTResponseFilter.class);
        container.deploy(deployment);
    }
}
