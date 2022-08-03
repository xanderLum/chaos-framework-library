package utility

@Grab(group = 'com.cloudbees', module = 'groovy-cps', version = '1.24')
import com.cloudbees.groovy.cps.NonCPS

class APIReqBuilder implements Serializable {
    static String HTTPS = "https://";
    static String IP = "10.11.57.125"; //change to variable in pipeline

    /**
     * Build Data request for the API Injection faults
     *
     * @param workspace
     * @param reqFile
     * @return
     */
    @NonCPS
    static def dataReqBuilder(String workspace, reqFile) {
        def form = new StringBuilder()
        StringBuilder sb = new StringBuilder()
        sb.append(workspace + "/" + "${reqFile}")
        new File(sb.toString()).withInputStream { stream ->
            stream.eachLine { line ->
                form.append(line)
            }
        }
        return form.toString()
    }
}
