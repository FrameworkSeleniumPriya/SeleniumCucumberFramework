pipeline {
    agent any

    tools {
        maven 'Maven'  // Replace 'Maven' with your configured Maven installation name
        jdk 'JDK21'    // Replace 'JDK' with your configured JDK installation name
    }

    environment {
        ALLURE_RESULTS_DIR = 'allure-results' // Make sure this path is correct
        ALLURE_REPORT_DIR = 'allure-report'
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
                bat 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Executing tests...'
                bat 'mvn test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                echo 'Generating Allure report...'
                // Ensure allure-results are generated before generating the report
                bat 'allure generate allure-results --clean -o allure-report'
                // Archive the results for further steps, including sending via email
                archiveArtifacts allowEmptyArchive: true, artifacts: '**/allure-report/**/*', onlyIfSuccessful: true
            }
        }

        stage('Cleanup') {
            steps {
                echo 'Cleaning up...'
                bat 'taskkill /F /IM java.exe /T'  // This kills any java processes running the Allure server (or other Java-based processes).
            }
        }

        stage('Send Allure Report via Email') {
            steps {
                script {
                    // Send an email with the allure-report artifact
                    emailext(
                        subject: "Allure Report - ${currentBuild.fullDisplayName}",
                        body: "Please find attached the Allure report for the Jenkins build.",
                        to: 'seleniumframeworkpriya@gmail.com', // Add the email recipient
                        attachmentsPattern: '**/allure-report/*'  // Attach the Allure report to the email
                    )
                }
            }
        }
    }

    post {
        always {
            cleanWs() // Clean up workspace after build
        }
    }
}
