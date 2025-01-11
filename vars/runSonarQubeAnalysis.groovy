def call(Map params) {
    if (!params.projectKey || !params.sonarUrl || !params.credentialsId) {
        error "Missing required parameters: 'projectKey', 'sonarUrl', or 'credentialsId'."
    }

    def demo = params.get('demo', false) // Default to false if not provided

    if (demo) {
        echo "Demo mode enabled. Simulating SonarQube analysis for project '${params.projectKey}'."
        writeFile file: 'sonar-report-mock.txt', text: "Mock SonarQube analysis for project '${params.projectKey}' completed successfully."
        archiveArtifacts artifacts: 'sonar-report-mock.txt'
    } else {
        withCredentials([string(credentialsId: params.credentialsId, variable: 'SONAR_TOKEN')]) {
            sh """
            sonar-scanner \\
                -Dsonar.projectKey=${params.projectKey} \\
                -Dsonar.sources=. \\
                -Dsonar.host.url=${params.sonarUrl} \\
                -Dsonar.login=$SONAR_TOKEN
            """
        }
    }
}
