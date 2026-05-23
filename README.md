# Jenkins Shared Library

Jenkins Shared Library for CI/CD automation.

## Repository Structure

* **`vars/`** - Global pipeline steps (DSL interfaces).
* **`src/`** - Core Groovy classes and business logic.

## Usage Example

Always pin to a specific Git tag to ensure production stability:

```groovy
@Library('jb-shared-library@v1.0.0') _

pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                // Call global variable from vars/
                buildApp(engine: 'maven', goals: 'clean package')
            }
        }
    }
}
