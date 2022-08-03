package vars


import constants.APIGroovy
import utility.APIReqBuilder
import utility.FileUtils

@Grab(group = 'com.github.groovy-wslite', module = 'groovy-wslite', version = '1.1.3')


def call(Map config) {
    node {
        /*stage('Clean Workspace') {
            sh 'rm -rf *'
        }
        stage('Checkout') {
            checkout scm
        }*/

        stage("Upload Request.zip file (*-req.json)") {
            def inputFile = input message: 'Please provide a file', parameters: [base64File('file')]
            withEnv(["fileBase64=$inputFile"]) {
                sh 'echo $fileBase64 | base64 -d > request.zip'
                sh 'ls'
            }
//            new hudson.FilePath(new File("$workspace/request.zip")).copyFrom(inputFile)
//            inputFile.delete()
        }

        stage("List workspace contents") {
            sh 'ls'
        }

        stage("Process File parameter") {
            println "START Processing file parameter"
            FileUtils.processFileParam(this)
            println "END Processing file parameter"
        }

        stage('Invoke API Test') {
            println "START INVOKE API TEST"
            println "whereami pwd() "
//            def data = APIReqBuilder.dataReqBuilder(this, APIGroovy.TEST_API.reqFile, APIGroovy.TEST_API.id)
            try {
                apiCall(urlBuilder(APIGroovy.TEST_CONTEXT_PATH.apiURL, APIGroovy.TEST_API.apiURL), "POST", APIReqBuilder.dataReqBuilder(this, APIGroovy.TEST_API.reqFile, APIGroovy.TEST_API.id))
            } catch (Exception e) {
                println e.getMessage()
            }

            println "END INVOKE API TEST"
        }
    }
}

def apiCall(String url, String method, String data) {
    sh 'pwd'
    def response = sh(returnStdout: true, script: 'curl -X POST -d \'${data}\' -H \'Authorization:Token 0418bfa3937504586f4a0ea80c9fffb9\' \"${url}\"')

    script.sh "apiCall response: ${response}"
}

def urlBuilder(String contextPath, String apiURL) {
    return "${APIReqBuilder.HTTPS}${contextPath}${apiURL}"
}

