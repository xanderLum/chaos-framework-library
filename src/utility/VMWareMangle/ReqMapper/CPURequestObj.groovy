package utility.VMWareMangle.ReqMapper

import com.sun.deploy.util.StringUtils

class CPURequestObj extends BaseRequestObj {
    int cpuLoad

    CPURequestObj(String endpointName, int timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, int cpuLoad) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName)
        //if cpuLoad is empty, default cpuLoad is 100
        this.cpuLoad = StringUtils.is(String.valueOf(cpuLoad)) ? 100 : cpuLoad
    }
}
