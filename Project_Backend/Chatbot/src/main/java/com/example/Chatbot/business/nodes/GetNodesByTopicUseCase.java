package com.example.Chatbot.business.nodes;

import com.example.Chatbot.domain.nodes.GetNodesByTopicRequest;
import com.example.Chatbot.domain.nodes.GetNodesByTopicResponse;

public interface GetNodesByTopicUseCase {
    GetNodesByTopicResponse getNodesByTopic(GetNodesByTopicRequest request);
}
