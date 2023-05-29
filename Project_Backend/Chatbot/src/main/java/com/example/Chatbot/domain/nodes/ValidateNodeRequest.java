package com.example.Chatbot.domain.nodes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidateNodeRequest {
    private Long id;
    @NotNull
    private int yes;
    @NotNull
    private int no;
    @NotNull
    private int count;
}
