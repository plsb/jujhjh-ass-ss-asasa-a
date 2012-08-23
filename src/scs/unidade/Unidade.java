package scs.unidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import scs.usuario.Usuario;

@Entity
@Table(name="unidade")
public class Unidade  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO, generator="cod")  
	@SequenceGenerator(name="cod", sequenceName="unidade_codigo_unidade_seq")
	private Integer codigo_unidade;
	private String codigo_sia_sus;
	private String end_rua;
	private String end_num;
	private String end_complemento;
	private String bairro;
	private String cep;
	private String telefone;
	//private int coordenador;
	@ManyToOne
	@JoinColumn(name="coordenador")
	private Usuario funcionario;
	
	public Usuario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Usuario funcionario) {
		this.funcionario = funcionario;
	}
	
	
	public Integer getCodigo_unidade() {
		return codigo_unidade;
	}
	public void setCodigo_unidade(Integer codigo_unidade) {
		this.codigo_unidade = codigo_unidade;
	}
	public String getCodigo_sis_sus() {
		return codigo_sia_sus;
	}
	public void setCodigo_sis_sus(String codigo_sis_sus) {
		this.codigo_sia_sus = codigo_sis_sus;
	}
	public String getEnd_rua() {
		return end_rua;
	}
	public void setEnd_rua(String end_rua) {
		this.end_rua = end_rua;
	}
	public String getEnd_num() {
		return end_num;
	}
	public void setEnd_num(String end_num) {
		this.end_num = end_num;
	}
	public String getEnd_complemento() {
		return end_complemento;
	}
	public void setEnd_complemento(String end_complemento) {
		this.end_complemento = end_complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getCodigo_sia_sus() {
		return codigo_sia_sus;
	}
	public void setCodigo_sia_sus(String codigo_sia_sus) {
		this.codigo_sia_sus = codigo_sia_sus;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result
				+ ((codigo_sia_sus == null) ? 0 : codigo_sia_sus.hashCode());
		result = prime * result
				+ ((codigo_unidade == null) ? 0 : codigo_unidade.hashCode());
		result = prime * result
				+ ((end_complemento == null) ? 0 : end_complemento.hashCode());
		result = prime * result + ((end_num == null) ? 0 : end_num.hashCode());
		result = prime * result + ((end_rua == null) ? 0 : end_rua.hashCode());
		result = prime * result
				+ ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unidade other = (Unidade) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (codigo_sia_sus == null) {
			if (other.codigo_sia_sus != null)
				return false;
		} else if (!codigo_sia_sus.equals(other.codigo_sia_sus))
			return false;
		if (codigo_unidade == null) {
			if (other.codigo_unidade != null)
				return false;
		} else if (!codigo_unidade.equals(other.codigo_unidade))
			return false;
		if (end_complemento == null) {
			if (other.end_complemento != null)
				return false;
		} else if (!end_complemento.equals(other.end_complemento))
			return false;
		if (end_num == null) {
			if (other.end_num != null)
				return false;
		} else if (!end_num.equals(other.end_num))
			return false;
		if (end_rua == null) {
			if (other.end_rua != null)
				return false;
		} else if (!end_rua.equals(other.end_rua))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
	
		

}
