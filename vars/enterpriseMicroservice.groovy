def call(Map config = [:]) {
    // Set default values if not provided by the developer
    String appName = config.appName ?: 'unknown-service'
    boolean runScanner = config.runScanner ?: false

    pipeline {
        agent any

        stages {
            stage('Build') {
                steps {
                    echo "Building Docker image for ${appName}..."
                    // Mock build logic
                    sh "sleep 2" 
                }
            }
            stage('Test') {
                steps {
                    echo "Running unit tests for ${appName}..."
                    // Mock test logic
                    sh "sleep 2"
                }
            }
            stage('Security Scan') {
                steps {
                    script {
                        // Calling the custom step created in Task 3
                        securityScan(failOnHighSeverity: runScanner)
                    }
                }
            }
        }
        post {
            always {
                script {
                    // Calling the custom step created in Task 2
                    slackNotifier(currentBuild.currentResult)
                }
            }
        }
    }
}
