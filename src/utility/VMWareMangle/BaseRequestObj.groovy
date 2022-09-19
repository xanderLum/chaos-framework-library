package utility.VMWareMangle

import com.fasterxml.jackson.annotation.JsonInclude
import constants.StaticRequestObj
import org.apache.groovy.parser.antlr4.util.StringUtils

@JsonInclude(JsonInclude.Include.NON_NULL)
class BaseRequestObj implements Serializable {
    String endpointName
    def timeoutInMilliseconds
    String id
    String injectionHomeDir
    String taskName
    Boolean randomEndpoint
    ScheduleRequestObj schedule

    BaseRequestObj(String endpointName, def timeoutInMilliseconds, String id, String injectionHomeDir, String taskName, randomEndpoint,
                   String cronExpression, def schedTimeoutInMilliseconds, String schedId, String description) {
        this.endpointName = StringUtils.isEmpty(endpointName) ? StaticRequestObj.ENDPOINT : endpointName
        this.timeoutInMilliseconds = timeoutInMilliseconds < 1 ? StaticRequestObj.timeoutInMilliseconds : timeoutInMilliseconds
        this.id = StringUtils.isEmpty(id) ? StaticRequestObj.ID : id
        this.injectionHomeDir = StringUtils.isEmpty(injectionHomeDir) ? StaticRequestObj.INJECTIONHOMEDIR : injectionHomeDir
        this.taskName = StringUtils.isEmpty(taskName) ? 'task' : StaticRequestObj.PREFIXTASKNAME + taskName
        this.randomEndpoint = randomEndpoint
        if (!StringUtils.isEmpty(cronExpression) || schedTimeoutInMilliseconds != null) {
            this.schedule = new ScheduleRequestObj(cronExpression, schedTimeoutInMilliseconds, schedId, description)
        }
    }

    @Override
    public String toString() {
        return "BaseRequestObj{" +
                "endpointName='" + endpointName + '\'' +
                ", timeoutInMilliseconds=" + timeoutInMilliseconds +
                ", id='" + id + '\'' +
                ", injectionHomeDir='" + injectionHomeDir + '\'' +
                ", taskName='" + taskName + '\'' +
                ", randomEndpoint=" + randomEndpoint +
                ", schedule=" + schedule +
                '}';
    }
}
