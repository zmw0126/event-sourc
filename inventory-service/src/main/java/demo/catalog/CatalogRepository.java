package demo.catalog;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface CatalogRepository extends Neo4jRepository<Catalog, Long> {
    Catalog findCatalogByCatalogNumber(@Param("catalogNumber") Long catalogNumber);
}
