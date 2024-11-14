pipeline {
    agent any

    stages {

        stage ('GetProject') {
            steps{
                git branch:'master', url: 'https://github.com/RoisinNUIG/CT5171_CARoisinsPetition.git'
            }
        }
        stage ('Build') {
            steps{
                sh "mvn clean:clean"
                sh 'mvn dependency:copy-dependencies'
                sh 'mvn compiler:compile'
            }
        }

                stage('Package') {
                    steps{
                       sh 'mvn package'
                    }
                }

                stage('Exec') {
                    steps{
                       // sh 'mvn exec:java'
                       sh 'mvn exec:java'
                    }
                }
            }

            post{
                success{
                    archiveArtifacts allowEmptyArchive: true,
                        artifacts:'**/CT5171_CARoisinsPetition*.jar'
                }
            }

        }
