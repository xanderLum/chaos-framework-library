package utility.VMWareMangle

class KernelPanicRequestObj extends BaseRequestObj {

    KernelPanicRequestObj(String endpointName, String id, String injectionHomeDir,
                          String taskName, randomEndpoint,
                          String cronExpression, def timeInMilliseconds, String schedId, String description) {
        super(endpointName, id, injectionHomeDir, taskName, randomEndpoint,
                cronExpression, timeInMilliseconds, schedId, description)
    }
}
