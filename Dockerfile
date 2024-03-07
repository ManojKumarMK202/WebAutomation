FROM bellsoft/liberica-openjdk-alpine:17
# In order to check hub status curl and jq will be installed
RUN apk add curl jq

WORKDIR /home/docker-webAutomation

ADD target/docker-resources ./
# Removed entry point becoz command is in runner.sh added runner.sh into docker
ADD runner.sh   runner.sh

# ENTRYPOINT java \
#             -Dbrowser=${BROWSER} \
#             -DgridExecution=true \
#             -Dhub=${HUB} \
#             -cp 'libs/*' \
#             org.testng.TestNG \
#             -threadcount ${THREADCOUNT} \
#             ${TEST_SUITE}

# Entry point to execute runner.sh
ENTRYPOINT sh runner.sh