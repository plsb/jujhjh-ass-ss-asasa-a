package scs.procedimentos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "procedimentos")
public class Procedimentos implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6157039197132287963L;
	@Id
	@GeneratedValue
	private Integer id;
	private Integer atendimento_especifico;
	private Integer visita_inspecao_sanitaria;
	private Integer atend_individual_prof_nivel_superior;
	private Integer curativos;
	private Integer inalacoes;
	private Integer injecoes;
	private Integer retirada_pontos;
	private Integer terapia_reidratacao_oral;
	private Integer sutura;
	private Integer atend_grupo_educ_saude;
	private Integer procedimento_coletivos;
	private Integer reunioes;
	private Integer visita_docimiliar;
	private Date data_cadastro;
	
	public String getDataFormtada(){
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
		return formatador.format(getData_cadastro());
	}	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((atend_grupo_educ_saude == null) ? 0
						: atend_grupo_educ_saude.hashCode());
		result = prime
				* result
				+ ((atend_individual_prof_nivel_superior == null) ? 0
						: atend_individual_prof_nivel_superior.hashCode());
		result = prime
				* result
				+ ((atendimento_especifico == null) ? 0
						: atendimento_especifico.hashCode());
		result = prime * result
				+ ((curativos == null) ? 0 : curativos.hashCode());
		result = prime * result
				+ ((data_cadastro == null) ? 0 : data_cadastro.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((inalacoes == null) ? 0 : inalacoes.hashCode());
		result = prime * result
				+ ((injecoes == null) ? 0 : injecoes.hashCode());
		result = prime
				* result
				+ ((procedimento_coletivos == null) ? 0
						: procedimento_coletivos.hashCode());
		result = prime * result
				+ ((retirada_pontos == null) ? 0 : retirada_pontos.hashCode());
		result = prime * result
				+ ((reunioes == null) ? 0 : reunioes.hashCode());
		result = prime * result + ((sutura == null) ? 0 : sutura.hashCode());
		result = prime
				* result
				+ ((terapia_reidratacao_oral == null) ? 0
						: terapia_reidratacao_oral.hashCode());
		result = prime
				* result
				+ ((visita_docimiliar == null) ? 0 : visita_docimiliar
						.hashCode());
		result = prime
				* result
				+ ((visita_inspecao_sanitaria == null) ? 0
						: visita_inspecao_sanitaria.hashCode());
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
		Procedimentos other = (Procedimentos) obj;
		if (atend_grupo_educ_saude == null) {
			if (other.atend_grupo_educ_saude != null)
				return false;
		} else if (!atend_grupo_educ_saude.equals(other.atend_grupo_educ_saude))
			return false;
		if (atend_individual_prof_nivel_superior == null) {
			if (other.atend_individual_prof_nivel_superior != null)
				return false;
		} else if (!atend_individual_prof_nivel_superior
				.equals(other.atend_individual_prof_nivel_superior))
			return false;
		if (atendimento_especifico == null) {
			if (other.atendimento_especifico != null)
				return false;
		} else if (!atendimento_especifico.equals(other.atendimento_especifico))
			return false;
		if (curativos == null) {
			if (other.curativos != null)
				return false;
		} else if (!curativos.equals(other.curativos))
			return false;
		if (data_cadastro == null) {
			if (other.data_cadastro != null)
				return false;
		} else if (!data_cadastro.equals(other.data_cadastro))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inalacoes == null) {
			if (other.inalacoes != null)
				return false;
		} else if (!inalacoes.equals(other.inalacoes))
			return false;
		if (injecoes == null) {
			if (other.injecoes != null)
				return false;
		} else if (!injecoes.equals(other.injecoes))
			return false;
		if (procedimento_coletivos == null) {
			if (other.procedimento_coletivos != null)
				return false;
		} else if (!procedimento_coletivos.equals(other.procedimento_coletivos))
			return false;
		if (retirada_pontos == null) {
			if (other.retirada_pontos != null)
				return false;
		} else if (!retirada_pontos.equals(other.retirada_pontos))
			return false;
		if (reunioes == null) {
			if (other.reunioes != null)
				return false;
		} else if (!reunioes.equals(other.reunioes))
			return false;
		if (sutura == null) {
			if (other.sutura != null)
				return false;
		} else if (!sutura.equals(other.sutura))
			return false;
		if (terapia_reidratacao_oral == null) {
			if (other.terapia_reidratacao_oral != null)
				return false;
		} else if (!terapia_reidratacao_oral
				.equals(other.terapia_reidratacao_oral))
			return false;
		if (visita_docimiliar == null) {
			if (other.visita_docimiliar != null)
				return false;
		} else if (!visita_docimiliar.equals(other.visita_docimiliar))
			return false;
		if (visita_inspecao_sanitaria == null) {
			if (other.visita_inspecao_sanitaria != null)
				return false;
		} else if (!visita_inspecao_sanitaria
				.equals(other.visita_inspecao_sanitaria))
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAtendimento_especifico() {
		return atendimento_especifico;
	}
	public void setAtendimento_especifico(Integer atendimento_especifico) {
		this.atendimento_especifico = atendimento_especifico;
	}
	public Integer getVisita_inspecao_sanitaria() {
		return visita_inspecao_sanitaria;
	}
	public void setVisita_inspecao_sanitaria(Integer visita_inspecao_sanitaria) {
		this.visita_inspecao_sanitaria = visita_inspecao_sanitaria;
	}
	public Integer getAtend_individual_prof_nivel_superior() {
		return atend_individual_prof_nivel_superior;
	}
	public void setAtend_individual_prof_nivel_superior(
			Integer atend_individual_prof_nivel_superior) {
		this.atend_individual_prof_nivel_superior = atend_individual_prof_nivel_superior;
	}
	public Integer getCurativos() {
		return curativos;
	}
	public void setCurativos(Integer curativos) {
		this.curativos = curativos;
	}
	public Integer getInalacoes() {
		return inalacoes;
	}
	public void setInalacoes(Integer inalacoes) {
		this.inalacoes = inalacoes;
	}
	public Integer getInjecoes() {
		return injecoes;
	}
	public void setInjecoes(Integer injecoes) {
		this.injecoes = injecoes;
	}
	public Integer getRetirada_pontos() {
		return retirada_pontos;
	}
	public void setRetirada_pontos(Integer retirada_pontos) {
		this.retirada_pontos = retirada_pontos;
	}
	public Integer getTerapia_reidratacao_oral() {
		return terapia_reidratacao_oral;
	}
	public void setTerapia_reidratacao_oral(Integer terapia_reidratacao_oral) {
		this.terapia_reidratacao_oral = terapia_reidratacao_oral;
	}
	public Integer getSutura() {
		return sutura;
	}
	public void setSutura(Integer sutura) {
		this.sutura = sutura;
	}
	public Integer getAtend_grupo_educ_saude() {
		return atend_grupo_educ_saude;
	}
	public void setAtend_grupo_educ_saude(Integer atend_grupo_educ_saude) {
		this.atend_grupo_educ_saude = atend_grupo_educ_saude;
	}
	public Integer getProcedimento_coletivos() {
		return procedimento_coletivos;
	}
	public void setProcedimento_coletivos(Integer procedimento_coletivos) {
		this.procedimento_coletivos = procedimento_coletivos;
	}
	public Integer getReunioes() {
		return reunioes;
	}
	public void setReunioes(Integer reunioes) {
		this.reunioes = reunioes;
	}
	public Integer getVisita_docimiliar() {
		return visita_docimiliar;
	}
	public void setVisita_docimiliar(Integer visita_docimiliar) {
		this.visita_docimiliar = visita_docimiliar;
	}
	public Date getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	
	
	
}
