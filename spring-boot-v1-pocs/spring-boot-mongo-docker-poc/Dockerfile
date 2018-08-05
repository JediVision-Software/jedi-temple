FROM ubuntu

# Installing "add-apt-repository" support
RUN apt-get update && \
	apt-get install -y software-properties-common && \
	apt-get clean && \
	rm -rf /var/lib/apt/lists

# Java
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer

# Apache Maven
RUN apt-get update && \
	apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Git
RUN apt-get update && \
	apt-get install -y git-core && \
    rm -rf /var/lib/apt/lists/*

# Repository
RUN rm -rf spring-boot-mongo-docker-poc/
RUN git clone https://username:password/project.git
RUN cd spring-boot-mongo-docker-poc && \
    mvn clean install

# Mongodb
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10 && \
    echo "deb http://repo.mongodb.org/apt/ubuntu "$(lsb_release -sc)"/mongodb-org/3.2 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-3.2.list && \
    apt-get update && \
    apt-get install -y --force-yes pwgen mongodb-org mongodb-org-server mongodb-org-shell mongodb-org-mongos mongodb-org-tools && \
    echo "mongodb-org hold" | dpkg --set-selections && echo "mongodb-org-server hold" | dpkg --set-selections && \
    echo "mongodb-org-shell hold" | dpkg --set-selections && \
    echo "mongodb-org-mongos hold" | dpkg --set-selections && \
    echo "mongodb-org-tools hold" | dpkg --set-selections
RUN mkdir -p /data/db
RUN mkdir -p /log

# Environment variables
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
ENV M2_HOME /usr/share/maven

# Expose
EXPOSE 8787

# SpringBoot
CMD mongod --fork --logpath /log/mongodb.log && \
    java -jar spring-boot-mongo-docker-poc/target/spring-boot-mongo-docker-poc-1.0.jar

