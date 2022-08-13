def call(String command){
    if (isUnix()) {
        ah command
    }
    else {
        bat command
    }
}
