#!/usr/bin/env bash
echo "checking if hub is ready -$HUB_HOST"

while [ "$( curl -s http://$HUB_HOST:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
do
	sleep 1
done

java -cp selenium-docker-automation.jar:selenium-docker-automation-tests.jar:libs/*  -DHUB_HOST=$HUB_HOST -DBROWSER=$BROWSER org.testng.TestNG $MODULE