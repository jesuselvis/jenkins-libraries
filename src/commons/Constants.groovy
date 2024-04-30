#!groovy
// package pe.losandes.common
package commons

class Constants {
	public static String DEPLOY_PROJECT	= '.'
	public static String REPO_CONFIG	= ''
    
    public static Map PREFIX_ENV     	= [ desarrollo 		: 	'desa',
    										pruebas  	    : 	'cert',
    										produccion 		: 	'prod' ]

	public static Map GIT_JENKINS_PIPELINES	= [ dataplatform	: 	'https://github.com/victorjhqampier/jenkins-pipelines.git' ]
}