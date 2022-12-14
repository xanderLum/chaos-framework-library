import constants.APIGroovy
import constants.Credentials
import utility.APIReqBuilder
import utility.FileUtils

/**
 * Ochestrator main
 *
 * @param config
 * @return
 */
def call(Map config) {
    node {
        stage('Clean Workspace') {
            cleanWs()
        }
        stage('Checkout') {
            checkout scm
        }

        /*stage("Upload Request.zip file (*-req.json)") {
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
        }*/

        stage("Executing selected faults...") {
            echo "params is 'All' selected? : ${params.All}"
            echo "params is 'CPU' selected? : ${params.CPU}"
            echo "params is 'Memory' selected? : ${params.Memory}"
            echo "params is 'Disk-IO' selected? : ${params.DiskIO}"
            echo "params is 'Diskspace' selected? : ${params.Diskspace}"
            echo "params is 'TEST' selected? : ${params.TEST}"
        }

        stage('Invoke API Test') {
            if (fileExists(file: "${FileUtils.FOLDER}/${APIGroovy.TEST_API.reqFile}") && (params.ALL == true || params.TEST == true)) {
                println "START INVOKE API TEST"
                println "whereami"
                println "pwd"
                try {
                    apiCall(urlBuilder(APIGroovy.TEST_CONTEXT_PATH.apiURL, APIGroovy.TEST_API.apiURL, null),
                            "POST", APIReqBuilder.dataReqBuilder(this.WORKSPACE, "${FileUtils.FOLDER}/${APIGroovy.TEST_API.reqFile}"))
                } catch (Exception e) {
                    println e.getMessage()
                }
                println "END INVOKE API TEST"
            } else {
                println "No request file found. Skip API Test"
            }
        }

        stage('Invoke CPU Fault') {
            if (fileExists(file: "${FileUtils.FOLDER}/${APIGroovy.CPU_FAULT_API.reqFile}") && (params.ALL == true || params.CPU == true)) {
                println "START INVOKE CPU FAULT"
                println "whereami"
                println "pwd"
                try {
                    apiCall(urlBuilder(APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.CPU_FAULT_API.apiURL, APIReqBuilder.IP),
                            "POST", APIReqBuilder.dataReqBuilder(this.WORKSPACE, "${FileUtils.FOLDER}/${APIGroovy.CPU_FAULT_API.reqFile}"))
                } catch (Exception e) {
                    println e.getMessage()
                }
                println "END INVOKE CPU FAULT"
            } else {
                println "No request file found. Skip CPU FAULT"
            }
        }

        stage('Invoke Memory Fault') {
            if (fileExists(file: "${FileUtils.FOLDER}/${APIGroovy.MEMORY_FAULT_API.reqFile}") && (params.ALL == true || params.Memory == true)) {
                println "START INVOKE MEMORY FAULT"
                println "whereami"
                println "pwd"
                try {
                    apiCall(urlBuilder(APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.MEMORY_FAULT_API.apiURL, APIReqBuilder.IP),
                            "POST", APIReqBuilder.dataReqBuilder(this.WORKSPACE, "${FileUtils.FOLDER}/${APIGroovy.MEMORY_FAULT_API.reqFile}"))
                } catch (Exception e) {
                    println e.getMessage()
                }
                println "END INVOKE MEMORY FAULT"
            } else {
                println "No request file found. Skip MEMORY FAULT"
            }
        }

        stage('Invoke DISK-IO Fault') {
            if (fileExists(file: "${FileUtils.FOLDER}/${APIGroovy.DISK_IO_FAULT_API.reqFile}") && (params.ALL == true || params.DiskIO == true)) {
                println "START INVOKE DISK-IO FAULT"
                println "whereami"
                println "pwd"
                try {
                    apiCall(urlBuilder(APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.DISK_IO_FAULT_API.apiURL, APIReqBuilder.IP),
                            "POST", APIReqBuilder.dataReqBuilder(this.WORKSPACE, "${FileUtils.FOLDER}/${APIGroovy.DISK_IO_FAULT_API.reqFile}"))
                } catch (Exception e) {
                    println e.getMessage()
                }
                println "END INVOKE DISK-IO FAULT"
            } else {
                println "No request file found. Skip DISK-IO FAULT"
            }
        }

        stage('Invoke DISK-SPACE Fault') {
            if (fileExists(file: "${FileUtils.FOLDER}/${APIGroovy.DISK_SPACE_FAULT_API.reqFile}") && (params.ALL == true || params.Diskspace == true)) {
                println "START INVOKE DISK-SPACE FAULT"
                println "whereami"
                println "pwd"
                try {
                    apiCall(urlBuilder(APIGroovy.MANGLE_PORTAL_CONTEXT.apiURL, APIGroovy.DISK_SPACE_FAULT_API.apiURL, APIReqBuilder.IP),
                            "POST", APIReqBuilder.dataReqBuilder(this.WORKSPACE, "${FileUtils.FOLDER}/${APIGroovy.DISK_SPACE_FAULT_API.reqFile}"))
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

/**
 * Execute API call
 *
 * @param url
 * @param method
 * @param data
 * @return
 */
def apiCall(String url, String method, String data) {
    sh 'pwd'
    println(" Invoking API url:  ${url} ")
    println(" Invoking API method: ${method} ")
    println(" Invoking API data:  ${data} ")

    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: Credentials.mangleServiceAccount, usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
        println(" username: ${env.USERNAME} ")
        println(" password: ${env.PASSWORD} ")

        def response = sh(returnStdout: true, script: "curl -kv ${method} -d \'${data}\' " +
                "--user ${env.USERNAME}:${env.PASSWORD} -H 'Content-Type: application/json' ${url}").trim()
        echo "${response}"
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

