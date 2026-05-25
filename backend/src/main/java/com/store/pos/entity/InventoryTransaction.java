package com.store.pos.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryTransaction {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="inventory_transactions_id")
    private Long inverntoryTransactionId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @Column(nullable=false)
    private String type;

    @Column(nullable=false)
    private Integer quantity;

    private String remarks;

    @Column(name="created_at", updatable=false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

}
