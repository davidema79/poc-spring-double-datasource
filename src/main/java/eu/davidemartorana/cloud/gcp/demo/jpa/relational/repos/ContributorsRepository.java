package eu.davidemartorana.cloud.gcp.demo.jpa.relational.repos;

import eu.davidemartorana.cloud.gcp.demo.jpa.relational.entities.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContributorsRepository extends JpaRepository<Contributor, Long> {

    List<Contributor> findAllByComponentId(final long componentId);

}
