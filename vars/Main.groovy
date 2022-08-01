package vars

import constants.API
import utility.APIReqBuilder

//@Grab('com.github.groovy-wslite:groovy-wslite:1.1.3')

def call(Map config) {
    pipeline {
        agent { label config.nodeName }
        /*triggers {
  //      cron('0 9,12,15 * * 1-5')
            //trigger in 12nn and 6pm daily Monday to Friday
            cron('0 12,18 * * 1-5')
        }*/
        options {
            buildDiscarder(logRotator(numToKeepStr: config.numBuildToKeep))
            skipDefaultCheckout()
            disableConcurrentBuilds()
        }
        stages {
            stage('Clean Workspace') {
                steps {
                    script {
                        sh 'rm -rf *'
                    }
                }
            }

            stage('Checkout') {
                steps {
                    script {
                        checkout scm
                    }
                }
            }
            stage('Invoke test API') {
                steps {
                    script {
                        echo 'I am in the test api stage'
                        APIReqBuilder.callAPI this, API.TEST_API

                    }
                }
            }
        }
        post {
            always {
                script {
                    echo 'end'
                }
            }
        }
    }
}

return this