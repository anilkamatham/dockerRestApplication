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
                  dir("${env.WORKSPACE}/${env.TEMP_DOCKER_DIRECTORY}"){
                        sh 'pwd'
                        sh "docker image build -t ${env.DOCKER_HUB_USER_NAME}/${env.DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER} ."                          
                  }     
                  echo 'removing temp directory...'
                  sh "rm -R ${env.TEMP_DOCKER_DIRECTORY}*"                                   
             }
             post {
                 success {
                     echo 'docker image created successfully'
                 }
                 failure {
                     echo 'docker image creation failed'
                 }
             }
        }
        stage('Push image to docker hub') {
             steps {
                 withCredentials([string(credentialsId: 'docker-hub', variable: 'dockerSecretPassword')]) {
                     sh "docker login -u${env.DOCKER_HUB_USER_NAME} -p${dockerSecretPassword}"
                     sh "docker image push ${env.DOCKER_HUB_USER_NAME}/${env.DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER}"
                  }
             } 
             post {
                 success {
                     echo 'docker image pushed to hub successfully' 
                 }                  
             }                 
        }
        stage('Deploy to swarm cluster'){
            steps {                  
                 sshagent(['docker-swarm-master']){                    
                     sh 'scp -o StrictHostKeyChecking=no docker-compose.yml ec2-user@52.66.172.85:/usr/local/bin/docker-swarm-workloads'
                     sh "ssh -o StrickHostKeyChecking=no -t ec2-user@52.66.172.85  'cd /usr/local/bin/docker-swarm-workloads | docker stack deploy -c docker-compose.yml docker-restapp-stack'"
                 }
            }
            post {
                success {
                    echo 'docker stack depldoyed successfully...'
                }
                failure {
                    echo 'docker stack deployment failed'
                }
            }
        }

    }
}