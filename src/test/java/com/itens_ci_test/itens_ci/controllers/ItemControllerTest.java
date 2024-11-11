package com.itens_ci_test.itens_ci.controllers;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itens_ci_test.itens_ci.models.Item;
import com.itens_ci_test.itens_ci.repositories.ItemRepository;
import com.itens_ci_test.itens_ci.services.ItemService;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    private Item item;

    @BeforeEach
    public void setup() {
        item = new Item("Item Teste", "Descrição de teste");
    }

    @Test
    public void testGetAllItems() throws Exception {
        Mockito.when(itemService.findAll()).thenReturn(List.of(item));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(item.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value(item.getDescription()));
    }

    @Test
    public void testCreateItem() throws Exception {
        item.setId(1L);

        // Configura o comportamento do itemService para retornar o item com ID ao salvar
        Mockito.when(itemService.save(Mockito.any(Item.class))).thenReturn(item); 
        mockMvc.perform(MockMvcRequestBuilders.post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item)))
                // Verifica o status da resposta
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Verifica o corpo da resposta
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Item Teste"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Descrição de teste"));
    }
}
