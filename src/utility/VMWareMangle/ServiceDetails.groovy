package utility.VMWareMangle

class ServiceDetails {
    def endpoint
    def startTime
    def endTime
    def status

    ServiceDetails(endpoint, startTime, endTime, status) {
        this.endpoint = endpoint
        this.startTime = startTime
        this.endTime = endTime
        this.status = status
    }

    ServiceDetails() {
    }

    def getEndpoint() {
        return endpoint
    }

    void setEndpoint(endpoint) {
        this.endpoint = endpoint
    }

    def getStartTime() {
        return startTime
    }

    void setStartTime(startTime) {
        this.startTime = startTime
    }

    def getEndTime() {
        return endTime
    }

    void setEndTime(endTime) {
        this.endTime = endTime
    }

    def getStatus() {
        return status
    }

    void setStatus(status) {
        this.status = status
    }
}
