pipeline {
    agent any

    tools {
        // Install the Maven version configured as "Maven3.9.8" and add it to the path.
        maven "Maven3.9.8"
    }

    stages {
        stage('Git Clone') {
            steps {
                git 'https://github.com/als9045/-Board-Service.git'
            }
        }

        stage('Build') {
            steps {
                sh '''
                    echo Build start
                    mvn clean compile package -DskipTests=true
                '''
            }
        }

        stage('Deploy') {
            steps {
                deploy adapters: [tomcat9(
                    credentialsId: 'deploy_user',
                    path: '',
                    url: 'http://192.168.1.10:8080'
                )], contextPath: null, war: '**/*.war'
            }
        }
    }
}
