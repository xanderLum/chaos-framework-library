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

    static def injectMemoryFault(script, MemoryRequestObj memoryRequestObj) {
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.MEMORY_FAULT_API.apiURL, APIReqBuilder.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(memoryRequestObj))
    }

    static def injectDiskIOFault(script, DiskIORequestObj diskIORequestObj) {
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.DISK_IO_FAULT_API.apiURL, APIReqBuilder.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(diskIORequestObj))
    }

    static def injectDiskSpaceFault(script, DiskSpaceRequestObj diskSpaceRequestObj) {
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.DISK_SPACE_FAULT_API.apiURL, APIReqBuilder.IP)
        ApiUtil.apiCall(script, url, "POST", ParamBuilder.buildReqParam(diskSpaceRequestObj))
    }
}
