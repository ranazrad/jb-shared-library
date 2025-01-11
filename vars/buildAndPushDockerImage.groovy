def call(Map params) {
    if (!params.imageName || !params.dockerRegistry || !params.credentialsId) {
        error "Missing required parameters: 'imageName', 'dockerRegistry', or 'credentialsId'."
    }

    def demo = params.get('demo', false)

    if (demo) {
        echo "Demo mode enabled. Simulating Docker build and push for '${params.imageName}'."
        def mockDockerFile = 'docker-image-mock.txt'
        writeFile file: mockDockerFile, text: "Mock Docker image '${params.imageName}' created and pushed."
        archiveArtifacts artifacts: mockDockerFile
    } else {
        withCredentials([usernamePassword(credentialsId: params.credentialsId, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
            sh """
            echo $DOCKER_PASS | docker login ${params.dockerRegistry} -u $DOCKER_USER --password-stdin
            docker build -t ${params.imageName}:${params.tag ?: 'latest'} .
            docker push ${params.imageName}:${params.tag ?: 'latest'}
            """
        }
    }
}
