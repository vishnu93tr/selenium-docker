pipeline {
    agent any
	tools {
            maven 'Maven 3.8.1'
            jdk 'jdk8'
        }
    stages {
	    stage ('Initialize Maven and JDK 8') {
                steps {
                    sh '''
                        echo "PATH = ${PATH}"
                        echo "M2_HOME = ${M2_HOME}"
                    '''
                }
            }
        stage('Build Jar') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {

                sh "docker build -t='vishnu26121993/selenium-docker:${BUILD_NUMBER}' ."
                sh "docker build -t='vishnu26121993/selenium-docker' ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub_credentials', passwordVariable: 'pass', usernameVariable: 'user')]) {
			        sh "docker login --username=${user} --password=${pass}"
			        sh "docker push vishnu26121993/selenium-docker:${BUILD_NUMBER}"
			        sh "docker push vishnu26121993/selenium-docker:latest"
			    }
            }
        }
    }
    post{
    always{
    archiveArtifacts artifacts: 'book_flights.xml'
    }

    }
}
