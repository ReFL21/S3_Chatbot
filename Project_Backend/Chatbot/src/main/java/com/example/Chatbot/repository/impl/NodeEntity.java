package com.example.Chatbot.repository.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="node")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "parent_id")
    private Long parentId;
    @NotNull
    @Column(name = "node_text")
    private String nodeText;
    @NotNull
    @Column(name = "answer")
    private String answer;
    @NotNull
    @Column(name = "vraag")
    private String vraag;
    @NotNull
    @Column(name = "antwoord")
    private String antwoord;
    @OneToOne(mappedBy = "node",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private RatingEntity rating;

}
