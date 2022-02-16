package com.hepsiemlak.todo.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateToDoItemRequest {

    @NotNull
    @NotEmpty
    private String id;

    @NotNull
    @NotEmpty
    private String title;

    private Boolean status;
}
