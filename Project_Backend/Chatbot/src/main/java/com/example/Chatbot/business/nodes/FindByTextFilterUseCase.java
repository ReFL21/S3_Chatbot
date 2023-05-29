package com.example.Chatbot.business.nodes;

import com.example.Chatbot.domain.nodes.FindByTextFilterRequest;
import com.example.Chatbot.domain.nodes.FindByTextFilterResponse;

public interface FindByTextFilterUseCase {
     FindByTextFilterResponse findByTextFilter(FindByTextFilterRequest request);
}
