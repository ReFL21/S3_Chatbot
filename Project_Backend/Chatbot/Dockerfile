FROM gradle:7.5.0-jdk17
WORKDIR /opt/app
COPY ./ ./
RUN gradle assemble

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar build/libs/Chatbot-0.0.1-SNAPSHOT.jar" ]
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar chatbot.jar
