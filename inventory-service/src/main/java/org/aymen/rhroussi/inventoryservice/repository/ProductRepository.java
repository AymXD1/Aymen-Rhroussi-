package org.aymen.rhroussi.inventoryservice.repository;

import org.aymen.rhroussi.inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, String> {

}
