package utility.VMWareMangle

@Grab(group = 'org.codehaus.groovy', module = 'groovy', version = '3.0.12')
import org.apache.groovy.parser.antlr4.util.StringUtils

class CPURequestObj extends BaseRequestObj {
    int cpuLoad

    CPURequestObj(String endpointName, int timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, int cpuLoad) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName)
        //if cpuLoad is empty, default cpuLoad is 100
        this.cpuLoad = StringUtils.isEmpty(String.valueOf(cpuLoad)) ? 100 : cpuLoad
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "CPURequestObj{" +
                "cpuLoad=" + cpuLoad +
                '}';
    }
}
