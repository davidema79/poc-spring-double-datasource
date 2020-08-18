package eu.davidemartorana.cloud.gcp.demo.jpa.warehouse.repos;

import eu.davidemartorana.cloud.gcp.demo.jpa.warehouse.entities.UserComponentVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserComponentVisitRepository extends JpaRepository<UserComponentVisit, Long> {

    @Query(value = "SELECT DISTINCT email FROM poc.user_component_visits ", nativeQuery = true)
    List<String> getUsers();


    @Query(value = "SELECT * FROM user_component_visits WHERE date_time BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<UserComponentVisit> findAllBetween(@Param("startDate") final LocalDate start, @Param("endDate") final LocalDate end);


}
