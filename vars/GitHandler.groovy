import commons.Constants

class GitHandler {
    def script

    GitHandler(script) {
        this.script = script
    }

    def Checkout(String repository, String source, String branch) {
        script.dir(source) {
            script.checkout([$class: 'GitSCM',
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

        script.dir(source) {
            script.checkout([$class: 'GitSCM',
                      branches: [[name: 'master']],
                      doGenerateSubmoduleConfigurations: false,
                      extensions: [],
                      submoduleCfg: [],
                      userRemoteConfigs: [[credentialsId: 'git_credentials', url: repository]]
                     ])
        }
    }
}