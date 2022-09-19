package utility.VMWareMangle


import constants.StaticRequestObj
import org.apache.groovy.parser.antlr4.util.StringUtils

class ScheduleRequestObj implements Serializable {
    String cronExpression
    def timeInMilliseconds
    String id
    String description

    ScheduleRequestObj(String cronExpression, def timeInMilliseconds, String id, String description) {
        if (StringUtils.isEmpty(cronExpression) && timeInMilliseconds < 1) {
            throw new Exception("to schedule both cronExpression and timeoutInMilliseconds should not be null, " +
                    "please supply a value for cronExpression for recurring schedule; or supply a value in timeoutInMilliseconds for one time schedule.");
        }

        this.cronExpression = cronExpression
        this.timeInMilliseconds = timeInMilliseconds
        this.id = StringUtils.isEmpty(id) ? StaticRequestObj.ID : id
        this.description = description
    }

    @Override
    public String toString() {
        return "ScheduleRequestObj{" +
                "cronExpression='" + cronExpression + '\'' +
                ", timeoutInMilliseconds=" + timeoutInMilliseconds +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
