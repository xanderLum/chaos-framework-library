package utility


import constants.APIGroovy
import utility.VMWareMangle.*

class ChaosUtil {

    /**
     *
     * @param script
     * @param endpointName
     * @param timeoutInMilliseconds
     * @param id
     * @param injectionHomeDir
     * @param taskName
     * @param cpuLoad //if cpuLoad is less than 1, default cpuLoad is 100
     * @param randomEndpoint
     * @param cronExpression
     * @param timeInMilliseconds
     * @param schedId
     * @param description
     * @return
     */
    static def injectCPUFault(script, String endpointName, def timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, int cpuLoad, randomEndpoint,
                              String cronExpression, def timeInMilliseconds, String schedId, String description) {
        CPURequestObj cpuRequestObj = new CPURequestObj(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, cpuLoad, randomEndpoint,
                cronExpression, timeInMilliseconds, schedId, description)
        script.sh "echo ChaosUtil Injecting CPU Fault"
        script.sh "echo cpuRequestObj: ${cpuRequestObj.toString()}"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.CPU_FAULT_API.apiURL, ApiUtil.IP)
        def response = ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(cpuRequestObj))
        //resolve response to retrieve TaskId
        def taskId = ParamBuilder.resolveResponseAndRetrieveTaskId(response)
        //ReportUtil.getTaskDetails(script, taskId)
        def taskDetails = ReportUtil.getTaskDetails(script, taskId)
        //check-in git for the taskDetails
        //report pipeline to collate
        script.sh "echo taskDetailsRetrieved: ${taskDetails}"
    }

    /**
     * Injecting Memory Fault
     * @param script
     * @param endpointName
     * @param timeoutInMilliseconds
     * @param id
     * @param injectionHomeDir
     * @param taskName
     * @param memoryLoad //if memoryLoad is less than 1, default memoryLoad is 100
     * @param randomEndpoint
     * @param cronExpression
     * @param timeInMilliseconds
     * @param schedId
     * @param description
     * @return
     */
    static def injectMemoryFault(script, String endpointName, def timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, int memoryLoad, randomEndpoint,
                                 String cronExpression, def timeInMilliseconds, String schedId, String description) {
        MemoryRequestObj memoryRequestObj = new MemoryRequestObj(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, memoryLoad, randomEndpoint,
                cronExpression, timeInMilliseconds, schedId, description)
        script.sh "echo ChaosUtil Injecting Memory Fault"
        script.sh "echo memoryRequestObj: ${memoryRequestObj.toString()}"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.MEMORY_FAULT_API.apiURL, ApiUtil.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(memoryRequestObj))
    }

    /**
     * Inject Disk IO Fault
     *
     * @param script
     * @param endpointName
     * @param timeoutInMilliseconds
     * @param id
     * @param injectionHomeDir
     * @param taskName
     * @param ioSize //if ioSize is less than 1, default IO size is 8GB
     * @param targetDir //if targetDir is empty, default is "/home/"
     * @param randomEndpoint
     * @param cronExpression
     * @param timeInMilliseconds
     * @param schedId
     * @param description
     * @return
     */
    static def injectDiskIOFault(script, String endpointName, def timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, def ioSize, String targetDir, randomEndpoint,
                                 String cronExpression, def timeInMilliseconds, String schedId, String description) {
        DiskIORequestObj diskIORequestObj = new DiskIORequestObj(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, ioSize, targetDir, randomEndpoint,
                cronExpression, timeInMilliseconds, schedId, description)
        script.sh "echo ChaosUtil Injecting DiskIO Fault"
        script.sh "echo diskIORequestObj: ${diskIORequestObj.toString()}"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.DISK_IO_FAULT_API.apiURL, ApiUtil.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(diskIORequestObj))
    }

    /**
     * Inject DiskSpace Fault
     *
     * @param script
     * @param endpointName
     * @param timeoutInMilliseconds
     * @param id
     * @param injectionHomeDir
     * @param taskName
     * @param directoryPath //if directoryPath is empty, default is "/home/"
     * @param diskFillSize //if diskFillSize is empty, default diskFillSize is 90
     * @param randomEndpoint
     * @param cronExpression
     * @param timeInMilliseconds
     * @param schedId
     * @param description
     * @return
     */
    static def injectDiskSpaceFault(script, String endpointName, def timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, String directoryPath, def diskFillSize, randomEndpoint,
                                    String cronExpression, def timeInMilliseconds, String schedId, String description) {
        DiskSpaceRequestObj diskSpaceRequestObj = new DiskSpaceRequestObj(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, directoryPath, diskFillSize, randomEndpoint,
                cronExpression, timeInMilliseconds, schedId, description)
        script.sh "echo ChaosUtil Injecting DiskSpace Fault"
        script.sh "echo diskSpaceRequestObj: ${diskSpaceRequestObj.toString()}"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.DISK_SPACE_FAULT_API.apiURL, ApiUtil.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(diskSpaceRequestObj))
    }

    /**
     * Inject Kernel Panic Fault
     * @param script
     * @param endpointName
     * @param id
     * @param injectionHomeDir
     * @param taskName
     * @param randomEndpoint
     * @param cronExpression
     * @param timeInMilliseconds
     * @param schedId
     * @param description
     * @return
     */
    static def injectKernelPanicFault(script, String endpointName, String id, String injectionHomeDir, String taskName, randomEndpoint,
                                      String cronExpression, def timeInMilliseconds, String schedId, String description) {
        KernelPanicRequestObj kernelPanicRequestObj = new KernelPanicRequestObj(endpointName, id, injectionHomeDir, taskName, randomEndpoint,
                cronExpression, timeInMilliseconds, schedId, description)
        script.sh "echo ChaosUtil Injecting Kernel Panic Fault"
        script.sh "echo kernelPanicRequestObj: ${kernelPanicRequestObj.toString()}"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.KERNEL_PANIC_FAULT_API.apiURL, ApiUtil.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(kernelPanicRequestObj))
    }

    /**
     * Inject File Handler Fault
     *
     * @param script
     * @param endpointName
     * @param timeoutInMilliseconds
     * @param id
     * @param injectionHomeDir
     * @param taskName
     * @param randomEndpoint
     * @param cronExpression
     * @param timeInMilliseconds
     * @param schedId
     * @param description
     * @return
     */
    static def injectFileHandlerFault(script, String endpointName, def timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, randomEndpoint,
                                      String cronExpression, def timeInMilliseconds, String schedId, String description) {
        FileHandlerLeakRequestObj fileHandlerLeakRequestObj = new FileHandlerLeakRequestObj(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, randomEndpoint,
                cronExpression, timeInMilliseconds, schedId, description)
        script.sh "echo ChaosUtil Injecting File Handler Leak Fault"
        script.sh "echo fileHandlerLeakRequestObj: ${fileHandlerLeakRequestObj.toString()}"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.FILE_HANDLER_LEAK_FAULT_API.apiURL, ApiUtil.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(fileHandlerLeakRequestObj))
    }

    /**
     * Inject ClockSkew Fault
     *
     * @param script
     * @param endpointName
     * @param timeoutInMilliseconds
     * @param id
     * @param injectionHomeDir
     * @param taskName
     * @param clockSkewOperation
     * @param days
     * @param hr
     * @param min
     * @param sec
     * @param randomEndpoint
     * @param cronExpression
     * @param timeInMilliseconds
     * @param schedId
     * @param description
     * @return
     */
    static def injectClockSkewFault(script, String endpointName, def timeoutInMilliseconds, String id, String injectionHomeDir, String taskName,
                                    String clockSkewOperation, int days, int hr, int min, int sec, randomEndpoint,
                                    String cronExpression, def timeInMilliseconds, String schedId, String description) {
        ClockSkewRequestObj clockSkewRequestObj = new ClockSkewRequestObj(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName,
                clockSkewOperation, days, hr, min, sec, randomEndpoint, cronExpression, timeInMilliseconds, schedId, description)
        script.sh "echo ChaosUtil Injecting Clock Skew Fault"
        script.sh "echo clockSkewRequestObj: ${clockSkewRequestObj.toString()}"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.CLOCKSKEW_FAULT_API.apiURL, ApiUtil.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(clockSkewRequestObj))
    }

    /**
     * Inject KillProcess Fault
     *
     * @param script
     * @param endpointName
     * @param timeoutInMilliseconds
     * @param id
     * @param injectionHomeDir
     * @param taskName
     * @param killAll
     * @param processId
     * @param processIdentifier
     * @param remediationCommand
     * @param randomEndpoint
     * @param cronExpression
     * @param timeInMilliseconds
     * @param schedId
     * @param description
     * @return
     */
    static def injectKillProcessFault(script, String endpointName, def timeoutInMilliseconds, String id, String injectionHomeDir, String taskName,
                                      boolean killAll, String processId, String processIdentifier, String remediationCommand, randomEndpoint,
                                      String cronExpression, def timeInMilliseconds, String schedId, String description) {
        KillProcessRequestObj killProcessRequestObj = new KillProcessRequestObj(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName,
                killAll, processId, processIdentifier, remediationCommand, randomEndpoint, cronExpression, timeInMilliseconds, schedId, description)
        script.sh "echo ChaosUtil Injecting Kill Process Fault"
        script.sh "echo killProcessRequestObj: ${killProcessRequestObj.toString()}"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.KILL_PROCESS_FAULT.apiURL, ApiUtil.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(killProcessRequestObj))
    }

    /**
     * Inject Network Fault
     *
     * @param script
     * @param endpointName
     * @param timeoutInMilliseconds
     * @param id
     * @param injectionHomeDir
     * @param taskName
     * @param killAll
     * @param processId
     * @param processIdentifier
     * @param remediationCommand
     * @param randomEndpoint
     * @param cronExpression
     * @param timeInMilliseconds
     * @param schedId
     * @param description
     * @return
     */
    static def injectNetworkPacketLossFault(script, String endpointName, def timeoutInMilliseconds, String id, String injectionHomeDir, String taskName,
                                            boolean killAll, String processId, String processIdentifier, String remediationCommand, randomEndpoint,
                                            String cronExpression, def timeInMilliseconds, String schedId, String description) {
        KillProcessRequestObj killProcessRequestObj = new KillProcessRequestObj(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName,
                killAll, processId, processIdentifier, remediationCommand, randomEndpoint, cronExpression, timeInMilliseconds, schedId, description)
        script.sh "echo ChaosUtil Injecting Kill Process Fault"
        script.sh "echo killProcessRequestObj: ${killProcessRequestObj.toString()}"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.NETWORK_FAULT.apiURL, ApiUtil.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(killProcessRequestObj))
    }

}
