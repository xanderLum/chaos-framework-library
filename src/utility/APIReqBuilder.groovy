package utility

import constants.*

class APIReqBuilder {
    static String HTTPS = "https://";
    static String IP = "10.11.57.125"; //change to variable in pipeline

    static def callAPI(script, api) {
//        println('Calling api: ' + API.apiURL(api));
        println("Calling api"+API.apiURL(api));
//        script.sh "curl -X POST -d '{ \"request\": { \"url\": \"/hello-world\" }, \"response\": { \"body\": \"Hi!\" }}' -H 'Authorization:Token 0418bfa3937504586f4a0ea80c9fffb9' https://xander.mocklab.io/__admin/mappings"
        def urlBldr = new StringBuilder();
        urlBldr.append(HTTPS);
        urlBldr.append(API.TEST_CONTEXT_PATH);
        echo 'API test url: '+urlBldr.toString();
        def response = sh(script: 'curl -X POST -d @req.json -H \'Authorization:Token 0418bfa3937504586f4a0ea80c9fffb9\' https://xander.mocklab.io/__admin/mappings', returnStdout: true)
        echo 'response : ' + response;
//        return response;
    }
}
