import commons.Constants

def createAwsBackend(String backend, String workdir = Constants.WORKDIR_TO_DEPLOY) {    
    String backendName = backend.split("/").last()
    String awsProvider = '''
        provider "aws" {
            region  = "us-east-1"
        }
        '''
    sh "cp -f ${backend} ${workdir}/"    
    dir(workdir) {
        try {
            sh " echo '${awsProvider}' >> ${backendName}"
        } 
        catch (Exception e) {
            error("Error agregando credenciales AWS: ${e.message}")            
        }
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