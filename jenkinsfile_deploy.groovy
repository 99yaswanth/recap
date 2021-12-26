//Declerative pipeline
pipeline{
    agent any
    parameters{
        string(name: 'BRANCH', defaultValue: 'master', description: 'give branch name')
        string(name: 'SERVER_IP', defaultValue: '', description: 'give IP address')
        string(name: 'BUILD_NUMBER', defaultValue: '', description: 'give build num')
    }
    stages{
        stage("download artifacts"){
            steps{
                println "downloading artifacts from s3"
                sh """
                aws s3 ls s3://yashwanth24
                aws s3 cp s3://yashwanth24/${BRANCH}/hello-${BUILD_NUMBER}.war .
                """
            }
        }
        stage("copy artifact"){
            steps{
                println "copying artifacts "
               // sh "ssh -i /tmp/nvirginia.pem ec2-user@${SERVER_IP} "\systemctl status tomcat\""
                sh "scp -i /tmp/nvirginia1.pem hello-${BUILD_NUMBER}.war ec2-user@${SERVER_IP}:/tmp/"
                sh "ssh -i /tmp/nvirginia1.pem ec2-user@${SERVER_IP} \"sudo cp /tmp/hello-${BUILD_NUMBER}.war /var/lib/tomcat/webapps\""

            }
        }
    }
}