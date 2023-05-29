package com.example.Chatbot.domain.nodes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Node {
    private Long id;
    private String nodeText;
    private String answer;
    private String vraag;
    private String antwoord;
    private Long parentId;
}
