package constants;

enum APIGroovy {
    MANGLE_PORTAL_CONTEXT("mangle-context", "/mangle-services"),
    CPU_FAULT_API("cpu", "/rest/api/v1/faults/cpu"),
    MEMORY_FAULT_API("memory", "/rest/api/v1/faults/memory"),
    DISK_IO_FAULT_API("disk-io", "/rest/api/v1/faults/diskIO"),
    DISK_SPACE_FAULT_API("disk-space", "/rest/api/v1/faults/disk-space"),
    KERNEL_PANIC_FAULT_API("kernel-panic", "/rest/api/v1/faults/kernel-panic"),
    FILE_HANDLER_LEAK_FAULT_API("filehandler-leak", "/rest/api/v1/faults/filehandler-leak"),
    CLOCKSKEW_FAULT_API("clockskew", "/rest/api/v1/faults/clockSkew"),
    NETWORK_FAULT("network", "/rest/api/v1/faults/network-fault"),

    TEST_API("test-api", "/__admin/mappings"),
    TEST_CONTEXT_PATH("test-context", "xander.mocklab.io");

    APIGroovy(String id, String apiURL) {
        this.id = id;
        this.apiURL = apiURL;
    }

    final String id;
    final String apiURL;

    static final Map map

    static {
        map = [:] as TreeMap
        values().each { api ->
            println "id: " + api.getId() + ", apiURL:" + api.getApiURL()
            map.put(api.id, api)
        }

    }

    static getApiEnum(id) {
        map[id]
    }
}