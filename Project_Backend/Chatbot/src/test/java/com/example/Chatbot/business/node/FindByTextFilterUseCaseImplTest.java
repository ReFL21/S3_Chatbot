package com.example.Chatbot.business.node;

import com.example.Chatbot.business.impl.nodes.FindByTextFilterUseCaseImpl;
import com.example.Chatbot.domain.nodes.FindByTextFilterRequest;
import com.example.Chatbot.domain.nodes.FindByTextFilterResponse;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindByTextFilterUseCaseImplTest {
    @Mock
    NodeRepository nodeRepository;
    @InjectMocks
    FindByTextFilterUseCaseImpl findByTextFilterUseCase;

    @Test
    void findNodesByTestFillter(){
        NodeEntity node1 = NodeEntity.builder()
                .id(1L)
                .nodeText("hey")
                .answer("Here")
                .parentId(0L)
                .build();

        NodeEntity node2 = NodeEntity.builder()
                .id(3L)
                .nodeText("hey")
                .answer("That")
                .parentId(0L)
                .build();

        when(nodeRepository.findAll()).thenReturn(List.of(node1, node2));
        FindByTextFilterRequest request = FindByTextFilterRequest.builder().text("hey").build();

        FindByTextFilterResponse actualResponse = findByTextFilterUseCase.findByTextFilter(request);

        Node node1DTO = Node.builder()
                .id(1L)
                .nodeText("hey")
                .answer("Here")
                .parentId(0L)
                .build();
        Node node2DTO = Node.builder()
                .id(3L)
                .nodeText("hey")
                .answer("That")
                .parentId(0L)
                .build();
        FindByTextFilterResponse expectedResponse = FindByTextFilterResponse.builder()
                .nodes(List.of(node1DTO, node2DTO))
                .build();

        assertEquals(expectedResponse, actualResponse);
    }
}
