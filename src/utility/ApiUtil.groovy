package utility

import constants.Credentials

class ApiUtil {
    static String HTTPS = "https://";

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
            println(" username: ${env.USERNAME} ")
            println(" password: ${env.PASSWORD} ")

            def response = script.sh(returnStdout: true, script: "curl -kv ${method} -d \'${data}\' " +
                    "--user ${env.USERNAME}:${env.PASSWORD} -H 'Content-Type: application/json' ${url}").trim()
            script.echo "${response}"
            return response
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
