package scs.tipoAtendimentoMedicoEnfermeiro;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import scs.profissional.Profissional;
import scs.unidade.Unidade;

@Entity
@Table(name = "tipo_atendimento_med_enf")
public class TipoAtendimentoMedicoEnfermeiro implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3770029982806550288L;
	@Id
	@GeneratedValue
	private Integer id;
	private Integer puericultura;
	private Integer prenatal;
	private Integer prevencao_cancer_cevico_uterino;
	private Integer diabetes;
	private Integer dstaids;
	private Integer hipertensaoarterial;
	private Integer hanseniase;
	private Integer tuberculose;
	private Date data_cadastro;
	@ManyToOne
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;
	@ManyToOne
	@JoinColumn(name = "idprofissional")
	private Profissional profissional;
	
	
	public Profissional getProfissional() {
		return profissional;
	}
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((data_cadastro == null) ? 0 : data_cadastro.hashCode());
		result = prime * result
				+ ((diabetes == null) ? 0 : diabetes.hashCode());
		result = prime * result + ((dstaids == null) ? 0 : dstaids.hashCode());
		result = prime * result
				+ ((hanseniase == null) ? 0 : hanseniase.hashCode());
		result = prime
				* result
				+ ((hipertensaoarterial == null) ? 0 : hipertensaoarterial
						.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((prenatal == null) ? 0 : prenatal.hashCode());
		result = prime
				* result
				+ ((prevencao_cancer_cevico_uterino == null) ? 0
						: prevencao_cancer_cevico_uterino.hashCode());
		result = prime * result
				+ ((profissional == null) ? 0 : profissional.hashCode());
		result = prime * result
				+ ((puericultura == null) ? 0 : puericultura.hashCode());
		result = prime * result
				+ ((tuberculose == null) ? 0 : tuberculose.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
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
		TipoAtendimentoMedicoEnfermeiro other = (TipoAtendimentoMedicoEnfermeiro) obj;
		if (data_cadastro == null) {
			if (other.data_cadastro != null)
				return false;
		} else if (!data_cadastro.equals(other.data_cadastro))
			return false;
		if (diabetes == null) {
			if (other.diabetes != null)
				return false;
		} else if (!diabetes.equals(other.diabetes))
			return false;
		if (dstaids == null) {
			if (other.dstaids != null)
				return false;
		} else if (!dstaids.equals(other.dstaids))
			return false;
		if (hanseniase == null) {
			if (other.hanseniase != null)
				return false;
		} else if (!hanseniase.equals(other.hanseniase))
			return false;
		if (hipertensaoarterial == null) {
			if (other.hipertensaoarterial != null)
				return false;
		} else if (!hipertensaoarterial.equals(other.hipertensaoarterial))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (prenatal == null) {
			if (other.prenatal != null)
				return false;
		} else if (!prenatal.equals(other.prenatal))
			return false;
		if (prevencao_cancer_cevico_uterino == null) {
			if (other.prevencao_cancer_cevico_uterino != null)
				return false;
		} else if (!prevencao_cancer_cevico_uterino
				.equals(other.prevencao_cancer_cevico_uterino))
			return false;
		if (profissional == null) {
			if (other.profissional != null)
				return false;
		} else if (!profissional.equals(other.profissional))
			return false;
		if (puericultura == null) {
			if (other.puericultura != null)
				return false;
		} else if (!puericultura.equals(other.puericultura))
			return false;
		if (tuberculose == null) {
			if (other.tuberculose != null)
				return false;
		} else if (!tuberculose.equals(other.tuberculose))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}
	public String getDataFormtada(){
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
		return formatador.format(getData_cadastro());
	}	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPuericultura() {
		return puericultura;
	}
	public void setPuericultura(Integer puericultura) {
		this.puericultura = puericultura;
	}
	public Integer getPrenatal() {
		return prenatal;
	}
	public void setPrenatal(Integer prenatal) {
		this.prenatal = prenatal;
	}
	public Integer getPrevencao_cancer_cevico_uterino() {
		return prevencao_cancer_cevico_uterino;
	}
	public void setPrevencao_cancer_cevico_uterino(
			Integer prevencao_cancer_cevico_uterino) {
		this.prevencao_cancer_cevico_uterino = prevencao_cancer_cevico_uterino;
	}
	public Integer getDiabetes() {
		return diabetes;
	}
	public void setDiabetes(Integer diabetes) {
		this.diabetes = diabetes;
	}
	public Integer getDstaids() {
		return dstaids;
	}
	public void setDstaids(Integer dstaids) {
		this.dstaids = dstaids;
	}
	public Integer getHipertensaoarterial() {
		return hipertensaoarterial;
	}
	public void setHipertensaoarterial(Integer hipertensaoarterial) {
		this.hipertensaoarterial = hipertensaoarterial;
	}
	public Integer getHanseniase() {
		return hanseniase;
	}
	public void setHanseniase(Integer hanseniase) {
		this.hanseniase = hanseniase;
	}
	public Integer getTuberculose() {
		return tuberculose;
	}
	public void setTuberculose(Integer tuberculose) {
		this.tuberculose = tuberculose;
	}
	public Date getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	
	

}
