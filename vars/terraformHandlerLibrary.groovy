import commons.Constants
import org.yaml.snakeyaml.Yaml

def createAwsBackend(String _bucketKey, String _environment, String _awsRegion="us-east-1", String _workdir = Constants.WORKDIR_TO_DEPLOY) {
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

def initializeAws(String _workdir = Constants.WORKDIR_TO_DEPLOY) {
    dir(_workdir) {
        sh "terraform init -no-color"
    }
}

def planAws(String _accountToDeploy, String _ymlFile, String _awsRegion="us-east-1", String _workdir = Constants.WORKDIR_TO_DEPLOY) {
    Yaml yaml = new Yaml()
    def contenidoYAML = new File(WORKSPACE+'/'+_ymlFile).text
    def variables = yaml.load(contenidoYAML)
    String comandoTerraform = ''
    variables.each { clave, valor ->
        comandoTerraform += " -var=\"${clave}=${valor}\""
    }
    sh "echo \" ${comandoTerraform}\""
    // dir(_workdir) {
    //     withCredentials([[
    //         $class: 'AmazonWebServicesCredentialsBinding',
    //         credentialsId: Constants.AWS_ACCOUNTS_CREDENTIALS[_accountToDeploy],
    //         accessKeyVariable: 'AWS_ACCESS_KEY_ID',
    //         secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
    //     ]]) {
    //         sh "terraform plan -no-color -var=\"iac_aws_region=${_awsRegion}\" -var=\"iac_aws_access_key=${AWS_ACCESS_KEY_ID}\" -var=\"iac_aws_secret_key=${AWS_SECRET_ACCESS_KEY}\""+comandoTerraform
    //     }
    // }
}

def applyAws() {
    // Code to apply Terraform changes
}