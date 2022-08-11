package utility

import constants.APIGroovy
import utility.VMWareMangle.*

class ChaosUtil {

    static def injectCPUFault(script, String endpointName, int timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, int cpuLoad) {
        CPURequestObj cpuRequestObj = new CPURequestObj(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, cpuLoad)
        script.sh "echo ChaosUtil Injecting CPU Fault"
        script.sh "echo cpuRequestObj: ${cpuRequestObj.toString()}"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.CPU_FAULT_API.apiURL, APIReqBuilder.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(cpuRequestObj))
    }

    static def injectMemoryFault(script, String endpointName, int timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, int memoryLoad) {
        MemoryRequestObj memoryRequestObj = new MemoryRequestObj(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, memoryLoad)
        script.sh "echo ChaosUtil Injecting Memory Fault"
        script.sh "echo memoryRequestObj: ${memoryRequestObj.toString()}"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.MEMORY_FAULT_API.apiURL, APIReqBuilder.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(memoryRequestObj))
    }

    static def injectDiskIOFault(script, String endpointName, int timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, int ioSize, String targetDir) {
        DiskIORequestObj diskIORequestObj = new DiskIORequestObj(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, ioSize, targetDir)
        script.sh "echo ChaosUtil Injecting DiskIO Fault"
        script.sh "echo diskIORequestObj: ${diskIORequestObj.toString()}"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.DISK_IO_FAULT_API.apiURL, APIReqBuilder.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(diskIORequestObj))
    }

    static def injectDiskSpaceFault(script, String endpointName, int timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, String directoryPath, int diskFillSize) {
        DiskSpaceRequestObj diskSpaceRequestObj = new DiskSpaceRequestObj(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, directoryPath, diskFillSize)
        script.sh "echo ChaosUtil Injecting DiskSpace Fault"
        script.sh "echo diskSpaceRequestObj: ${diskSpaceRequestObj.toString()}"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.DISK_SPACE_FAULT_API.apiURL, APIReqBuilder.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(diskSpaceRequestObj))
    }
}
