package com.example.Chatbot.business.impl.nodes;

import com.example.Chatbot.business.nodes.DeleteNodeUseCase;
import com.example.Chatbot.repository.NodeRepository;
import com.example.Chatbot.repository.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteNodeUseCaseImpl implements DeleteNodeUseCase {
    private final NodeRepository nodeRepository;
    @Override
    public void deleteNode(long nodeId){
        this.nodeRepository.deleteById(nodeId);
    }
}
