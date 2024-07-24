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
                git branch: 'master', url: GIT_REPO_URL
            }
        }

        stage('Verify Directory Structure') {
            steps {
                script {
                    // 현재 작업 디렉토리 확인
                    sh 'pwd'
                    // 작업 디렉토리 내 파일 목록 확인
                    sh 'ls -al'
                    // BoardService 디렉토리 내 파일 목록 확인
                    sh 'ls -al BoardService'
                }
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // BoardService 디렉토리로 이동하여 Docker 이미지 빌드
                    dir('BoardService') {
                        // 현재 디렉토리 확인
                        sh 'pwd'
                        // 디렉토리 내 파일 목록 확인
                        sh 'ls -al'
                        // Docker 이미지 빌드
                        sh 'docker build -t $IMAGE_NAME .'
                    }
                }
            }
        }

        stage('Clean Up Docker Container') {
            steps {
                script {
                    // 기존 컨테이너 중지 및 삭제
                    sh '''
                    docker ps -q --filter name=my-tomcat-container | grep -q . && docker stop my-tomcat-container
                    docker ps -aq --filter name=my-tomcat-container | grep -q . && docker rm my-tomcat-container
                    '''
                }
            }
        }

        stage('Deploy Docker Container') {
            steps {
                script {
                    // Docker 컨테이너 실행
                    sh 'docker run -d -p 8081:8080 --name my-tomcat-container $IMAGE_NAME'
                }
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
            archiveArtifacts artifacts: '**/target/*.log', allowEmptyArchive: true
        }
    }
}
