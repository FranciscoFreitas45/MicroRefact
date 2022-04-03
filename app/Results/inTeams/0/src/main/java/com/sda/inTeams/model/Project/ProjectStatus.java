package com.sda.inTeams.model.Project;

import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum ProjectStatus {
    NOT_STARTED(1, "Not started"),
    STARTED(2, "Started"),
    FINISHED(3, "Finished");

    private final long id;
    private final String textRepresentation;

    public long getId() {
        return id;
    }

    public String getTextRepresentation() {
        return textRepresentation;
    }

    ProjectStatus(long id, String textRepresentation) {
        this.id = id;
        this.textRepresentation = textRepresentation;
    }

    private static final Map<Long, ProjectStatus> byId = new HashMap<>();
    static {
        Arrays.stream(ProjectStatus.values()).forEach(ps -> byId.put(ps.getId(), ps));
    }

    public static ProjectStatus getById(Long id) {
        return byId.get(id);
    }

}
