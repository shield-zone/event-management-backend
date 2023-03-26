#!/bin/sh

# export SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3306/events"
# export SPRING_DATASOURCE_USERNAME="root"
# export SPRING_DATASOURCE_PASSWORD="12345"
# mvn spring-boot:run

echo $SPRING_DATASOURCE_URL
echo $SPRING_DATASOURCE_USERNAME
echo $SPRING_DATASOURCE_PASSWORD

java -jar event-management-backend.jar
