package scs.area;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "area")
public class Area implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Area() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cod")
	@SequenceGenerator(name = "cod", sequenceName = "area_codigo_seq")
	private Integer codigo_area;
	private Integer codigo;
	private String  modelo_saude;
	private Integer codigo_supervisor;
	private Integer codigo_unidade;
	private Integer codigo_segmento;

	public Integer getCodigo_area() {
		return codigo_area;
	}
	public void setCodigo_area(Integer codigo_area) {
		this.codigo_area = codigo_area;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getModelo_saude() {
		return modelo_saude;
	}
	public void setModelo_saude(String modelo_saude) {
		this.modelo_saude = modelo_saude;
	}
	public Integer getCodigo_supervisor() {
		return codigo_supervisor;
	}
	public void setCodigo_supervisor(Integer codigo_supervisor) {
		this.codigo_supervisor = codigo_supervisor;
	}
	public Integer getCodigo_unidade() {
		return codigo_unidade;
	}
	public void setCodigo_unidade(Integer codigo_unidade) {
		this.codigo_unidade = codigo_unidade;
	}
	public Integer getCodigo_segmento() {
		return codigo_segmento;
	}
	public void setCodigo_segmento(Integer codigo_segmento) {
		this.codigo_segmento = codigo_segmento;
	}
	
	
}
