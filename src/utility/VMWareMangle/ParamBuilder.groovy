package utility.VMWareMangle

import com.fasterxml.jackson.databind.deser.impl.PropertyValue
import groovy.json.JsonOutput

class ParamBuilder<T> {

    static def buildReqParam(T requestObject) {
        return JsonOutput.toJson(requestObject)
    }

    static def buildKeyValPair(T requestObject) {

        // typical Groovy

        def map3 = [:]
        def list3 = []
        list3 << "hello"
        map3.'abc' = list3
        assert map3.'abc' == list3

        PropertyValue.Map m = [:].withDefault { key -> return [] }
        for (object in listOfObjects) {
            List valueList = m.get(object.myKey)
            m.put(object.myKey, valueList)
        }
    }


}
