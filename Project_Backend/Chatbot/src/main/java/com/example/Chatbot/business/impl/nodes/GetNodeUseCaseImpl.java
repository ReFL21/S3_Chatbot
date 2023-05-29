package com.example.Chatbot.business.impl.nodes;

import com.example.Chatbot.business.nodes.GetNodeUseCase;
import com.example.Chatbot.domain.nodes.Node;
import com.example.Chatbot.repository.NodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetNodeUseCaseImpl implements GetNodeUseCase {
    private final NodeRepository nodeRepository;
    @Override
    public Optional<Node> getNode(Long nodeId){
        return nodeRepository.findById(nodeId).map(NodeConverter::convert);
    }
}
