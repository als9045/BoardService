pipeline {
    agent any

    tools {
        maven 'Maven3.9.8'  // Maven 버전 설정
    }

    environment {
        DEPLOY_CREDENTIALS_ID = 'Git_per_token'  // 자격 증명 ID
        TOMCAT_URL = 'http://192.168.1.11:8080'  // Tomcat URL
        GIT_REPO_URL = 'https://github.com/als9045/-Board-Service.git'  // Git 저장소 URL
        IMAGE_NAME = 'my-tomcat-image'  // Docker 이미지 이름
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

        stage('Build Docker Image') {
            steps {
                script {
                    // Dockerfile이 위치한 디렉토리로 이동
                    sh 'cd -Board-Service && docker build -t $IMAGE_NAME .'
                }
            }
        }

        stage('Deploy Docker Container') {
            steps {
                script {
                    // Docker 컨테이너 실행
                    sh 'docker run -d -p 8081:8080 $IMAGE_NAME'
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
        }
    }
}
