package vars


import constants.APIGroovy
import utility.APIReqBuilder
import utility.FileUtils

@Grab(group = 'com.github.groovy-wslite', module = 'groovy-wslite', version = '1.1.3')


def call(Map config) {
    node {
        stage('Clean Workspace') {
            sh 'rm -rf *'
        }
        stage('Checkout') {
            checkout scm
        }

        stage("Upload Request.zip file (*-req.json)") {
            def inputFile = input message: 'Please provide a file', parameters: [base64File('file')]
            withEnv(["fileBase64=$inputFile"]) {
                if (inputFile != null) {
                    sh 'echo $fileBase64 | base64 -d > request.zip'
                } else {
                    sh 'echo required file not provided.  throw error'
                    throw new Exception("required request.zip file not provided.  throw error")
                }
            }
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
            if (fileExists(file: "${FileUtils.FOLDER}/${APIGroovy.TEST_API.reqFile}")) {
                println "START INVOKE API TEST"
                println "whereami"
                println "pwd"
                try {
                    apiCall(urlBuilder(APIGroovy.TEST_CONTEXT_PATH.apiURL, APIGroovy.TEST_API.apiURL, null),
                            "POST", APIReqBuilder.dataReqBuilder(this, "${FileUtils.FOLDER}/${APIGroovy.TEST_API.reqFile}", APIGroovy.TEST_API.id))
                } catch (Exception e) {
                    println e.getMessage()
                }
                println "END INVOKE API TEST"
            } else {
                println "No request file found. Skip API Test"
            }
        }

        stage('Invoke CPU Fault') {
            if (fileExists(file: "${FileUtils.FOLDER}/${APIGroovy.CPU_FAULT_API.reqFile}")) {
                println "START INVOKE CPU FAULT"
                println "whereami"
                println "pwd"
                try {
                    apiCall(urlBuilder(APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.CPU_FAULT_API.apiURL, APIReqBuilder.IP),
                            "POST", APIReqBuilder.dataReqBuilder(this, APIGroovy.CPU_FAULT_API.reqFile, APIGroovy.CPU_FAULT_API.id))
                } catch (Exception e) {
                    println e.getMessage()
                }
                println "END INVOKE CPU FAULT"
            } else {
                println "No request file found. Skip CPU FAULT"
            }
        }

        stage('Invoke Memory Fault') {
            if (fileExists(file: "${FileUtils.FOLDER}/${APIGroovy.MEMORY_FAULT_API.reqFile}")) {
                println "START INVOKE MEMORY FAULT"
                println "whereami"
                println "pwd"
                try {
                    apiCall(urlBuilder(APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.MEMORY_FAULT_API.apiURL, APIReqBuilder.IP),
                            "POST", APIReqBuilder.dataReqBuilder(this, APIGroovy.MEMORY_FAULT_API.reqFile, APIGroovy.MEMORY_FAULT_API.id))
                } catch (Exception e) {
                    println e.getMessage()
                }
                println "END INVOKE MEMORY FAULT"
            } else {
                println "No request file found. Skip MEMORY FAULT"
            }
        }

        stage('Invoke DISK-IO Fault') {
            if (fileExists(file: "${FileUtils.FOLDER}/${APIGroovy.DISK_IO_FAULT_API.reqFile}")) {
                println "START INVOKE DISK-IO FAULT"
                println "whereami"
                println "pwd"
                try {
                    apiCall(urlBuilder(APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.DISK_IO_FAULT_API.apiURL, APIReqBuilder.IP),
                            "POST", APIReqBuilder.dataReqBuilder(this, APIGroovy.DISK_IO_FAULT_API.reqFile, APIGroovy.DISK_IO_FAULT_API.id))
                } catch (Exception e) {
                    println e.getMessage()
                }
                println "END INVOKE DISK-IO FAULT"
            } else {
                println "No request file found. Skip DISK-IO FAULT"
            }
        }

        stage('Invoke DISK-SPACE Fault') {
            if (fileExists(file: "${FileUtils.FOLDER}/${APIGroovy.DISK_SPACE_FAULT_API.reqFile}")) {
                println "START INVOKE DISK-SPACE FAULT"
                println "whereami"
                println "pwd"
                try {
                    apiCall(urlBuilder(APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.DISK_SPACE_FAULT_API.apiURL, APIReqBuilder.IP),
                            "POST", APIReqBuilder.dataReqBuilder(this, APIGroovy.DISK_SPACE_FAULT_API.reqFile, APIGroovy.DISK_SPACE_FAULT_API.id))
                } catch (Exception e) {
                    println e.getMessage()
                }
                println "END INVOKE DISK-SPACE FAULT"
            } else {
                println "No request file found. DISK-SPACE FAULT"
            }
        }
    }
}

def apiCall(String url, String method, String data) {
    sh 'pwd'
    println(" Invoking API url:  ${url} ")
    println(" Invoking API method: ${method} ")
    println(" Invoking API data:  ${data} ")
    def response = sh(returnStdout: true, script: 'curl -X ${method} -d ${data} -H \'Authorization:Token 0418bfa3937504586f4a0ea80c9fffb9\' ${url}')

    sh "apiCall response: ${response}"
}

def urlBuilder(String contextPath, String apiURL, String host) {
    if (host != null) {
        def url = "${APIReqBuilder.HTTPS}${host}${contextPath}${apiURL}"
        sh "echo url: ${url}"
        return "${url}"
    } else {
        def url = "${APIReqBuilder.HTTPS}${contextPath}${apiURL}"
        sh "echo url: ${url}"
        return "${url}"
    }
}

