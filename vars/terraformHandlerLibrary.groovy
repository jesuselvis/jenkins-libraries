import commons.Constants

// def createAwsBackend(String backend, String workdir = Constants.WORKDIR_TO_DEPLOY, String awsRegion="us-east-1") {    
//     String backendName = new File(backend).name

//     sh "cp -f ${backend} ${workdir}/"    
    
//     dir(workdir) {
//         withCredentials([[
//             $class: 'AmazonWebServicesCredentialsBinding',
//             credentialsId: 'aws_account_dev_dataplatform',
//             accessKeyVariable: 'AWS_ACCESS_KEY_ID',
//             secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
//         ]]) {
//             sh "echo '\nprovider \"aws\" {\n\tregion = \"${awsRegion}\"\n\taccess_key = \"${AWS_ACCESS_KEY_ID}\"\n\tsecret_key = \"${AWS_SECRET_ACCESS_KEY}\"\n}' >> ${backendName}"
//         }
//     }
// }

def createAwsBackend(String _bucketName, String _bucketKey, String _credentials, String _awsRegion="us-east-1",String _workdir = Constants.WORKDIR_TO_DEPLOY) {
    dir(_workdir) {
        withCredentials([[
            $class: 'AmazonWebServicesCredentialsBinding',
            credentialsId: _credentials,
            accessKeyVariable: 'AWS_ACCESS_KEY_ID',
            secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
        ]]) {
            sh "echo '\nterraform{\n\tbackend \"s3\" {\n\t\tbucket = \"${_bucketName}\"\n\t\tencrypt = true\n\t\tkey = \"${_bucketKey}\"\n\t\tregion = \"${_awsRegion}\"\n\t\taccess_key = \"${AWS_ACCESS_KEY_ID}\"\n\t\tsecret_key = \"${AWS_SECRET_ACCESS_KEY}\"\n\t}\n}\n' > backend.tf"
            sh "cat backend.tf"
        }
    }
}

def initialize(String workdir = Constants.WORKDIR_TO_DEPLOY) {
    dir(workdir) {
        sh "terraform init -no-color"
    }
}

def plan() {
    // Code to create a Terraform plan
}

def apply() {
    // Code to apply Terraform changes
}

def destroy() {
    // Code to destroy Terraform resources
}