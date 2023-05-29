package com.example.Chatbot.repository;

import com.example.Chatbot.repository.impl.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NodeRepository extends JpaRepository<NodeEntity,Long> {
    List<NodeEntity> findAllByParentId(Long parentId);
    List<NodeEntity> findAllByNodeTextContainingIgnoreCaseOrVraagContainingIgnoreCase(String topic,String topicDutch);
    Optional<NodeEntity> findByNodeTextContainingIgnoreCase(String word);
    Optional<NodeEntity> findByVraagContainingIgnoreCase(String word);

    List<NodeEntity> findAllByParentIdIsNull();
}
