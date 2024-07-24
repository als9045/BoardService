pipeline {
    agent any

    tools {
        maven 'Maven3.9.8'  // Maven 버전 설정
    }

    environment {
        DEPLOY_CREDENTIALS_ID = 'Git_per_token'  // 자격 증명 ID
        TOMCAT_URL = 'http://192.168.1.11:8080'  // Tomcat URL
        GIT_REPO_URL = 'https://github.com/als9045/BoardService.git'  // Git 저장소 URL
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
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // 컨테이너가 이미 실행 중이면 중지 및 제거
                    sh '''
                      docker ps -q --filter "name=my-tomcat-container" | grep -q . && docker stop my-tomcat-container
                      docker ps -aq --filter "name=my-tomcat-container" | grep -q . && docker rm my-tomcat-container
                      docker build -t $IMAGE_NAME .
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
        }
    }
}
