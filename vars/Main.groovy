package vars

import constants.*
import utility.*

//import static utility.APIReqBuilder

def call() {
    println("I am in the test api stage")
    APIReqBuilder.callAPI this, API.TEST_API
}

return this