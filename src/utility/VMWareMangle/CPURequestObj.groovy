package utility.VMWareMangle

class CPURequestObj extends BaseRequestObj {
    int cpuLoad

    CPURequestObj(String endpointName, int timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, int cpuLoad) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName)
        //if cpuLoad is empty, default cpuLoad is 100
        this.cpuLoad = cpuLoad < 1 ? 100 : cpuLoad
    }

    @Override
    public String toString() {
        return "CPURequestObj{" + super.toString() +
                "cpuLoad=" + cpuLoad +
                '}';
    }
}
