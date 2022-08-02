package utility

class FileUtils {

    static String FILEPARAM = "request.zip"

    static def processFileParam(script) {
        script.sh 'echo from scrip.sh'
        println "from println whereami pwd"

        script.withFileParameter(FILEPARAM) {
            script.sh 'ls -lrt'
        }

        if (script.fileExists(file: FILEPARAM)) {
            script.sh 'unzip request.zip'
            script.sh 'ls -lrt'
        } else {
            println "Fileparam doesn't exist!"
        }
    }
}
