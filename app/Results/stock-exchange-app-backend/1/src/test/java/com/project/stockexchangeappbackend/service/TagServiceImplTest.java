package com.project.stockexchangeappbackend.service;

import com.project.stockexchangeappbackend.DTO.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    @BeforeEach
    void setup() {
        setTagsList();
    }

    private static List<Tag> tags;

    public static void assertTag(Tag expected, Tag output) {
        assertAll(() -> assertEquals(expected.getId(), output.getId()),
                () -> assertEquals(expected.getName(), output.getName()));
    }

    public static List<Tag> getTagsList() {
        if (tags == null) {
            setTagsList();
        }
        return tags;
    }

    private static void setTagsList() {
        tags = Arrays.asList(
                new Tag(1L, "DEFAULT"),
                new Tag(2L, "TEST")
        );
    }

}
