package org.codebase.kie.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import org.codebase.model.user.User;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;



/**
 *
 * @author salaboy
 */
public class KieContainerMavenTests {

    @Test
    public void remoteRepoTest() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("org.codebase",
                "codebase-kie",
                "1.0.0-SNAPSHOT"));
        assertNotNull(kContainer);
        System.out.println("Creating codebase-kie container!.....");
        Results results = kContainer.verify();
        results.getMessages().stream().forEach((message) -> {
            System.out.println(">> Message ( " + message.getLevel() + " ): " + message.getText());
        });
        assertThat(false, is(results.hasMessages(Message.Level.ERROR)));
        kContainer.getKieBaseNames().stream().map((kieBase) -> {
            System.out.println(">> Loading KieBase: " + kieBase);
            return kieBase;
        }).forEach((kieBase) -> {
            kContainer.getKieSessionNamesInKieBase(kieBase).stream().forEach((kieSession) -> {
                System.out.println("\t >> Containing KieSession: " + kieSession);
            });
        });

        KieSession newKieSession = kContainer.newKieSession();
        assertNotNull(newKieSession);

        newKieSession.insert(new User());

        int fireAllRules = newKieSession.fireAllRules();
        
        assertThat(fireAllRules, is(2));
        User user = new User("salaboy", "asd");
        List<String> roles = new ArrayList<String>();
        roles.add("admin");
        roles.add("student");
        user.setRoles(roles);
        newKieSession.insert(user);

        fireAllRules = newKieSession.fireAllRules();
        
        assertThat(fireAllRules, is(4));

    }

}
