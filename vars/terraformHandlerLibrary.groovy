import commons.Constants
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.nio.charset.StandardCharsets

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
  String backendName = new File(backend).name  
  String awsProviderContent = "provider \"aws\" {\n\t region = \"us-east-1\"\n}\n"
//   sh "echo '${awsProviderContent}'"
  println("workdir: ${workdir}, backend: ${backend}")
  File destinationFile = new File(workdir, backendName)
    
    try {
        // Copiar el archivo backend.tf al directorio de trabajo
        Files.copy(Paths.get(backend), Paths.get(workdir, backendName), StandardCopyOption.REPLACE_EXISTING)
        
        // Verificar que el archivo ha sido copiado
        if (!destinationFile.exists()) {
            throw new RuntimeException("No se pudo copiar el archivo backend.tf a workDirToDeploy.")
        }
        
        // Abrir el archivo destino en modo de escritura para agregar el contenido adicional
        destinationFile.withWriterAppend { writer ->
            writer.write(awsProviderContent)
        }
        
        println "Archivo '${backendName}' copiado y contenido de AWS añadido correctamente a ${workdir}."
        
    } catch (Exception e) {
        // Manejar excepciones y mostrar el mensaje de error
        error("> createAwsBackend ::: Error al agregar configuraciones de AWS: ${e.message}")
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