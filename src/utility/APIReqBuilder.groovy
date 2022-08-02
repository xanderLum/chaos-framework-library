package utility


import constants.APIGroovy

class APIReqBuilder {
    static String HTTPS = "https://";
    static String IP = "10.11.57.125"; //change to variable in pipeline

    static def callAPI(script, apiId) {
        println("Calling api" + APIGroovy.getApiEnum(apiId));
//        script.sh "curl -X POST -d '{ \"request\": { \"url\": \"/hello-world\" }, \"response\": { \"body\": \"Hi!\" }}' -H 'Authorization:Token 0418bfa3937504586f4a0ea80c9fffb9' https://xander.mocklab.io/__admin/mappings"
        def urlBldr = new StringBuilder();
        urlBldr.append(HTTPS);
        urlBldr.append(APIGroovy.TEST_CONTEXT_PATH.apiURL);
        println("API test url: " + urlBldr.toString());
        /*def response = sh(script: 'curl -X POST -d @req.json -H \'Authorization:Token 0418bfa3937504586f4a0ea80c9fffb9\' https://xander.mocklab.io/__admin/mappings', returnStdout: true)
        def postmanPost = new URL('https://xander.mocklab.io/__admin/mappings')
        def postConnection = postmanPost.openConnection()
        postConnection.requestMethod = 'POST'
        assert postConnection.responseCode == 200*/

//        def postmanPost = new URL('https://xander.mocklab.io/__admin/mappings')
        def postmanPost = new URL(urlBldr.toString());
        def postConnection = postmanPost.openConnection()
//        def form = "param1=This is request parameter."
        def form = [] new File('/src/api/input/req.json').eachLine { line -> form.add(line) }
        postConnection.doOutput = true
        def text
        postConnection.with {
            outputStream.withWriter { outputStreamWriter ->
                outputStreamWriter << form
            }
            text = content.text
        }
        assert postConnection.responseCode == 200

        println("response : " + postConnection.responseCode);
        return postConnection.responseCode;
    }
}
