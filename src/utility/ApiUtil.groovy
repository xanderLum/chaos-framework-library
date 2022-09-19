package utility

import constants.Credentials

class ApiUtil {
    static String HTTPS = "https://";
//    static String IP = "10.11.57.125"; //change to variable in pipeline
    static String IP = "mangle.devops.karnagi.monster:8443"

    /**
     * Execute API call
     *
     * @param url
     * @param method
     * @param data
     * @return
     */
    static def apiCall(script, String url, String method, String data) {
        script.sh 'pwd'
        println(" Invoking API url:  ${url} ")
        println(" Invoking API method: ${method} ")
        println(" Invoking API data:  ${data} ")

        script.withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: Credentials.mangleServiceAccount, usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
            println(" username: ${script.env.USERNAME} ")
            println(" password: ${script.env.PASSWORD} ")

            if (method != "GET") {
                def response = script.sh(returnStdout: true, script: "curl -kv ${method} -d \'${data}\' " +
                        "--user ${script.env.USERNAME}:${script.env.PASSWORD} -H 'Content-Type: application/json' ${url}").trim()
                script.echo "${response}"
                return response
            } else {
                //todo build GET request params
                def response = script.sh(returnStdout: true, script: "curl -kv ${method} -d \'${data}\' " +
                        "--user ${script.env.USERNAME}:${script.env.PASSWORD} -H 'Content-Type: application/json' ${url}").trim()
                script.echo "${response}"
                return response
            }
        }
    }

/**
 * Build URL of the API
 *
 * @param contextPath
 * @param apiURL
 * @param host
 * @return
 */
    static def urlBuilder(script, String contextPath, String apiURL, String host) {
        if (host != null) {
            def url = HTTPS + "${host}${contextPath}${apiURL}"
            script.sh "echo url: ${url}"
            return "${url}"
        } else {
            def url = HTTPS + "${contextPath}${apiURL}"
            script.sh "echo url: ${url}"
            return "${url}"
        }
    }
}
