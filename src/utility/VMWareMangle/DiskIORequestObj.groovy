package utility.VMWareMangle

@Grab(group = 'org.codehaus.groovy', module = 'groovy', version = '3.0.12')
import org.apache.groovy.parser.antlr4.util.StringUtils

class DiskIORequestObj extends BaseRequestObj {
    def ioSize
    String targetDir

    DiskIORequestObj(String endpointName, timeoutInMilliseconds, String id, String injectionHomeDir,
                     String taskName, ioSize, String targetDir, randomEndpoint,
                     String cronExpression, def timeInMilliseconds, String schedId, String description) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, randomEndpoint,
                cronExpression, timeInMilliseconds, schedId, description)
        //if ioSize is less than 1, default IO size is 8GB
        this.ioSize = ioSize < 1 ? 8e+6 : ioSize
        //if targetDir is empty, default is "/home/"
        this.targetDir = StringUtils.isEmpty(String.valueOf(targetDir)) ? "/home/" : targetDir
    }


    @Override
    public String toString() {
        return "DiskIORequestObj{" + super.toString() +
                "ioSize=" + ioSize +
                ", targetDir='" + targetDir + '\'' +
                '}';
    }
}
