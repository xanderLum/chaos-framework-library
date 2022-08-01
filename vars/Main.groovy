package vars


import constants.API
import utility.APIReqBuilder

//@Grab('com.github.groovy-wslite:groovy-wslite:1.1.3')
def call() {
    pipeline {
        stages {
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