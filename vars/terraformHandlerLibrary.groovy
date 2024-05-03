import commons.Constants

def createAwsBackend(String backend, String workdir = Constants.WORKDIR_TO_DEPLOY) {    
    String backendName = new File(backend).name
    String access_key = "none"
    String secret_key = "none"
    String awsProviderContent = "\nprovider \"aws\" {\n\t region = \"us-east-1\"\n\taccess_key = \"${access_key}\"\n\tsecret_key = \"${secret_key}\"\n}"
    sh "cp -f ${backend} ${workdir}/"    
    dir(workdir) {
        try {
            sh "echo '${awsProviderContent}' >> ${backendName}"
        } 
        catch (Exception e) {
            error("> createAwsBackend ::: Add AWS Credentials ::: ${e.message}")         
        }
    }
}

def initialize(String workdir = Constants.WORKDIR_TO_DEPLOY) {
    dir(workdir) {
        sh "terraform init"
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