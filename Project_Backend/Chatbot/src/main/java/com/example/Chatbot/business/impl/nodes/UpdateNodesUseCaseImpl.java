package com.example.Chatbot.business.impl.nodes;

import com.example.Chatbot.business.nodes.UpdateNodesUseCase;
import com.example.Chatbot.domain.nodes.UpdateNodeRequest;
import com.example.Chatbot.repository.NodeRepository;
import com.example.Chatbot.repository.impl.NodeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateNodesUseCaseImpl implements UpdateNodesUseCase {
    private final NodeRepository nodeRepository;
    @Override
    public void updateNode(UpdateNodeRequest request){
        Optional<NodeEntity> nodeOptional = nodeRepository.findById(request.getId());
        NodeEntity node = nodeOptional.get();
        updateFields(request,node);
    }
    private void updateFields(UpdateNodeRequest request, NodeEntity node){
        node.setAnswer(request.getAnswer());
        node.setNodeText(request.getNodeText());
        node.setVraag(request.getVraag());
        node.setAntwoord(request.getAntwoord());
        node.setParentId(request.getParentId());
        nodeRepository.save(node);
    }
}
