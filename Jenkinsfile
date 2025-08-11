pipeline {
    agent any

    stages {
        stage('Build Backend') {
            steps {
                sh 'docker compose build backend_distribution'
            }
        }

        stage('Build Frontend') {
            steps {
                sh 'docker compose build frontend_distribution'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker compose down && docker compose up -d'
            }
        }
    }
}
