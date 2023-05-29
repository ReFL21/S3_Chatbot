package com.example.Chatbot.business.node;

import com.example.Chatbot.business.impl.nodes.DeleteNodeUseCaseImpl;
import com.example.Chatbot.repository.NodeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteNodeUseCaseImplTest {

    @Mock
    NodeRepository nodeRepository;
    @InjectMocks
    DeleteNodeUseCaseImpl deleteNodeUseCase;
    @Test
    void deleteNode_shouldBeDeleted(){
        deleteNodeUseCase.deleteNode(1L);
        verify(nodeRepository).deleteById(1L);
    }

}
