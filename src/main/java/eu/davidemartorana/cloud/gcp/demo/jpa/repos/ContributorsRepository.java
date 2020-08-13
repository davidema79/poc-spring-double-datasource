package eu.davidemartorana.cloud.gcp.demo.jpa.repos;

import eu.davidemartorana.cloud.gcp.demo.jpa.entities.Contributor;
import eu.davidemartorana.cloud.gcp.demo.jpa.entities.Version;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ContributorsRepository extends PagingAndSortingRepository<Contributor, Long> {

    List<Contributor> findAllByComponentId(final long componentId);

}
