package com.example.Chatbot.business.node;

import com.example.Chatbot.business.impl.nodes.CreateNodeUseCaseImpl;
import com.example.Chatbot.domain.nodes.CreateNodeRequest;
import com.example.Chatbot.domain.nodes.CreateNodeResponse;
import com.example.Chatbot.repository.NodeRepository;
import com.example.Chatbot.repository.RatingRepository;
import com.example.Chatbot.repository.impl.NodeEntity;
import com.example.Chatbot.repository.impl.RatingEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateNodeUseCaseImplTest {
    @Mock
    NodeRepository nodeRepository;

    @InjectMocks
    CreateNodeUseCaseImpl createNodeUseCase;

    //@Test
    void saveNode_shouldReturn200(){
        NodeEntity expectedNode = NodeEntity.builder()
                .id(null)
                .nodeText("Where?")
                .answer("Here")
                .vraag("dutchQuestion")
                .antwoord("dutchAnswer")
                .parentId(1L)
                .build();
        expectedNode.setRating(RatingEntity.builder()
                .id(null)
                .timesSatisfied(0)
                .timesUnsatisfied(0)
                .timesReached(0)
                .node(expectedNode)
                .build());

        NodeEntity savedNode = NodeEntity.builder()
                .id(1L)
                .nodeText("Where?")
                .answer("Here")
                .vraag("dutchQuestion")
                .antwoord("dutchAnswer")
                .parentId(1L)
                .build();
        savedNode.setRating(RatingEntity.builder()
                .id(1L)
                .timesSatisfied(0)
                .timesUnsatisfied(0)
                .timesReached(0)
                .node(savedNode)
                .build());

        lenient().when(nodeRepository.save(expectedNode)).thenReturn(savedNode);


        CreateNodeRequest createNodeRequest = CreateNodeRequest.builder()
                .nodeText("Where?")
                .answer("Here")
                .vraag("dutchQuestion")
                .antwoord("dutchAnswer")
                .parentId(1L)
                .build();

        CreateNodeResponse actualResponse = createNodeUseCase.createNode(createNodeRequest);
        CreateNodeResponse expectedResponse = CreateNodeResponse.builder().nodeId(1L).build();

        assertEquals(expectedResponse, actualResponse);
        verify(nodeRepository).save(expectedNode);
    }
}
