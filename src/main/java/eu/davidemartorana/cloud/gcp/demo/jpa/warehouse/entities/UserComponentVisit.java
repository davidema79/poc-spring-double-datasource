package eu.davidemartorana.cloud.gcp.demo.jpa.warehouse.entities;


import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@Table("user_component_visits")
public class UserComponentVisit {

    @Id
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "component_id")
    private Long componentId;

}
