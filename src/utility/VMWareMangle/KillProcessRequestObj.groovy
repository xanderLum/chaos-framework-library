package utility.VMWareMangle

import org.apache.groovy.parser.antlr4.util.StringUtils

class KillProcessRequestObj extends BaseRequestObj {

    boolean killAll
    String processId
    String processIdentifier
    String remediationCommand

    KillProcessRequestObj(String endpointName, def timeoutInMilliseconds, String id, String injectionHomeDir, String taskName,
                          boolean killAll, String processId, String processIdentifier,
                          String remediationCommand, randomEndpoint,
                          String cronExpression, def timeInMilliseconds, String schedId, String description) {
        super(endpointName, timeoutInMilliseconds, id, injectionHomeDir, taskName, randomEndpoint,
                cronExpression, timeInMilliseconds, schedId, description)
        this.processId = StringUtils.isEmpty(processId) ? "123" : processId
        this.processIdentifier = StringUtils.isEmpty(processIdentifier) ? "123" : processIdentifier
        this.remediationCommand = StringUtils.isEmpty(remediationCommand) ? "systemctl <service> status" : remediationCommand
    }


    @Override
    public String toString() {
        return "KillProcessRequestObj{" + super.toString() +
                "killAll=" + killAll +
                ", processId='" + processId + '\'' +
                ", processIdentifier='" + processIdentifier + '\'' +
                ", remediationCommand='" + remediationCommand + '\'' +
                '}';
    }
}

