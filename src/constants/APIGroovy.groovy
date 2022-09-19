package constants;

enum APIGroovy {
    MANGLE_PORTAL_CONTEXT("mangle-context", "/mangle-services", null),
    CPU_FAULT_API("cpu", "/rest/api/v1/faults/cpu", "cpu-req.json"),
    MEMORY_FAULT_API("memory", "/rest/api/v1/faults/memory", "memory-req.json"),
    DISK_IO_FAULT_API("disk-io", "/rest/api/v1/faults/diskIO", "disk-io-req.json"),
    DISK_SPACE_FAULT_API("disk-space", "/rest/api/v1/faults/disk-space", "disk-space-req.json"),
    KERNEL_PANIC_FAULT_API("kernel-panic", "/rest/api/v1/faults/kernel-panic", "kernel-panic-req.json"),
    FILE_HANDLER_LEAK_FAULT_API("filehandler-leak", "/rest/api/v1/faults/filehandler-leak", "filehandler-leak-req.json"),
    CLOCKSKEW_FAULT_API("clockskew", "/rest/api/v1/faults/clockSkew", "clockskew-req.json"),
    NETWORK_FAULT("network", "/rest/api/v1/faults/network-fault", "network-req.json"),
    KILL_PROCESS_FAULT("kill-process", "/rest/api/v1/faults/kill-process", "kill-process-req.json"),
    GET_TASK_DETAILS_BY_TASKID("get-taskDetailsByTaskID", "/rest/api/v1/tasks/{taskId}", null),

    TEST_API("test-api", "/__admin/mappings", "test-req.json"),
    TEST_CONTEXT_PATH("test-context", "xander.mocklab.io", null);

    APIGroovy(String id, String apiURL, String reqFile) {
        this.id = id;
        this.apiURL = apiURL;
        this.reqFile = reqFile;
    }

    final String id;
    final String apiURL;
    final String reqFile;

    static final Map map

    static {
        map = [:] as TreeMap
        values().each { api ->
            println "id: " + api.getId() + ", apiURL:" + api.getApiURL() + ", reqFile: " + api.getReqFile()
            map.put(api.id, api)
        }

    }

    static getApiEnum(id) {
        map[id]
    }
}