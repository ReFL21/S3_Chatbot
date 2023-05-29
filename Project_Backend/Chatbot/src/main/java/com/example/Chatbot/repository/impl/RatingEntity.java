package com.example.Chatbot.repository.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="rating")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="node_id")
    private Long id;
    @NotNull
    @Column(name ="times_reached")
    private int timesReached;
    @NotNull
    @Column(name ="times_satisfied")
    private int timesSatisfied;
    @NotNull
    @Column(name ="times_unsatisfied")
    private int timesUnsatisfied;
    @OneToOne
    @MapsId
    @JoinColumn(name= "node_id" )
    private NodeEntity node;

}
