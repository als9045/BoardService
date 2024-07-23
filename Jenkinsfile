pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // GitHub에서 소스 코드 체크아웃
                checkout scm
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // Docker 이미지를 빌드
                    docker.build('board-service-image', '.')
                }
            }
        }
        stage('Run Docker Container') {
            steps {
                script {
                    // Docker 컨테이너를 실행
                    docker.image('board-service-image').run('-d -p 8081:8080')
                }
            }
        }
    }
}
