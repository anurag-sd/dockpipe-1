pipeline {
    agent any 
    stages {
        stage('Pull') { 
            steps {
                git branch: 'main', url: 'https://github.com/anurag-sd/dockpipe-1.git'
            }
        }
       
        stage('Build Docker Image') {
            steps {
               sh 'docker build . -t studentapp'
            }
        }

        stage('Test') { 
            steps {
                echo 'Test successfully'
            }
        }

        stage('Deploy') {
            steps {
               sh "docker run -d -p 8080:80 studentapp"
                }
            }
        }
    }

