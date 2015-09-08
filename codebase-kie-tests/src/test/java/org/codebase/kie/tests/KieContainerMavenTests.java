package org.codebase.kie.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.codebase.model.user.User;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


/**
 *
 * @author salaboy
 */
public class KieContainerMavenTests {

   @Test
   public void remoteRepoTest(){
       
       
       KieServices ks = KieServices.Factory.get();
       KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("org.codebase", 
                                                                    "codebase-kie", 
                                                                    "1.0.0-SNAPSHOT"));
       assertNotNull(kContainer);
       
       KieSession newKieSession = kContainer.newKieSession();
       assertNotNull(newKieSession);
       
       newKieSession.insert(new User());
       
       int fireAllRules = newKieSession.fireAllRules();
       
   }
    
   

}
