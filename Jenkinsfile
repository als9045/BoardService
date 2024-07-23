pipeline {
    agent {
        docker {
            image 'maven:3.8.5-openjdk-17'
            args '-v /root/.m2:/root/.m2' // Maven 로컬 캐시를 Jenkins와 공유합니다.
        }
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/als9045/-Board-Service.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Run') {
            steps {
                sh 'java -jar target/*.jar'
            }
        }
    }
}
