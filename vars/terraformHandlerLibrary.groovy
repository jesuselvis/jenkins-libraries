import commons.Constants
import java.nio.file.Files
import java.nio.file.Paths

def createAwsBackend(String backend, String workdir = Constants.WORKDIR_TO_DEPLOY) {    
    String backendName = backend.split("/").last()
    String awsProvider = '''
        provider "aws" {
            region  = "us-east-1"
        }
        '''
    
    // File backendFile = new File(backend)
    // File workDir = new File(workdir)
    
    // if (!backendFile.exists()) {
    //     error("El archivo ${backend} no existe.")
    // }

    Files.copy(backend, Paths.get(workdir, backendName))

    File destinationFile = new File(workdir, backendName)
    
    destinationFile.withWriterAppend('UTF-8') { writer ->
        writer.writeLine(awsProvider)
    }

    println destinationFile.text
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