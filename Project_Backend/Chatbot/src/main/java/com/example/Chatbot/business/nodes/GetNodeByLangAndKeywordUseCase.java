package com.example.Chatbot.business.nodes;

import com.example.Chatbot.domain.nodes.Node;

import java.util.Optional;

public interface GetNodeByLangAndKeywordUseCase {
    Optional<Node> getNode(String keyword, String language);
}
