FROM openjdk:8-jre-alpine
RUN apk add curl jq
WORKDIR /usr/share/selenium-docker-automation

#copy jar packages and libs contain selenium dependencies
ADD target/selenium-docker-automation.jar selenium-docker-automation.jar
ADD target/selenium-docker-automation-tests.jar selenium-docker-automation-tests.jar
ADD target/libs libs
#copy test data
ADD src/test/resources/testcases/FlightsModule/Registration.json testcases/FlightsModule/Registration.json
#copy xml files
ADD book_flights.xml book_flights.xml
ADD search_module.xml search_module.xml


#Add health check script
ADD healthcheck.sh healthcheck.sh
#entrypoint
#BROWSER,HUB_HOST,MODULE
ENTRYPOINT sh healthcheck.sh