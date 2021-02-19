pipeline {
    agent any
    stages {
        stage('Build Jar') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {

                sh "docker build -t='vishnu26121993/selenium-docker' ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub_credentials', passwordVariable: 'pass', usernameVariable: 'user')]) {
			        sh "docker login --username=${user} --password=${pass}"
			        sh "docker push vishnu26121993/selenium-docker:latest"
			    }
            }
        }
    }
}