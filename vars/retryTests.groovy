def call(Closure testClosure, int retryCount = 3, boolean demo = false) {
    if (demo) {
        echo "Demo mode enabled. Simulating retries (${retryCount} attempts)."
        for (int attempt = 1; attempt <= retryCount; attempt++) {
            echo "Simulated retry attempt ${attempt}."
        }
        writeFile file: 'retry-mock-log.txt', text: "Simulated ${retryCount} retry attempts completed."
        archiveArtifacts artifacts: 'retry-mock-log.txt'
    } else {
        int attempt = 0
        while (attempt < retryCount) {
            try {
                testClosure()
                return
            } catch (Exception e) {
                attempt++
                echo "Attempt ${attempt} failed. Retrying..."
                if (attempt == retryCount) {
                    throw e
                }
            }
        }
    }
}
