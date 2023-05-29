package com.example.Chatbot.business.impl.nodes;

import com.example.Chatbot.business.nodes.GetNodeByLangAndKeywordUseCase;
import com.example.Chatbot.domain.nodes.Node;
import com.example.Chatbot.repository.NodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetNodeByLangAndKeywordUseCaseImpl implements GetNodeByLangAndKeywordUseCase {
    private final NodeRepository nodeRepository;

    @Override
    public Optional<Node> getNode(String keyword, String language) {
        if(language.equals("nl"))
            return nodeRepository.findByVraagContainingIgnoreCase(keyword).map(NodeConverter::convert);
        return nodeRepository.findByNodeTextContainingIgnoreCase(keyword).map(NodeConverter::convert);
    }
}
