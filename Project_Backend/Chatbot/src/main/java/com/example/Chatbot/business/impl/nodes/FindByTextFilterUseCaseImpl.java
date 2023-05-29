package com.example.Chatbot.business.impl.nodes;

import com.example.Chatbot.business.nodes.FindByTextFilterUseCase;
import com.example.Chatbot.domain.nodes.FindByTextFilterRequest;
import com.example.Chatbot.domain.nodes.FindByTextFilterResponse;
import com.example.Chatbot.domain.nodes.Node;
import com.example.Chatbot.repository.NodeRepository;
import com.example.Chatbot.repository.impl.NodeEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
@AllArgsConstructor
public class FindByTextFilterUseCaseImpl implements FindByTextFilterUseCase {
    @Autowired
    private NodeRepository nodeRepository;


    @Override
    public FindByTextFilterResponse findByTextFilter(FindByTextFilterRequest request) {
        List<NodeEntity> nodes = nodeRepository.findAll();
        Pattern pattern = Pattern.compile(request.getText(), Pattern.CASE_INSENSITIVE);
        List<NodeEntity> returnedNode = new ArrayList<>();


        for (NodeEntity node:nodes){
            if (node.getParentId()==0){
                Matcher matcher = pattern.matcher(node.getNodeText());
                boolean macherFound = matcher.find();
                if (macherFound){
                    returnedNode.add(node);
                }
            }

        }


        List<Node> nodes1 = returnedNode
                .stream()
                .map(NodeConverter::convert)
                .toList();

        return FindByTextFilterResponse.builder()
                .nodes(nodes1)
                .build();
    }
}
