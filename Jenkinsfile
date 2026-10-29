pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Artem Kostin'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x gradlew && ./gradlew clean assemble --no-daemon'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test --no-daemon'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t kostin-artem-student-api:v1.0-kostin-artem .'
            }
        }

        stage('Verify') {
            steps {
                sh 'docker image inspect kostin-artem-student-api:v1.0-kostin-artem'
            }
        }
    }
}
