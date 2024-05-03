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
    
    File backendFile = new File(backend)
    File workDir = new File(workdir)
    
    if (!backendFile.exists()) {
        throw new FileNotFoundException("El archivo ${backend} no existe.")
    }

    Files.copy(backendFile.toPath(), Paths.get(workDir, backendName))

    File destinationFile = new File(workDir, backendName)
    
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