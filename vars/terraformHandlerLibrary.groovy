import commons.Constants

def createAwsBackend(String _bucketKey, String _environment, String _awsRegion="us-east-1",String _workdir = Constants.WORKDIR_TO_DEPLOY) {
    dir(_workdir) {
        withCredentials([[
            $class: 'AmazonWebServicesCredentialsBinding',
            credentialsId: Constants.TF_STATES_CREDENTIALS_ID[_environment],
            accessKeyVariable: 'AWS_ACCESS_KEY_ID',
            secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
        ]]) {
            sh "echo '\nterraform{\n\tbackend \"s3\" {\n\t\tbucket = \"${Constants.TF_STATES_S3_BUCKET[_environment]}\"\n\t\tencrypt = true\n\t\tkey = \"${_bucketKey}\"\n\t\tregion = \"${_awsRegion}\"\n\t\taccess_key = \"${AWS_ACCESS_KEY_ID}\"\n\t\tsecret_key = \"${AWS_SECRET_ACCESS_KEY}\"\n\t}\n}\n' > backend.tf"            
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