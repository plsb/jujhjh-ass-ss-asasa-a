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
	@JoinColumn(name = "endereco")
	private Rua endereco;
	@ManyToOne
	@JoinColumn(name = "bairro")
	private Bairro bairro;
	@ManyToOne
	@JoinColumn(name = "segmento")
	private Segmento segmento;
	@ManyToOne
	@JoinColumn(name = "area")
	private Area area;
	@ManyToOne
	@JoinColumn(name = "microarea")
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
	private String outromeiocomunicacao;
	private String outromeiotransporte;
	private String outroparticipagrupo;
	private String possuiplanosaude;
	private Integer numeropessoascobertasplanosaude;
	private String nomeplanosaude;
	private Integer numerocomodos;
	private boolean possuienergiaeletrica;
	private Integer numerofamilia;
	private String complemento;
	private String utiliza_beneficio;
	private String nomebeneficio;
	
	
	public String getUtiliza_beneficio() {
		return utiliza_beneficio;
	}

	public void setUtiliza_beneficio(String utiliza_beneficio) {
		this.utiliza_beneficio = utiliza_beneficio;
	}

	public String getNomebeneficio() {
		return nomebeneficio;
	}

	public void setNomebeneficio(String nomebeneficio) {
		this.nomebeneficio = nomebeneficio;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento.toUpperCase();
	}

	public Integer getNumerofamilia() {
		return numerofamilia;
	}

	public void setNumerofamilia(Integer numerofamilia) {
		this.numerofamilia = numerofamilia;
	}

	public Integer getNumerocomodos() {
		return numerocomodos;
	}

	public void setNumerocomodos(Integer numerocomodos) {
		this.numerocomodos = numerocomodos;
	}

	public boolean isPossuienergiaeletrica() {
		return possuienergiaeletrica;
	}

	public void setPossuienergiaeletrica(boolean possuienergiaeletrica) {
		this.possuienergiaeletrica = possuienergiaeletrica;
	}

	public String getOutromeiotransporte() {
		return outromeiotransporte;
	}

	public void setOutromeiotransporte(String outromeiotransporte) {
		this.outromeiotransporte = outromeiotransporte.toUpperCase();
	}

	public String getOutromeiocomunicacao() {
		return outromeiocomunicacao;
	}

	public void setOutromeiocomunicacao(String outromeiocomunicacao) {
		this.outromeiocomunicacao = outromeiocomunicacao.toUpperCase();
	}

	public String getOutroparticipagrupo() {
		return outroparticipagrupo;
	}

	public void setOutroparticipagrupo(String outroparticipagrupo) {
		this.outroparticipagrupo = outroparticipagrupo.toUpperCase();
	}

	public String getPossuiplanosaude() {
		return possuiplanosaude;
	}

	public void setPossuiplanosaude(String possuiplanosaude) {
		this.possuiplanosaude = possuiplanosaude;
	}

	public Integer getNumeropessoascobertasplanosaude() {
		return numeropessoascobertasplanosaude;
	}

	public void setNumeropessoascobertasplanosaude(
			Integer numeropessoascobertasplanosaude) {
		this.numeropessoascobertasplanosaude = numeropessoascobertasplanosaude;
	}

	public String getNomeplanosaude() {
		return nomeplanosaude;
	}

	public void setNomeplanosaude(String nomeplanosaude) {
		this.nomeplanosaude = nomeplanosaude.toUpperCase();
	}

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
				+ ((complemento == null) ? 0 : complemento.hashCode());
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
				+ ((nomebeneficio == null) ? 0 : nomebeneficio.hashCode());
		result = prime * result
				+ ((nomeplanosaude == null) ? 0 : nomeplanosaude.hashCode());
		result = prime * result
				+ ((num_residencia == null) ? 0 : num_residencia.hashCode());
		result = prime * result
				+ ((numerocomodos == null) ? 0 : numerocomodos.hashCode());
		result = prime * result
				+ ((numerofamilia == null) ? 0 : numerofamilia.hashCode());
		result = prime
				* result
				+ ((numeropessoascobertasplanosaude == null) ? 0
						: numeropessoascobertasplanosaude.hashCode());
		result = prime * result
				+ ((ourtoCasoDoenca == null) ? 0 : ourtoCasoDoenca.hashCode());
		result = prime * result
				+ ((outroTipoCasa == null) ? 0 : outroTipoCasa.hashCode());
		result = prime
				* result
				+ ((outromeiocomunicacao == null) ? 0 : outromeiocomunicacao
						.hashCode());
		result = prime
				* result
				+ ((outromeiotransporte == null) ? 0 : outromeiotransporte
						.hashCode());
		result = prime
				* result
				+ ((outroparticipagrupo == null) ? 0 : outroparticipagrupo
						.hashCode());
		result = prime * result
				+ ((participagrupo == null) ? 0 : participagrupo.hashCode());
		result = prime * result + (possuienergiaeletrica ? 1231 : 1237);
		result = prime
				* result
				+ ((possuiplanosaude == null) ? 0 : possuiplanosaude.hashCode());
		result = prime * result
				+ ((segmento == null) ? 0 : segmento.hashCode());
		result = prime * result
				+ ((tatamentoagua == null) ? 0 : tatamentoagua.hashCode());
		result = prime * result
				+ ((tipocasa == null) ? 0 : tipocasa.hashCode());
		result = prime
				* result
				+ ((utiliza_beneficio == null) ? 0 : utiliza_beneficio
						.hashCode());
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
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
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
		if (nomebeneficio == null) {
			if (other.nomebeneficio != null)
				return false;
		} else if (!nomebeneficio.equals(other.nomebeneficio))
			return false;
		if (nomeplanosaude == null) {
			if (other.nomeplanosaude != null)
				return false;
		} else if (!nomeplanosaude.equals(other.nomeplanosaude))
			return false;
		if (num_residencia == null) {
			if (other.num_residencia != null)
				return false;
		} else if (!num_residencia.equals(other.num_residencia))
			return false;
		if (numerocomodos == null) {
			if (other.numerocomodos != null)
				return false;
		} else if (!numerocomodos.equals(other.numerocomodos))
			return false;
		if (numerofamilia == null) {
			if (other.numerofamilia != null)
				return false;
		} else if (!numerofamilia.equals(other.numerofamilia))
			return false;
		if (numeropessoascobertasplanosaude == null) {
			if (other.numeropessoascobertasplanosaude != null)
				return false;
		} else if (!numeropessoascobertasplanosaude
				.equals(other.numeropessoascobertasplanosaude))
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
		if (outromeiocomunicacao == null) {
			if (other.outromeiocomunicacao != null)
				return false;
		} else if (!outromeiocomunicacao.equals(other.outromeiocomunicacao))
			return false;
		if (outromeiotransporte == null) {
			if (other.outromeiotransporte != null)
				return false;
		} else if (!outromeiotransporte.equals(other.outromeiotransporte))
			return false;
		if (outroparticipagrupo == null) {
			if (other.outroparticipagrupo != null)
				return false;
		} else if (!outroparticipagrupo.equals(other.outroparticipagrupo))
			return false;
		if (participagrupo == null) {
			if (other.participagrupo != null)
				return false;
		} else if (!participagrupo.equals(other.participagrupo))
			return false;
		if (possuienergiaeletrica != other.possuienergiaeletrica)
			return false;
		if (possuiplanosaude == null) {
			if (other.possuiplanosaude != null)
				return false;
		} else if (!possuiplanosaude.equals(other.possuiplanosaude))
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
		if (utiliza_beneficio == null) {
			if (other.utiliza_beneficio != null)
				return false;
		} else if (!utiliza_beneficio.equals(other.utiliza_beneficio))
			return false;
		return true;
	}

}
