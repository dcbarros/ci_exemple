package com.itens_ci_test.itens_ci.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.itens_ci_test.itens_ci.models.Item;
import com.itens_ci_test.itens_ci.repositories.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void testFindAll() {
        Item item = new Item("Item Teste", "Descrição de teste");
        Mockito.when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> items = itemService.findAll();
        Assertions.assertEquals(1, items.size());
        Assertions.assertEquals("Item Teste", items.get(0).getName());
        Assertions.assertEquals("Descrição de teste", items.get(0).getDescription());
    }

    @Test
    public void testSave() {
        Item item = new Item("Novo Item", "Nova descrição");
        Mockito.when(itemRepository.save(item)).thenReturn(item);

        Item savedItem = itemService.save(item);
        Assertions.assertEquals("Novo Item", savedItem.getName());
        Assertions.assertEquals("Nova descrição", savedItem.getDescription());
    }
}
