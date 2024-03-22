package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime date = LocalDateTime.now();
    private Boolean completed;
}
