def call(String buildStatus) {
    echo "=================================================="
    echo "🔔 SLACK ALERT: Pipeline finished with status: ${buildStatus}"
    echo "=================================================="
}
