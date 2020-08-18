package eu.davidemartorana.cloud.gcp.demo.jpa.relational.repos;


import eu.davidemartorana.cloud.gcp.demo.jpa.relational.entities.Component;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ComponentsRepository extends PagingAndSortingRepository<Component, Long> {
}
