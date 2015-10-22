FROM openshift/wildfly-8-centos

MAINTAINER "salaboy" "salaboy@gmail.com"

USER wildfly

ADD maven/extra/ROOT.war /wildfly/standalone/deployments/ROOT.war

ADD maven/extra /wildfly/extra/

CMD ["/wildfly/extra/execute.sh"]

