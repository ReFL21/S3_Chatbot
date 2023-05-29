package com.example.Chatbot.business.nodes;

import com.example.Chatbot.domain.nodes.Node;

import java.util.Optional;

public interface GetNodeUseCase {
    Optional<Node> getNode(Long nodeId);
}
