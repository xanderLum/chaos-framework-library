package utility

import constants.APIGroovy

class ReportUtil {

    static def getTaskDetails(script, def taskId) {
        script.sh "echo Retrieving TaskDetails by TaskID"
        def url = ApiUtil.urlBuilder(script, APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.GET_TASK_DETAILS_BY_TASKID.apiURL, ApiUtil.IP)
        def taskDetails = ApiUtil.apiCall(script, url, "GET", taskId)
        return taskDetails
    }

    static def processTask() {

    }

}
