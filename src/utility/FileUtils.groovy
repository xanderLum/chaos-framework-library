package utility

class FileUtils {

    static String FILEPARAM = "request.zip"
//    static String FOLDER = "request"
    static String FOLDER = "src/api/input"

    /**
     * Process File Parameter request.zip file containing:
     * *-req.json files for different type of fault injection API requests
     *
     * @param script
     * @return
     */
    static def processFileParam(script) {
        script.sh 'echo whereami'
        script.sh 'pwd'

        if (script.fileExists(file: "${FILEPARAM}")) {
            script.sh "echo Fileparam exist!"
            script.sh "echo unzipping... ${FILEPARAM}"
            script.sh "unzip ${FILEPARAM}"
            script.sh 'ls'

            determineFaults(script, FOLDER).each {
                script.sh "echo content: ${FOLDER}/${it} "
                script.sh "more ${FOLDER}/${it} "
            }
        } else {
            script.sh "echo Fileparam doesn't exist!"
        }
    }

    /**
     * Check Faults to be executed
     *
     * @param script
     * @param folder
     * @return
     */
    static def determineFaults(script, folder) {
        def foundFiles = script.sh(script: "ls -1 ${folder}", returnStdout: true).split()
        return foundFiles
    }
}
