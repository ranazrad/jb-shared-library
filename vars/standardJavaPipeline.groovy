def call(Map config = [:]) {
    pipeline {
        agent { label config.agentLabel ?: 'k8s-agent' }
        stages {
            stage('Compile & Test') {
                steps {
                    echo 'mvn clean test'
                }
            }
            stage('SonarQube Analysis') {
                steps {
                    echo "Running SonarQube for project: ${config.projectName}"
                }
            }
        }
        post {
            always {
                cleanWs()
            }
        }
    }
}

//standardJavaPipeline(projectName: 'billing-service')
