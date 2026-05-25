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
    }
}

//standardJavaPipeline(projectName: 'billing-service')
