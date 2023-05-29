package com.example.Chatbot.business.impl.nodes;

import com.example.Chatbot.domain.nodes.Node;
import com.example.Chatbot.repository.impl.NodeEntity;

public class NodeConverter {
    private NodeConverter(){

    }
    public static Node convert(NodeEntity node) {
        return Node.builder()
                .id(node.getId())
                .nodeText(node.getNodeText())
                .answer(node.getAnswer())
                .parentId(node.getParentId())
                .vraag(node.getVraag())
                .antwoord(node.getAntwoord())
                .build();
    }
}
