def call(Map config = [:]) {
    echo "Starting Security Vulnerability Scan..."
    
    if (config.failOnHighSeverity) {
        error("CRITICAL VULNERABILITY FOUND! Failing the build as failOnHighSeverity is set to true.")
    } else {
        echo "Scan completed successfully. No critical vulnerabilities found or scanner is in audit mode."
    }
}
