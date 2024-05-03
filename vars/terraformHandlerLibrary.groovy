import commons.Constants

def createAwsBackend(String backend, String workdir = Constants.WORKDIR_TO_DEPLOY, String awsRegion="us-east-1") {    
    String backendName = new File(backend).name
    
    sh "cp -f ${backend} ${workdir}/"    
    
    dir(workdir) {
        withCredentials([[
            $class: 'AmazonWebServicesCredentialsBinding',
            credentialsId: 'your-aws-credentials-id',
            accessKeyVariable: 'AWS_ACCESS_KEY_ID',
            secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
        ]]) {
            sh "echo 'provider \"aws\" {\n\tregion = \"${awsRegion}\"\n\taccess_key = \"${AWS_ACCESS_KEY_ID}\"\n\tsecret_key = \"${AWS_SECRET_ACCESS_KEY}\"\n}' >> ${backendName}"
        }
        // try {
        //     sh "echo '${awsProviderContent}' >> ${backendName}"
        // } 
        // catch (Exception e) {
        //     error("> createAwsBackend ::: Add AWS Credentials ::: ${e.message}")         
        // }
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