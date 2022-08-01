package constants

class MangleAPIs {
    public static String MANGLE_PORTAL_CONTEXT="/mangle-services"
    public static String CPU_FAULT_API="/rest/api/v1/faults/cpu"
    public static String MEMORY_FAULT_API="/rest/api/v1/faults/memory"
    public static String DISK_IO_FAULT_API="/rest/api/v1/faults/diskIO"
    public static String DISK_SPACE_FAULT_API="/rest/api/v1/faults/disk-space"
    public static String KERNEL_PANIC_FAULT_API="/rest/api/v1/faults/kernel-panic"
    public static String FILE_HANDLER_LEAK_FAULT_API="/rest/api/v1/faults/filehandler-leak"
    public static String CLOCKSKEW_FAULT_API="/rest/api/v1/faults/clockSkew"
    public static String NETWORK_FAULT="/rest/api/v1/faults/network-fault"

    public static String TEST_API="/__admin/mappings"
    public static String TEST_CONTEXT_PATH="/xander.mocklab.io"
}
