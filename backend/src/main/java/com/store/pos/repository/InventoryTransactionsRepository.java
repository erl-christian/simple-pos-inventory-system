package com.store.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.pos.entity.InventoryTransaction;

@Repository
public interface InventoryTransactionsRepository extends JpaRepository<InventoryTransaction, Long>{
    
}
