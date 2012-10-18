package scs.web;

import java.util.HashMap;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;

import scs.util.ContextoUtil;
import scs.web.util.RelatorioUtil;

import com.sun.xml.bind.CycleRecoverable.Context;
import com.sun.xml.internal.ws.util.UtilException;

@ManagedBean(name="relatorioBean")
@RequestScoped
public class RelatorioBean {
	
	private StreamedContent arquivoRetorno;
	private int tipoRelatorio;
	
	public StreamedContent getArquivoRetorno() throws scs.util.UtilException {
		FacesContext context = FacesContext.getCurrentInstance();
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametrosRelatorio = new HashMap<>();
		String nomeRelatorioJasper = "SSA2_Monitoramento";
		String nomeRelatorioSaida = "SSA2_Monitormaneto";
		try {
			this.arquivoRetorno = relatorioUtil.geraRelatorio(parametrosRelatorio, 
					nomeRelatorioJasper, nomeRelatorioSaida, this.tipoRelatorio);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return arquivoRetorno;
	}
	
	public void setArquivoRetorno(StreamedContent arquivoRetorno) {
		this.arquivoRetorno = arquivoRetorno;
	}
	
	public int getTipoRelatorio() {
		return tipoRelatorio;
	}
	
	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	
	

}
