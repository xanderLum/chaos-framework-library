package vars

import constants.*
import utility.*
//import static utility.APIReqBuilder

def call() {
//    pipeline {
        stage('Invoke test API') {
//            script {
                println("I am in the test api stage")
                APIReqBuilder.callAPI this, API.TEST_API
//            }
        }
        post {
            always {
                script {
                    echo 'end'
                }
            }
        }
//    }
}

return this