FROM centos:7

MAINTAINER Lynx <http://www.gatblau.org/lynx>

LABEL Vendor="gatblau" \
      Version=0.0.1 \
      io.k8s.description="The database services for Lynx surveys." \
      io.k8s.display-name="Lynx Data" \
      io.openshift.expose-services="3306:lynxdata" \
      io.openshift.tags="lynx, data"

# installs mariadb yum repo
COPY mariadb-10-1.repo /etc/yum.repos.d/

# copy server configuration files
COPY tables.sql mysql-init.sql perm.sh start.sh /config/

# installs mariadb server
RUN yum -y install --setopt=tsflags=nodocs epel-release && \
    yum -y install --setopt=tsflags=nodocs MariaDB-server bind-utils pwgen psmisc hostname && \
    yum -y erase vim-minimal && \
    yum -y update && \
    yum clean all && \
    test "$(id mysql)" = "uid=997(mysql) gid=996(mysql) groups=996(mysql)"

# copy mariadb configuration file
COPY my.cnf /etc/

# assign permissions
RUN  /config/perm.sh /var/lib/mysql/ && \
     /config/perm.sh /run/ && \
     /config/perm.sh /config/ && \
     /config/perm.sh /etc/my.cnf && \
     /config/perm.sh /etc/my.cnf.d/

ENTRYPOINT ["/config/start.sh"]

# after any changes to the file system!
VOLUME /var/lib/mysql

# runs as mariadb mysql user 997 (or a random user in OpenShift)
USER 997

EXPOSE 3306