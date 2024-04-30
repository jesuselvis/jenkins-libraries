import common.Constants

class GitHandler {
    // def proyect(Map param = [:]) {
    //     def repository = param.get("repository")
    //     def source = param.get("source")
    //     def branch = param.get("branch")
    def Checkout(String repository, String source, String branch) {
        dir(source) {
            checkout([$class: 'GitSCM',
                      branches: [[name: "${branch}"]],
                      doGenerateSubmoduleConfigurations: false,
                      extensions: [],
                      submoduleCfg: [],
                      userRemoteConfigs: [[/*credentialsId: 'git_credentials',*/ url: repository + source + '.git']]
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

// // Crea una instancia de CheckoutGit
// def checkoutGit = new CheckoutGit()

// // Llama a los métodos directamente según sea necesario
// // Por ejemplo:
// checkoutGit.proyect(repository: 'http://example.com', source: 'sourceFolder', branch: 'main')
// checkoutGit.config(source: '.', repository: 'http://configrepo.com')
