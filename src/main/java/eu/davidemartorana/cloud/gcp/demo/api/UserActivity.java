package eu.davidemartorana.cloud.gcp.demo.api;

import eu.davidemartorana.cloud.gcp.demo.jpa.entities.UserComponentVisit;
import eu.davidemartorana.cloud.gcp.demo.jpa.repos.UserComponentVisitRepo;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserActivity {

    @Autowired
    private UserComponentVisitRepo userComponentVisitRepo;

    @GetMapping(path = {"/",""})
    public List<ImmutablePair> getUsers(){
        return this.userComponentVisitRepo.getUsers().stream()
                .map(email -> ImmutablePair.of("email", email))
                .collect(Collectors.toList());
    }

    @GetMapping("/visits")
    public List<UserComponentVisit> retrieveVisit(@RequestParam(value = "from", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
                                                  @RequestParam(value = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {


        if( endDate == null){
            endDate = LocalDate.now().plusDays(1);
        }

        if(endDate.isBefore(startDate)){
            throw new IllegalArgumentException("Start date must be smaller than End date");
        }

        if (startDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("The date range cannot start in the future.");
        }

        return this.userComponentVisitRepo.findAllBetween(startDate, endDate);
    }

    @PostMapping("/visits")
    @ResponseStatus(HttpStatus.CREATED)
    public UserComponentVisit addUserComponentVisit(@RequestBody final UserComponentVisit userComponentVisit) {
        return this.userComponentVisitRepo.save(userComponentVisit);
    }

}
