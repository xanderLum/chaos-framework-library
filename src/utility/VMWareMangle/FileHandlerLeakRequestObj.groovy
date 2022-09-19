package utility.VMWareMangle

class FileHandlerLeakRequestObj extends BaseRequestObj {

    FileHandlerLeakRequestObj(String endpointName, def timeoutInMilliseconds, String id,
                              String injectionHomeDir, String taskName, randomEndpoint,
                              String cronExpression, def schedTimeoutInMilliseconds, String schedId, String description) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, randomEndpoint,
                cronExpression, schedTimeoutInMilliseconds, schedId, description)
    }
}
