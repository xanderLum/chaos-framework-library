package utility

@Grab(group = 'com.github.groovy-wslite', module = 'groovy-wslite', version = '1.1.3')
@Grab(group = 'com.cloudbees', module = 'groovy-cps', version = '1.24')
import constants.APIGroovy

class APIReqBuilder implements Serializable {
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

        /*def postmanPost = new URL(urlBldr.toString());
        script.sh "echo postmanPost new URL "
        def postConnection = postmanPost.openConnection()
        script.sh "echo postConnection open"
        postConnection.requestMethod = 'POST'
        script.sh "echo set postConnection requestMethod POST.. getting inputJSONReq..."*/

//        def form = "param1=This is request parameter."
//        def form = []
//        new File(new StringBuilder(workspace).append('/src/api/input/req.json').toString()).eachLine { line -> form.add(line) }
        String form = " ${getInputJSONReq(script)} "

//        def form = []
//        script.sh "echo 'executing new File'"
//        new File(new StringBuilder(String.valueOf(workspace)).append('/src/api/input/req.json').toString()).eachLine { line -> form.add(line) }
//        script.sh "echo 'form req json :'${form}'"

        script.sh "echo retrieved inputJSONReq form"

        restCall(script, "POST", form)
        /*postConnection.doOutput = true
        script.sh "echo postConnection doOutput=true"

        def text
        postConnection.with {
            outputStream.withWriter { outputStreamWriter ->
                outputStreamWriter << form
            }
            text = content.text
        }
        assert postConnection.responseCode == 200
        script.sh "echo 'response: '${postConnection.responseCode}"*/
        script.sh "echo 'end call'"
    }

//    @NonCPS
    static def getInputJSONReq(script) {
        def form = []
        String workspace = script.WORKSPACE
        script.sh "echo 'executing new File'"
        StringBuilder sb = new StringBuilder()
        sb.append(workspace)
        sb.append('/src/api/input/req.json')
        new File(sb.toString()).eachLine { line -> form.add(line) }
        script.sh "echo 'form req json :'${form}"
        return form
    }

//    def restCall(String method, String resource, String data = '') {
    static def restCall(script, String method, String data = '') {
        script.sh "echo 'data content: '${data}"
//        def URL url = new URL("${Params.REST_BASE_URI}/${resource}")
        def URL url = new URL("${HTTPS}${APIGroovy.TEST_CONTEXT_PATH.apiURL}${APIGroovy.TEST_API.apiURL}")
        def HttpURLConnection connection = url.openConnection()

        /*script.withCredentials([usernamePassword(credentialsId: 'restful-api', passwordVariable: 'RA_PASS', usernameVariable: 'RA_USER')]) {
            String encoded = Base64.getEncoder().encodeToString(("${env.RA_USER}:${env.RA_PASS}").getBytes(StandardCharsets.UTF_8))
            connection.setRequestProperty("Authorization", "Basic ${encoded}");
        }*/

        connection.setRequestProperty("Authorization", "Token 0418bfa3937504586f4a0ea80c9fffb9")
        connection.setRequestProperty("content-type", "application/json");
        connection.setRequestMethod(method)
        connection.doOutput = true

        if (data != '') {
            def writer = new OutputStreamWriter(connection.outputStream)
            writer.write(data)
            writer.flush()
            writer.close()
        }

        connection.connect();

        def statusCode = connection.responseCode
        if (statusCode != 200 && statusCode != 201) {
            script.sh "echo 'statusCode: '${statusCode} "
            String text = connection.getErrorStream().text
            script.sh "echo 'connection getErrorStream: '${text}"
            connection = null
            throw new Exception(text)
        }

        String text = connection.content.text
        script.sh "echo 'connection content: '${text}"
        connection = null
    }
}
