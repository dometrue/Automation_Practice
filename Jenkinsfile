pipeline {
    agent any  // Runs on any available agent

    stages {
        stage('Clean & Test') {
            steps {
                // Run all tests (UI + API) via Maven
                bat 'mvn clean test'  // Windows agent
                // If Linux agent: sh 'mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                // Generate Allure report from results
                bat 'allure generate target/allure-results --clean -o target/allure-report'
            }
        }

        stage('Publish Allure Report') {
            steps {
                // Open Allure report
                bat 'allure open target/allure-report'
            }
        }
    }

    post {
        always {
            // Publish test results to Jenkins for visualization
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
