package utility.VMWareMangle

class TaskAPIResponseTriggerResolver implements Serializable {
    def startTime
    def endTime
    def taskStatus
    def mangleTaskInfo

    def childTaskIDs
    def node
    def taskRetriggered
    def triggerMetricCollected
}
