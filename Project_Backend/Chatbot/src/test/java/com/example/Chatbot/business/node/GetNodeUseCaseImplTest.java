package com.example.Chatbot.business.node;

import com.example.Chatbot.business.impl.nodes.GetNodeUseCaseImpl;
import com.example.Chatbot.domain.nodes.Node;
import com.example.Chatbot.repository.NodeRepository;
import com.example.Chatbot.repository.impl.NodeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetNodeUseCaseImplTest {

    @Mock
    NodeRepository nodeRepository;
    @InjectMocks
    GetNodeUseCaseImpl getNodeUseCase;

    @Test
    void getNode_shouldReturnNode(){
        NodeEntity node1 = NodeEntity.builder()
                .id(1L)
                .nodeText("Where?")
                .answer("Here")
                .vraag("same")
                .antwoord("same")
                .parentId(1L)
                .build();

        when(nodeRepository.findById(1L)).thenReturn(Optional.of(node1));
        Optional<Node> nodeOptional = getNodeUseCase.getNode(1L);
        Node actualNode = nodeOptional.orElseThrow();

        Node expectedNode = Node.builder()
                .id(1L)
                .nodeText("Where?")
                .answer("Here")
                .vraag("same")
                .antwoord("same")
                .parentId(1L)
                .build();

        assertEquals(actualNode, expectedNode);
        verify(nodeRepository).findById(1L);
    }
}
