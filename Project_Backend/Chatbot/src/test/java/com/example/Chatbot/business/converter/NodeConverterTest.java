package com.example.Chatbot.business.converter;

import com.example.Chatbot.business.impl.nodes.NodeConverter;
import com.example.Chatbot.domain.nodes.Node;
import com.example.Chatbot.repository.impl.NodeEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeConverterTest {
    @Test
    void shouldConvertNodeEntitytoNode(){
        NodeEntity node1 = NodeEntity.builder()
                .id(1L)
                .nodeText("Where?")
                .answer("Here")
                .vraag("same")
                .antwoord("same")
                .parentId(1L)
                .build();

        Node actualNode = NodeConverter.convert(node1);

        Node expectedNode = Node.builder()
                .id(1L)
                .nodeText("Where?")
                .answer("Here")
                .vraag("same")
                .antwoord("same")
                .parentId(1L)
                .build();

        assertEquals(actualNode, expectedNode);
    }
}
