package dev.example.emarket.dao;

import dev.example.emarket.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo  extends JpaRepository<Supplier, Integer> {
}
