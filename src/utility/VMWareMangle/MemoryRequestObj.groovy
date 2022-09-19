package utility.VMWareMangle

class MemoryRequestObj extends BaseRequestObj {
    def memoryLoad

    MemoryRequestObj(String endpointName, def timeoutInMilliseconds, String id, String injectionHomeDir,
                     String taskName, def memoryLoad, randomEndpoint,
                     String cronExpression, def timeInMilliseconds, String schedId, String description) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, randomEndpoint,
                cronExpression, timeInMilliseconds, schedId, description)
        //if memoryLoad is less than 1, default memoryLoad is 100
        this.memoryLoad = memoryLoad < 1 ? 100 : memoryLoad
    }


    @Override
    public String toString() {
        return "MemoryRequestObj{" + super.toString() +
                "memoryLoad=" + memoryLoad +
                '}';
    }
}
