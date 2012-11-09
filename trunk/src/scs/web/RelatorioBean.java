package scs.web;

import java.util.ArrayList;
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
import scs.util.ContextoUtil;
import scs.web.util.RelatorioUtil;

import com.sun.xml.bind.CycleRecoverable.Context;
import com.sun.xml.internal.ws.util.UtilException;

@ManagedBean(name = "relatorioBean")
@RequestScoped
public class RelatorioBean {

	private StreamedContent arquivoRetornoSSA_2;
	private StreamedContent arquivoRetornoA2;
	private StreamedContent arquivoRetornoSSA2_Ges;
	private StreamedContent arquivoRetornoFichaB;
	private StreamedContent arquivoRetornoA2ConsolidacaoAnual;
	private StreamedContent arquivoRetornoA2TotaisCondicaoReferida;
	private Area area;
	private Microarea microarea;
	private String condicaoRelatorio;
	private List<SelectItem> areaSelect;
	private List<SelectItem> microareaSelect;

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

	public StreamedContent getArquivoRetornoSSA_2()
			throws scs.util.UtilException {
		FacesContext context = FacesContext.getCurrentInstance();
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametrosRelatorio = new HashMap<>();
		String nomeRelatorioJasper = "SSA2_Monitoramento";
		String nomeRelatorioSaida = "SSA2_Monitormaneto";

		try {
			this.arquivoRetornoSSA_2 = relatorioUtil.geraRelatorio(
					parametrosRelatorio, nomeRelatorioJasper,
					nomeRelatorioSaida, this.tipoRelatorio);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return arquivoRetornoSSA_2;
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
