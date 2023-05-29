package com.example.Chatbot.business.node;

import com.example.Chatbot.business.impl.nodes.UpdateNodesUseCaseImpl;
import com.example.Chatbot.domain.nodes.UpdateNodeRequest;
import com.example.Chatbot.repository.NodeRepository;
import com.example.Chatbot.repository.impl.NodeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateNodeUseCaseImplTest {
    @Mock
    NodeRepository nodeRepository;
    @InjectMocks
    UpdateNodesUseCaseImpl updateNodesUseCase;

    @Test
    void updateNodeShouldChange(){
        NodeEntity beforeUpdateNode = NodeEntity.builder()
                .id(1L)
                .nodeText("Where?")
                .answer("Here")
                .vraag("Where?")
                .antwoord("Here")
                .parentId(2L)
                .build();

        when(nodeRepository.findById(1L)).thenReturn(Optional.of(beforeUpdateNode));
        UpdateNodeRequest request = UpdateNodeRequest.builder()
                .id(1L)
                .answer("Here")
                .nodeText("Where is that?")
                .vraag("Where?")
                .antwoord("Here")
                .parentId(2L)
                .build();
        updateNodesUseCase.updateNode(request);

        NodeEntity expectedSavedNode = NodeEntity.builder()
                .id(1L)
                .nodeText("Where is that?")
                .answer("Here")
                .vraag("Where?")
                .antwoord("Here")
                .parentId(2L)
                .build();
        Optional<NodeEntity> actualNode= nodeRepository.findById(1L);
        assert actualNode.orElse(null) != null;
        assertEquals(expectedSavedNode.getId(),actualNode.orElse(null).getId());
        assertEquals(expectedSavedNode.getNodeText(),actualNode.orElse(null).getNodeText());
        verify(nodeRepository, times(2)).findById(1L);
    }
}
