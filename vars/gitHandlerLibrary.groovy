import commons.Constants

def checkout(String repository, String branch, String source = Constants.DEPLOY_WORKDIR) {
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

def config(Map param = [:]) {
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