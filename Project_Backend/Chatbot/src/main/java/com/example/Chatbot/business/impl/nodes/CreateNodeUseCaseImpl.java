package com.example.Chatbot.business.impl.nodes;

import com.example.Chatbot.business.nodes.CreateNodeUseCase;
import com.example.Chatbot.domain.nodes.CreateNodeRequest;
import com.example.Chatbot.domain.nodes.CreateNodeResponse;
import com.example.Chatbot.repository.NodeRepository;
import com.example.Chatbot.repository.RatingRepository;
import com.example.Chatbot.repository.impl.NodeEntity;
import com.example.Chatbot.repository.impl.RatingEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CreateNodeUseCaseImpl implements CreateNodeUseCase {
    private final NodeRepository nodeRepository;
    @Transactional
    @Override
    public CreateNodeResponse createNode(CreateNodeRequest request) {
        NodeEntity savedNode= saveNewNode(request);

        return CreateNodeResponse.builder()
                .nodeId(savedNode.getId())
                .build();
    }
    private NodeEntity saveNewNode(CreateNodeRequest request){
        NodeEntity newNode = NodeEntity.builder()
                .answer(request.getAnswer())
                .nodeText(request.getNodeText())
                .vraag(request.getVraag())
                .antwoord(request.getAntwoord())
                .parentId(request.getParentId())
                .build();

        newNode.setRating(RatingEntity.builder()
                        .id(newNode.getId())
                .timesReached(0)
                .timesSatisfied(0)
                .timesUnsatisfied(0)
                .node(newNode).build());

        return nodeRepository.save(newNode);
    }

}