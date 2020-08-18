package eu.davidemartorana.cloud.gcp.demo.jpa.relational.repos;

import eu.davidemartorana.cloud.gcp.demo.jpa.relational.entities.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionsRepository extends JpaRepository<Version, Long> {
}
