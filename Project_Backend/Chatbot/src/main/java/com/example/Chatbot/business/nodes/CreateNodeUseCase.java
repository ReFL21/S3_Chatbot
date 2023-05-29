package com.example.Chatbot.business.nodes;

import com.example.Chatbot.domain.nodes.CreateNodeRequest;
import com.example.Chatbot.domain.nodes.CreateNodeResponse;

public interface CreateNodeUseCase {
    CreateNodeResponse createNode(CreateNodeRequest request);
}
