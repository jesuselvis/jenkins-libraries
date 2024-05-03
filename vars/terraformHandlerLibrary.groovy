import commons.Constants

def createAwsBackend(String backendName, String source = ".") {
    dir(source) {
        sh "cd ${WORKSPACE} && ls -lS"
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