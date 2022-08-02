package utility

class FileUtils {

    static String FILEPARAM = "request.zip"

    static def processFileParam(script) {
        script.sh "echo from scrip.sh: pwd()"
        script.sh "echo fileparam: ${FILEPARAM}"
        script.withFileParameter("${FILEPARAM}") {
            script.sh 'ls -lrt'
        }

        if (script.fileExists(file: "${FILEPARAM}")) {
            script.sh "echo unzipping... ${FILEPARAM}"
            script.sh "unzip ${FILEPARAM}"
            script.sh 'ls -lrt'
        } else {
            script.sh "echo Fileparam doesn't exist!"
        }
    }
}
