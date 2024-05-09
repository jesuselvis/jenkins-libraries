//https://www.jenkins.io/doc/book/pipeline/shared-libraries/#defining-shared-libraries
package commons

class Constants {
	public static String DEPLOY_PROJECT	= '.'
	public static String REPO_CONFIG	= ''
	public static String WORKDIR_TO_DEPLOY	= 'workdirToDeploy'
    
    public static Map TF_STATES_S3_BUCKET = [ 	develop 	: 	'devops-iac-dev-jenkins-tfstates',
    											test  	    : 	'devops-iac-test-jenkins-tfstates',
    											production	: 	'devops-iac-prod-jenkins-tfstates']
	
	public static Map TF_STATES_CREDENTIALS_ID = [	develop 	: 	'AWS_TFSTATES__BACKEND_ACCOUNT',
    												test  	    : 	'',
    												production	: 	'' ]
	

	public static Map GIT_JENKINS_PIPELINES	= [ dataPlatform	: 	'https://github.com/victorjhqampier/jenkins-pipelines.git' ]
}