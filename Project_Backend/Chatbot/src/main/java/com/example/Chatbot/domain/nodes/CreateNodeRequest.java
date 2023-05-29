package com.example.Chatbot.domain.nodes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNodeRequest {
    @NotBlank
    private String nodeText;
    @NotBlank
    private String answer;

    @NotBlank
    private String vraag;
    @NotBlank
    private String antwoord;

    @Nullable
    private Long parentId;
}
