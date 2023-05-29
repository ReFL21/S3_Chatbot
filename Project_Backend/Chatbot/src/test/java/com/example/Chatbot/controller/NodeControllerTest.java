package com.example.Chatbot.controller;

import com.example.Chatbot.business.nodes.CreateNodeUseCase;
import com.example.Chatbot.business.nodes.DeleteNodeUseCase;
import com.example.Chatbot.business.nodes.GetNodeUseCase;
import com.example.Chatbot.business.nodes.GetNodesUseCase;
import com.example.Chatbot.domain.nodes.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NodeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreateNodeUseCase createNodeUseCase;
    @MockBean
    private DeleteNodeUseCase deleteNodeUseCase;
    @MockBean
    private GetNodesUseCase getNodesUseCase;
    @MockBean
    private GetNodeUseCase getNodeUseCase;

    //@Test
    void createNode_shouldReturn201() throws Exception {
        CreateNodeRequest request = CreateNodeRequest.builder()
                .nodeText("Where?")
                .answer("Here")
                .parentId(1L)
                .build();

        when(createNodeUseCase.createNode(request)).thenReturn(CreateNodeResponse.builder().nodeId(2L).build());

        mockMvc.perform(post("/nodes").contentType(APPLICATION_JSON_VALUE).content(
                    """
                        {
                        "question":"Where",
                        "answer":"Here",
                        "parentId":1
                        }
                        """

        )).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type",APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                        {"id": 2}
                        """
                ));

        verify(createNodeUseCase).createNode(request);
    }

    //@Test
    void deleteNode_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/nodes/5"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteNodeUseCase).deleteNode(5L);
    }

    //@Test
    void getNode_shouldReturnValidNode() throws Exception {
        Node savedNode = Node.builder()
                .id(1L)
                .nodeText("Where?")
                .answer("Here")
                .build();

        when(getNodeUseCase.getNode(1L)).thenReturn(Optional.of(savedNode));

        mockMvc.perform(get("/nodes/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                        {
                        "id": 1,
                        "question": "Where",
                        "answer": "Here"
                        }
                        
                        """

                ));
        verify(getNodeUseCase).getNode(1L);
    }


    //@Test
    void getNodes_ShouldReturnListOfNodes() throws Exception {
        Node node1 = Node.builder()
                .id(1L)
                .nodeText("Where?")
                .answer("Here")
                .parentId(2L)
                .build();
        Node node2 = Node.builder()
                .id(2L)
                .nodeText("Where?")
                .answer("Here")
                .parentId(2L)
                .build();

        GetNodesResponse response = GetNodesResponse.builder()
                .nodes(List.of(node1, node2))
                .build();

        when(getNodesUseCase.getNodes(GetNodesRequest.builder().parentId(2L).build())).thenReturn(response);

        mockMvc.perform(get("/nodes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                        {
                        "nodes": [
                        {
                        "id": 1,
                        "question": "Where",
                        "answer": "Here",
                        "parentId": 2
                        },
                        {
                        "id": 2,
                        "question": "Where",
                        "answer": "Here",
                        "parentId": 2
                        }
                        ]
                        }
                        """
                ));

    }

    //@Test
    void getNode_shouldReturn404_whenNotFound() throws Exception {
        when(getNodeUseCase.getNode(12L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/nodes/12"))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(getNodeUseCase).getNode(12L);
    }



}
