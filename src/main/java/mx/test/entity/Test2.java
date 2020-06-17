package mx.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="test2")
public class Test2 {

	@Id
	@Column(name="id")
	private Integer id;
	@Column(name="nir")
	private Integer nir;
	@Column(name="serie")
	private Integer serie;
	@Column(name="num_ini")
	private Integer numIni;
	@Column(name="num_fin")
	private Integer numFin;

}
