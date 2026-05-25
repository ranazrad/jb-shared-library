def call(String version, Closure body) {
    def image = "node:${version}-alpine"
    container(image) {
        body()
    }
}

/*
withNodeEnv('20') {
    sh 'npm install'
    sh 'npm test'
}
*/
