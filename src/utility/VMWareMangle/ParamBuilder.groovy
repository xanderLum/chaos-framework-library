package utility.VMWareMangle

import groovy.json.JsonOutput

class ParamBuilder<T> {

    static def buildReqParam(T requestObject) {
        return JsonOutput.toJson(requestObject)
    }

}
