import commons.Constants

def createAwsBackend(String backend, String workdir = Constants.WORKDIR_TO_DEPLOY) {    
    String backendName = new File(backend).name  
    String awsProviderContent = "provider \"aws\" {\n\t region = \"us-east-1\"\n}\n"
    sh "cp -f ${backend} ${workdir}/"    
    dir(workdir) {
        try {
            sh "echo '${awsProviderContent}' >> ${backendName} && cat ${backendName}"
        } 
        catch (Exception e) {
            error("> createAwsBackend ::: Add AWS Credentials ::: ${e.message}")            
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