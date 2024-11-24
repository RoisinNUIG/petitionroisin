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
                 artifacts:'**/ct5171caroisinspetition*.war'
        }
             }

        stage('Deploy') {
             steps{
                 sh 'docker build -f Dockerfile -t ct5171caroisinspetition . '
                 sh 'docker rm -f "myappcontainer" || true'
                 sh 'docker run --name "myappcontainer" -p 9090:8080 --detach ct5171caroisinspetition:latest'
             }
        }
    }
 }
