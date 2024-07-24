pipeline {
    agent any

    tools {
        maven 'Maven3.9.8'
    }

    environment {
        DEPLOY_CREDENTIALS_ID = 'Git_per_token'
        TOMCAT_URL = 'http://192.168.1.11:8080'
        GIT_REPO_URL = 'https://github.com/als9045/BoardService.git'
        IMAGE_NAME = 'my-tomcat-image'
    }

    stages {
        stage('Git Clone') {
            steps {
                // Git 저장소를 클론합니다.
                git branch: 'master', url: GIT_REPO_URL
                // 디렉토리 내용 확인
                sh 'echo "Directory Structure after Git Clone:"'
                sh 'ls -al /var/jenkins_home/workspace/sbb_1'
            }
        }

        stage('Verify Clone') {
            steps {
                script {
                    // 클론 후 디렉토리 구조 확인
                    sh 'echo "Directory Structure inside BoardService:"'
                    sh 'ls -al /var/jenkins_home/workspace/sbb_1'
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    // Maven 빌드
                    sh 'mvn clean package -DskipTests=true'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Docker 이미지 빌드
                    sh 'docker build -t ${IMAGE_NAME} /var/jenkins_home/workspace/sbb_1'
                }
            }
        }

        stage('Deploy Docker Container') {
            steps {
                script {
                    // Docker 컨테이너 배포
                    sh 'docker run -d -p 8080:8080 ${IMAGE_NAME}'
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
