package eu.davidemartorana.cloud.gcp.demo.jpa.relational.entities;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Table("versions")
public class Version {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "version", nullable = false)
  private String version;

  @Column(name = "component_id", nullable = false)
  private long componentId;

}
