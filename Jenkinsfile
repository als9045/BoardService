pipeline {
    agent any

    tools {
        maven 'Maven3.9.8'
    }

    environment {
        DEPLOY_CREDENTIALS_ID = 'Git_per_token'
        GIT_REPO_URL = 'https://github.com/als9045/BoardService.git'
        IMAGE_NAME = 'my-tomcat-image'
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'  // Docker Compose 파일 경로
        DOCKER_PORT = '8082'
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

        stage('Deploy Docker Containers') {
            steps {
                script {
                    // Docker Compose를 사용하여 기존 컨테이너 중지 및 삭제
                    sh '''
                        docker-compose -f ${DOCKER_COMPOSE_FILE} down
                    '''
                    // Docker Compose를 사용하여 컨테이너 시작
                    sh 'docker-compose -f ${DOCKER_COMPOSE_FILE} up -d'
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
