def call(Map params) {
    // Parameters validation
    if (!params.containsKey('nexusUrl') || !params.containsKey('credentialsId') || !params.containsKey('artifactPath')) {
        error "Missing required parameters: nexusUrl, credentialsId, artifactPath"
    }

    def demo = params.get('demo', false) // Default value is false if demo is not provided

    if (demo) {
        echo "Demo mode is enabled. Creating and archiving a demo file."

        // Create a simple demo file
        def demoFileName = "demo-artifact.txt"
        writeFile file: demoFileName, text: "This is a demo artifact for Nexus upload."

        // Archive the demo file in Jenkins build
        archiveArtifacts artifacts: demoFileName, allowEmptyArchive: false

        echo "Demo file ${demoFileName} has been archived. Skipping Nexus upload."
    } else {
        echo "Uploading artifact to Nexus."

        // Use credentials to authenticate with Nexus
        withCredentials([usernamePassword(credentialsId: params.credentialsId, usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASSWORD')]) {
            sh """
            curl -u $NEXUS_USER:$NEXUS_PASSWORD \
                --upload-file ${params.artifactPath} \
                ${params.nexusUrl}
            """
        }

        echo "Artifact uploaded successfully to Nexus at ${params.nexusUrl}"
    }
}
