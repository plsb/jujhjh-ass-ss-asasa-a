package scs.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;

import scs.familiar.Familiar;
import scs.familiar.FamiliarRN;
import scs.microarea.Microarea;
import scs.microarea.MicroareaRN;
import scs.residencia.Residencia;
import scs.residencia.ResidenciaRN;
import scs.util.HibernateUtil;

@ManagedBean(name = "familiarBean")
@RequestScoped
public class FamiliarBean {

	private Familiar familiar = new Familiar();
	private List<Familiar> lista;
	private List<Familiar> listaStandBy;
	private List<SelectItem> familiarSelect;
	private List<SelectItem> numeroSelect;
	private List<String> numeroSelect2;
	private List<SelectItem> complementoSelect;
	private List<String> complementoSelect2;

	public Familiar getFamiliar() {
		return familiar;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	public String salvar(String tipo) {
		FacesContext context = FacesContext.getCurrentInstance();
		FamiliarRN familiarRN = new FamiliarRN();
		Integer codigo = familiar.getId();
		if (codigo == null || codigo == 0) {
			if (verificaUnique()) {
				DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
				String data = formatador.format(new Date());
				familiar.setIdMD5(md5(familiar.getNome() + getCPUSerial()
						+ data));
				context.addMessage(null, new FacesMessage(
						"Sucesso ao Inserir: " + familiar.getNome(), ""));

			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar: "
					+ familiar.getNome(), ""));

		}

		// adiciona o id da residencia
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		SQLQuery query=null;
		if(familiar.getComplemento()!=null){
			query = session.createSQLQuery("select * from residencias r "
					+ " where r.endereco="
					+ familiar.getRuaFamilia().getCodigo_rua().toString()
					+ " and r.num_residencia= " + familiar.getNumero().toString()
					+ " and coalesce(complemento,'')='"
					+  familiar.getComplemento().toString()+ "'");
			
		} else {
			query = session.createSQLQuery("select * from residencias r "
					+ " where r.endereco="
					+ familiar.getRuaFamilia().getCodigo_rua().toString()
					+ " and r.num_residencia= " + familiar.getNumero().toString()
					+ " and coalesce(complemento,'')=''");
		}
		
		if(query.list().size()>0){
			Residencia res = new Residencia();
			ResidenciaRN resRN = new ResidenciaRN();
			List listRes=query.list();
			for (Object[] obj : (List<Object[]>) listRes) {
				String codigoStr = (String) obj[0].toString();
				res = resRN.carregar(Integer.parseInt(codigoStr));
			}
			familiar.setResidencia(res);
			familiar.setArea(res.getArea());
						
		} else {
			context.addMessage(null, new FacesMessage(
					"Residencia N�o Encontrada, Informe outro Endere�o!"));
			return "";
		}
		//fim adiciona id residencia

		familiarRN.salvar(this.familiar);
		if (tipo.equals("1")) {
			return "/restrito/lista_familiar";// this.destinoSalvar;
		} else {
			return "/restrito/lista_familiares_stand_by";
		}
	}

	public boolean verificaUnique() {

		boolean a;
		FacesContext context = FacesContext.getCurrentInstance();
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select u.id from familiar u where u.nome='"
						+ familiar.getNome() + "' and u.rua="
						+ familiar.getRuaFamilia().getCodigo_rua().toString()
						+ " and numero=" + familiar.getNumero().toString());
		List ar = query.list();
		// query.setParameter("idfunc", codigo).uniqueResult();
		if (ar.isEmpty()) {

			a = true;

		} else {
			context.addMessage(
					null,
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Familiar Ja Cadastrado para Esse Endere�o, Informe Outro Familiar!",
							""));
			a = false;

		}

		return a;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((complementoSelect == null) ? 0 : complementoSelect
						.hashCode());
		result = prime
				* result
				+ ((complementoSelect2 == null) ? 0 : complementoSelect2
						.hashCode());
		result = prime * result
				+ ((familiar == null) ? 0 : familiar.hashCode());
		result = prime * result
				+ ((familiarSelect == null) ? 0 : familiarSelect.hashCode());
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((listaStandBy == null) ? 0 : listaStandBy.hashCode());
		result = prime * result
				+ ((numeroSelect == null) ? 0 : numeroSelect.hashCode());
		result = prime * result
				+ ((numeroSelect2 == null) ? 0 : numeroSelect2.hashCode());
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
		FamiliarBean other = (FamiliarBean) obj;
		if (complementoSelect == null) {
			if (other.complementoSelect != null)
				return false;
		} else if (!complementoSelect.equals(other.complementoSelect))
			return false;
		if (complementoSelect2 == null) {
			if (other.complementoSelect2 != null)
				return false;
		} else if (!complementoSelect2.equals(other.complementoSelect2))
			return false;
		if (familiar == null) {
			if (other.familiar != null)
				return false;
		} else if (!familiar.equals(other.familiar))
			return false;
		if (familiarSelect == null) {
			if (other.familiarSelect != null)
				return false;
		} else if (!familiarSelect.equals(other.familiarSelect))
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (listaStandBy == null) {
			if (other.listaStandBy != null)
				return false;
		} else if (!listaStandBy.equals(other.listaStandBy))
			return false;
		if (numeroSelect == null) {
			if (other.numeroSelect != null)
				return false;
		} else if (!numeroSelect.equals(other.numeroSelect))
			return false;
		if (numeroSelect2 == null) {
			if (other.numeroSelect2 != null)
				return false;
		} else if (!numeroSelect2.equals(other.numeroSelect2))
			return false;
		return true;
	}

	public List<Familiar> getListaStandBy() {
		if (this.lista == null) {
			FamiliarRN familiaRN = new FamiliarRN();
			this.lista = familiaRN.listarStandBy();
		}
		return lista;
	}

	public List<Familiar> getLista() {
		if (this.lista == null) {
			FamiliarRN familiaRN = new FamiliarRN();
			this.lista = familiaRN.listar();
		}
		return lista;
	}

	public void setLista(List<Familiar> lista) {
		this.lista = lista;
	}

	public String novo() {
		this.familiar = new Familiar();
		return "/restrito/familiar";
	}

	public String editar() {
		return "/restrito/familiar";
	}

	public String editarStandBy() {
		return "/restrito/familiarStandBy";
	}

	public String excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "
				+ familiar.getNome(), ""));
		FamiliarRN familiarRN = new FamiliarRN();
		familiarRN.excluir(this.familiar);
		this.lista = null;
		return null;
	}

	public List<SelectItem> getFamiliarSelect() {
		if (this.familiarSelect == null) {
			this.familiarSelect = new ArrayList<SelectItem>();
			// ContextoBean contextoBean =
			// scs.util.ContextoUtil.getContextoBean();

			FamiliarRN familiarRN = new FamiliarRN();
			List<Familiar> categorias = familiarRN.listar();
			this.montaDadosSelectFamiliar(this.familiarSelect, categorias, "");
		}
		return familiarSelect;
	}

	private void montaDadosSelectFamiliar(List<SelectItem> select,
			List<Familiar> familiares, String prefixo) {

		SelectItem item = null;
		if (familiares != null) {
			for (Familiar familiar : familiares) {
				item = new SelectItem(familiar, familiar.getNome());
				item.setEscape(false);
				select.add(item);
				// this.montaDadosSelect(select, usuario.getNome(), prefixo +
				// "&nbsp;&nbsp;");
			}
		}
	}

	public List<String> getComplementoSelect2() {
		if (this.complementoSelect2 == null) {
			this.complementoSelect2 = new ArrayList<String>();
			// ContextoBean contextoBean =
			// scs.util.ContextoUtil.getContextoBean();

			ResidenciaRN residenciaRN = new ResidenciaRN();
			List<Residencia> categorias = residenciaRN.listar();
			this.montaDadosSelectComplemento2(this.complementoSelect2,
					categorias, "");
		}

		return complementoSelect2;
	}

	private void montaDadosSelectComplemento2(List<String> select,
			List<Residencia> residencias, String prefixo) {

		SelectItem item = null;
		if (residencias != null) {
			for (Residencia residencia : residencias) {
				// item = new SelectItem(residencia,
				// residencia.getNum_residencia().toString());
				// item.setEscape(false);
				if (familiar.getRuaFamilia() == null) {
					if (residencia.getComplemento() != null) {
						if (!residencia.getComplemento().equalsIgnoreCase("")) {
							select.add(residencia.getComplemento().toString());
						}
					}
				} else {
					if (residencia.getEndereco().getCodigo_rua() == familiar
							.getRuaFamilia().getCodigo_rua()) {
						if (residencia.getNum_residencia().equals(
								familiar.getNumero())) {
							if (residencia.getComplemento() != null) {
								if (residencia.getComplemento() != "") {
									select.add(residencia.getComplemento()
											.toString());
								}
							}
						}

					}
				}
				// select.add(item);
				// this.montaDadosSelect(select, usuario.getNome(), prefixo +
				// "&nbsp;&nbsp;");
			}
		}
	}

	public List<String> getNumeroSelect2() {

		if (this.numeroSelect2 == null) {
			this.numeroSelect2 = new ArrayList<String>();
			// ContextoBean contextoBean =
			// scs.util.ContextoUtil.getContextoBean();

			ResidenciaRN residenciaRN = new ResidenciaRN();
			List<Residencia> categorias = residenciaRN.listar();
			this.montaDadosSelectNumero2(this.numeroSelect2, categorias, "");
		}

		return numeroSelect2;
	}

	private void montaDadosSelectNumero2(List<String> select,
			List<Residencia> residencias, String prefixo) {

		SelectItem item = null;
		if (residencias != null) {
			for (Residencia residencia : residencias) {
				// item = new SelectItem(residencia,
				// residencia.getNum_residencia().toString());
				// item.setEscape(false);
				if (familiar.getRuaFamilia() == null) {
					select.add(residencia.getNum_residencia().toString());
				} else {
					if (residencia.getEndereco().getCodigo_rua() == familiar
							.getRuaFamilia().getCodigo_rua()) {
						select.add(residencia.getNum_residencia().toString());
					}
				}
				// select.add(item);
				// this.montaDadosSelect(select, usuario.getNome(), prefixo +
				// "&nbsp;&nbsp;");
			}
		}
	}

	public static String md5(String s) {
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest
					.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++)
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getCPUSerial() {
		String result = "";
		try {
			File file = File.createTempFile("tmp", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);

			String vbs = "On Error Resume Next \r\n\r\n"
					+ "strComputer = \".\"  \r\n"
					+ "Set objWMIService = GetObject(\"winmgmts:\" _ \r\n"
					+ "    & \"{impersonationLevel=impersonate}!\\\\\" & strComputer & \"\\root\\cimv2\") \r\n"
					+ "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_Processor\")  \r\n "
					+ "For Each objItem in colItems\r\n "
					+ "    Wscript.Echo objItem.ProcessorId  \r\n "
					+ "    exit for  ' do the first cpu only! \r\n"
					+ "Next                    ";

			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec(
					"cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				result += line;
			}
			input.close();
		} catch (Exception e) {

		}
		if (result.trim().length() < 1 || result == null) {
			result = "NO_CPU_ID";
		}
		return result.trim();
	}

}
