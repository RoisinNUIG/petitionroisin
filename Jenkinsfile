pipeline {
    agent any
tools{
git 'Default'
}

environment {
        DOCKER_USERNAME = 'your-docker-username'
        DOCKER_PASSWORD = 'your-docker-password'
        DOCKER_IMAGE = 'myapp:latest'
        CONTAINER_NAME = 'myappcontainer'
        }

 stages {

        stage ('GetProject') {
            steps{
                git branch:'master', url: 'https://github.com/RoisinNUIG/CT5171CARoisinsPetition.git'
            }
        }
        stage ('Build') {
            steps{
                sh "mvn clean:clean"
        }
            }
                stage('Package') {
                    steps{
                       sh 'mvn package'
                    }
                }

                  stage('Archive') {
                       steps{
                          archiveArtifacts allowEmptyArchive: true,
                              artifacts:'**/CT5171CARoisinsPetition*.war'
                     }
                  }

                 stage('Docker Login') {
                             steps {
                                 script {
                                     echo "Logging into Docker Hub..."
                                     sh """
                                         echo "${DOCKER_PASSWORD}" | docker login -u "${DOCKER_USERNAME}" --password-stdin
                                     """
                                 }
                             }
                 }

                 stage('Deploy') {

                     steps{
                        //script {
                        //echo "Building and deploying Docker container..."
                        //}
                        //sh 'docker login -u <student> -p <pass>'
                        sh 'docker build -f Dockerfile -t myapp . '
                        sh 'docker rm -f "myappcontainer" || true'
                        sh 'docker run --name "myappcontainer" -p 9090:8080 --detach myapp:latest'
                     }
                 }

           // post{
               // success{
                  //  archiveArtifacts allowEmptyArchive: true,
                    //    artifacts:'**/CT5171_CARoisinsPetition*.war'
               // }
           // }
        }
}
