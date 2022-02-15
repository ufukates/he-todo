package com.hepsiemlak.todo.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import javax.validation.constraints.NotNull;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Data
@Document
@Builder
public class TodoItem {

    @Id
    @GeneratedValue(strategy = UNIQUE)
    private String id;

    @NotNull
    private String title;

    private Boolean status;

    private String todoId;
}
