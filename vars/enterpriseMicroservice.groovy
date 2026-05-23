def call(Map config = [:]) {
    // Set default values
    String appName = config.appName ?: 'unknown-service'
    boolean runScanner = config.runScanner ?: false
    // Set agent label, default to 'any'
    String agentLabel = config.agent ?: 'any'

    pipeline {
        agent {
            label agentLabel
        }

        stages {
            stage('Build') {
                steps {
                    echo "Building Docker image for ${appName} using agent: ${agentLabel}..."
                    sh "sleep 2" 
                }
            }
            stage('Test') {
                steps {
                    echo "Running unit tests for ${appName}..."
                    sh "sleep 2"
                }
            }
            stage('Security Scan') {
                steps {
                    script {
                        securityScan(failOnHighSeverity: runScanner)
                    }
                }
            }
        }
        post {
            always {
                script {
                    slackNotifier(currentBuild.currentResult)
                }
            }
        }
    }
}
