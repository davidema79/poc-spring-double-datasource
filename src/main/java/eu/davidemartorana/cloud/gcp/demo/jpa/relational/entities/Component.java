package eu.davidemartorana.cloud.gcp.demo.jpa.relational.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Component")
@Table(name = "components")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="description")
    private String description;

}
