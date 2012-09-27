package scs.residencia;

import scs.area.Area;
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import scs.bairro.Bairro;
import scs.microarea.Microarea;
import scs.rua.Rua;
import scs.segmento.Segmento;
import scs.web.ResidenciasBean;

@Entity
@Table(name = "residencias")
public class Residencia implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2630239465448080237L;
	
	
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name="endereco")
	private Rua endereco;
	@ManyToOne
	@JoinColumn(name="bairro")
	private Bairro bairro;
	@ManyToOne
	@JoinColumn(name="segmento")
	private Segmento segmento;
	@ManyToOne
	@JoinColumn(name="area")
	private Area area;
	@ManyToOne
	@JoinColumn(name="microarea")
	private Microarea microarea;
	private Date datacadastro;
	private String tipocasa;
	private String outroTipoCasa;
	private String destlixo;
	private String tatamentoagua;
	private String abastecimentoagua;
	private String destfezes;
	private String casodoenca;
	private String ourtoCasoDoenca;
	private String meiocomunicacao;
	private String participagrupo;
	private String meiotransporte;
	private Integer num_residencia;
	
	
	public String getOutroTipoCasa() {
		return outroTipoCasa;
	}
	public void setOutroTipoCasa(String outroTipoCasa) {
		this.outroTipoCasa = outroTipoCasa.toUpperCase();
	}
	public String getOurtoCasoDoenca() {
		return ourtoCasoDoenca;
	}
	public void setOurtoCasoDoenca(String ourtoCasoDoenca) {
		this.ourtoCasoDoenca = ourtoCasoDoenca.toUpperCase();
	}	
	public Integer getNum_residencia() {
		return num_residencia;
	}
	public void setNum_residencia(Integer num_residencia) {
		this.num_residencia = num_residencia;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Rua getEndereco() {
		return endereco;
	}
	public void setEndereco(Rua endereco) {
		this.endereco = endereco;
	}
	public Bairro getBairro() {
		return bairro;
	}
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	public Segmento getSegmento() {
		return segmento;
	}
	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public Microarea getMicroarea() {
		return microarea;
	}
	public void setMicroarea(Microarea microarea) {
		this.microarea = microarea;
	}
	public Date getDatacadastro() {
		return datacadastro;
	}
	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}
	public String getTipocasa() {
		return tipocasa;
	}
	public void setTipocasa(String tipocasa) {
		this.tipocasa = tipocasa;
	}
	
	public String getDestlixo() {
		return destlixo;
	}
	public void setDestlixo(String destlixo) {
		this.destlixo = destlixo;
	}
	public String getTatamentoagua() {
		return tatamentoagua;
	}
	public void setTatamentoagua(String tatamentoagua) {
		this.tatamentoagua = tatamentoagua;
	}
	public String getAbastecimentoagua() {
		return abastecimentoagua;
	}
	public void setAbastecimentoagua(String abastecimentoagua) {
		this.abastecimentoagua = abastecimentoagua;
	}
	public String getDestfezes() {
		return destfezes;
	}
	public void setDestfezes(String destfezes) {
		this.destfezes = destfezes;
	}
	public String getCasodoenca() {
		return casodoenca;
	}
	public void setCasodoenca(String casodoenca) {
		this.casodoenca = casodoenca;
	}
	public String getMeiocomunicacao() {
		return meiocomunicacao;
	}
	public void setMeiocomunicacao(String meiocomunicacao) {
		this.meiocomunicacao = meiocomunicacao;
	}
	public String getParticipagrupo() {
		return participagrupo;
	}
	public void setParticipagrupo(String participagrupo) {
		this.participagrupo = participagrupo;
	}
	public String getMeiotransporte() {
		return meiotransporte;
	}
	public void setMeiotransporte(String meiotransporte) {
		this.meiotransporte = meiotransporte;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((abastecimentoagua == null) ? 0 : abastecimentoagua
						.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result
				+ ((casodoenca == null) ? 0 : casodoenca.hashCode());
		result = prime * result
				+ ((datacadastro == null) ? 0 : datacadastro.hashCode());
		result = prime * result
				+ ((destfezes == null) ? 0 : destfezes.hashCode());
		result = prime * result
				+ ((destlixo == null) ? 0 : destlixo.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((meiocomunicacao == null) ? 0 : meiocomunicacao.hashCode());
		result = prime * result
				+ ((meiotransporte == null) ? 0 : meiotransporte.hashCode());
		result = prime * result
				+ ((microarea == null) ? 0 : microarea.hashCode());
		result = prime * result
				+ ((num_residencia == null) ? 0 : num_residencia.hashCode());
		result = prime * result
				+ ((ourtoCasoDoenca == null) ? 0 : ourtoCasoDoenca.hashCode());
		result = prime * result
				+ ((outroTipoCasa == null) ? 0 : outroTipoCasa.hashCode());
		result = prime * result
				+ ((participagrupo == null) ? 0 : participagrupo.hashCode());
		result = prime * result
				+ ((segmento == null) ? 0 : segmento.hashCode());
		result = prime * result
				+ ((tatamentoagua == null) ? 0 : tatamentoagua.hashCode());
		result = prime * result
				+ ((tipocasa == null) ? 0 : tipocasa.hashCode());
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
		Residencia other = (Residencia) obj;
		if (abastecimentoagua == null) {
			if (other.abastecimentoagua != null)
				return false;
		} else if (!abastecimentoagua.equals(other.abastecimentoagua))
			return false;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (casodoenca == null) {
			if (other.casodoenca != null)
				return false;
		} else if (!casodoenca.equals(other.casodoenca))
			return false;
		if (datacadastro == null) {
			if (other.datacadastro != null)
				return false;
		} else if (!datacadastro.equals(other.datacadastro))
			return false;
		if (destfezes == null) {
			if (other.destfezes != null)
				return false;
		} else if (!destfezes.equals(other.destfezes))
			return false;
		if (destlixo == null) {
			if (other.destlixo != null)
				return false;
		} else if (!destlixo.equals(other.destlixo))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (meiocomunicacao == null) {
			if (other.meiocomunicacao != null)
				return false;
		} else if (!meiocomunicacao.equals(other.meiocomunicacao))
			return false;
		if (meiotransporte == null) {
			if (other.meiotransporte != null)
				return false;
		} else if (!meiotransporte.equals(other.meiotransporte))
			return false;
		if (microarea == null) {
			if (other.microarea != null)
				return false;
		} else if (!microarea.equals(other.microarea))
			return false;
		if (num_residencia == null) {
			if (other.num_residencia != null)
				return false;
		} else if (!num_residencia.equals(other.num_residencia))
			return false;
		if (ourtoCasoDoenca == null) {
			if (other.ourtoCasoDoenca != null)
				return false;
		} else if (!ourtoCasoDoenca.equals(other.ourtoCasoDoenca))
			return false;
		if (outroTipoCasa == null) {
			if (other.outroTipoCasa != null)
				return false;
		} else if (!outroTipoCasa.equals(other.outroTipoCasa))
			return false;
		if (participagrupo == null) {
			if (other.participagrupo != null)
				return false;
		} else if (!participagrupo.equals(other.participagrupo))
			return false;
		if (segmento == null) {
			if (other.segmento != null)
				return false;
		} else if (!segmento.equals(other.segmento))
			return false;
		if (tatamentoagua == null) {
			if (other.tatamentoagua != null)
				return false;
		} else if (!tatamentoagua.equals(other.tatamentoagua))
			return false;
		if (tipocasa == null) {
			if (other.tipocasa != null)
				return false;
		} else if (!tipocasa.equals(other.tipocasa))
			return false;
		return true;
	}	
	
	

}
