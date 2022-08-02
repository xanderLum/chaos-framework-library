package vars


import constants.APIGroovy
import utility.APIReqBuilder

/*
def call() {
    println("I am in the test api stage")
    APIReqBuilder.callAPI this, API.TEST_API
}

return this*/

def call(Map config) {
    pipeline {
        stage('Clean Workspace') {
            sh 'rm -rf *'
        }

        stage('Checkout') {

            checkout scm
        }

        stage('Invoke API Test') {
            println("I am in the test api stage")
            APIReqBuilder.callAPI this, APIGroovy.TEST_API
        }
    }
    post {
        always {
            script {
                println("end!")
            }
        }
    }
}
