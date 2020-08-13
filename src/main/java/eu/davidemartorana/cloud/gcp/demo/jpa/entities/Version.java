package eu.davidemartorana.cloud.gcp.demo.jpa.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Version" )
@Table(name = "versions" )
public class Version {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "version", nullable = false)
  private String version;

  @Column(name = "component_id", nullable = false)
  private long componentId;

}
