pipeline {
    agent any 
    stages {
        stage('Pull') { 
            steps {
                git clone 'https://github.com/Prajwal2023/dockpipe-1.git'
            }
        }
       
        stage('Build Docker Image') {
            steps {
                script {
                    def dockerImage = docker.build(
                        context: '.',
                        dockerfile: '/home/ubuntu/Dockerfile',
                        tags: "testapp:${env.BUILD_ID}"
                    )
                }
            }
        }

        stage('Test') { 
            steps {
                echo 'Test successfully'
            }
        }

        stage('Deploy on Same Instance') {
            steps {
                script {
                    sh "docker stop testapp || true"  // Stop existing container if exists
                    sh "docker rm testapp || true"    // Remove existing container if exists

                    sh "docker run -d -p 8080:80 --testapp testapp:${env.BUILD_ID}"
                }
            }
        }
    }
}
