package utility

import constants.APIGroovy
import utility.VMWareMangle.ParamBuilder
import utility.VMWareMangle.ReqMapper.CPURequestObj
import utility.VMWareMangle.ReqMapper.DiskIORequestObj
import utility.VMWareMangle.ReqMapper.DiskSpaceRequestObj
import utility.VMWareMangle.ReqMapper.MemoryRequestObj

class ChaosUtil {

    static def injectCPUFault(script, CPURequestObj cpuRequestObj) {
        script.sh "echo ChaosUtil Injecting CPU Fault"
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
