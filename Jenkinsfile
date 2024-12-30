pipeline {
    agent any

    tools {
        maven 'Maven'  // Replace 'Maven' with your configured Maven installation name
        jdk 'JDK'      // Replace 'JDK' with your configured JDK installation name
    }

    environment {
        ALLURE_RESULTS_DIR = 'target/allure-results'
        ALLURE_REPORT_DIR = 'target/allure-report'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out Git repository...'
                checkout scmGit(
                    branches: [[name: '*/master']],
                    extensions: [],
                    userRemoteConfigs: [[
                        credentialsId: 'SSH_Jenkins',
                        url: 'git@github.com:FrameworkSeleniumPriya/SeleniumCucumberFramework.git'
                    ]]
                )
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Executing tests...'
                sh 'mvn test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                echo 'Generating Allure report...'
                sh 'allure serve allure-results'
            }
        }
    }
}
