pipeline{
    agent any
    stages{
        stage("clone a code"){
            steps{
                git branch: "master"
                url:"https://github.com/KuruvaSomaSekhar/boxfuse-sample-java-war-hello.git"
            }
        }
    }
    stages{
        stage("build"){
            steps{
                println("building the code")
                sh "mvn clean package"
                sh "ls -l"
            }
        }
        stage("uploading the artifacts"){
            steps{
                println("uploading artifacts to s3")
            }
        }
    }
}