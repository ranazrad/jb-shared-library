def runCommand(String cmd){
    if (isUnix()) {
        ah cmd
    }
    else {
        bat cmd
    }
}
