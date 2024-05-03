import commons.Constants

def createAwsBackend(String backend, String workdir = Constants.WORKDIR_TO_DEPLOY) {    
    String backendName = new File(backend).name
    String access_key = "none"
    String secret_key = "none"
    String awsProviderContent = "\nprovider \"aws\" {\n\t region = \"us-east-1\"\n\taccess_key = \"${access_key}\"\n\tsecret_key = \"${secret_key}\"\n}"
    // sh "cp -f ${backend} ${workdir}/"    
    // dir(workdir) {
    //     try {
    //         // sh "echo '${awsProviderContent}' >> ${backendName}"
    //         // new File(backendName).append(awsProviderContent)
    //     } 
    //     catch (Exception e) {
    //         error("> createAwsBackend ::: Add AWS Credentials ::: ${e.message}")         
    //     }
    // }
    try {
        // Copiar el archivo backend al directorio de trabajo y cambiar permisos
        sh "cp -f ${backend} ${workdir}/"
        
        // Define la ruta completa del archivo destino
        String destinationFilePath = "${workdir}/${backendName}"
        
        // Ajustar permisos para permitir la escritura (opcional y de acuerdo a las necesidades)
        sh "chmod 644 ${destinationFilePath}"
        
        // Abrir el archivo destino para agregar contenido de AWS
        new File(destinationFilePath).append(awsProviderContent)
        
        println "Contenido de AWS agregado correctamente a ${destinationFilePath}"
        
    } catch (Exception e) {
        // Manejar errores de permisos
        error("> createAwsBackend ::: Error al agregar credenciales de AWS: ${e.message}")
    }
}

def initialize(String workdir = Constants.WORKDIR_TO_DEPLOY) {
    dir(workdir) {
        sh "terraform init -no-color"
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