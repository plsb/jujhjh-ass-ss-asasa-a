package scs.municipio;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "municipio")
public class Municipio  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Municipio() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Id
	@GeneratedValue
	private Integer codigo;
	private Integer codigo_ibge;
	private String nome;
	private String cep;
	private String uf;
	
	
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo_ibge() {
		return codigo_ibge;
	}

	public void setCodigo_ibge(Integer codigo_ibge) {
		this.codigo_ibge = codigo_ibge;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	

}