package com.itens_ci_test.itens_ci.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itens_ci_test.itens_ci.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {}