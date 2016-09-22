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
COPY tables.sql start.sh /config/

# installs mariadb server
RUN yum -y install --setopt=tsflags=nodocs epel-release && \
    yum -y install --setopt=tsflags=nodocs MariaDB-server bind-utils pwgen psmisc hostname && \
    yum -y erase vim-minimal && \
    yum clean all

# copy mariadb configuration file
COPY my.cnf /etc/

# assign permissions
RUN chown -R mysql /var/lib/mysql/ /run/ /config/ /etc/my.cnf /etc/my.cnf.d/ && \
    chgrp -R 0 /var/lib/mysql/ /run/ /config/ /etc/my.cnf /etc/my.cnf.d/  && \
    chmod -R g+rw /var/lib/mysql/ /run/ /config/ /etc/my.cnf /etc/my.cnf.d/  && \
    find /var/lib/mysql/ -type d -exec chmod g+x {} + && \
    find /run/ -type d -exec chmod g+x {} + && \
    find /config/ -type d -exec chmod g+x {} + && \
    find /etc/my.cnf -type d -exec chmod g+x {} + && \
    find /etc/my.cnf.d/ -type d -exec chmod g+x {} +

ENTRYPOINT ["/config/start.sh"]

# after any changes to the file system!
VOLUME /var/lib/mysql

# runs as mariadb mysql user (or a random user in OpenShift)
USER mysql

EXPOSE 3306