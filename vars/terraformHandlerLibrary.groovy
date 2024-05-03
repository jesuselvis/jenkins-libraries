import commons.Constants
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.charset.StandardCharsets
import java.nio.file.StandardCopyOption
import java.nio.file.StandardOpenOption

def createAwsBackend(String backend, String workdir = Constants.WORKDIR_TO_DEPLOY) {

    String backendName = backend.split("/").last()    
    String awsProvider = '''
        provider "aws" {
            region = "us-east-1"
        }
    '''    
    String destinationPath = "${workdir}/${backendName}"

    try {
        // Copiar el archivo backend al directorio de trabajo
        Files.copy(Paths.get(backend), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING)        
        // Añadir el bloque de proveedor AWS al archivo de backend
        Files.write(Paths.get(destinationPath), awsProvider.bytes, StandardOpenOption.APPEND)
        sh "cat ${destinationPath}"     
    } catch (Exception e) {
        error("Error agregando credenciales AWS: ${e.message}")
    }
}
// import commons.Constants

// def createAwsBackend(String backend, String workdir = Constants.WORKDIR_TO_DEPLOY) {    
//     String backendName = backend.split("/").last()
//     String awsProvider = '''
//         provider "aws" {
//             region  = "us-east-1"
//         }
//         '''
//     sh "cp -f ${backend} ${workdir}/"    
//     dir(workdir) {
//         try {
//             sh " echo '${awsProvider}' >> ${backendName}"
//         } 
//         catch (Exception e) {
//             error("Error agregando credenciales AWS: ${e.message}")            
//         }
//     }
// }

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