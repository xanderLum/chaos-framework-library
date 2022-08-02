package utility

import com.cloudbees.groovy.cps.NonCPS
@Grab(group = 'com.github.groovy-wslite', module = 'groovy-wslite', version = '1.1.3')
@Grab(group = 'com.cloudbees', module = 'groovy-cps', version = '1.24')
import constants.APIGroovy

class APIReqBuilder {
    static String HTTPS = "https://";
    static String IP = "10.11.57.125"; //change to variable in pipeline

    static def callAPI(script, apiId) {
        String workspace = script.WORKSPACE
        script.sh "echo 'workspace...'"
        script.sh "echo ${workspace}"

        script.sh "echo 'Calling api '${APIGroovy.getApiEnum(apiId)}"
//        script.sh "curl -X POST -d '{ \"request\": { \"url\": \"/hello-world\" }, \"response\": { \"body\": \"Hi!\" }}' -H 'Authorization:Token 0418bfa3937504586f4a0ea80c9fffb9' https://xander.mocklab.io/__admin/mappings"
        def urlBldr = new StringBuilder()
        urlBldr.append(HTTPS)
        urlBldr.append(APIGroovy.TEST_CONTEXT_PATH.apiURL)
        urlBldr.append(APIGroovy.TEST_API.apiURL)
        script.sh "echo 'API test url: '${urlBldr.toString()}"
        /*def response = sh(script: 'curl -X POST -d @req.json -H \'Authorization:Token 0418bfa3937504586f4a0ea80c9fffb9\' https://xander.mocklab.io/__admin/mappings', returnStdout: true)
        def postmanPost = new URL('https://xander.mocklab.io/__admin/mappings')
        def postConnection = postmanPost.openConnection()
        postConnection.requestMethod = 'POST'
        assert postConnection.responseCode == 200*/

        def postmanPost = new URL(urlBldr.toString());

        def postConnection = postmanPost.openConnection()
        postConnection.requestMethod = 'POST'

//        def form = "param1=This is request parameter."
//        def form = []
//        new File(new StringBuilder(workspace).append('/src/api/input/req.json').toString()).eachLine { line -> form.add(line) }
        def form = "${getInputJSONReq(script, workspace)}"
        postConnection.doOutput = true
        def text
        postConnection.with {
            outputStream.withWriter { outputStreamWriter ->
                outputStreamWriter << form
            }
            text = content.text
        }
        assert postConnection.responseCode == 200
        script.sh "echo 'response: '${postConnection.responseCode}"
        return postConnection.responseCode;
    }

    @NonCPS
    static def getInputJSONReq(script, workspace) {
        def form = []
        new File(new StringBuilder(String.valueOf(workspace)).append('/src/api/input/req.json').toString()).eachLine { line -> form.add(line) }
        script.sh "echo 'form req json :'${form}"
        return form
    }
}
