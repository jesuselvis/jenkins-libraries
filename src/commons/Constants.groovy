//https://www.jenkins.io/doc/book/pipeline/shared-libraries/#defining-shared-libraries
package commons

class Constants {
	public static String DEPLOY_PROJECT	= '.'
	public static String REPO_CONFIG	= ''
	public static String DEPLOY_WORKDIR	= 'ToDeploy'
    
    public static Map GIT_PIPELINE_BRANCHES = [ dev 	: 	'dev',
    										test  	    : 	'test',
    										prod 		: 	'prod' ]

	public static Map GIT_JENKINS_PIPELINES	= [ dataPlatform	: 	'https://github.com/victorjhqampier/jenkins-pipelines.git' ]
}