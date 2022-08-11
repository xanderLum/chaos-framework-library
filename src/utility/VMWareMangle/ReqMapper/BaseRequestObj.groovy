package utility.VMWareMangle.ReqMapper


import constants.StaticRequestObj
import org.apache.groovy.parser.antlr4.util.StringUtils

class BaseRequestObj {
    String endpointName
    int timeoutInMilliseconds
    String id
    String injectionHomeDir
    String taskName

    BaseRequestObj(String endpointName, int timeoutInMilliseconds, String id, String injectionHomeDir, String taskName) {
        this.endpointName = StringUtils.isEmpty(endpointName) ? StaticRequestObj.ENDPOINT : endpointName
        this.timeoutInMilliseconds = StringUtils.isEmpty(String.valueOf(timeoutInMilliseconds)) ? StaticRequestObj.TIMEOUTINMILLISECONDS : timeoutInMilliseconds
        this.id = StringUtils.isEmpty(id) ? StaticRequestObj.ID : id
        this.injectionHomeDir = StringUtils.isEmpty(injectionHomeDir) ? StaticRequestObj.INJECTIONHOMEDIR : injectionHomeDir
        this.taskName = StringUtils.isEmpty(taskName) ? 'task' : StaticRequestObj.PREFIXTASKNAME + taskName
    }
}
