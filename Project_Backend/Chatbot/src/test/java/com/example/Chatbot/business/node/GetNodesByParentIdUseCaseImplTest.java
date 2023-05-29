package com.example.Chatbot.business.node;

import com.example.Chatbot.business.impl.nodes.GetNodesUseCaseImpl;
import com.example.Chatbot.domain.nodes.GetNodesRequest;
import com.example.Chatbot.domain.nodes.GetNodesResponse;
import com.example.Chatbot.domain.nodes.Node;
import com.example.Chatbot.repository.NodeRepository;
import com.example.Chatbot.repository.impl.NodeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetNodesByParentIdUseCaseImplTest {
    @Mock
    NodeRepository nodeRepository;
    @InjectMocks
    GetNodesUseCaseImpl getNodesUseCase;

    @Test
    void getNodesByParentId_shouldReturnAllNodes(){
        NodeEntity node1 = NodeEntity.builder()
                .id(1L)
                .nodeText("Where?")
                .answer("Here")
                .vraag("same")
                .antwoord("same")
                .parentId(1L)

                .build();


        NodeEntity node2 = NodeEntity.builder()
                .id(2L)
                .nodeText("What?")
                .answer("That")
                .vraag("same")
                .antwoord("same")
                .parentId(1L)
                .build();

        when(nodeRepository.findAllByParentId(1L)).thenReturn(List.of(node1, node2));
        GetNodesRequest request = GetNodesRequest.builder().parentId(1L).build();
        GetNodesResponse actualresponse = getNodesUseCase.getNodes(request);

        Node nodeDTO1 = Node.builder()
                .id(1L)
                .nodeText("Where?")
                .answer("Here")
                .vraag("same")
                .antwoord("same")
                .parentId(1L)
                .build();

        Node nodeDTO2 = Node.builder()
                .id(2L)
                .nodeText("What?")
                .answer("That")
                .vraag("same")
                .antwoord("same")
                .parentId(1L)
                .build();

    GetNodesResponse expectedResponse = GetNodesResponse.builder().nodes(List.of(nodeDTO1, nodeDTO2)).build();

    assertEquals(actualresponse, expectedResponse);
    verify(nodeRepository).findAllByParentId(1L);
    }
}
