pipeline {
    agent any
tools{
git 'Default'
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


                 stage('Deploy') {

                     steps{
                        //script {
                        //echo "Building and deploying Docker container..."
                        //}
                        sh 'docker login -u <student> -p <pass>'
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