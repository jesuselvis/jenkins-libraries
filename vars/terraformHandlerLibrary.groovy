import commons.Constants

// def createAwsBackend(String backend, String workdir = Constants.WORKDIR_TO_DEPLOY) {    
//     String backendName = backend.split("/").last()      
//     String awsProvider = '\nprovider "aws" {\n\t region = "us-east-1"\n}'   
//     sh "cp -f ${backend} ${workdir}/"    
//     dir(workdir) {
//         try {
//             sh " echo '${awsProvider}' >> ${backendName}"
//             sh "cat ${backendName}"
//         } 
//         catch (Exception e) {
//             error("> createAwsBackend ::: Add AWS Credentials ::: ${e.message}")            
//         }
//     }
// }

def createAwsBackend(String backend, String workdir = Constants.WORKDIR_TO_DEPLOY) {
  // Extract filename from backend path
  String backendName = new File(backend).name
  
  // Combine AWS provider config with newline
  String awsProviderContent = "provider \"aws\" {\n\t region = \"us-east-1\"\n}\n"
  sh "echo '${awsProviderContent}'"
  // Use try-with-resource for automatic file closing and error handling
  try {
    // Open files with write access
    new File(workdir, backendName).withWriter { writer ->
      writer << new File(backend).bytes // Copy backend content
      writer << awsProviderContent.bytes // Append AWS provider config
    }
  } catch (Exception e) {
    error("> createAwsBackend ::: Add AWS Credentials ::: ${e.message}")
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