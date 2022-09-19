package utility.VMWareMangle

import org.apache.groovy.parser.antlr4.util.StringUtils

class ClockSkewRequestObj extends BaseRequestObj {

    String clockSkewOperation
    int days
    int hr
    int min
    int sec

    ClockSkewRequestObj(String endpointName, def timeoutInMilliseconds, String id, String injectionHomeDir, String taskName,
                        String clockSkewOperation, int days, int hr, int min, int sec, randomEndpoint,
                        String cronExpression, def schedTimeoutInMilliseconds, String schedId, String description) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, randomEndpoint,
                cronExpression, schedTimeoutInMilliseconds, schedId, description)
        this.clockSkewOperation = StringUtils.isEmpty(clockSkewOperation) ? "FUTURE" : clockSkewOperation
        this.days = days < 0 ? 365 : days
        this.hr = hr < 0 ? 24 : hr
        this.min = min < 0 ? 60 : min
        this.sec = sec < 0 ? 60 : sec
    }

    @Override
    public String toString() {
        return "ClockSkewRequestObj{" + super.toString() +
                "clockSkewOperation='" + clockSkewOperation + '\'' +
                ", days=" + days +
                ", hr=" + hr +
                ", min=" + min +
                ", sec=" + sec +
                '}';
    }
}

