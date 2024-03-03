FROM bellsoft/liberica-openjdk-alpine:17

WORKDIR /home/docker-webAutomation

ADD target/docker-resources ./

ENTRYPOINT java -Dbrowser=${BROWSER} -DgridExecution=true -Dhub=${HUB} -cp 'libs/*' org.testng.TestNG -threadcount ${THREADCOUNT} {TEST_SUITE}