package com.example.Chatbot.business.node;

import com.example.Chatbot.business.impl.nodes.GetNodeByLangAndKeywordUseCaseImpl;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetNodeByLangAndKeywordUseCaseImplTest {
    @Mock
    NodeRepository nodeRepository;
    @InjectMocks
    GetNodeByLangAndKeywordUseCaseImpl getNodeByLangAndKeywordUseCase;
    @Test
    void GetNodeByLangAndKeywordHappyFlow(){
        NodeEntity node1 = NodeEntity.builder()
                .id(1L)
                .nodeText("hey")
                .answer("Here")
                .antwoord("hello")
                .vraag("hello")
                .parentId(0L)
                .build();

        NodeEntity node2 = NodeEntity.builder()
                .id(2L)
                .nodeText("hey")
                .answer("That")
                .antwoord("hello")
                .vraag("hello")
                .parentId(0L)
                .build();

        when(nodeRepository.findByVraagContainingIgnoreCase("hello")).thenReturn(Optional.of(node2));
        when(nodeRepository.findByNodeTextContainingIgnoreCase("hey")).thenReturn(Optional.of(node1));

        Optional<Node> node1DTO = Optional.of(Node.builder()
                .id(1L)
                .nodeText("hey")
                .answer("Here")
                .antwoord("hello")
                .vraag("hello")
                .parentId(0L)
                .build());
        Optional<Node> node2DTO = Optional.of(Node.builder()
                .id(2L)
                .nodeText("hey")
                .answer("That")
                .antwoord("hello")
                .vraag("hello")
                .parentId(0L)
                .build());
        Optional<Node> actualNode1=getNodeByLangAndKeywordUseCase.getNode("hey","eng");
        Optional<Node> actualNode2=getNodeByLangAndKeywordUseCase.getNode("hello","nl");
        assertEquals(actualNode1, node1DTO);
        assertEquals(actualNode2, node2DTO);
    }
}
