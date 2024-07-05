package com.api.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.commerce.entity.Item;

public interface ItemRepository extends JpaRepository<Item, String>{

}
