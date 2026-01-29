pipeline {
    agent any

    tools {
        jdk 'jdk-17'
        maven 'maven-3.9.12'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & API Tests') {
            steps {
                bat 'mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml'
            }
        }

        stage('UI Tests') {
            steps {
                bat 'mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-ui.xml'
            }
        }
    }

    post {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}
