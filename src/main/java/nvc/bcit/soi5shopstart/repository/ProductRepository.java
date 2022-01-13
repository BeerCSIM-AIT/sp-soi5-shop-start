package nvc.bcit.soi5shopstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nvc.bcit.soi5shopstart.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
