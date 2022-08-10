package utility.VMWareMangle.ReqMapper

import org.apache.groovy.parser.antlr4.util.StringUtils

class DiskIORequestObj extends BaseRequestObj {
    int ioSize

    DiskIORequestObj(String endpointName, int timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, int ioSize) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName)
        //if ioSize is empty, default IO size is 8GB
        this.ioSize = StringUtils.isEmpty(String.valueOf(ioSize)) ? 8e+9 : ioSize
    }
}
