pipeline {
    agent {
        label 'docker-slave'
    }
    parameters { 
        string(name: 'docker-dev', defaultValue:'65.0.71.180', description: 'deploying institute application to docker dev environemnt')
    }
    environment {
        TEMP_DOCKER_DIRECTORY= "dockerartifacts"
        DOCKER_HUB_USER_NAME= "anilkamatham"
        DOCKER_IMAGE_NAME= "dockerrestapp"

    }
    tools {
        maven 'localmaven'
    }
    triggers {
        pollSCM('* * * * *')
    }
    stages {
        stage('Build artifacts') {
            steps {
                sh 'mvn clean package'
            }
            post {
                success {
                    echo 'Archieve artifacts...'                    
                    archiveArtifacts artifacts: '**/target/*.jar'
                }
                failure {
                    echo 'build failed...'
                }
            }
        }

        stage('Copy Dockerfile and Artifacts') {
            steps {                
                echo 'creating temp directory...'
                sh "mkdir ${env.TEMP_DOCKER_DIRECTORY}"
                echo 'copying dockerfile...'
                sh "cp Dockerfile ${env.TEMP_DOCKER_DIRECTORY}"
                echo 'copying artifacts...'
                sh "cp **/*.jar ${env.TEMP_DOCKER_DIRECTORY}"
            }            
        }
        stage('Build image') {
             steps {
                  echo 'Building docker image ...'
                  sh "cd ${env.TEMP_DOCKER_DIRECTORY}"
                  sh 'pwd'
                  sh "docker image build -t ${env.DOCKER_HUB_USER_NAME}/${env.DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER} ."  
                  echo 'removing temp directory...'
                  sh "rm -R ${env.TEMP_DOCKER_DIRECTORY}"
             }
             post {
                 success {
                     echo 'docker image created successfully'
                 }
                 failure {
                     echo 'docker image creating failed'
                 }
             }
        }
        stage('Push image to docker hub') {
             steps {
                 withCredentials([string(credentialsId: 'docker-hub', variable: 'dockerSecretPassword')]) {
                     sh "docker login -u${env.DOCKER_HUB_USER_NAME} -p${dockerSecretPassword}"
                     sh "docker image push ${env.DOCKER_HUB_USER_NAME}/${env.DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER}"
                  }
             post {
                  echp 'docker image pushed to hub successfully'
             }      
        }    
        }

    }
}