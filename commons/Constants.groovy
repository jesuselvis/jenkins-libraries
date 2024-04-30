#!groovy
package pe.losandes.common

class Constants {
	public static String DEPLOY_PROJECT	= '.'
	public static String REPO_CONFIG	= 'https://github.com/victorjhqampier/jenkins-pipelines.git'
    
    public static Map PREFIX_ENV     	= [ desarrollo 		: 	'desa',
    										pruebas  	    : 	'cert',
    										produccion 		: 	'prod' ]
}