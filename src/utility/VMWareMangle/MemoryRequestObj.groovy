package utility.VMWareMangle

@Grab(group = 'org.codehaus.groovy', module = 'groovy', version = '3.0.12')
import org.apache.groovy.parser.antlr4.util.StringUtils

class MemoryRequestObj extends BaseRequestObj {
    int memoryLoad

    MemoryRequestObj(String endpointName, int timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, int memoryLoad) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName)
        //if memoryLoad is empty, default memoryLoad is 100
        this.memoryLoad = StringUtils.isEmpty(String.valueOf(memoryLoad)) ? 100 : memoryLoad
    }


    @Override
    public String toString() {
        return "MemoryRequestObj{" + super.toString() +
                "memoryLoad=" + memoryLoad +
                '}';
    }
}