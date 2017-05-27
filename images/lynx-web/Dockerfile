FROM centos:7

MAINTAINER Lynx <lynx@gatblau.org>

LABEL Vendor="gatblau" \
      Version=0.0.1 \
      io.k8s.description="The Web App for Lynx." \
      io.k8s.display-name="Lynx Web App" \
      io.openshift.expose-services="80:lynx-web" \
      io.openshift.tags="lynx, web"

# copy server configuration files
COPY start.sh /config/

# installs java runtime environment
RUN yum -y install --setopt=tsflags=nodocs java-1.8.0-openjdk && \
    yum -y erase vim-minimal && \
    yum clean all