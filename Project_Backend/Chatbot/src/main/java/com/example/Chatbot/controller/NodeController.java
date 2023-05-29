package com.example.Chatbot.controller;

import com.example.Chatbot.business.nodes.*;
import com.example.Chatbot.domain.nodes.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/nodes")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class NodeController {
    private final GetNodesUseCase getNodesUseCase;
    private final GetNodeUseCase getNodeUseCase;
    private final GetNodesByTopicUseCase getNodesByTopicUseCase;
    private final CreateNodeUseCase createNodeUseCase;
    private final UpdateNodesUseCase updateNodesUseCase;
    private final DeleteNodeUseCase deleteNodeUseCase;
    private final FindByTextFilterUseCase findByTextFilterUseCase;

    private final GetNodeByLangAndKeywordUseCase getNodeByLangAndKeywordUseCase;

    @GetMapping
    public ResponseEntity<GetNodesResponse> getAllNodes(@RequestParam(value = "parentId", required = false) Long parentId) {
        if(parentId==null){
            parentId=0L;
        }
        GetNodesRequest request = GetNodesRequest.builder().parentId(parentId).build();
        GetNodesResponse response = getNodesUseCase.getNodes(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/find{id}")
    public ResponseEntity<Node> getNode(@RequestParam(value = "id", required = false) Long id) {
        final Optional<Node> nodeOptional = getNodeUseCase.getNode(id);
        if(nodeOptional.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(nodeOptional.get());
    }
    @GetMapping("/filter")
    public ResponseEntity<GetNodesByTopicResponse> getNodesByTopic(@RequestParam(value = "topic", required = false) ArrayList<String> topic) {
        GetNodesByTopicRequest request = GetNodesByTopicRequest.builder().topicString(topic).build();
        GetNodesByTopicResponse response = getNodesByTopicUseCase.getNodesByTopic(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping()
    public ResponseEntity<CreateNodeResponse> createNode(@RequestBody @Valid CreateNodeRequest request) {
        CreateNodeResponse response = createNodeUseCase.createNode(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> updateNode(@PathVariable("id") long id,
                                           @RequestBody @Valid UpdateNodeRequest request) {
        request.setId(id);
        updateNodesUseCase.updateNode(request);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteNode(@PathVariable long id) {
        deleteNodeUseCase.deleteNode(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filteredText")
    public ResponseEntity<FindByTextFilterResponse> findByTextFilter(@PathVariable(value = "filteredText") String filteredText){
               FindByTextFilterRequest request = FindByTextFilterRequest.builder().text(filteredText).build();
                FindByTextFilterResponse response = findByTextFilterUseCase.findByTextFilter(request);
        return ResponseEntity.ok(response);
    }

    //Get the first message aka the welcoming message this method can later be deleted if needed but for now it's needed
    //signed Arenco
    @GetMapping("/{key}")
    public ResponseEntity<Optional<Node>> getNodeByLangAndKeyword(@PathVariable(value = "key")String keyword, @RequestParam(value ="Lang", required=false)   String lang ){
        final Optional<Node> nodeOptional = getNodeByLangAndKeywordUseCase.getNode(keyword,lang);
        if(nodeOptional.isEmpty())
            return ResponseEntity.notFound().build();
        return  ResponseEntity.ok(nodeOptional);
    }

}
