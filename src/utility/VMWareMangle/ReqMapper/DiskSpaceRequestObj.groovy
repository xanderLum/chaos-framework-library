package utility.VMWareMangle.ReqMapper

@Grab(group = 'org.codehaus.groovy', module = 'groovy', version = '3.0.12')
import org.apache.groovy.parser.antlr4.util.StringUtils

class DiskSpaceRequestObj extends BaseRequestObj {
    String directoryPath
    int diskFillSize

    DiskSpaceRequestObj(String endpointName, int timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, String directoryPath, int diskFillSize) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName)
        this.directoryPath = StringUtils.isEmpty(directoryPath) ? "/home/" : directoryPath
        //if diskFillSize is empty, default diskFillSize is 80
        this.diskFillSize = StringUtils.isEmpty(String.valueOf(diskFillSize)) ? 80 : diskFillSize
    }
}
