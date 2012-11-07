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
			nomeRelatorio = "";
		} else if (condicaoRelatorio.equals("Hipertensão")) {
			nomeRelatorio = "";
		} else if (condicaoRelatorio.equals("Diabetes")) {
			nomeRelatorio = "Diabetes";
		} else if (condicaoRelatorio.equals("Hanseníase")) {
			nomeRelatorio = "Hanseniase";
		}
		String nomeRelatorioJasper = nomeRelatorio;
		String nomeRelatorioSaida = nomeRelatorio;
		parametrosRelatorio.put("area", area.getCodigo());
		parametrosRelatorio.put("microarea", microarea.getCodigo_microarea());
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

}
