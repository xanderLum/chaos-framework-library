package constants;

enum APIGroovy {
    MANGLE_PORTAL_CONTEXT("/mangle-services"),
    CPU_FAULT_API("/rest/api/v1/faults/cpu"),
    MEMORY_FAULT_API("/rest/api/v1/faults/memory"),
    DISK_IO_FAULT_API("/rest/api/v1/faults/diskIO"),
    DISK_SPACE_FAULT_API("/rest/api/v1/faults/disk-space"),
    KERNEL_PANIC_FAULT_API("/rest/api/v1/faults/kernel-panic"),
    FILE_HANDLER_LEAK_FAULT_API("/rest/api/v1/faults/filehandler-leak"),
    CLOCKSKEW_FAULT_API("/rest/api/v1/faults/clockSkew"),
    NETWORK_FAULT("/rest/api/v1/faults/network-fault"),

    TEST_API("/__admin/mappings"),
    TEST_CONTEXT_PATH("xander.mocklab.io");


    APIGroovy(String string) {
    }

//    String apiName;
    String apiURL;

//    Map map;
}