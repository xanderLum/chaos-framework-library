package vars

import constants.*
import utility.*

//@Grab('com.github.groovy-wslite:groovy-wslite:1.1.3')

def call() {
    pipeline {
            stage('Invoke test API') {
                    script {
                        echo 'I am in the test api stage'
                        APIReqBuilder.callAPI this, API.TEST_API
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