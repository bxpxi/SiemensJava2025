package com.siemens.internship;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllItems_ShouldReturnOk() throws Exception {
        when(itemService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/items"))
                .andExpect(status().isOk());
    }

    @Test
    void getItemById_Found() throws Exception {
        Item item = new Item(1L, "Test", "Desc", "NEW", "test@example.com");
        when(itemService.findById(1L)).thenReturn(Optional.of(item));

        mockMvc.perform(get("/api/items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    void createItem_ShouldReturnCreated() throws Exception {
        Item item = new Item(null, "New", "Desc", "NEW", "test@example.com");
        when(itemService.save(any(Item.class))).thenReturn(item);

        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isCreated());
    }
}
