package com.itens_ci_test.itens_ci.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itens_ci_test.itens_ci.models.Item;
import com.itens_ci_test.itens_ci.repositories.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
    
    private final ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }
}
