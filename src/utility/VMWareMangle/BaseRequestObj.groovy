package utility.VMWareMangle


import constants.StaticRequestObj
import org.apache.groovy.parser.antlr4.util.StringUtils

class BaseRequestObj implements Serializable {
    String endpointName
    int timeoutInMilliseconds
    String id
    String injectionHomeDir
    String taskName

    BaseRequestObj(String endpointName, int timeoutInMilliseconds, String id, String injectionHomeDir, String taskName) {
        this.endpointName = StringUtils.isEmpty(endpointName) ? StaticRequestObj.ENDPOINT : endpointName
        this.timeoutInMilliseconds = timeoutInMilliseconds < 1 ? StaticRequestObj.TIMEOUTINMILLISECONDS : timeoutInMilliseconds
        this.id = StringUtils.isEmpty(id) ? StaticRequestObj.ID : id
        this.injectionHomeDir = StringUtils.isEmpty(injectionHomeDir) ? StaticRequestObj.INJECTIONHOMEDIR : injectionHomeDir
        this.taskName = StringUtils.isEmpty(taskName) ? 'task' : StaticRequestObj.PREFIXTASKNAME + taskName
    }


    @Override
    public String toString() {
        return "BaseRequestObj{" +
                "endpointName='" + endpointName + '\'' +
                ", timeoutInMilliseconds=" + timeoutInMilliseconds +
                ", id='" + id + '\'' +
                ", injectionHomeDir='" + injectionHomeDir + '\'' +
                ", taskName='" + taskName + '\'' +
                '}';
    }
}
