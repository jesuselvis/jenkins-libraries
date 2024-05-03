import commons.Constants
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.charset.StandardCharsets

def createAwsBackend(String backend, String workdir = Constants.WORKDIR_TO_DEPLOY) {
    // Extraer el nombre del archivo backend
    String backendName = backend.split("/").last()
    
    // Definir la configuración de AWS
    String awsProvider = '''
        provider "aws" {
            region = "us-east-1"
        }
    '''

    // Crear el directorio de trabajo si no existe
    def workDirPath = Paths.get(workdir)
    if (!Files.exists(workDirPath)) {
        Files.createDirectories(workDirPath)
    }
    
    // Copiar el archivo backend al directorio de trabajo
    def sourcePath = Paths.get(backend)
    def destinationPath = workDirPath.resolve(backendName)
    
    // Copia el archivo backend al directorio de trabajo
    try {
        Files.copy(sourcePath, destinationPath)
    } catch (Exception e) {
        println "Error copiando el archivo backend: ${e.message}"
        return
    }
    
    // Añadir la configuración de AWS al archivo backend
    if (Files.exists(destinationPath)) {
        try {
            def existingContent = new String(Files.readAllBytes(destinationPath), StandardCharsets.UTF_8)
            Files.write(destinationPath, (existingContent + awsProvider).getBytes(StandardCharsets.UTF_8))
        } catch (Exception e) {
            println "Error escribiendo en el archivo backend: ${e.message}"
        }
    } else {
        println "El archivo backend ${backendName} no existe en ${workdir}"
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