pipeline {
    agent any

    tools {
        maven 'Maven3.9.8'
    }

    environment {
        DEPLOY_CREDENTIALS_ID = 'Git_per_token'
        TOMCAT_URL = 'http://192.168.1.11:8081'  // 포트를 8081로 수정
        GIT_REPO_URL = 'https://github.com/als9045/BoardService.git'
        IMAGE_NAME = 'my-tomcat-image'
    }

    stages {
        stage('Git Clone') {
            steps {
                git branch: 'master', url: GIT_REPO_URL
                sh 'echo "Directory Structure after Git Clone:"'
                sh 'ls -al /var/jenkins_home/workspace/sbb_1'
            }
        }

        stage('Verify Clone') {
            steps {
                script {
                    sh 'echo "Directory Structure inside BoardService:"'
                    sh 'ls -al /var/jenkins_home/workspace/sbb_1'
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    sh 'mvn clean package -DskipTests=true'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t ${IMAGE_NAME} /var/jenkins_home/workspace/sbb_1'
                }
            }
        }

        stage('Deploy Docker Container') {
            steps {
                script {
                    // Tomcat 컨테이너를 8081 포트에서 실행
                    sh 'docker run -d -p 8082:8080 ${IMAGE_NAME}'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
            archiveArtifacts artifacts: '**/target/*.war', allowEmptyArchive: true
            junit '**/target/surefire-reports/*.xml'
        }

        failure {
            echo 'Build failed!'
        }
    }
}
