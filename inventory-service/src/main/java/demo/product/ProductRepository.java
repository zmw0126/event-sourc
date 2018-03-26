package demo.product;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends Neo4jRepository<Product, Long> {
    Product getProductByProductId(@Param("productId") String productId);
}
