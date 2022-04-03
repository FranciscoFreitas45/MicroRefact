package com.sda.inTeams.DTO;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum TaskStatus {
    NOT_STARTED(1, "Not started"),
    WORK_IN_PROGRESS(2, "Work in progress"),
    PENDING_VERIFICATION(3, "Pending verification"),
    FINISHED(4, "Finished");

    private final long id;
    private final String textRepresentation;

    public long getId() {
        return id;
    }

    public String getTextRepresentation() {
        return textRepresentation;
    }

    TaskStatus(long id, String textRepresentation) {
        this.id = id;
        this.textRepresentation = textRepresentation;
    }

    private static final Map<Long, TaskStatus> byId = new HashMap<>();
    static {
        Arrays.stream(TaskStatus.values()).forEach(ps -> byId.put(ps.getId(), ps));
    }

    public static TaskStatus getById(Long id) {
        return byId.get(id);
    }

}
