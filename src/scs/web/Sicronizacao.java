package scs.web;

import scs.acompanhamentoPadrao.AcompanhamentoPadrao;
import scs.acompanhamentoPadrao.AcompanhamentoPadraoRN;
import scs.acompcrianca.AcompCrianca;
import scs.acompcrianca.AcompCriancaRN;
import scs.agendamento.Agendamento;
import scs.agendamento.AgendamentoRN;
import scs.area.Area;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.XMLOutputter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import com.mchange.v2.async.StrandedTaskReporting;

import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.diabetes.Diabetes;
import scs.diabetes.DiabetesRN;
import scs.familiar.Familiar;
import scs.familiar.FamiliarRN;
import scs.gestante.Gestante;
import scs.gestante.GestanteRN;
import scs.hanseniase.Hanseniase;
import scs.hanseniase.HanseniaseRN;
import scs.hipertensao.HipertensaoRN;
import scs.hipertensao.Hipertesao;
import scs.microarea.Microarea;
import scs.municipio.Municipio;
import scs.municipio.MunicipioRN;
import scs.profissional.Profissional;
import scs.profissional.ProfissionalRN;
import scs.residencia.Residencia;
import scs.residencia.ResidenciaRN;
import scs.rua.Rua;
import scs.segmento.Segmento;
import scs.tuberculose.Tuberculose;
import scs.tuberculose.TuberculoseRN;
import scs.usuario.Usuario;
import scs.usuario.UsuarioRN;
import scs.util.CarregarXML;
import scs.util.HibernateUtil;
import scs.vacinas.Vacinas;
import scs.vacinas.VacinasRN;
import scs.visitas.Visitas;
import scs.visitas.VisitasRN;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import javax.servlet.ServletContext;

@ManagedBean(name = "exportacaoBean")
@RequestScoped
public class Sicronizacao {

	private CarregarXML xml;
	private Session session;
	private StreamedContent arquivoSicronizacao;
	Element scs;

	public void setSesson(Session session) {
		this.session = session;
	}

	public void importar(FileUploadEvent event) {
		xml = new CarregarXML();
		importarResidencias(event.getFile().getFileName());
		importarFamiliar(event.getFile().getFileName());
		importarVacinas(event.getFile().getFileName());
		importarAgendamento(event.getFile().getFileName());
		importarGestante(event.getFile().getFileName());
		importarHanseniase(event.getFile().getFileName());
		importarDiabetes(event.getFile().getFileName());
		importarHipertensao(event.getFile().getFileName());
		importarTuberculose(event.getFile().getFileName());
		importarCrianca(event.getFile().getFileName());
		importarVisitas(event.getFile().getFileName());
		importarAcomPadrao(event.getFile().getFileName());
		FacesMessage msg = new FacesMessage("Sucesso ao Importar o Arquivo:",
				event.getFile().getFileName() + ".");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void importarAcomPadrao(String nomeArquivo) {

		try {

			@SuppressWarnings("rawtypes")
			List<Element> acomps = xml.carregar(nomeArquivo, "ACOMPPADRAO");

			AcompanhamentoPadrao acompPadrao;
			for (Element acomp : acomps) {
				acompPadrao = new AcompanhamentoPadrao();

				acompPadrao.setIdfamiliar(acomp.getChildText("HASH"));
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							acomp.getChildText("DT_VISITA")).getTime());
					acompPadrao.setDataVisita(data); // ve data nascimento
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (acomp.getChildText("FL_HOSPITALIZADA")
						.equalsIgnoreCase("S")) {
					acompPadrao.setHospitalizada(true);
				} else {
					acompPadrao.setHospitalizada(false);
				}
				acompPadrao.setMotivo_hospitalizacao(acomp
						.getChildText("MOTIVO_HOSPITALIZACAO"));
				if (acomp.getChildText("FL_DOENTE").equalsIgnoreCase("S")) {
					acompPadrao.setDoente(true);
				} else {
					acompPadrao.setDoente(false);
				}
				acompPadrao.setQual_doenca(acomp.getChildText("DESC_DOENCA"));
				acompPadrao.setObservacao(acomp.getChildText("OBSERVACAO"));

				AcompanhamentoPadraoRN acomRN = new AcompanhamentoPadraoRN();
				acomRN.salvar(acompPadrao);

			}
			System.out.println("Acompanhamento Padrao importada!");
		} catch (FileNotFoundException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDOMException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public void importarVisitas(String nomeArquivo) {
		try {

			@SuppressWarnings("rawtypes")
			List<Element> visitas = xml.carregar(nomeArquivo, "VISITA");

			Visitas visi;
			for (Element visita : visitas) {
				visi = new Visitas();

				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							visita.getChildText("DATA")).getTime());
					visi.setData(data); // ve data nascimento
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					SimpleDateFormat format = new SimpleDateFormat("hh:mm");
					java.sql.Date hora = new java.sql.Date(format.parse(
							visita.getChildText("HORA")).getTime());
					visi.setHora(hora); // ve data nascimento
				} catch (Exception e) {
					// TODO: handle exception
				}

				if (!visita.getChildText("COD_AGENTE").equalsIgnoreCase("0000")) {
					UsuarioRN usuRN = new UsuarioRN();
					Usuario usuario = new Usuario();
					usuario = usuRN.carregar(Integer.parseInt(visita
							.getChildText("COD_AGENTE")));
					visi.setAgente(usuario);
				}
				visi.setEndereco(visita.getChildText("ENDERECO"));
				visi.setNumero(Integer.parseInt(visita.getChildText("NUMERO")));
				visi.setComplemento(visita.getChildText("COMPLEMENTO"));
				visi.setCasa_fechada(visita.getChildText("FL_CASA_FECHADA"));
				visi.setVisita_confirmada(visita
						.getChildText("FL_VISITA_CONFIRMADA"));

				VisitasRN visiRN = new VisitasRN();
				visiRN.salvar(visi);

			}
			System.out.println("Visitas importada!");
		} catch (FileNotFoundException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDOMException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	// importar residencias
	public void importarResidencias(String nomeArquivo) {
		try {

			@SuppressWarnings("rawtypes")
			List<Element> residencias = xml
					.carregar(nomeArquivo, "RESIDENCIAS");

			Residencia resid;
			for (Element residencia : residencias) {
				if (vDuplResid(residencia.getChildText("ENDERECO"),
						residencia.getChildText("NUMERO")) == null) {
					resid = new Residencia();
				} else {
					resid = vDuplResid(residencia.getChildText("ENDERECO"),
							residencia.getChildText("NUMERO"));
				}

				resid.setEndereco(pegaRua(residencia.getChildText("ENDERECO")));
				resid.setBairro(pegaBairro(residencia.getChildText("CODBAIRRO")));
				resid.setSegmento(pegaSegmento(residencia
						.getChildText("SEGTERRITORIAL")));
				resid.setArea(pegaArea(residencia.getChildText("AREA")));
				resid.setMicroarea(pegaMicroarea(residencia
						.getChildText("MICROAREA")));
				resid.setNum_residencia(Integer.parseInt(residencia
						.getChildText("NUMERO")));
				// ve data cadastro
				resid.setTipocasa(residencia.getChildText("TIPOCASA"));
				resid.setOutroTipoCasa(residencia.getChildText("TIPOCASAOUTRO"));
				resid.setDestlixo(residencia.getChildText("DESTLIXO"));
				resid.setTatamentoagua(residencia
						.getChildText("TRATAMENTOAGUA"));
				resid.setAbastecimentoagua(residencia
						.getChildText("ABASTECIMENTOAGUA"));
				resid.setDestfezes(residencia.getChildText("DESTFEZES"));
				resid.setCasodoenca(residencia.getChildText("CASODOENCA"));
				resid.setOurtoCasoDoenca(residencia
						.getChildText("CASODOENCAOUTRO"));
				resid.setMeiocomunicacao(residencia
						.getChildText("MEIOCOMUNICACAO"));
				resid.setParticipagrupo(residencia
						.getChildText("PARTICIPAGRUPO"));
				resid.setMeiotransporte(residencia
						.getChildText("MEIOTRANSPORTE"));
				resid.setPossuiplanosaude(residencia
						.getChildText("FL_PLANO_SAUDE"));
				if (residencia.getChildText("NUM_PESSOAS_COBERTAS").equals("")) {
					resid.setNumeropessoascobertasplanosaude(Integer
							.parseInt(residencia
									.getChildText("NUM_PESSOAS_COBERTAS")));
				}

				resid.setNomeplanosaude(residencia
						.getChildText("NOME_PLANO_SAUDE"));
				resid.setOutromeiocomunicacao(residencia
						.getChildText("MEIOCOMUNICACAO_OUTROS"));
				resid.setOutromeiotransporte(residencia
						.getChildText("MEIOTRANSPORTE_OUTROS"));
				resid.setOutroparticipagrupo(residencia
						.getChildText("PARTICIPAGRUPO_OUTROS"));
				resid.setNumerocomodos(Integer.parseInt(residencia
						.getChildText("NUM_COMODOS")));
				if (residencia.getChildText("POSSUI_ENERGIA_ELETRICA")
						.equalsIgnoreCase("S")) {
					resid.setPossuienergiaeletrica(true);
				} else {
					resid.setPossuienergiaeletrica(false);
				}
				if (!residencia.getChildText("NUMERO_FAMILIA").equals("")) {
					resid.setNumerofamilia(Integer.parseInt(residencia
							.getChildText("NUMERO_FAMILIA")));
				}
				resid.setComplemento(residencia.getChildText("COMPLEMENTO"));
				resid.setUtiliza_beneficio(residencia
						.getChildText("FL_APTO_BENEFICIO"));
				resid.setNomebeneficio("NOME_BENEFICIO");
				ResidenciaRN residRN = new ResidenciaRN();
				residRN.salvar(resid);

			}
			System.out.println("Residencia importada!");
		} catch (FileNotFoundException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDOMException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	// importar familiar
	public void importarFamiliar(String nomeArquivo) {
		try {

			@SuppressWarnings("rawtypes")
			List<Element> familiares = xml.carregar(nomeArquivo, "FAMILIAR");

			Familiar famili;
			for (Element familiar : familiares) {
				if (vDuplFamiliar(familiar.getChildText("HASH")) == null) {
					famili = new Familiar();
				} else {
					famili = vDuplFamiliar(familiar.getChildText("HASH"));
				}

				famili.setNome(familiar.getChildText("NOME").toUpperCase());
				famili.setRuaFamilia(pegaRua(familiar
						.getChildText("COD_ENDERECO")));
				famili.setNumero(Integer.parseInt(familiar
						.getChildText("NUMERO")));
				famili.setNomemae(familiar.getChildText("NOME_PAI"));
				famili.setNomepai(familiar.getChildText("NOME_MAE"));

				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							familiar.getChildText("DTNASCIMENTO")).getTime());
					famili.setDataNascimento(data); // ve data nascimento
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (familiar.getChildText("SEXO").equals("M")) {
					famili.setSexo('M');
				} else {
					famili.setSexo('F');
				}
				if (familiar.getChildText("FREQ_ESCOLA").equals("S")) {
					famili.setFreqEsc('S');
				} else {
					famili.setFreqEsc('N');
				}
				if (familiar.getChildText("ALFABETIZADO").equals("S")) {
					famili.setAlfabetizado('S');
				} else {
					famili.setAlfabetizado('N');
				}
				famili.setOcupacao(familiar.getChildText("OCUPACAO")
						.toUpperCase());
				if (familiar.getChildText("HANSENIASE").equals("S")) {
					famili.setHanseniase(true);
				} else {
					famili.setHanseniase(false);
				}
				if (familiar.getChildText("HIPERTENSAO").equals("S")) {
					famili.setHipertensao(true);
				} else {
					famili.setHipertensao(false);
				}
				if (familiar.getChildText("GESTANTE").equals("S")) {
					famili.setGestante(true);
				} else {
					famili.setGestante(false);
				}
				if (familiar.getChildText("TUBERCULOSE").equals("S")) {
					famili.setTuberculose(true);
				} else {
					famili.setTuberculose(false);
				}
				if (familiar.getChildText("ALCOOLISMO").equals("S")) {
					famili.setAlcolismo(true);
				} else {
					famili.setAlcolismo(false);
				}
				if (familiar.getChildText("CHAGAS").equals("S")) {
					famili.setChagas(true);
				} else {
					famili.setChagas(false);
				}
				if (familiar.getChildText("DEFICIENTE").equals("S")) {
					famili.setDeficiencia(true);
				} else {
					famili.setDeficiencia(false);
				}
				if (familiar.getChildText("MALARIA").equals("S")) {
					famili.setMalaria(true);
				} else {
					famili.setMalaria(false);
				}
				if (familiar.getChildText("DIABETE").equals("S")) {
					famili.setDiabestes(true);
				} else {
					famili.setDiabestes(false);
				}
				if (familiar.getChildText("EPILETICO").equals("S")) {
					famili.setEpilepsia(true);
				} else {
					famili.setEpilepsia(false);
				}
				if (familiar.getChildText("FL_OBITO").equals("S")) {
					famili.setObito(true);
				} else {
					famili.setObito(false);
				}
				if (familiar.getChildText("FL_MUDOU_SE").equals("S")) {
					famili.setMudou_se(true);
				} else {
					famili.setMudou_se(false);
				}
				famili.setIdMD5(familiar.getChildText("HASH"));
				famili.setComplemento(familiar.getChildText("COMPLEMENTO"));

				// adiciona o id da residencia e area
				Session session;
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				SQLQuery query = null;
				if (famili.getComplemento() != null) {
					query = session
							.createSQLQuery("select * from residencias r "
									+ " where r.endereco="
									+ famili.getRuaFamilia().getCodigo_rua()
											.toString()
									+ " and r.num_residencia= "
									+ famili.getNumero().toString()
									+ " and coalesce(complemento,'')='"
									+ famili.getComplemento().toString() + "'");

				} else {
					query = session
							.createSQLQuery("select * from residencias r "
									+ " where r.endereco="
									+ famili.getRuaFamilia().getCodigo_rua()
											.toString()
									+ " and r.num_residencia= "
									+ famili.getNumero().toString()
									+ " and coalesce(complemento,'')=''");
				}

				if (query.list().size() > 0) {
					Residencia res = new Residencia();
					ResidenciaRN resRN = new ResidenciaRN();
					List listRes = query.list();
					for (Object[] obj : (List<Object[]>) listRes) {
						String codigoStr = (String) obj[0].toString();
						res = resRN.carregar(Integer.parseInt(codigoStr));
					}
					famili.setResidencia(res);
					famili.setArea(res.getArea());

				} else {

				}
				// fim adiciona id residencia e id area

				FamiliarRN familRN = new FamiliarRN();
				familRN.salvar(famili);

			}

			System.out.println("Familiar importada!");
		} catch (FileNotFoundException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDOMException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	// importarAgenfamento
	public void importarAgendamento(String nomeArquivo) {
		try {

			@SuppressWarnings("rawtypes")
			List<Element> agendamentos = xml.carregar(nomeArquivo,
					"AGENDAMENTO");

			Agendamento agen;
			for (Element agendamento : agendamentos) {
				agen = new Agendamento();

				agen.setIdfamiliar(agendamento.getChildText("HASH"));
				agen.setDescricao(agendamento.getChildText("DESCRICAO")
						.toUpperCase());
				agen.setTpconsulta(agendamento.getChildText("TIPO_AGENDAMENTO")
						.toUpperCase());
				if (agendamento.getChildText("URGENTE").equals("S")) {
					agen.setUrgente(true);
				} else {
					agen.setUrgente(false);
				}
				agen.setAgendada(false);
				ProfissionalRN priRN = new ProfissionalRN();
				Profissional prof = new Profissional();
				prof = priRN.carregar(Integer.parseInt(agendamento
						.getChildText("PROFISSIONAL")));
				agen.setProfissional(prof);
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							agendamento.getChildText("DT_AGENDAMENTO"))
							.getTime());
					agen.setDtagendamento(data); // ve data nascimento
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					SimpleDateFormat format = new SimpleDateFormat("hh:mm");
					java.sql.Date hora = new java.sql.Date(format.parse(
							agendamento.getChildText("HORA_AGENDAMENTO"))
							.getTime());
					agen.setHora(hora); // ve data nascimento
				} catch (Exception e) {
					// TODO: handle exception
				}

				// adiciona o id da residencia e area
				Session session;
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				SQLQuery query = null;
				query = session
						.createSQLQuery("select id from familiar where idmd5='"
								+ agen.getIdfamiliar().toString() + "'");

				if (query.list().size() > 0) {
					Familiar fam = new Familiar();
					FamiliarRN famRN = new FamiliarRN();
					List listRes = query.list();
					for (Object[] obj : (List<Object[]>) listRes) {
						String codigoStr = (String) obj[0].toString();
						fam = famRN.carregar(Integer.parseInt(codigoStr));
					}
					agen.setArea(fam.getArea());
					
				} else {

				}
				// fim adiciona id residencia e id area

				AgendamentoRN agendamentoRN = new AgendamentoRN();
				agendamentoRN.salvar(agen);

			}
			System.out.println("Agendamento importada!");
		} catch (FileNotFoundException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDOMException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	// importa vacina
	public void importarVacinas(String nomeArquivo) {
		try {

			@SuppressWarnings("rawtypes")
			List<Element> vacinas = xml.carregar(nomeArquivo, "VACINA");

			Vacinas vaci;
			for (Element vacina : vacinas) {
				if (vDuplVacina(vacina.getChildText("HASH"),
						vacina.getChildText("TIPO_VACINA"),
						vacina.getChildText("DOSE_APLICADA")) == null) {
					vaci = new Vacinas();
				} else {
					vaci = vDuplVacina(vacina.getChildText("HASH"),
							vacina.getChildText("TIPO_VACINA"),
							vacina.getChildText("DOSE_APLICADA"));
				}

				vaci.setIdfamiliar(vacina.getChildText("HASH"));
				vaci.setTipoVacina(vacina.getChildText("TIPO_VACINA"));
				vaci.setDoseAplicada(vacina.getChildText("DOSE_APLICADA"));
				vaci.setLoteVacina(vacina.getChildText("LOTE"));
				vaci.setTipo(vacina.getChildText("TIPO"));
				if (vacina.getChildText("FL_APLICADA").equals("S")) {
					vaci.setAplicada(true);
				} else {
					vaci.setAplicada(false);
				}
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							vacina.getChildText("DT_APLICACAO")).getTime());
					vaci.setDataAplicacao(data); // ve data nascimento
				} catch (Exception e) {
					// TODO: handle exception
				}

				VacinasRN vacinaRN = new VacinasRN();
				vacinaRN.salvar(vaci);

			}
			System.out.println("Vacina importada!");
		} catch (FileNotFoundException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDOMException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public void importarHipertensao(String nomeArquivo) {
		try {

			@SuppressWarnings("rawtypes")
			List<Element> hipertensoes = xml.carregar(nomeArquivo,
					"HIPERTENSAO");

			Hipertesao hiper = null;
			for (Element hipertensao : hipertensoes) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							hipertensao.getChildText("DT_VISITA")).getTime());

					if (vDuplHipertensao(hipertensao.getChildText("HASH"), data) == null) {
						hiper = new Hipertesao();
					} else {
						hiper = vDuplHipertensao(
								hipertensao.getChildText("HASH"), data);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				hiper.setIdMD5familiar(hipertensao.getChildText("HASH"));
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							hipertensao.getChildText("DT_VISITA")).getTime());
					hiper.setDtVisita(data); // ve data nascimento

					data = new java.sql.Date(format.parse(
							hipertensao.getChildText("DT_ULTIMA_VISITA"))
							.getTime());
					hiper.setDtUltVisita(data); // ve data nascimento

				} catch (Exception e) {
					// TODO: handle exception
				}
				hiper.setFzDieta(hipertensao.getChildText("FAZ_DIETA"));
				hiper.setTmMedicacao(hipertensao.getChildText("TOMA_MEDICACAO"));
				hiper.setFzExFisicos(hipertensao.getChildText("FAZ_EXERCICIOS"));
				hiper.setPressaoArterial(hipertensao
						.getChildText("PRESSAO_ARTERIAL"));
				hiper.setObs(hipertensao.getChildText("OBSERVACAO"));

				HipertensaoRN hipertensaoRN = new HipertensaoRN();
				hipertensaoRN.salvar(hiper);

			}
			System.out.println("Hipertensão importada!");
		} catch (FileNotFoundException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDOMException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void importarTuberculose(String nomeArquivo) {
		try {

			@SuppressWarnings("rawtypes")
			List<Element> tuberculoses = xml.carregar(nomeArquivo,
					"TUBERCULOSE");

			Tuberculose tuber = null;
			for (Element tuberculose : tuberculoses) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							tuberculose.getChildText("DT_VISITA")).getTime());

					if (vDuplTuberculose(tuberculose.getChildText("HASH"), data) == null) {
						tuber = new Tuberculose();
					} else {
						tuber = vDuplTuberculose(
								tuberculose.getChildText("HASH"), data);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				tuber.setIdMD5familiar(tuberculose.getChildText("HASH"));
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							tuberculose.getChildText("DT_VISITA")).getTime());
					tuber.setDtvisita(data); // ve data nascimento

				} catch (Exception e) {
					// TODO: handle exception
				}
				tuber.setTmmeddiaria(tuberculose.getChildText("MEDIC_DIARIA"));
				tuber.setRecindesej(tuberculose
						.getChildText("REACOES_INDESEJADAS"));
				tuber.setExescar(tuberculose.getChildText("EXAME_ESCARRO"));
				tuber.setComexami(Integer.parseInt(tuberculose
						.getChildText("COMUNIC_EXAMINADOS")));
				tuber.setMn5anoscombcg(Integer.parseInt(tuberculose
						.getChildText("MENOR_BCG")));
				tuber.setObs(tuberculose.getChildText("OBSERVACAO"));

				TuberculoseRN tuberculoseRN = new TuberculoseRN();
				tuberculoseRN.salvar(tuber);

			}
			System.out.println("Tuberculose importada!");
		} catch (FileNotFoundException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDOMException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void importarCrianca(String nomeArquivo) {
		try {

			@SuppressWarnings("rawtypes")
			List<Element> acompanhamentos = xml
					.carregar(nomeArquivo, "CRIANCA");

			AcompCrianca acomp = null;
			for (Element acompanhamento : acompanhamentos) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							acompanhamento.getChildText("DT_VISITA")).getTime());

					if (vDuplAcompanhento(acompanhamento.getChildText("HASH"),
							data) == null) {
						acomp = new AcompCrianca();
					} else {
						acomp = vDuplAcompanhento(
								acompanhamento.getChildText("HASH"), data);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				acomp.setIdfamiliar(acompanhamento.getChildText("HASH"));
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							acompanhamento.getChildText("DT_VISITA")).getTime());
					acomp.setDtvisita(data); // ve data nascimento

				} catch (Exception e) {
					// TODO: handle exception
				}
				if (!acompanhamento.getChildText("ALTURA").equals("")) {
					acomp.setAltura(Float.valueOf(acompanhamento
							.getChildText("ALTURA")));
				}
				if (!acompanhamento.getChildText("PESO").equals("")) {
					acomp.setPeso(Float.valueOf(acompanhamento
							.getChildText("PESO")));
				}
				if (!acompanhamento.getChildText("PER_CEFALICO").equals("")) {
					acomp.setPerimetrocefalico(Float.valueOf(acompanhamento
							.getChildText("PER_CEFALICO")));
				}
				if (!acompanhamento.getChildText("APGAR5").equalsIgnoreCase("")) {
					acomp.setApgar(Float.valueOf(acompanhamento
							.getChildText("APGAR5")));
				}
				acomp.setTipoparto(acompanhamento.getChildText("TP_PARTO"));
				acomp.setSituacao(acompanhamento.getChildText("SITUACAO"));

				acomp.setObs(acompanhamento.getChildText("OBSERVACAO"));

				AcompCriancaRN acompRN = new AcompCriancaRN();
				acompRN.salvar(acomp);

			}
			System.out.println("Crianca importada!");
		} catch (FileNotFoundException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDOMException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void importarDiabetes(String nomeArquivo) {
		try {

			@SuppressWarnings("rawtypes")
			List<Element> diabetes = xml.carregar(nomeArquivo, "DIABETE");

			Diabetes diabe = null;
			for (Element diabete : diabetes) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							diabete.getChildText("DT_VISITA")).getTime());

					if (vDuplDiabetes(diabete.getChildText("HASH"), data) == null) {
						diabe = new Diabetes();
					} else {
						diabe = vDuplDiabetes(diabete.getChildText("HASH"),
								data);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				diabe.setIdMD5familiar(diabete.getChildText("HASH"));
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							diabete.getChildText("DT_VISITA")).getTime());
					diabe.setDtVisita(data); // ve data nascimento

					data = new java.sql.Date(format.parse(
							diabete.getChildText("DT_ULTIMA_VISITA")).getTime());
					diabe.setDtUltVisita(data); // ve data nascimento

				} catch (Exception e) {
					// TODO: handle exception
				}
				diabe.setFzDieta(diabete.getChildText("FAZ_DIETA"));
				diabe.setFzExFisicos(diabete.getChildText("FAZ_EXCERCICIOS"));
				diabe.setUsInsulina(diabete.getChildText("USA_INSULINA"));
				diabe.setTmHipoglicOral(diabete
						.getChildText("USA_HIPOGLICEMIANTE"));
				diabe.setObs(diabete.getChildText("OBSERVACAO"));

				DiabetesRN diabetesRN = new DiabetesRN();
				diabetesRN.salvar(diabe);

			}

			System.out.println("Diabetes importada!");
		} catch (FileNotFoundException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDOMException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void importarHanseniase(String nomeArquivo) {
		try {

			@SuppressWarnings("rawtypes")
			List<Element> hanseniases = xml.carregar(nomeArquivo, "HANSENIASE");

			Hanseniase hanse = null;
			for (Element hanseniase : hanseniases) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							hanseniase.getChildText("DT_VISITA")).getTime());

					if (vDuplHanseniase(hanseniase.getChildText("HASH"), data) == null) {
						hanse = new Hanseniase();
					} else {
						hanse = vDuplHanseniase(
								hanseniase.getChildText("HASH"), data);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				hanse.setIdMD5familiar(hanseniase.getChildText("HASH"));
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							hanseniase.getChildText("DT_VISITA")).getTime());
					hanse.setDtVisita(data); // ve data nascimento

					data = new java.sql.Date(format.parse(
							hanseniase.getChildText("DT_ULTIMA_CONSULTA"))
							.getTime());
					hanse.setDtUltCOnsulta(data); // ve data nascimento

					data = new java.sql.Date(format.parse(
							hanseniase.getChildText("DT_ULTIMA_DOSE"))
							.getTime());
					hanse.setDtUtDoseSupervisionada(data); // ve data nascimento

				} catch (Exception e) {
					// TODO: handle exception
				}
				hanse.setTmMedicacaoDiaria(hanseniase
						.getChildText("TOMA_MEDICACAO"));
				hanse.setFzAutosCuidados(hanseniase
						.getChildText("AUTO_CUIDADOS"));
				hanse.setComExaminados(Integer.parseInt(hanseniase
						.getChildText("COMUNICANTES_EXAMINADOS")));
				hanse.setCmRecebBCG(Integer.parseInt(hanseniase
						.getChildText("COMUNICANTES_BCG")));

				HanseniaseRN hanseniaseRN = new HanseniaseRN();
				hanseniaseRN.salvar(hanse);

			}
			System.out.println("Hanseniase importada!");
		} catch (FileNotFoundException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDOMException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void importarGestante(String nomeArquivo) {

		try {

			@SuppressWarnings("rawtypes")
			List<Element> gestantes = xml.carregar(nomeArquivo, "GESTANTE");

			Gestante gest = null;
			for (Element gestante : gestantes) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							gestante.getChildText("DT_VISITA")).getTime());

					if (vDuplGestante(gestante.getChildText("HASH"), data) == null) {
						gest = new Gestante();
					} else {
						gest = vDuplGestante(gestante.getChildText("HASH"),
								data);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				gest.setIdMD5familiar(gestante.getChildText("HASH"));
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(
							gestante.getChildText("DT_VISITA")).getTime());
					gest.setDtVisita(data); // ve data nascimento

					data = new java.sql.Date(format.parse(
							gestante.getChildText("DT_ULTIMA_REGRA")).getTime());
					gest.setDtUltRegra(data); // ve data nascimento

					data = new java.sql.Date(format.parse(
							gestante.getChildText("DT_PROVAVEL_PARTO"))
							.getTime());
					gest.setDtProvavelParto(data); // ve data nascimento

					data = new java.sql.Date(format.parse(
							gestante.getChildText("DT_PRE_NATAL")).getTime());
					gest.setDtConsulPreNatal(data); // ve data nascimento
				} catch (Exception e) {
					// TODO: handle exception
				}
				gest.setEstNutricional(gestante.getChildText("EST_NUTRICIONAL"));
				gest.setMesGestacao(Integer.parseInt(gestante
						.getChildText("MES_GESTACAO")));
				gest.setFr6mGestacao(retornBoolean(gestante
						.getChildText("MAIS6GESTACOES")));
				gest.setFr36ouMais(retornBoolean(gestante
						.getChildText("MAIS36ANOS")));
				gest.setFrSangramento(retornBoolean(gestante
						.getChildText("SANGRAMENTO")));
				gest.setFrDiabetes(retornBoolean(gestante
						.getChildText("DIABETES")));
				gest.setFrNatrimAborto(retornBoolean(gestante
						.getChildText("NATIMORTO")));
				gest.setFrMeno20anos(retornBoolean(gestante
						.getChildText("MENOS20ANOS")));
				gest.setFrEdema(retornBoolean(gestante.getChildText("EDEMA")));
				gest.setFrPressaoAlta(retornBoolean(gestante
						.getChildText("PRESSAOALTA")));
				gest.setObs(gestante.getChildText("OBSERVACAO"));
				gest.setResultado_gestacao(gestante
						.getChildText("RESULTADO_GESTACAO"));

				GestanteRN gestanteRN = new GestanteRN();
				gestanteRN.salvar(gest);

			}
			System.out.println("Gestante importada!");
		} catch (FileNotFoundException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDOMException e) {
			FacesMessage msg = new FacesMessage("Erro ao importar: "
					+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public boolean retornBoolean(String a) {
		if (a.equals("S")) {
			return true;
		} else {
			return false;
		}
	}

	// traz um objeto rua
	private Rua pegaRua(String codigo) {
		Session session2;
		session2 = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session2.createQuery("From Rua where codigo_rua='"
				+ codigo + "'");
		List<Rua> rua = query.list();
		return rua.get(0);
	}

	// traz um objeto bairro
	private Bairro pegaBairro(String codigo) {
		Session session2;
		session2 = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session2.createQuery("From Bairro where codigo_bairro='"
				+ codigo + "'");
		List<Bairro> bairro = query.list();
		return bairro.get(0);
	}

	// traz um objeto segmento
	private Segmento pegaSegmento(String codigo) {
		Session session2;
		session2 = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session2
				.createQuery("From Segmento where codigo_segmento='" + codigo
						+ "'");
		List<Segmento> segmento = query.list();
		return segmento.get(0);
	}

	// traz um objeto area
	private Area pegaArea(String codigo) {
		Session session2;
		session2 = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session2.createQuery("From Area where codigo_area='"
				+ codigo + "'");
		List<Area> area = query.list();
		return area.get(0);
	}

	// traz um objeto microarea
	private Microarea pegaMicroarea(String codigo) {
		Session session2;
		session2 = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session2
				.createQuery("From Microarea where codigo_microarea='" + codigo
						+ "'");
		List<Microarea> microarea = query.list();
		return microarea.get(0);
	}

	// verifica se Tuberculose ja existe
	public Tuberculose vDuplTuberculose(String hash, Date data)
			throws ParseException {
		Session session2;
		session2 = HibernateUtil.getSessionFactory().getCurrentSession();

		SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat out = new SimpleDateFormat("dd.MM.yyyy");

		String result = out.format(in.parse(data.toString()));

		System.out.println(String.valueOf(result));
		Query query = session2
				.createQuery("From Tuberculose where idMD5familiar='" + hash
						+ "' and dtvisita='" + result + "'");
		if (query.list().size() > 0) {
			List<Tuberculose> resid = query.list();
			return resid.get(0);
		} else {
			return null;
		}
	}

	// verifica se Tuberculose ja existe
	public AcompCrianca vDuplAcompanhento(String hash, Date data)
			throws ParseException {
		Session session2;
		session2 = HibernateUtil.getSessionFactory().getCurrentSession();

		SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat out = new SimpleDateFormat("dd.MM.yyyy");

		String result = out.format(in.parse(data.toString()));

		System.out.println(String.valueOf(result));
		Query query = session2
				.createQuery("From AcompCrianca where idfamiliar='" + hash
						+ "' and dtvisita='" + result + "'");
		if (query.list().size() > 0) {
			List<AcompCrianca> resid = query.list();
			return resid.get(0);
		} else {
			return null;
		}
	}

	// verifica se GESTANTE ja existe
	public Hipertesao vDuplHipertensao(String hash, Date data)
			throws ParseException {
		Session session2;
		session2 = HibernateUtil.getSessionFactory().getCurrentSession();

		SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat out = new SimpleDateFormat("dd.MM.yyyy");

		String result = out.format(in.parse(data.toString()));

		System.out.println(String.valueOf(result));
		Query query = session2
				.createQuery("From Hipertesao where idMD5familiar='" + hash
						+ "' and dtVisita='" + result + "'");
		if (query.list().size() > 0) {
			List<Hipertesao> resid = query.list();
			return resid.get(0);
		} else {
			return null;
		}
	}

	// verifica se GESTANTE ja existe
	public Gestante vDuplGestante(String hash, Date data) throws ParseException {
		Session session2;
		session2 = HibernateUtil.getSessionFactory().getCurrentSession();

		SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat out = new SimpleDateFormat("dd.MM.yyyy");

		String result = out.format(in.parse(data.toString()));

		System.out.println(String.valueOf(result));
		Query query = session2
				.createQuery("From Gestante where idMD5familiar='" + hash
						+ "' and dtVisita='" + result + "'");
		if (query.list().size() > 0) {
			List<Gestante> resid = query.list();
			return resid.get(0);
		} else {
			return null;
		}
	}

	// verifica se hanseniase ja existe
	public Hanseniase vDuplHanseniase(String hash, Date data)
			throws ParseException {
		Session session2;
		session2 = HibernateUtil.getSessionFactory().getCurrentSession();

		SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat out = new SimpleDateFormat("dd.MM.yyyy");

		String result = out.format(in.parse(data.toString()));

		System.out.println(String.valueOf(result));
		Query query = session2
				.createQuery("From Hanseniase where idMD5familiar='" + hash
						+ "' and dtVisita='" + result + "'");
		if (query.list().size() > 0) {
			List<Hanseniase> resid = query.list();
			return resid.get(0);
		} else {
			return null;
		}
	}

	// verifica se diabetes ja existe
	public Diabetes vDuplDiabetes(String hash, Date data) throws ParseException {
		Session session2;
		session2 = HibernateUtil.getSessionFactory().getCurrentSession();

		SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat out = new SimpleDateFormat("dd.MM.yyyy");

		String result = out.format(in.parse(data.toString()));

		Query query = session2
				.createQuery("From Diabetes where idMD5familiar='" + hash
						+ "' and dtVisita='" + result + "'");
		if (query.list().size() > 0) {
			List<Diabetes> resid = query.list();
			return resid.get(0);
		} else {
			return null;
		}
	}

	// verifica se residencia ja existe
	public Residencia vDuplResid(String rua, String numero) {
		Session session2;
		session2 = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session2
				.createQuery("From Residencia where endereco.codigo_rua=" + rua
						+ " and num_residencia=" + numero);
		if (query.list().size() > 0) {
			List<Residencia> resid = query.list();
			return resid.get(0);
		} else {
			return null;
		}
	}

	// verifica se familiar ja existe
	public Familiar vDuplFamiliar(String hash) {
		Session session2;
		session2 = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session2.createQuery("From Familiar where idMD5='" + hash
				+ "'");
		if (query.list().size() > 0) {
			List<Familiar> famil = query.list();
			return famil.get(0);
		} else {
			return null;
		}
	}

	// verifica se vacina ja existe
	public Vacinas vDuplVacina(String hash, String vacina, String dose) {
		Session session2;
		session2 = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session2.createQuery("From Vacinas where idfamiliar='"
				+ hash + "' and tipoVacina='" + vacina + "' and doseAplicada='"
				+ dose + "'");
		if (query.list().size() > 0) {
			List<Vacinas> vaci = query.list();
			return vaci.get(0);
		} else {
			return null;
		}
	}

	// exportação de dados
	public void exortar() {

		scs = new Element("scs");
		expoUsuarios();
		expoBairros();
		// expoSegmento();
		// expoArea();
		// expoMicroAreas();
		expoRuas();
		expoResidencias();
		expoFamiliares();
		expoHanseniase();
		expoDiabetes();
		expoHipertensao();
		expoGestante();
		expoTuberculose();
		expoAcompCrianca();
		expoProfissionais();
		expoAgendamentos();
		expoAcompPadrao();

		Document doc = new Document();
		doc.setRootElement(scs);
		FacesContext context = FacesContext.getCurrentInstance();

		try {

			XMLOutputter xout = new XMLOutputter();

			OutputStream out = new FileOutputStream(new File("c:/scs/scs.xml"));
			// FileWriter out = new FileWriter(new
			// File("/home/publico/arquivo.xml"));

			xout.output(doc, out);

			FacesMessage msg = new FacesMessage(
					"Sucesso ao Exportar Caminho:C:\\scs\\scs.xml");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * InputStream stream = ((ServletContext) FacesContext
		 * .getCurrentInstance().getExternalContext().getContext())
		 * .getResourceAsStream("/publico/scs.xml"); arquivoSicronizacao = new
		 * DefaultStreamedContent(stream, "/publico/xml", "scs.xml");
		 * context.addMessage(null, new FacesMessage(
		 * "Exportação Realizada com Sucesso! Localização C:\\scs\\scs.xml",
		 * null)); return arquivoSicronizacao;
		 */
	}

	private void expoAcompPadrao() {

		AcompanhamentoPadraoRN acompRN = new AcompanhamentoPadraoRN();
		List<AcompanhamentoPadrao> listaprof = acompRN.listar();

		if (listaprof.size() > 0) {
			for (AcompanhamentoPadrao acomp : listaprof) {
				Element dados = new Element("acomppadrao");
				Element id = new Element("id");
				Element hospitalizada = new Element("hospitalizada");
				Element motivo_hospitalizacao = new Element(
						"motivo_hospitalizacao");
				Element doente = new Element("doente");
				Element qual_doenca = new Element("qual_doenca");
				Element observacao = new Element("observacao");
				Element idfamiliar = new Element("idfamiliar");
				Element datavisita = new Element("datavisita");

				id.setText(String.valueOf(acomp.getId()));
				if (acomp.getHospitalizada() == true) {
					hospitalizada.setText("S");
				} else {
					hospitalizada.setText("N");
				}
				motivo_hospitalizacao.setText(acomp.getMotivo_hospitalizacao());
				if (acomp.getDoente() == true) {
					doente.setText("S");
				} else {
					doente.setText("N");
				}
				qual_doenca.setText(acomp.getQual_doenca());
				observacao.setText(acomp.getObservacao());
				idfamiliar.setText(acomp.getIdfamiliar());
				datavisita.setText(transformaDateString(acomp.getDataVisita()));

				dados.addContent(id);
				dados.addContent(hospitalizada);
				dados.addContent(motivo_hospitalizacao);
				dados.addContent(doente);
				dados.addContent(qual_doenca);
				dados.addContent(observacao);
				dados.addContent(idfamiliar);
				dados.addContent(datavisita);

				scs.addContent(dados);

			}
		}

		System.out.println("XML acopanhamento padrão com sucesso!");

	}

	private void expoProfissionais() {
		ProfissionalRN profRN = new ProfissionalRN();
		List<Profissional> listaprof = profRN.listar();

		if (listaprof.size() > 0) {
			for (Profissional prof : listaprof) {
				Element dados = new Element("profissional");
				Element id = new Element("id");
				Element nome = new Element("nome");
				Element tipo = new Element("tipo");
				Element especialidade = new Element("especialidade");

				id.setText(String.valueOf(prof.getId()));
				nome.setText(prof.getNome());
				tipo.setText(prof.getTipo());
				especialidade.setText(prof.getEspecialidade());

				dados.addContent(id);
				dados.addContent(nome);
				dados.addContent(tipo);
				dados.addContent(especialidade);

				scs.addContent(dados);

			}
		}

		System.out.println("XML profissional com sucesso!");

	}

	private void expoAgendamentos() {
		AgendamentoRN agendRN = new AgendamentoRN();
		List<Agendamento> listagen = agendRN.listar();

		if (listagen.size() > 0) {
			for (Agendamento agend : listagen) {
				Element dados = new Element("agendamento");
				Element descricao = new Element("descricao");
				Element idfamiliar = new Element("idfamiliar");
				Element urgente = new Element("urgente");
				Element dtagendamento = new Element("dtagendamento");
				Element tpconsulta = new Element("tpconsulta");
				Element hora = new Element("hora");
				Element profissional = new Element("profissional");
				if (agend.getDtagendamento() != null) {
					descricao.setText(agend.getDescricao());
					idfamiliar.setText(agend.getIdfamiliar());
					if (agend.isUrgente() == true) {
						urgente.setText("S");
					} else {
						urgente.setText("N");
					}
					dtagendamento.setText(transformaDateString(agend
							.getDtagendamento()));
					tpconsulta.setText(agend.getTpconsulta());
					hora.setText(agend.getHoraConvertida());
					profissional.setText(String.valueOf(agend.getProfissional()
							.getId()));

					if (agend.isConsultarealizada() == false) {
						dados.addContent(descricao);
						dados.addContent(idfamiliar);
						dados.addContent(urgente);
						dados.addContent(dtagendamento);
						dados.addContent(tpconsulta);
						dados.addContent(hora);
						dados.addContent(profissional);

						scs.addContent(dados);

					}
				}
			}

		}
		System.out.println("XML agendamento com sucesso!");

	}

	private void expoUsuarios() {
		UsuarioRN usuarioRN = new UsuarioRN();
		List<Usuario> listusuarios = usuarioRN.listar();

		if (listusuarios.size() > 0) {
			for (Usuario usuario : listusuarios) {
				Element dados = new Element("usuario");
				Element codigo = new Element("codigoUsuario");
				Element nome = new Element("nomeUsuario");
				Element login = new Element("loginUsuario");
				Element senha = new Element("senhaUsuario");
				Element ativo = new Element("ativoUsuario");

				codigo.setText(usuario.getCodigo().toString());
				nome.setText(usuario.getNome());
				login.setText(usuario.getLogin());
				senha.setText(usuario.getSenha());
				if (usuario.isAtivo()) {
					ativo.setText("S");
				} else
					ativo.setText("N");

				if (verificaUsuarioMobile(usuario.getCodigo())) {
					dados.addContent(codigo);
					dados.addContent(nome);
					dados.addContent(login);
					dados.addContent(senha);
					dados.addContent(ativo);

					scs.addContent(dados);

				}
			}

		}
		System.out.println("XML usuario com sucesso!");
	}

	private boolean verificaUsuarioMobile(Integer codigo) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select u.permissao from usuario_permissao u where u.funcionario= "
						+ String.valueOf(codigo)
						+ " and u.permissao='ROLE_USU_MOBILE' ");
		List permissoes = query.list();
		// query.setParameter("idfunc", codigo).uniqueResult();
		if (permissoes.isEmpty()) {
			return false;
		} else
			return true;

	}

	public void expoBairros() {
		BairroRN bairroRN = new BairroRN();
		List<Bairro> listbairro = bairroRN.listar();

		if (listbairro.size() > 0) {
			for (Bairro bairro : listbairro) {
				Element dados = new Element("bairro");
				Element codigo = new Element("codigoBairro");
				Element descricao = new Element("descricaoBairro");
				Element cep = new Element("cepBairro");

				codigo.setText(bairro.getCodigo_bairro().toString());
				descricao.setText(bairro.getDescricao());
				cep.setText(bairro.getCep());

				dados.addContent(codigo);
				dados.addContent(descricao);
				dados.addContent(cep);

				scs.addContent(dados);

			}

		}
		System.out.println("XML bairro com sucesso!");
	}

	public void expoRuas() {
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select mr.cod_rua,r.descricao,m.idagente,m.codigo_microarea,m.idarea,a.codigo_segmento,s.idbairro from microarea_ruas mr "
						+ "join ruas r on mr.cod_rua=r.codigo_rua "
						+ "join microarea m on mr.cod_microarea=m.codigo_microarea "
						+ "join area a on a.codigo_area=m.idarea "
						+ "join segmento s on s.codigo_segmento=a.codigo_segmento");

		List listaRuas = query.list();

		if (listaRuas.size() > 0) {
			for (Object[] obj : (List<Object[]>) listaRuas) {

				Element dados = new Element("rua");
				Element codigo = new Element("codigoRua");
				Element descricao = new Element("descricaoRua");
				Element codigoAgente = new Element("codigoAgenteRua");
				Element codigoArea = new Element("codigoAreaRua");
				Element codigoMicroarea = new Element("codigoMicroareaRua");
				Element codigoSegmento = new Element("codigoSegmentoRua");
				Element codigoBairro = new Element("codigoBairroRua");

				String codigoStr = (String) obj[0].toString(), descricaoStr = (String) obj[1]
						.toString(), codigoAgenteStr = (String) obj[2]
						.toString(), codigoMicroareaStr = (String) obj[3]
						.toString(), codigoAreaStr = (String) obj[4].toString(), codigoSegmentoStr = (String) obj[5]
						.toString(), codigoBairroStr = (String) obj[6]
						.toString();

				codigo.setText(codigoStr);
				descricao.setText(descricaoStr);
				codigoAgente.setText(codigoAgenteStr);
				codigoArea.setText(codigoAreaStr);
				codigoMicroarea.setText(codigoMicroareaStr);
				codigoSegmento.setText(codigoSegmentoStr);
				codigoBairro.setText(codigoBairroStr);

				dados.addContent(codigo);
				dados.addContent(descricao);
				dados.addContent(codigoAgente);
				dados.addContent(codigoArea);
				dados.addContent(codigoMicroarea);
				dados.addContent(codigoSegmento);
				dados.addContent(codigoBairro);

				scs.addContent(dados);

			}

		}
		System.out.println("XML ruas com sucesso!");

	}

	public void expoResidencias() {
		ResidenciaRN residenciaRN = new ResidenciaRN();
		List<Residencia> listResidencia = residenciaRN.listar();

		MunicipioRN munRN = new MunicipioRN();
		List<Municipio> listMuni = munRN.listar();

		if (listResidencia.size() > 0) {
			for (Residencia residencia : listResidencia) {
				Element dados = new Element("residencia");
				Element codigoRua = new Element("codigoRuaResidencia");
				Element nomeRua = new Element("nomeRuaResicencia");
				Element num_residencia = new Element("num_residenciaResidencia");
				Element codigoBairro = new Element("codigoBairroResidencia");
				Element nomeBairro = new Element("nomeBairroResidencia");
				Element segmento = new Element("segmentoResidencia");
				Element area = new Element("areaResidencia");
				Element microarea = new Element("microareaResidencia");
				Element tipocasa = new Element("tipocasaResidencia");
				Element outrotipocasa = new Element("outrotipocasaResidencia");
				Element destlixo = new Element("destlixoResidencia");
				Element tatamentoagua = new Element("tatamentoaguaResidencia");
				Element abastecimentoagua = new Element(
						"abastecimentoaguaResidencia");
				Element destfezes = new Element("destfezesResidencia");
				Element casodoenca = new Element("casodoencaResidencia");
				Element ourtocasodoenca = new Element(
						"ourtocasodoencaResidencia");
				Element meiocomunicacao = new Element(
						"meiocomunicacaoResidencia");
				Element participagrupo = new Element("participagrupoResidencia");
				Element meiotransporte = new Element("meiotransporteResidencia");
				Element nomeMunicipio = new Element("nomeMunicipioResidencia");
				Element codIBGE = new Element("codIBGEResidencia");
				Element cep = new Element("cep");

				Element outromeiocomunicacao = new Element(
						"outromeiocomunicacao");
				Element outromeiotransporte = new Element("outromeiotransporte");
				Element outroparticipagrupo = new Element("outroparticipagrupo");
				Element possuiplanosaude = new Element("possuiplanosaude");
				Element numeropessoascobertasplanosaude = new Element(
						"numeropessoascobertasplanosaude");
				Element nomeplanosaude = new Element("nomeplanosaude");
				Element numerocomodos = new Element("numerocomodos");
				Element possuienergiaeletrica = new Element(
						"possuienergiaeletrica");
				Element numerofamilia = new Element("numero_familia");
				Element complemento = new Element("complemento");
				Element utiliza_beneficio = new Element("utiliza_beneficio");
				Element nomebeneficio = new Element("nomebeneficio");

				utiliza_beneficio.setText(residencia.getUtiliza_beneficio());
				nomebeneficio.setText(residencia.getNomebeneficio());
				codigoRua.setText(residencia.getEndereco().getCodigo_rua()
						.toString());
				if (residencia.getNumerofamilia() != null) {
					numerofamilia.setText(residencia.getNumerofamilia()
							.toString());
				}
				nomeRua.setText(residencia.getEndereco().getDescricao());
				num_residencia.setText(residencia.getNum_residencia()
						.toString());
				complemento.setText(residencia.getComplemento());
				codigoBairro.setText(residencia.getBairro().getCodigo_bairro()
						.toString());
				nomeBairro.setText(residencia.getBairro().getDescricao());
				segmento.setText(residencia.getSegmento().getCodigo_segmento()
						.toString());
				area.setText(residencia.getArea().getCodigo_area().toString());
				microarea.setText(residencia.getMicroarea()
						.getCodigo_microarea().toString());
				tipocasa.setText(residencia.getTipocasa());
				outrotipocasa.setText(residencia.getOutroTipoCasa());
				destlixo.setText(residencia.getDestlixo());
				tatamentoagua.setText(residencia.getTatamentoagua());
				abastecimentoagua.setText(residencia.getAbastecimentoagua());
				destfezes.setText(residencia.getDestfezes());
				casodoenca.setText(residencia.getCasodoenca());
				ourtocasodoenca.setText(residencia.getOurtoCasoDoenca());
				meiocomunicacao.setText(residencia.getMeiocomunicacao());
				participagrupo.setText(residencia.getParticipagrupo());

				outromeiocomunicacao.setText(residencia
						.getOutromeiocomunicacao());
				outromeiotransporte
						.setText(residencia.getOutromeiotransporte());
				outroparticipagrupo
						.setText(residencia.getOutroparticipagrupo());
				possuiplanosaude.setText(residencia.getPossuiplanosaude());
				numeropessoascobertasplanosaude.setText(String
						.valueOf(residencia
								.getNumeropessoascobertasplanosaude()));
				nomeplanosaude.setText(residencia.getNomeplanosaude());
				numerocomodos.setText(String.valueOf(residencia
						.getNumerocomodos()));
				if (residencia.isPossuienergiaeletrica() == true) {
					possuienergiaeletrica.setText("S");
				} else {
					possuienergiaeletrica.setText("N");
				}

				meiotransporte.setText(residencia.getMeiotransporte());
				if (listMuni.size() > 0) {
					nomeMunicipio.setText(listMuni.get(0).getNome());
					codIBGE.setText(listMuni.get(0).getCodigo_ibge().toString());
					cep.setText(listMuni.get(0).getCep());
				}

				dados.addContent(codigoRua);
				dados.addContent(nomeRua);
				dados.addContent(num_residencia);
				dados.addContent(codigoBairro);
				dados.addContent(nomeBairro);
				dados.addContent(segmento);
				dados.addContent(area);
				dados.addContent(microarea);
				dados.addContent(tipocasa);
				dados.addContent(outrotipocasa);
				dados.addContent(destlixo);
				dados.addContent(tatamentoagua);
				dados.addContent(abastecimentoagua);
				dados.addContent(destfezes);
				dados.addContent(casodoenca);
				dados.addContent(ourtocasodoenca);
				dados.addContent(meiocomunicacao);
				dados.addContent(participagrupo);
				dados.addContent(meiotransporte);
				if (listMuni.size() > 0) {
					dados.addContent(nomeMunicipio);
					dados.addContent(codIBGE);
					dados.addContent(cep);
				}
				dados.addContent(outromeiocomunicacao);
				dados.addContent(outromeiotransporte);
				dados.addContent(outroparticipagrupo);
				dados.addContent(possuiplanosaude);
				dados.addContent(numeropessoascobertasplanosaude);
				dados.addContent(nomeplanosaude);
				dados.addContent(numerocomodos);
				dados.addContent(possuienergiaeletrica);
				dados.addContent(numerofamilia);
				dados.addContent(complemento);
				dados.addContent(nomebeneficio);
				dados.addContent(utiliza_beneficio);

				scs.addContent(dados);

			}

		}
		System.out.println("XML residencia com sucesso!");
	}

	public void expoFamiliares() {
		FamiliarRN familiaresRN = new FamiliarRN();
		List<Familiar> listFamiliar = familiaresRN.listar();

		if (listFamiliar.size() > 0) {
			for (Familiar familiar : listFamiliar) {
				Element dados = new Element("familiar");
				Element idMD5 = new Element("idMD5Familiar");
				Element nome = new Element("nomeFamiliar");
				Element codigoRua = new Element("codigoRuaFamiliar");
				Element rua = new Element("ruaFamiliar");
				Element numero = new Element("numeroFamiliar");
				Element dataNascimento = new Element("dataNascimentoFamiliar");
				Element sexo = new Element("sexoFamiliar");
				Element freqEsc = new Element("freqEscFamiliar");
				Element alfabetizado = new Element("alfabetizadoFamiliar");
				Element ocupacao = new Element("ocupacaoFamiliar");
				Element hanseniase = new Element("hanseniaseFamiliar");
				Element hipertensao = new Element("hipertensaoFamiliar");
				Element gestante = new Element("gestanteFamiliar");
				Element tuberculose = new Element("tuberculoseFamiliar");
				Element alcolismo = new Element("alcolismoFamiliar");
				Element chagas = new Element("chagasFamiliar");
				Element deficiencia = new Element("deficienciaFamiliar");
				Element malaria = new Element("malariaFamiliar");
				Element diabetes = new Element("diabetesFamiliar");
				Element epilepsia = new Element("epilepsiaFamiliar");
				Element nomepai = new Element("nomepai");
				Element nomemae = new Element("nomemae");
				Element complemento = new Element("complemento");
				Element obito = new Element("obito");
				Element mudou_se = new Element("mudou_se");
				if (familiar.isMudou_se() == false) {
					if (familiar.isObito() == true) {
						obito.setText("S");
					} else {
						obito.setText("N");
					}
					if (familiar.isMudou_se() == true) {
						mudou_se.setText("S");
					} else {
						mudou_se.setText("N");
					}

					idMD5.setText(familiar.getIdMD5());
					nome.setText(familiar.getNome());
					codigoRua.setText(familiar.getRuaFamilia().getCodigo_rua()
							.toString());
					rua.setText(familiar.getRuaFamilia().getDescricao());
					numero.setText(familiar.getNumero().toString());
					DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
					dataNascimento.setText(formatador.format(familiar
							.getDataNascimento()));
					sexo.setText(String.valueOf(familiar.getSexo()));
					freqEsc.setText(String.valueOf(familiar.getFreqEsc()));
					alfabetizado.setText(String.valueOf(familiar
							.getAlfabetizado()));
					ocupacao.setText(familiar.getOcupacao());
					hanseniase.setText(transformBooleanString(familiar
							.getHanseniase()));
					hipertensao.setText(transformBooleanString(familiar
							.getHipertensao()));
					gestante.setText(transformBooleanString(familiar
							.getGestante()));
					tuberculose.setText(transformBooleanString(familiar
							.getTuberculose()));
					alcolismo.setText(transformBooleanString(familiar
							.getAlcolismo()));
					chagas.setText(transformBooleanString(familiar.getChagas()));
					deficiencia.setText(transformBooleanString(familiar
							.getDeficiencia()));
					malaria.setText(transformBooleanString(familiar
							.getMalaria()));
					diabetes.setText(transformBooleanString(familiar
							.getDiabestes()));
					epilepsia.setText(transformBooleanString(familiar
							.getEpilepsia()));
					if (familiar.getNomemae() != null) {
						nomemae.setText(familiar.getNomemae().toString());
					}
					if (familiar.getNomepai() != null) {
						nomepai.setText(familiar.getNomepai().toString());
					}
					complemento.setText(familiar.getComplemento());

					dados.addContent(idMD5);
					dados.addContent(nome);
					dados.addContent(codigoRua);
					dados.addContent(rua);
					dados.addContent(numero);
					dados.addContent(dataNascimento);
					dados.addContent(sexo);
					dados.addContent(freqEsc);
					dados.addContent(alfabetizado);
					dados.addContent(ocupacao);
					dados.addContent(hanseniase);
					dados.addContent(hipertensao);
					dados.addContent(gestante);
					dados.addContent(tuberculose);
					dados.addContent(alcolismo);
					dados.addContent(chagas);
					dados.addContent(deficiencia);
					dados.addContent(malaria);
					dados.addContent(diabetes);
					dados.addContent(epilepsia);
					dados.addContent(nomemae);
					dados.addContent(nomepai);
					dados.addContent(complemento);
					dados.addContent(obito);
					dados.addContent(mudou_se);

					scs.addContent(dados);
				}
			}

		}
		System.out.println("XML failiares com sucesso!");

	}

	public String transformBooleanString(boolean b) {
		if (b == true) {
			return "S";
		} else {
			return "N";
		}

	}

	public String transformaDateString(Date d) {
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(d);
	}

	public void expoHanseniase() {
		HanseniaseRN hanseniaseRN = new HanseniaseRN();
		List<Hanseniase> listhanseniase = hanseniaseRN.listar();

		if (listhanseniase.size() > 0) {
			for (Hanseniase hanaseniase : listhanseniase) {
				Element dados = new Element("hanseniase");
				Element idmd5familiar = new Element("idmd5familiar");
				Element dtvisita = new Element("dtvisita");
				Element tmmedicacaodiaria = new Element("tmmedicacaodiaria");
				Element dtutdosesupervisionada = new Element(
						"dtutdosesupervisionada");
				Element fzautoscuidados = new Element("fzautoscuidados");
				Element comexaminados = new Element("comexaminados");
				Element cmrecebbcg = new Element("cmrecebbcg");
				Element observacoes = new Element("observacoes");
				Element dtultconsulta = new Element("dtultconsulta");

				idmd5familiar.setText(hanaseniase.getIdMD5familiar());
				dtvisita.setText(transformaDateString(hanaseniase.getDtVisita()));
				tmmedicacaodiaria.setText(hanaseniase.getTmMedicacaoDiaria());
				dtutdosesupervisionada.setText(transformaDateString(hanaseniase
						.getDtUtDoseSupervisionada()));
				fzautoscuidados.setText(hanaseniase.getFzAutosCuidados());
				comexaminados
						.setText(hanaseniase.getComExaminados().toString());
				cmrecebbcg.setText(hanaseniase.getCmRecebBCG().toString());
				observacoes.setText(hanaseniase.getObservacoes());
				dtultconsulta.setText(transformaDateString(hanaseniase
						.getDtUltCOnsulta()));

				dados.addContent(idmd5familiar);
				dados.addContent(dtvisita);
				dados.addContent(tmmedicacaodiaria);
				dados.addContent(dtutdosesupervisionada);
				dados.addContent(fzautoscuidados);
				dados.addContent(comexaminados);
				dados.addContent(cmrecebbcg);
				dados.addContent(observacoes);
				dados.addContent(dtultconsulta);

				scs.addContent(dados);

			}

		}
		System.out.println("XML hanseniase com sucesso!");

	}

	public void expoDiabetes() {
		DiabetesRN diabetesRN = new DiabetesRN();
		List<Diabetes> listdiabetes = diabetesRN.listar();

		if (listdiabetes.size() > 0) {
			for (Diabetes diabetes : listdiabetes) {
				Element dados = new Element("diabetes");
				Element idmd5familiar = new Element("idmd5familiar");
				Element dtvisita = new Element("dtvisita");
				Element fzdieta = new Element("fzdieta");
				Element fzexfisicos = new Element("fzexfisicos");
				Element usinsulina = new Element("usinsulina");
				Element tmhipoglicoral = new Element("tmhipoglicoral");
				Element dtultvisita = new Element("dtultvisita");
				Element obs = new Element("obs");

				idmd5familiar.setText(diabetes.getIdMD5familiar());
				dtvisita.setText(transformaDateString(diabetes.getDtVisita()));
				fzdieta.setText(diabetes.getFzDieta());
				fzexfisicos.setText(diabetes.getFzExFisicos());
				usinsulina.setText(diabetes.getUsInsulina());
				tmhipoglicoral.setText(diabetes.getTmHipoglicOral());
				dtultvisita.setText(transformaDateString(diabetes
						.getDtUltVisita()));
				obs.setText(diabetes.getObs());

				dados.addContent(idmd5familiar);
				dados.addContent(dtvisita);
				dados.addContent(fzdieta);
				dados.addContent(fzexfisicos);
				dados.addContent(usinsulina);
				dados.addContent(tmhipoglicoral);
				dados.addContent(dtultvisita);
				dados.addContent(obs);

				scs.addContent(dados);

			}

		}
		System.out.println("XML diabetes com sucesso!");

	}

	public void expoAcompCrianca() {
		AcompCriancaRN acompRN = new AcompCriancaRN();
		List<AcompCrianca> listacomp = acompRN.listar();

		if (listacomp.size() > 0) {
			for (AcompCrianca acompCrianca : listacomp) {
				Element dados = new Element("acompcrianca");
				Element idmd5familiar = new Element("idmd5familiar");
				Element dtvisita = new Element("dtvisita");
				Element altura = new Element("altura");
				Element peso = new Element("peso");
				Element perimetrocefalico = new Element("perimetrocefalico");
				Element apgar = new Element("apgar");
				Element tipoparto = new Element("tipoparto");
				Element obs = new Element("obs");
				Element situacao = new Element("situacao");

				idmd5familiar.setText(acompCrianca.getIdfamiliar());
				dtvisita.setText(transformaDateString(acompCrianca
						.getDtvisita()));
				altura.setText(String.valueOf(acompCrianca.getAltura()));
				peso.setText(String.valueOf(acompCrianca.getPeso()));
				perimetrocefalico.setText(String.valueOf(acompCrianca
						.getPerimetrocefalico()));
				apgar.setText(String.valueOf(acompCrianca.getApgar()));
				tipoparto.setText(acompCrianca.getTipoparto());
				obs.setText(acompCrianca.getObs());
				situacao.setText(acompCrianca.getSituacao());

				dados.addContent(idmd5familiar);
				dados.addContent(dtvisita);
				dados.addContent(altura);
				dados.addContent(peso);
				dados.addContent(perimetrocefalico);
				dados.addContent(apgar);
				dados.addContent(tipoparto);
				dados.addContent(obs);
				dados.addContent(situacao);

				scs.addContent(dados);

			}

		}
		System.out.println("XML Acompanhamento Criancas com sucesso!");

	}

	public void expoHipertensao() {
		HipertensaoRN hipertensaoRN = new HipertensaoRN();
		List<Hipertesao> listhipertensao = hipertensaoRN.listar();

		if (listhipertensao.size() > 0) {
			for (Hipertesao hipertensao : listhipertensao) {
				Element dados = new Element("hipertensao");
				Element idmd5familiar = new Element("idmd5familiar");
				Element dtvisita = new Element("dtvisita");
				Element fzdieta = new Element("fzdieta");
				Element fzexfisicos = new Element("fzexfisicos");
				Element tmmedicacao = new Element("tmmedicacao");
				Element pressaoarterial = new Element("pressaoarterial");
				Element dtultvisita = new Element("dtultvisita");
				Element obs = new Element("obs");

				idmd5familiar.setText(hipertensao.getIdMD5familiar());
				dtvisita.setText(transformaDateString(hipertensao.getDtVisita()));
				fzdieta.setText(hipertensao.getFzDieta());
				fzexfisicos.setText(hipertensao.getFzExFisicos());
				tmmedicacao.setText(hipertensao.getTmMedicacao());
				pressaoarterial.setText(String.valueOf(hipertensao
						.getPressaoArterial()));
				dtultvisita.setText(transformaDateString(hipertensao
						.getDtUltVisita()));
				obs.setText(hipertensao.getObs());

				dados.addContent(idmd5familiar);
				dados.addContent(dtvisita);
				dados.addContent(fzdieta);
				dados.addContent(fzexfisicos);
				dados.addContent(tmmedicacao);
				dados.addContent(pressaoarterial);
				dados.addContent(dtultvisita);
				dados.addContent(obs);

				scs.addContent(dados);

			}

		}
		System.out.println("XML hipertensão com sucesso!");

	}

	public void expoGestante() {
		GestanteRN gestanteRN = new GestanteRN();
		List<Gestante> listgestante = gestanteRN.listar();

		if (listgestante.size() > 0) {
			for (Gestante gestante : listgestante) {
				Element dados = new Element("gestante");
				Element idmd5familiar = new Element("idmd5familiar");
				Element dtvisita = new Element("dtvisita");
				Element dtultregra = new Element("dtultregra");
				Element dtprovavelparto = new Element("dtprovavelparto");
				Element estnutricional = new Element("estnutricional");
				Element mesgestacao = new Element("mesgestacao");
				Element dtconsulprenatal = new Element("dtconsulprenatal");
				Element fr6mgestacao = new Element("fr6mgestacao");
				Element fr36oumais = new Element("fr36oumais");
				Element frsangramento = new Element("frsangramento");
				Element frdiabetes = new Element("frdiabetes");
				Element frnatrimaborto = new Element("frnatrimaborto");
				Element frmeno20anos = new Element("frmeno20anos");
				Element fredema = new Element("fredema");
				Element frpressaoalta = new Element("frpressaoalta");
				Element dtconspuerbio = new Element("dtconspuerbio");
				Element obs = new Element("obs");

				idmd5familiar.setText(gestante.getIdMD5familiar());
				dtvisita.setText(transformaDateString(gestante.getDtVisita()));
				dtultregra.setText(transformaDateString(gestante
						.getDtUltRegra()));
				dtprovavelparto.setText(transformaDateString(gestante
						.getDtProvavelParto()));
				estnutricional.setText(gestante.getEstNutricional());
				mesgestacao.setText(gestante.getMesGestacao().toString());
				dtconsulprenatal.setText(transformaDateString(gestante
						.getDtConsulPreNatal()));
				fr6mgestacao.setText(transformBooleanString(gestante
						.isFr6mGestacao()));
				fr36oumais.setText(transformBooleanString(gestante
						.isFr36ouMais()));
				frsangramento.setText(transformBooleanString(gestante
						.isFrSangramento()));
				frdiabetes.setText(transformBooleanString(gestante
						.isFrDiabetes()));
				frnatrimaborto.setText(transformBooleanString(gestante
						.isFrNatrimAborto()));
				frmeno20anos.setText(transformBooleanString(gestante
						.isFrMeno20anos()));
				fredema.setText(transformBooleanString(gestante.isFrEdema()));
				// if()
				frpressaoalta.setText(transformBooleanString(gestante
						.isFrPressaoAlta()));
				if (gestante.getDtConsPuerbio() != null) {
					dtconspuerbio.setText(transformaDateString(gestante
							.getDtConsPuerbio()));
				}
				obs.setText(gestante.getObs());

				dados.addContent(idmd5familiar);
				dados.addContent(dtvisita);
				dados.addContent(dtultregra);
				dados.addContent(dtprovavelparto);
				dados.addContent(estnutricional);
				dados.addContent(mesgestacao);
				dados.addContent(dtconsulprenatal);
				dados.addContent(fr6mgestacao);
				dados.addContent(fr36oumais);
				dados.addContent(frsangramento);
				dados.addContent(frdiabetes);
				dados.addContent(frnatrimaborto);
				dados.addContent(frmeno20anos);
				dados.addContent(fredema);
				dados.addContent(frpressaoalta);
				dados.addContent(dtconspuerbio);
				dados.addContent(obs);

				scs.addContent(dados);

			}

		}
		System.out.println("XML gestante com sucesso!");
	}

	public void expoTuberculose() {

		TuberculoseRN tuberculoseRN = new TuberculoseRN();
		List<Tuberculose> listtuberculose = tuberculoseRN.listar();

		if (listtuberculose.size() > 0) {
			for (Tuberculose tuberculose : listtuberculose) {
				Element dados = new Element("tuberculose");
				Element idmd5familiar = new Element("idmd5familiar");
				Element dtvisita = new Element("dtvisita");
				Element tmmeddiaria = new Element("tmmeddiaria");
				Element recindesej = new Element("recindesej");
				Element exescar = new Element("exescar");
				Element comexami = new Element("comexami");
				Element mn5anoscombcg = new Element("mn5anoscombcg");
				Element obs = new Element("obs");

				idmd5familiar.setText(tuberculose.getIdMD5familiar());
				dtvisita.setText(transformaDateString(tuberculose.getDtvisita()));
				tmmeddiaria.setText(tuberculose.getTmmeddiaria());
				recindesej.setText(tuberculose.getRecindesej());
				exescar.setText(tuberculose.getExescar());
				comexami.setText(tuberculose.getComexami().toString());
				mn5anoscombcg
						.setText(tuberculose.getMn5anoscombcg().toString());
				obs.setText(tuberculose.getObs());

				dados.addContent(idmd5familiar);
				dados.addContent(dtvisita);
				dados.addContent(tmmeddiaria);
				dados.addContent(recindesej);
				dados.addContent(exescar);
				dados.addContent(comexami);
				dados.addContent(mn5anoscombcg);
				dados.addContent(obs);

				scs.addContent(dados);

			}

		}
		System.out.println("XML tuberculose com sucesso!");

	}

}
