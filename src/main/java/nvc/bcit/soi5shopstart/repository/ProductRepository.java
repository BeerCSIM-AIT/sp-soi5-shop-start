package nvc.bcit.soi5shopstart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nvc.bcit.soi5shopstart.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    // SELECT * FROM product WHERE name LIKE '%b%'
    List<Product> findByNameContaining(String name);
    // SELECT * FROM product WHERE name = 'xxx'
    // find by product description findByDescription(String desc

    // select * from product where price <= 100;    findByPriceLessThanEqual(int price)
    List<Product> findByPriceLessThanEqual(double price);

    // ต้องการรู้ว่าสินค้าอะไรยังไม่ต้องเติมสต๊อก (unitInStock > 50)
    List<Product> findByUnitInStockGreaterThan(int unit);

    
}
