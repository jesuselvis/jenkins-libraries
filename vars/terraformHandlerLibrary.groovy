import commons.Constants

def createAwsBackend(String backend, String workdir = Constants.WORKDIR_TO_DEPLOY) {    
    String backendName = backend.split("/").last()
    String awsProvider = '''
        provider "aws" {
            region  = "us-east-1"
        }
        '''
    sh "cp -f ${backend} ${workdir}/ || true"    
    dir(workdir) {
        // sh "echo '${awsProvider}' >> ${backenName}"
        sh "cat ${backenName}"
    }
}

def initialize() {
    // Code to initialize Terraform
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