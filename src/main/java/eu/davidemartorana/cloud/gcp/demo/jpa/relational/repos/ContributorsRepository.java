package eu.davidemartorana.cloud.gcp.demo.jpa.relational.repos;

import eu.davidemartorana.cloud.gcp.demo.jpa.relational.entities.Contributor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ContributorsRepository extends PagingAndSortingRepository<Contributor, Long> {

    List<Contributor> findAllByComponentId(final long componentId);

}
