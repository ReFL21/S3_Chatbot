package com.example.Chatbot.repository;

import com.example.Chatbot.repository.impl.NodeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class NodeRepositoryTest {
    @Autowired
     private EntityManager entityManager;

    @Autowired
    private NodeRepository nodeRepository;

@Test
    void save_shouldAddNodeWithFields(){
        NodeEntity node1 = NodeEntity.builder()
                .id(1L)
                .nodeText("Where?")
                .answer("Here")
                .vraag("same")
                .antwoord("same")
                .parentId(1L)
                .build();

        NodeEntity savedNode = nodeRepository.save(node1);
        assertNotNull(savedNode.getId());
        savedNode = entityManager.find(NodeEntity.class, savedNode.getId());
        NodeEntity expected = NodeEntity.builder()
                .id(savedNode.getId())
                .nodeText("Where?")
                .answer("Here")
                .vraag("same")
                .antwoord("same")
                .parentId(1L)
                .build();

        assertEquals(expected, savedNode);
    }
    @Test
    void save_AddNodeWithFields_ThrowsExeption(){
        NodeEntity empty = NodeEntity.builder().build();
        assertThrows(ConstraintViolationException.class, () -> nodeRepository.save(empty));
    }



}
