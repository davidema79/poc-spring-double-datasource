package eu.davidemartorana.cloud.gcp.demo.jpa.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="user_component_visits")
public class UserComponentVisit {

    @Id
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "component_id")
    private Long componentId;

}
