package com.example.Chatbot.business.node;

import com.example.Chatbot.business.impl.nodes.GetNodesUseCaseImpl;
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
public class GetNodesUseCaseImplTest {
    @Mock
    private NodeRepository nodeRepository;
    @InjectMocks
    private GetNodesUseCaseImpl getNodesUseCase;
    @Test
    void GetNodesHappyFlow(){
        NodeEntity nodeEntity1 = NodeEntity.builder()
                .id(1L)
                .build();
        NodeEntity nodeEntity2 = NodeEntity.builder()
                .id(2L)
                .build();
        NodeEntity nodeEntity3 = NodeEntity.builder()
                .id(3L)
                .build();
        when(nodeRepository.findAll()).thenReturn(List.of(nodeEntity1,nodeEntity2,nodeEntity3));
        GetNodesResponse actualResponse=getNodesUseCase.getNodes(null);
        Node node1 = Node.builder()
                .id(1L)
                .build();
        Node node2 = Node.builder()
                .id(2L)
                .build();
        Node node3 = Node.builder()
                .id(3L)
                .build();
        GetNodesResponse expectedResponse= GetNodesResponse.builder().nodes(List.of(node1,node2,node3)).build();
        assertEquals(expectedResponse,actualResponse);
        verify(nodeRepository).findAll();
    }
}
