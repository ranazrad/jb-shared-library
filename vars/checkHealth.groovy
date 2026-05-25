def call(Map config = [:]) {
    def url = config.url
    def maxRetries = config.maxRetries ?: 3
    
    echo "Starting health check for ${url}..."
    
    retry(maxRetries) {
        sh "curl -f -s ${url} > /dev/null"
    }
}
