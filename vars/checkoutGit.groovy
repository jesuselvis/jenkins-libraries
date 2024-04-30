import commons.Constants

def call(Map param = [:]) {
    def kind = param.get("kind")

    if(!['proyect','config'].contains(kind)) {
        error('Param arg is required and only accept as values proyect and config')
    }

    "${kind}"(param)
}

def proyect(Map param = [:]) {
	def repository  = param.get("repository")
    def source      = param.get("source")
    def branch      = param.get("branch")

    dir(source) {
		checkout([$class: 'GitSCM', 
				   branches: [[name: "${branch}"]], 
				   doGenerateSubmoduleConfigurations: false, 
				   extensions: [], submoduleCfg: [], 
				   userRemoteConfigs: [[/*credentialsId: 'git_credentials',*/ url: repository+source+'.git']]
				])
	}
}

def config(Map param = [:]) {
	def source		= param.get("source",'.')
	def repository  = Constants.DEPLOY_PROJECT
	dir(source) {
		checkout([$class: 'GitSCM', 
				   branches: [[name: 'master']], 
				   doGenerateSubmoduleConfigurations: false, 
				   extensions: [], submoduleCfg: [], 
				   userRemoteConfigs: [[credentialsId: 'git_credentials', url: repository]]
				])
	}
}