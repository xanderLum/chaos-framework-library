package utility.VMWareMangle

class TaskAPIResponseTaskDataResolver implements Serializable {
    def id
    def schedule
    def specType
    def readyForChildExecution
    def childSpecType
    def faultSpec
    def endpointName

    def taskSubstage
}
