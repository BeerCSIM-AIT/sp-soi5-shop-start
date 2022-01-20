package nvc.bcit.soi5shopstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nvc.bcit.soi5shopstart.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
