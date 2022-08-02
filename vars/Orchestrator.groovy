package vars


import constants.APIGroovy
import utility.APIReqBuilder

@Grab(group = 'com.github.groovy-wslite', module = 'groovy-wslite', version = '1.1.3')

/*
def call() {
    println("I am in the test api stage")
    APIReqBuilder.callAPI this, API.TEST_API
}

return this*/

def call(Map config) {
    node {
        stage('Clean Workspace') {
            sh 'rm -rf *'
        }

        stage('Checkout') {

            checkout scm
        }

        stage('Invoke API Test') {
            println("I am in the test api stage")
            println("printing pwd: " + pwd())
            APIReqBuilder.callAPI(this, APIGroovy.TEST_API.id)
        }
    }
}
