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

    parameters {
       choice(name: 'CUCUMBER_TAGS', choices: ['@smoke', '@regression', '@sanity','@test'], description: 'Tags to run, e.g., @smoke, @regression')
        
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
                script {
                    echo "Executing tests with tags: ${params.CUCUMBER_TAGS}"
                    bat "mvn test -Dcucumber.filter.tags=${params.CUCUMBER_TAGS}"
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                echo 'Generating Allure report...'
                bat 'allure generate allure-results --clean -o allure-report'
                archiveArtifacts allowEmptyArchive: true, artifacts: '**/allure-report/**/*', onlyIfSuccessful: true
            }
        }

        stage('Publish Allure Report') {
            steps {
                echo 'Publishing Allure report...'
                allure includeProperties: false, jdk: '', results: [[path: "${env.ALLURE_RESULTS_DIR}"]]
            }
        }

        stage('Send Allure Report via Email') {
            steps {
                script {
                    emailext(
                        subject: "Allure Report - ${currentBuild.fullDisplayName}",
                        body: "Please find attached the Allure report for the Jenkins build.",
                        to: 'seleniumpracticepriya@gmail.com',
                        attachmentsPattern: '**/allure-report/**/*'
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
