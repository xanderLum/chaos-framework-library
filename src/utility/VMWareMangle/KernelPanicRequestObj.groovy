package utility.VMWareMangle

class KernelPanicRequestObj extends BaseRequestObj {

    KernelPanicRequestObj(String endpointName, String id, String injectionHomeDir,
                          String taskName, randomEndpoint,
                          String cronExpression, def schedTimeoutInMilliseconds, String schedId, String description) {
        super(endpointName, id, injectionHomeDir, taskName, randomEndpoint,
                cronExpression, schedTimeoutInMilliseconds, schedId, description)
    }
}
