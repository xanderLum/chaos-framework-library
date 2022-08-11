package utility.VMWareMangle

@Grab(group = 'org.codehaus.groovy', module = 'groovy', version = '3.0.12')
import org.apache.groovy.parser.antlr4.util.StringUtils

class DiskIORequestObj extends BaseRequestObj {
    int ioSize
    String targetDir

    DiskIORequestObj(String endpointName, int timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, int ioSize, String targetDir) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName)
        //if ioSize is empty, default IO size is 8GB
        this.ioSize = ioSize < 1 ? 8e+6 : ioSize
        this.targetDir = StringUtils.isEmpty(String.valueOf(targetDir)) ? "/home/" : targetDir
    }


    @Override
    public String toString() {
        return "DiskIORequestObj{" + super.toString() +
                "ioSize=" + ioSize +
                "targetDir=" + targetDir +
                '}';
    }
}
