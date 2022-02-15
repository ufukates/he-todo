package com.hepsiemlak.todo.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.index.QueryIndexed;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import java.util.List;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Data
@Document
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = UNIQUE)
    private String id;

    @QueryIndexed
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
