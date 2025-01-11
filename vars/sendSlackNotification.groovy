def call(String status, String channel, String message, boolean demo = false) {
    if (demo) {
        echo "Demo mode enabled. Simulating Slack notification to '${channel}' with status '${status}'."
        writeFile file: 'slack-notification-mock.txt', text: "[${status}] - ${message} (Channel: ${channel})"
    } else {
        slackSend(
            channel: channel,
            color: status == 'SUCCESS' ? 'good' : 'danger',
            message: "[${status}] - ${message}"
        )
    }
}
