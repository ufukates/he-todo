package com.hepsiemlak.todo.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDoItemResponse {

    private String id;

    private String todoId;

    private String title;

    private boolean status;
}
