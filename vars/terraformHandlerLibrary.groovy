import commons.Constants
import org.yaml.snakeyaml.Yaml

def createAwsBackend(String _bucketKey, String _environment, String _awsRegion="us-east-1",String _workdir = Constants.WORKDIR_TO_DEPLOY) {
    dir(_workdir) {
        withCredentials([[
            $class: 'AmazonWebServicesCredentialsBinding',
            credentialsId: Constants.TF_STATES_CREDENTIALS_ID[_environment],
            accessKeyVariable: 'AWS_ACCESS_KEY_ID',
            secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
        ]]) {
            sh "echo '\nterraform{\n\tbackend \"s3\" {\n\t\tbucket = \"${Constants.TF_STATES_S3_BUCKET[_environment]}\"\n\t\tencrypt = true\n\t\tkey = \"${_bucketKey}\"\n\t\tregion = \"${_awsRegion}\"\n\t\taccess_key = \"${AWS_ACCESS_KEY_ID}\"\n\t\tsecret_key = \"${AWS_SECRET_ACCESS_KEY}\"\n\t}\n}\n' > backend.tf"            
        }
    }
}

def initialize(String _workdir = Constants.WORKDIR_TO_DEPLOY) {
    dir(_workdir) {
        sh "terraform init -no-color"
    }
}

def plan(String _workdir = Constants.WORKDIR_TO_DEPLOY) {
    sh "cd ${WORKSPACE}/Develop/DataPlatform && ls -l"
    String rutaArchivoYAML = 'Develop/DataPlatform/env.yml'
    Yaml yaml = new Yaml()
    def contenidoYAML = new File(WORKSPACE+'/'+rutaArchivoYAML).text
    def variables = yaml.load(contenidoYAML)
    def comandoTerraform = 'terraform plan'
    variables.each { clave, valor ->
        comandoTerraform += " -var=\"${clave}=${valor}\""
    }
    println("Comando Terraform plan construido: ${comandoTerraform}")
    // dir(_workdir) {
    //     sh "terraform plan -no-color"
    // }
}

def apply() {
    // Code to apply Terraform changes
}

def destroy() {
    // Code to destroy Terraform resources
}