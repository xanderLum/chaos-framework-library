package vars

import constants.*
import utility.*

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