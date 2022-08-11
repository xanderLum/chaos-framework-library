package utility.VMWareMangle

class MemoryRequestObj extends BaseRequestObj {
    int memoryLoad

    MemoryRequestObj(String endpointName, int timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, int memoryLoad) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName)
        //if memoryLoad is empty, default memoryLoad is 100
        this.memoryLoad = memoryLoad < 1 ? 100 : memoryLoad
    }


    @Override
    public String toString() {
        return "MemoryRequestObj{" + super.toString() +
                "memoryLoad=" + memoryLoad +
                '}';
    }
}
