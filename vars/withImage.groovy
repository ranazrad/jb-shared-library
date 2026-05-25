def call(String image, Closure body) {
    container(image) {
        body()
    }
}

/*
withNodeEnv('dotnet') {
    sh 'dotnet build'
    sh 'dotnet publish'
}
*/
