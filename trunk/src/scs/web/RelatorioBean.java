package scs.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.model.StreamedContent;

import scs.area.Area;
import scs.area.AreaRN;
import scs.microarea.Microarea;
import scs.microarea.MicroareaRN;
import scs.unidade.Unidade;
import scs.usuario.Usuario;
import scs.util.ContextoUtil;
import scs.web.util.RelatorioUtil;

import com.sun.xml.bind.CycleRecoverable.Context;
import com.sun.xml.internal.ws.util.UtilException;

@ManagedBean(name = "relatorioBean")
@RequestScoped
public class RelatorioBean {

	private StreamedContent arquivoRetornoSSA2_1;
	private StreamedContent arquivoRetornoA2;
	private StreamedContent arquivoRetornoPMA2_1;
	private StreamedContent arquivoRetornoFichaB;
	private StreamedContent arquivoRetornoA2ConsolidacaoAnual;
	private StreamedContent arquivoRetornoA2TotaisCondicaoReferida;
	private Area area;
	private Unidade unidade;
	private Microarea microarea;
	private String condicaoRelatorio;
	private List<SelectItem> areaSelect;
	private List<SelectItem> microareaSelect;
	private Date dtInicial;
	private Date dtFinal;
	
	
	
	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	public String getChamaPMA2_1(){
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		if (usuario.getArea() != null) {
			unidade = usuario.getArea().getUnidade();
		}
		
		return "/restrito/relatorioPMA2_1.jsf";
	}

	public String getChamaSSA_1(){
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		if (usuario.getArea() != null) {
			area = usuario.getArea();
		}
		
		return "/restrito/relatorioSSA2_1.jsf";
	}
	
	public boolean getDisableItensRelatorios() {
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		boolean result = true;
		for (int i = 0; i < usuario.getPermissao().size(); i++) {
			if (usuario.getPermissao().get(i).equals("ROLE_ADMIN")) {
				result = false;
			}
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result
				+ ((areaSelect == null) ? 0 : areaSelect.hashCode());
		result = prime
				* result
				+ ((arquivoRetornoA2 == null) ? 0 : arquivoRetornoA2.hashCode());
		result = prime
				* result
				+ ((arquivoRetornoA2ConsolidacaoAnual == null) ? 0
						: arquivoRetornoA2ConsolidacaoAnual.hashCode());
		result = prime
				* result
				+ ((arquivoRetornoA2TotaisCondicaoReferida == null) ? 0
						: arquivoRetornoA2TotaisCondicaoReferida.hashCode());
		result = prime
				* result
				+ ((arquivoRetornoFichaB == null) ? 0 : arquivoRetornoFichaB
						.hashCode());
		result = prime
				* result
				+ ((arquivoRetornoPMA2_1 == null) ? 0 : arquivoRetornoPMA2_1
						.hashCode());
		result = prime
				* result
				+ ((arquivoRetornoSSA2_1 == null) ? 0 : arquivoRetornoSSA2_1
						.hashCode());
		result = prime
				* result
				+ ((condicaoRelatorio == null) ? 0 : condicaoRelatorio
						.hashCode());
		result = prime * result + ((dtFinal == null) ? 0 : dtFinal.hashCode());
		result = prime * result
				+ ((dtInicial == null) ? 0 : dtInicial.hashCode());
		result = prime * result
				+ ((microarea == null) ? 0 : microarea.hashCode());
		result = prime * result
				+ ((microareaSelect == null) ? 0 : microareaSelect.hashCode());
		result = prime * result + tipoRelatorio;
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
		RelatorioBean other = (RelatorioBean) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (areaSelect == null) {
			if (other.areaSelect != null)
				return false;
		} else if (!areaSelect.equals(other.areaSelect))
			return false;
		if (arquivoRetornoA2 == null) {
			if (other.arquivoRetornoA2 != null)
				return false;
		} else if (!arquivoRetornoA2.equals(other.arquivoRetornoA2))
			return false;
		if (arquivoRetornoA2ConsolidacaoAnual == null) {
			if (other.arquivoRetornoA2ConsolidacaoAnual != null)
				return false;
		} else if (!arquivoRetornoA2ConsolidacaoAnual
				.equals(other.arquivoRetornoA2ConsolidacaoAnual))
			return false;
		if (arquivoRetornoA2TotaisCondicaoReferida == null) {
			if (other.arquivoRetornoA2TotaisCondicaoReferida != null)
				return false;
		} else if (!arquivoRetornoA2TotaisCondicaoReferida
				.equals(other.arquivoRetornoA2TotaisCondicaoReferida))
			return false;
		if (arquivoRetornoFichaB == null) {
			if (other.arquivoRetornoFichaB != null)
				return false;
		} else if (!arquivoRetornoFichaB.equals(other.arquivoRetornoFichaB))
			return false;
		if (arquivoRetornoPMA2_1 == null) {
			if (other.arquivoRetornoPMA2_1 != null)
				return false;
		} else if (!arquivoRetornoPMA2_1.equals(other.arquivoRetornoPMA2_1))
			return false;
		if (arquivoRetornoSSA2_1 == null) {
			if (other.arquivoRetornoSSA2_1 != null)
				return false;
		} else if (!arquivoRetornoSSA2_1.equals(other.arquivoRetornoSSA2_1))
			return false;
		if (condicaoRelatorio == null) {
			if (other.condicaoRelatorio != null)
				return false;
		} else if (!condicaoRelatorio.equals(other.condicaoRelatorio))
			return false;
		if (dtFinal == null) {
			if (other.dtFinal != null)
				return false;
		} else if (!dtFinal.equals(other.dtFinal))
			return false;
		if (dtInicial == null) {
			if (other.dtInicial != null)
				return false;
		} else if (!dtInicial.equals(other.dtInicial))
			return false;
		if (microarea == null) {
			if (other.microarea != null)
				return false;
		} else if (!microarea.equals(other.microarea))
			return false;
		if (microareaSelect == null) {
			if (other.microareaSelect != null)
				return false;
		} else if (!microareaSelect.equals(other.microareaSelect))
			return false;
		if (tipoRelatorio != other.tipoRelatorio)
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Microarea getMicroarea() {
		return microarea;
	}

	public void setMicroarea(Microarea microarea) {
		this.microarea = microarea;
	}

	public String getCondicaoRelatorio() {
		return condicaoRelatorio;
	}

	public void setCondicaoRelatorio(String condicaoRelatorio) {
		this.condicaoRelatorio = condicaoRelatorio;
	}

	private int tipoRelatorio;

	public StreamedContent getArquivoRetornoFichaB()
			throws scs.util.UtilException {
		FacesContext context = FacesContext.getCurrentInstance();
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametrosRelatorio = new HashMap<>();
		String nomeRelatorio = "";
		if (condicaoRelatorio.equals("Tuberculose")) {
			nomeRelatorio = "Tuberculose";
		} else if (condicaoRelatorio.equals("Gestante")) {
			nomeRelatorio = "Gestante";
		} else if (condicaoRelatorio.equals("Hipertensão")) {
			nomeRelatorio = "Hipertensos";
		} else if (condicaoRelatorio.equals("Diabetes")) {
			nomeRelatorio = "Diabetes";
		} else if (condicaoRelatorio.equals("Hanseníase")) {
			nomeRelatorio = "Hanseniase";
		}
		String nomeRelatorioJasper = nomeRelatorio;
		String nomeRelatorioSaida = nomeRelatorio;
		parametrosRelatorio.put("area", area.getCodigo());
		parametrosRelatorio.put("microarea", microarea.getDescricao());
		parametrosRelatorio.put("segmento", area.getSegmento().getCodigo());
		parametrosRelatorio.put("unidade", area.getUnidade()
				.getCodigo_sia_sus());
		try {
			this.arquivoRetornoFichaB = relatorioUtil.geraRelatorio(
					parametrosRelatorio, nomeRelatorioJasper,
					nomeRelatorioSaida, this.tipoRelatorio);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return arquivoRetornoFichaB;
	}

	public StreamedContent getArquivoRetornoSSA2_1()
			throws scs.util.UtilException {
		FacesContext context = FacesContext.getCurrentInstance();
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametrosRelatorio = new HashMap<>();
		
		if(UsuarioBean.isAdministrador()==false){
			Usuario usuario = new Usuario();
			ContextoBean cx = new ContextoBean();
			usuario = cx.getUsuarioLogado();
			if (usuario.getArea() != null) {
				area = usuario.getArea();
			}
		}
		
		
		parametrosRelatorio.put("area", area.getCodigo());
		parametrosRelatorio.put("segmento", area.getSegmento().getCodigo());
		parametrosRelatorio.put("unidade", area.getUnidade().getCodigo_sia_sus());
		parametrosRelatorio.put("dtIni", dtInicial);
		parametrosRelatorio.put("dtFim", dtFinal);
		String nomeRelatorioJasper = "SSA2_1";
		String nomeRelatorioSaida = "SSA2_1";

		try {
			this.arquivoRetornoSSA2_1 = relatorioUtil.geraRelatorio(
					parametrosRelatorio, nomeRelatorioJasper,
					nomeRelatorioSaida, this.tipoRelatorio);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return arquivoRetornoSSA2_1;
	}
	
	public StreamedContent getArquivoRetornoPMA2_1()
			throws scs.util.UtilException {
		FacesContext context = FacesContext.getCurrentInstance();
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametrosRelatorio = new HashMap<>();
		
		if(UsuarioBean.isAdministrador()==false){
			Usuario usuario = new Usuario();
			ContextoBean cx = new ContextoBean();
			usuario = cx.getUsuarioLogado();
			if (usuario.getArea() != null) {
				unidade = usuario.getArea().getUnidade();
			}
		}
		
		
		parametrosRelatorio.put("unidade", unidade.getCodigo_unidade());
		parametrosRelatorio.put("dt_inicial", dtInicial);
		parametrosRelatorio.put("dt_final", dtFinal);
		String nomeRelatorioJasper = "PMA2_1";
		String nomeRelatorioSaida = "PMA2_1";

		try {
			this.arquivoRetornoPMA2_1 = relatorioUtil.geraRelatorio(
					parametrosRelatorio, nomeRelatorioJasper,
					nomeRelatorioSaida, this.tipoRelatorio);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return arquivoRetornoPMA2_1;
	}

	public StreamedContent getArquivoRetornoA2() throws scs.util.UtilException {
		FacesContext context = FacesContext.getCurrentInstance();
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametrosRelatorio = new HashMap<>();
		parametrosRelatorio.put("area", area.getCodigo());
		parametrosRelatorio.put("segmento", area.getSegmento().getCodigo());
		parametrosRelatorio.put("unidade", area.getUnidade()
				.getCodigo_sia_sus());
		String nomeRelatorioJasper = "A2";
		String nomeRelatorioSaida = "A2";
		try {
			this.arquivoRetornoA2 = relatorioUtil.geraRelatorio(
					parametrosRelatorio, nomeRelatorioJasper,
					nomeRelatorioSaida, this.tipoRelatorio);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return arquivoRetornoA2;
	}

	public StreamedContent getArquivoRetornoSSA2_Ges()
			throws scs.util.UtilException {
		FacesContext context = FacesContext.getCurrentInstance();
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametrosRelatorio = new HashMap<>();
		parametrosRelatorio.put("area", area.getCodigo());
		parametrosRelatorio.put("segmento", area.getSegmento().getCodigo());
		parametrosRelatorio.put("unidade", area.getUnidade()
				.getCodigo_sia_sus());
		String nomeRelatorioJasper = "SSA2_GES";
		String nomeRelatorioSaida = "SSA2_GESTANTE";
		try {
			this.arquivoRetornoA2 = relatorioUtil.geraRelatorio(
					parametrosRelatorio, nomeRelatorioJasper,
					nomeRelatorioSaida, this.tipoRelatorio);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return arquivoRetornoA2;
	}

	public StreamedContent getArquivoRetornoA2ConsolidacaoAnual()
			throws scs.util.UtilException {
		FacesContext context = FacesContext.getCurrentInstance();
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametrosRelatorio = new HashMap<>();
		parametrosRelatorio.put("area", area.getCodigo());
		parametrosRelatorio.put("segmento", area.getSegmento().getCodigo());
		parametrosRelatorio.put("unidade", area.getUnidade()
				.getCodigo_sia_sus());
		String nomeRelatorioJasper = "A2_Consolidacao_Anual";
		String nomeRelatorioSaida = "A2CONSOLIDACAOANUAL";
		try {
			this.arquivoRetornoA2ConsolidacaoAnual = relatorioUtil
					.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper,
							nomeRelatorioSaida, this.tipoRelatorio);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return arquivoRetornoA2ConsolidacaoAnual;
	}

	public StreamedContent getArquivoRetornoA2TotaisCondicaoReferida()
			throws scs.util.UtilException {
		FacesContext context = FacesContext.getCurrentInstance();
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametrosRelatorio = new HashMap<>();
		parametrosRelatorio.put("area", area.getCodigo());
		parametrosRelatorio.put("segmento", area.getSegmento().getCodigo());
		parametrosRelatorio.put("unidade", area.getUnidade()
				.getCodigo_sia_sus());
		String nomeRelatorioJasper = "A2_Totais_Condicao_Referida";
		String nomeRelatorioSaida = "A2TOTAISCONDICAOREFERIDA";
		try {
			this.arquivoRetornoA2TotaisCondicaoReferida = relatorioUtil
					.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper,
							nomeRelatorioSaida, this.tipoRelatorio);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return arquivoRetornoA2TotaisCondicaoReferida;

	}

	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<SelectItem> getAreaSelect() {
		if (this.areaSelect == null) {
			this.areaSelect = new ArrayList<SelectItem>();
			// ContextoBean contextoBean =
			// scs.util.ContextoUtil.getContextoBean();

			AreaRN areaRN = new AreaRN();
			List<Area> categorias = areaRN.listar();
			this.montaDadosSelectArea(this.areaSelect, categorias, "");
		}
		return areaSelect;
	}

	private void montaDadosSelectArea(List<SelectItem> select,
			List<Area> areas, String prefixo) {

		SelectItem item = null;
		if (areas != null) {
			for (Area area : areas) {
				item = new SelectItem(area, "Código: " + area.getCodigo()
						+ " | Segmento: " + area.getSegmento().getCodigo());
				item.setEscape(false);
				select.add(item);
				// this.montaDadosSelect(select, usuario.getNome(), prefixo +
				// "&nbsp;&nbsp;");
			}
		}
	}

	public List<SelectItem> getMicroareaSelect() {
		if (this.microareaSelect == null) {
			this.microareaSelect = new ArrayList<SelectItem>();
			// ContextoBean contextoBean =
			// scs.util.ContextoUtil.getContextoBean();

			MicroareaRN microareaRN = new MicroareaRN();
			List<Microarea> categorias = microareaRN.listar();
			this.montaDadosSelectMicroArea(this.microareaSelect, categorias, "");
		}

		return microareaSelect;
	}

	private void montaDadosSelectMicroArea(List<SelectItem> select,
			List<Microarea> microareas, String prefixo) {

		SelectItem item = null;
		if (microareas != null) {
			for (Microarea microarea : microareas) {
				item = new SelectItem(microarea, microarea.getDescricao()
						+ " | Agente: "
						+ microarea.getAgente().getNome().toString()
						+ " | Área: "
						+ microarea.getArea().getCodigo().toString());
				item.setEscape(false);
				if (area == null) {
					select.add(item);
				} else {
					if (microarea.getArea().getCodigo_area() == area
							.getCodigo_area()) {
						select.add(item);
					}
				}
				// this.montaDadosSelect(select, usuario.getNome(), prefixo +
				// "&nbsp;&nbsp;");
			}
		}
	}

}
