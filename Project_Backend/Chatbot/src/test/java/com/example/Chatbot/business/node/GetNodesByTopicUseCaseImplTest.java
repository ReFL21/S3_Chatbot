package com.example.Chatbot.business.node;

import com.example.Chatbot.business.impl.nodes.GetNodesByTopicUseCaseImpl;
import com.example.Chatbot.business.nodes.GetNodesByTopicUseCase;
import com.example.Chatbot.domain.nodes.*;
import com.example.Chatbot.repository.NodeRepository;
import com.example.Chatbot.repository.impl.NodeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetNodesByTopicUseCaseImplTest {
    @Mock
    NodeRepository nodeRepository;
    @InjectMocks
    GetNodesByTopicUseCaseImpl getNodesByTopicUseCase;
    @Test
    void GetNodesByTopicHappyFlow(){
        NodeEntity node1 = NodeEntity.builder()
                .id(1L)
                .nodeText("hey")
                .answer("Here")
                .parentId(0L)
                .build();

        when(nodeRepository.findAll()).thenReturn(List.of(node1));
        when(nodeRepository.findAllByNodeTextContainingIgnoreCaseOrVraagContainingIgnoreCase("hey","hey")).thenReturn(List.of(node1));
        GetNodesByTopicRequest request = GetNodesByTopicRequest.builder().topicString(List.of("hey")).build();

        GetNodesByTopicResponse actualResponse = getNodesByTopicUseCase.getNodesByTopic(request);

        Node node1DTO = Node.builder()
                .id(1L)
                .nodeText("hey")
                .answer("Here")
                .parentId(0L)
                .build();
        GetNodesByTopicResponse expectedResponse = GetNodesByTopicResponse.builder()
                .nodes(List.of(node1DTO))
                .build();

        assertEquals(expectedResponse, actualResponse);
    }
}
