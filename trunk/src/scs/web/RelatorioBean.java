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
import scs.util.ContextoUtil;
import scs.web.util.RelatorioUtil;

import com.sun.xml.bind.CycleRecoverable.Context;
import com.sun.xml.internal.ws.util.UtilException;

@ManagedBean(name="relatorioBean")
@RequestScoped
public class RelatorioBean {
	
	private StreamedContent arquivoRetornoSSA_2;
	private StreamedContent arquivoRetornoA2;
	private StreamedContent arquivoRetornoSSA2_Ges;
	private StreamedContent arquivoRetornoA2ConsolidacaoAnual;
	private Area area;
	private int tipoRelatorio;
	
	public StreamedContent getArquivoRetornoSSA_2() throws scs.util.UtilException {
		FacesContext context = FacesContext.getCurrentInstance();
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametrosRelatorio = new HashMap<>();
		String nomeRelatorioJasper = "SSA2_Monitoramento";
		String nomeRelatorioSaida = "SSA2_Monitormaneto";
		try {
			this.arquivoRetornoSSA_2 = relatorioUtil.geraRelatorio(parametrosRelatorio, 
					nomeRelatorioJasper, nomeRelatorioSaida, this.tipoRelatorio);
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
		parametrosRelatorio.put("unidade", area.getUnidade().getCodigo_sia_sus());
		String nomeRelatorioJasper = "A2";
		String nomeRelatorioSaida = "A2";
		try {
			this.arquivoRetornoA2 = relatorioUtil.geraRelatorio(parametrosRelatorio, 
					nomeRelatorioJasper, nomeRelatorioSaida, this.tipoRelatorio);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return arquivoRetornoA2;
	}	
	public StreamedContent getArquivoRetornoSSA2_Ges() throws scs.util.UtilException {
		FacesContext context = FacesContext.getCurrentInstance();
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametrosRelatorio = new HashMap<>();
		parametrosRelatorio.put("area", area.getCodigo());
		parametrosRelatorio.put("segmento", area.getSegmento().getCodigo());
		parametrosRelatorio.put("unidade", area.getUnidade().getCodigo_sia_sus());
		String nomeRelatorioJasper = "SSA2_GES";
		String nomeRelatorioSaida = "SSA2_GESTANTE";
		try {
			this.arquivoRetornoA2 = relatorioUtil.geraRelatorio(parametrosRelatorio, 
					nomeRelatorioJasper, nomeRelatorioSaida, this.tipoRelatorio);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return arquivoRetornoA2;
	}
	
	public StreamedContent getArquivoRetornoA2ConsolidacaoAnual() throws scs.util.UtilException{
		FacesContext context = FacesContext.getCurrentInstance();
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametrosRelatorio = new HashMap<>();
		parametrosRelatorio.put("area", area.getCodigo());
		parametrosRelatorio.put("segmento", area.getSegmento().getCodigo());
		parametrosRelatorio.put("unidade", area.getUnidade().getCodigo_sia_sus());
		String nomeRelatorioJasper = "A2_Consolidacao_Anual";
		String nomeRelatorioSaida = "A2CONSOLIDACAOANUAL";
		try {
			this.arquivoRetornoA2ConsolidacaoAnual = relatorioUtil.geraRelatorio(parametrosRelatorio, 
					nomeRelatorioJasper, nomeRelatorioSaida, this.tipoRelatorio);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return arquivoRetornoA2ConsolidacaoAnual;
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
