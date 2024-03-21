pipeline {
    agent any

    stages {
        stage('Build jar') {
            steps {
                sh "mvn clean -DskipTests"
            }
        }

        stage('Build Image') {
            steps {
                sh "docker build -t=manojhonda691/docker-web-automation ."
            }
        }

        stage('Push Image') {
            steps {
                sh "docker push manojhonda691/docker-web-automation"
            }
        }
    }
}
