package utility

import constants.APIGroovy
import utility.VMWareMangle.ParamBuilder

class ReportUtil {

    static def getTaskDetails(script, def taskId) {
        script.sh "echo Retrieving TaskDetails by TaskID"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.GET_TASK_DETAILS_BY_TASKID.apiURL, ApiUtil.IP)
        ApiUtil.apiCall(script, url, "GET", ParamBuilder.buildReqParam(cpuRequestObj))
    }

}
