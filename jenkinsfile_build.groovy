pipeline{
    agent any
    parameters{
        string(name: 'BRANCH', defaultValue: 'master', description: '')
    }
    stages{
        stage("clone a code"){
            steps{
                println "clone a code"
                git branch: "${BRANCH}",
                url: 'https://github.com/KuruvaSomaSekhar/boxfuse-sample-java-war-hello.git'
            }
        }

        stage("build"){
            steps{
                println "building the code"
                sh "mvn clean package"
                sh "ls -l"
            }
        }
        stage("uploading the artifacts"){
            steps{
                println "uploading artifacts to s3"
            }
        }
    }
}