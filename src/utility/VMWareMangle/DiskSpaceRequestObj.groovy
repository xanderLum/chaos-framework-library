package utility.VMWareMangle

@Grab(group = 'org.codehaus.groovy', module = 'groovy', version = '3.0.12')
import org.apache.groovy.parser.antlr4.util.StringUtils

class DiskSpaceRequestObj extends BaseRequestObj {
    String directoryPath
    def diskFillSize

    DiskSpaceRequestObj(String endpointName, def timeoutInMilliseconds, String id, String injectionHomeDir,
                        String taskName, String directoryPath, def diskFillSize, randomEndpoint,
                        String cronExpression, def schedTimeoutInMilliseconds, String schedId, String description) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, randomEndpoint,
                cronExpression, schedTimeoutInMilliseconds, schedId, description)
        //if directoryPath is empty, default is "/home/"
        this.directoryPath = StringUtils.isEmpty(directoryPath) ? "/home/" : directoryPath
        //if diskFillSize is empty, default diskFillSize is 90
        this.diskFillSize = diskFillSize < 1 ? 90 : diskFillSize
    }


    @Override
    public String toString() {
        return "DiskSpaceRequestObj{" + super.toString() +
                "directoryPath='" + directoryPath + '\'' +
                ", diskFillSize=" + diskFillSize +
                '}';
    }
}
