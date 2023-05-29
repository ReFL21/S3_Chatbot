package com.example.Chatbot.business.nodes;

import com.example.Chatbot.domain.nodes.GetNodesRequest;
import com.example.Chatbot.domain.nodes.GetNodesResponse;

public interface GetNodesUseCase {
    GetNodesResponse getNodes(GetNodesRequest request);
}
