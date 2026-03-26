pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "sunithriyansh/onlinebookstore:latest"
        EMAIL = "203b1a0565suni@gmail.com"
    }

    stages {

        stage('Checkout') {
            steps {
                script {
                    try {
                        git url: 'https://github.com/ramadevipanthagadi/onlinebookstore.git', branch: 'main'
                        mail to: "${EMAIL}",
                             subject: "✅ Checkout Success",
                             body: "Code checkout completed successfully."
                    } catch (Exception e) {
                        mail to: "${EMAIL}",
                             subject: "❌ Checkout Failed",
                             body: "Checkout stage failed."
                        error "Checkout failed"
                    }
                }
            }
        }

        stage('Build WAR') {
            steps {
                script {
                    try {
                        sh 'mvn clean package'
                        mail to: "${EMAIL}",
                             subject: "✅ Build Success",
                             body: "Maven build completed successfully."
                    } catch (Exception e) {
                        mail to: "${EMAIL}",
                             subject: "❌ Build Failed",
                             body: "Build stage failed."
                        error "Build failed"
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    try {
                        sh 'docker build -t $DOCKER_IMAGE .'
                        mail to: "${EMAIL}",
                             subject: "✅ Docker Build Success",
                             body: "Docker image built successfully."
                    } catch (Exception e) {
                        mail to: "${EMAIL}",
                             subject: "❌ Docker Build Failed",
                             body: "Docker build failed."
                        error "Docker build failed"
                    }
                }
            }
        }

        stage('Docker Login') {
            steps {
                script {
                    try {
                        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                            sh 'echo $PASS | docker login -u $USER --password-stdin'
                        }
                        mail to: "${EMAIL}",
                             subject: "✅ Docker Login Success",
                             body: "Logged into DockerHub successfully."
                    } catch (Exception e) {
                        mail to: "${EMAIL}",
                             subject: "❌ Docker Login Failed",
                             body: "Docker login failed."
                        error "Docker login failed"
                    }
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    try {
                        sh 'docker push $DOCKER_IMAGE'
                        mail to: "${EMAIL}",
                             subject: "✅ Docker Push Success",
                             body: "Docker image pushed successfully."
                    } catch (Exception e) {
                        mail to: "${EMAIL}",
                             subject: "❌ Docker Push Failed",
                             body: "Docker push failed."
                        error "Docker push failed"
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    try {
                        sh 'kubectl apply -f k8s/deployment.yml'
                        sh 'kubectl apply -f k8s/service.yml'
                        mail to: "${EMAIL}",
                             subject: "✅ Deployment Success",
                             body: "Application deployed successfully."
                    } catch (Exception e) {
                        mail to: "${EMAIL}",
                             subject: "❌ Deployment Failed",
                             body: "Deployment failed."
                        error "Deployment failed"
                    }
                }
            }
        }
    }

    post {
        success {
            mail to: "${EMAIL}",
                 subject: "🎉 Pipeline Success",
                 body: "All stages completed successfully."
        }

        failure {
            mail to: "${EMAIL}",
                 subject: "🚨 Pipeline Failed",
                 body: "Pipeline execution failed. Check Jenkins logs."
        }
    }
}
}
