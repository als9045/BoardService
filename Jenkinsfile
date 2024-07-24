pipeline {
    agent any

    tools {
        // Install the Maven version configured as "Maven3.9.8" and add it to the path.
        maven "Maven3.9.8"
    }

    environment {
        DEPLOY_CREDENTIALS_ID = 'Git_per_token' // Jenkins에서 설정한 자격 증명 ID
        TOMCAT_URL = 'http://192.168.1.10:8080'
        GIT_REPO_URL = 'https://github.com/als9045/-Board-Service.git'
    }

    stages {
        stage('Git Clone') {
            steps {
                git GIT_REPO_URL
            }
        }

        stage('Build') {
            steps {
                sh '''
                    echo Build start
                    mvn clean compile package -DskipTests=true
                '''
            }
        }

        stage('Deploy') {
            steps {
                deploy adapters: [
                    tomcat9(
                        credentialsId: DEPLOY_CREDENTIALS_ID,
                        path: '',
                        url: TOMCAT_URL
                    )
                ], contextPath: null, war: '**/*.war'
            }
        }
    }

    post {
        always {
            // Archive the build artifacts
            archiveArtifacts artifacts: '**/target/*.war', allowEmptyArchive: true

            // Archive the test results
            junit '**/target/surefire-reports/*.xml'
        }

        failure {
            // Send notification on failure (customize as needed1212
            echo 'Build failed!'
            // Example: notify via email or slack
            // mail to: 'team@example.com', subject: "Failed Pipeline: ${currentBuild.fullDisplayName}", body: "Something is wrong with ${env.BUILD_URL}"
        }
    }
}
