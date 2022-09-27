package utility.VMWareMangle

import com.fasterxml.jackson.annotation.JsonProperty

class TaskAPIResponseBaseResolver implements Serializable {
    def id
    def taskName
    def taskType
    def taskDescription

    @JsonProperty("triggers")
    def TaskAPIResponseTriggerResolver[] triggerList

    @JsonProperty("taskData")
    def TaskAPIResponseTaskDataResolver
}
