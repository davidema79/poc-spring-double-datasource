package eu.davidemartorana.cloud.gcp.demo.jpa.repos;

import eu.davidemartorana.cloud.gcp.demo.jpa.entities.UserComponentVisit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserComponentVisitRepo extends PagingAndSortingRepository<UserComponentVisit, String> {

    @Query(value = "SELECT DISTINCT email FROM user_component_visits ", nativeQuery = true)
    List<String> getUsers();


    @Query(value = "SELECT * FROM user_component_visits WHERE date_time BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<UserComponentVisit> findAllBetween(@Param("startDate") final LocalDate start, @Param("endDate") final LocalDate end);
}
