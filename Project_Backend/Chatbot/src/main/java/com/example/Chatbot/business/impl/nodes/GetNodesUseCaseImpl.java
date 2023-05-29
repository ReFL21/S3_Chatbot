package com.example.Chatbot.business.impl.nodes;

import com.example.Chatbot.business.nodes.GetNodesUseCase;
import com.example.Chatbot.domain.nodes.GetNodesRequest;
import com.example.Chatbot.domain.nodes.GetNodesResponse;
import com.example.Chatbot.domain.nodes.Node;
import com.example.Chatbot.repository.NodeRepository;
import com.example.Chatbot.repository.impl.NodeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetNodesUseCaseImpl implements GetNodesUseCase {
    private final NodeRepository nodeRepository;
    @Override
    public GetNodesResponse getNodes(final GetNodesRequest request){
        List<NodeEntity> results;
        try {
            if(request.getParentId()<1){
                results = nodeRepository.findAllByParentIdIsNull();
            }
            else if (request.getParentId()>0) {
                results = nodeRepository.findAllByParentId(request.getParentId());
            } else {
                results = nodeRepository.findAll();
            }
        }
        catch (Exception e)
        {
            results = nodeRepository.findAll();
        }


        final GetNodesResponse response = new GetNodesResponse();
        List<Node> nodes = results
                .stream()
                .map(NodeConverter::convert)
                .toList();
        response.setNodes(nodes);

        return response;
    }
}
