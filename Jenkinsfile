pipeline {
    agent any
tools{
    git 'Default'
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
                 artifacts:'**/CT5171CAroisinspetition*.war'
        }
             }

        stage('Deploy') {
             steps{
                 sh 'docker build -f Dockerfile -t CT5171CAroisinspetition . '
                 sh 'docker rm -f "myappcontainer" || true'
                 sh 'docker run --name "myappcontainer" -p 9090:8080 --detach myapp:latest'
             }
        }
    }
 }
