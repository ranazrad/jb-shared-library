def call(String command){
    script {
        if (isUnix()) {
            sh command
        }
        else {
            bat command
        }
    }
}
