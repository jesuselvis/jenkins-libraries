import commons.Constants

class GitHandler {
    def script

    GitHandler(script) {
        this.script = script
    }
    
    def Checkout(String repository, String source, String branch) {
        dir(source) {
            checkout([$class: 'GitSCM',
                      branches: [[name: "${branch}"]],
                      doGenerateSubmoduleConfigurations: false,
                      extensions: [],
                      submoduleCfg: [],
                      userRemoteConfigs: [[/*credentialsId: 'git_credentials',*/ url: repository]]
                     ])
        }
    }

    def Config(Map param = [:]) {
        def source = param.get("source", '.')
        def repository = Constants.DEPLOY_PROJECT

        dir(source) {
            checkout([$class: 'GitSCM',
                      branches: [[name: 'master']],
                      doGenerateSubmoduleConfigurations: false,
                      extensions: [],
                      submoduleCfg: [],
                      userRemoteConfigs: [[credentialsId: 'git_credentials', url: repository]]
                     ])
        }
    }
}