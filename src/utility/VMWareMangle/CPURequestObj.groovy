package utility.VMWareMangle

class CPURequestObj extends BaseRequestObj {
    def cpuLoad

    /**
     * CPU Request Obj
     *
     * @param endpointName
     * @param timeoutInMilliseconds
     * @param id
     * @param injectionHomeDir
     * @param taskName
     * @param cpuLoad
     * @param randomEndpoint
     * @param cronExpression
     * @param schedTimeoutInMilliseconds
     * @param schedId
     * @param description
     */
    CPURequestObj(String endpointName, def timeoutInMilliseconds, String id, String injectionHomeDir,
                  String taskName, def cpuLoad, randomEndpoint,
                  String cronExpression, def schedTimeoutInMilliseconds, String schedId, String description) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, randomEndpoint,
                cronExpression, schedTimeoutInMilliseconds, schedId, description)
        //if cpuLoad is less than 1, default cpuLoad is 100
        this.cpuLoad = cpuLoad < 1 ? 100 : cpuLoad
    }

    @Override
    public String toString() {
        return "CPURequestObj{" + super.toString() +
                "cpuLoad=" + cpuLoad +
                '}';
    }
}
