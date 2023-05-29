package com.example.Chatbot.business.impl.nodes;

import com.example.Chatbot.business.nodes.GetNodesByTopicUseCase;
import com.example.Chatbot.domain.nodes.GetNodesByTopicResponse;
import com.example.Chatbot.domain.nodes.GetNodesByTopicRequest;
import com.example.Chatbot.domain.nodes.Node;
import com.example.Chatbot.repository.NodeRepository;
import com.example.Chatbot.repository.impl.NodeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetNodesByTopicUseCaseImpl implements GetNodesByTopicUseCase {
    private final NodeRepository nodeRepository;
    @Override
    public GetNodesByTopicResponse getNodesByTopic(final GetNodesByTopicRequest request) {
        List<NodeEntity> results;
        try {
            results=nodeRepository.findAll();
            if (!request.getTopicString().isEmpty())
            {
                List<NodeEntity> foundMatches= new ArrayList<>();
                for (String topic : request.getTopicString()) {
                    foundMatches.addAll(nodeRepository.findAllByNodeTextContainingIgnoreCaseOrVraagContainingIgnoreCase(topic,topic));
                }
                results=foundMatches;
            }
        }
        catch (Exception e)
        {
            results = nodeRepository.findAll();
        }
        final GetNodesByTopicResponse response= new GetNodesByTopicResponse();
        List<Node> nodes= results
                .stream()
                .map(NodeConverter::convert)
                .toList();
        response.setNodes(nodes);
        return response;
    }
}
