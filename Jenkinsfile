pipeline {
    agent any
tools{
git 'Default'
}

    parameters {
        string(name: 'DOCKER_USERNAME', defaultValue: 'student', description: 'Enter your Docker Hub username')
        password(name: 'DOCKER_PASSWORD', defaultValue: 'pass', description: 'Enter your Docker Hub password')
        booleanParam(name: 'CONSENT', description: 'Are you sure you want to deploy?')
    }

 stages {

        stage ('GetProject') {
            steps{
                git branch:'master', url: 'https://github.com/RoisinNUIG/CT5171_CARoisinsPetition.git'
            }
        }
        stage ('Build') {
            steps{
                sh "mvn clean:clean"
                //sh 'mvn dependency:copy-dependencies'
                //sh 'mvn compiler:compile'
            }
        }

                stage('Package') {
                    steps{
                       sh 'mvn package'
                    }
                }

               // stage('Exec') {
                 //   steps{
                    //   sh 'mvn exec:java'
                   //    sh 'mvn exec:java'
                    //}
                //}
                  stage('Archive') {
                       steps{
                          archiveArtifacts allowEmptyArchive: true,
                              artifacts:'**/CT5171_CARoisinsPetition*.war'
                     }
                  }

                  stage('Docker Login') {
                              steps {
                               script {
                                  echo "Logging into Docker Hub..."
                                sh """
                                                        echo '${params.DOCKER_PASSWORD}' | docker login -u '${params.DOCKER_USERNAME}' --password-stdin
                                                    """
                                                }
                                            }
                                        }
                 stage('Deploy') {
                     when {
                         expression {params.CONSENT == 'True' }
                     }
                     steps{
                        //script {
                        //echo "Building and deploying Docker container..."
                        //}
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