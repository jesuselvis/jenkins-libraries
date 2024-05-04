import commons.Constants

def InicializarTerraformS3(String awsRegion, String bucketName, String keyPath) {
    terraform {
        provider "aws" {
            access_key = credentialsId('IAM_S3_TFStates').access_key
            secret_key = credentialsId('IAM_S3_TFStates').secret_key
            region      = awsRegion
        }

        terraform init

        workspace "workspace-guardar-tfstates"
        sourcePath "path/a/tu/pipeline/guardar-tfstates.tf"
        vars {
            // Define las variables necesarias para el pipeline de guardar tfstates
        }
        backend {
            bucket = bucketName
            key    = keyPath
        }
    }
}

// Definición de función "PlanificarCambiosS3"
def PlanificarCambiosS3(String awsRegion, String bucketName, String keyPath) {
    terraform {
        provider "aws" {
            access_key = credentialsId('IAM_S3_TFStates').access_key
            secret_key = credentialsId('IAM_S3_TFStates').secret_key
            region      = awsRegion
        }

        terraform plan
    }
}

// Definición de función "AplicarCambiosS3"
def AplicarCambiosS3(String awsRegion, String bucketName, String keyPath) {
    terraform {
        provider "aws" {
            access_key = credentialsId('IAM_S3_Provisioning').access_key
            secret_key = credentialsId('IAM_S3_Provisioning').secret_key
            region      = awsRegion
        }

        terraform apply
    }
}