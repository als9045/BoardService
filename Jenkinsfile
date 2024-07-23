pipeline {
    agent any

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('my-image', '.')
                }
            }
        }
        stage('Run Docker Container') {
            steps {
                script {
                    docker.image('my-image').run('-d -p 8081:80')
                }
            }
        }
    }
}
