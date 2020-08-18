package eu.davidemartorana.cloud.gcp.demo.jpa.relational.repos;

import eu.davidemartorana.cloud.gcp.demo.jpa.relational.entities.Version;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VersionsRepository extends PagingAndSortingRepository<Version, Long> {
}
