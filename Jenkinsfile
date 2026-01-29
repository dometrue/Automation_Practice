pipeline {
    agent any

    tools {
        // Use JDK 17 (matches your Maven config)
        jdk 'jdk-17'
        // Use Maven (configured in Jenkins global tools)
        maven 'Maven 3.9.0'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean & Build') {
            steps {
                // Clean target and run tests
                bat 'mvn clean test'
            }
        }

        stage('Allure Report') {
            steps {
                // Generate Allure report
                sh 'mvn allure:report'
            }
            post {
                always {
                    // Publish Allure report in Jenkins
                    allure includeProperties: false, reportBuildPolicy: ALWAYS, results: [[path: 'target/allure-results']]
                }
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
