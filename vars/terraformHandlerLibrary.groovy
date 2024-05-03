import commons.Constants

def createAwsBackend(String backend, String workdir = Constants.WORKDIR_TO_DEPLOY) {    
    """
    Creates an AWS backend configuration file.
    Args:
      backend (str): Path to the base backend configuration file.
      workdir (str, optional): Working directory for the operation. Defaults to "WORKDIR_TO_DEPLOY".
    Returns:
      None
    """
  // Extract filename from backend path
  def backendName = new File(backend).name
  // Combine AWS provider config with newline
  def awsProviderContent = "provider \"aws\" {\n\t region = \"us-east-1\"\n}\n"
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