pipeline {
    agent any

    tools {
        maven 'Maven3.9.8'  // Maven 버전 설정
    }

    environment {
        GIT_REPO_URL = 'https://github.com/als9045/-Board-Service.git'  // Git 저장소 URL
        WAR_FILE = 'target/BoardService-0.0.1-SNAPSHOT.war'  // WAR 파일 경로
        IMAGE_NAME = 'my-tomcat-image'  // Docker 이미지 이름
        CONTAINER_NAME = 'my-tomcat-container'  // Docker 컨테이너 이름
        DOCKER_PORT = 8081  // Tomcat 컨테이너 포트
        DOCKERFILE_DIR = 'Boardservice'  // Dockerfile이 있는 디렉토리
    }

    stages {
        stage('Git Clone') {
            steps {
                // Git 저장소 클론
                git branch: 'master', url: GIT_REPO_URL
            }
        }

        stage('Build') {
            steps {
                // Maven 빌드
                sh 'mvn clean package -DskipTests=true'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Dockerfile이 있는 디렉토리로 이동하여 Docker 이미지 빌드
                    dir(DOCKERFILE_DIR) {
                        sh 'docker build -t $IMAGE_NAME .'
                    }
                }
            }
        }

        stage('Deploy Docker Container') {
            steps {
                script {
                    // 기존 컨테이너가 있으면 삭제
                    sh "docker rm -f $CONTAINER_NAME || true"

                    // Docker 컨테이너 실행
                    sh "docker run -d --name $CONTAINER_NAME -p $DOCKER_PORT:8080 $IMAGE_NAME"
                }
            }
        }
    }

    post {
        always {
            // WAR 파일 아카이브
            archiveArtifacts artifacts: '**/target/*.war', allowEmptyArchive: true
            // 테스트 리포트
            junit '**/target/surefire-reports/*.xml'
        }

        failure {
            echo 'Build failed!'
        }
    }
}
