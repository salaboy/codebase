FROM jboss/wildfly:8.2.0.Final

MAINTAINER "grogdj" "grogdj@gmail.com"

####### ENVIRONMENT ############

USER root

ADD maven/ROOT.war /opt/jboss/wildfly/standalone/deployments/


####### INTER WAR DEPENDENCY DESCRIPTOR FOR WILDFLY8 ############
RUN chown jboss:jboss $JBOSS_HOME/standalone/deployments/*

####### CUSTOM JBOSS USER ############
# Switchback to jboss user
USER jboss


####### RUNNING WILDFLY ############
WORKDIR $JBOSS_HOME/bin/
CMD ["./standalone.sh", "-b", "0.0.0.0"]