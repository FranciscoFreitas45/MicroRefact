package com.sda.inTeams.model.Comment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum CommentType {
    QUESTION(1, "Question"),
    ANSWER(2,"Answer"),
    BUG_FOUND(3,"Bug found"),
    BUG_FIX(4,"Bug fix");

    private final long id;
    private final String textRepresentation;

    public long getId() {
        return id;
    }

    public String getTextRepresentation() {
        return textRepresentation;
    }

    CommentType(long id, String textRepresentation) {
        this.id = id;
        this.textRepresentation = textRepresentation;
    }

    private static final Map<Long,CommentType> byId = new HashMap<>();
    static {
        Arrays.stream(CommentType.values()).forEach(ct -> byId.put(ct.getId(),ct));
    }

    public static CommentType getById(Long id) {
        return byId.get(id);
    }
}
