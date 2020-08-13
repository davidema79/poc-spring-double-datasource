package eu.davidemartorana.cloud.gcp.demo.jpa.repos;

import eu.davidemartorana.cloud.gcp.demo.jpa.entities.Version;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VersionsRepository extends PagingAndSortingRepository<Version, Long> {
}
