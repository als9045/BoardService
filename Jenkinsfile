pipeline {
    agent any

    tools {
        maven 'Maven3.9.8'  // Maven 버전 설정
    }

    environment {
        DEPLOY_CREDENTIALS_ID = 'Git_per_token'  // 자격 증명 ID
        TOMCAT_URL = 'http://192.168.1.10:8080'  // Tomcat URL
        GIT_REPO_URL = 'https://github.com/als9045/-Board-Service.git'  // Git 저장소 URL
    }

    stages {
        stage('Git Clone') {
            steps {
                git branch: 'master', url: GIT_REPO_URL
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }

        stage('Deploy') {
            steps {
                deploy adapters: [tomcat9(
                    credentialsId: DEPLOY_CREDENTIALS_ID,
                    url: TOMCAT_URL,
                    path: ''
                )], contextPath: '', war: '**/*.war'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.war', allowEmptyArchive: true
            junit '**/target/surefire-reports/*.xml'
        }

        failure {
            echo 'Build failed!'
        }
    }
}
