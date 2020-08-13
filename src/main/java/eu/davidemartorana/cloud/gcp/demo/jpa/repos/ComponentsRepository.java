package eu.davidemartorana.cloud.gcp.demo.jpa.repos;

import eu.davidemartorana.cloud.gcp.demo.jpa.entities.Component;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComponentsRepository extends PagingAndSortingRepository<Component, Long> {
}
