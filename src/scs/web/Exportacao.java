package scs.web;

import scs.area.Area;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import scs.area.AreaRN;
import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.municipio.Municipio;
import scs.municipio.MunicipioRN;
import scs.residencia.Residencia;
import scs.residencia.ResidenciaRN;
import scs.rua.Rua;
import scs.rua.RuaRN;
import scs.segmento.Segmento;
import scs.segmento.SegmentoRN;
import scs.usuario.Usuario;
import scs.usuario.UsuarioRN;
import scs.util.HibernateUtil;

@ManagedBean(name = "exportacaoBean")
@RequestScoped
public class Exportacao {

	private Session session;
	Element scs;

	public void setSesson(Session session) {
		this.session = session;
	}
	
	public void expoMobile() {

		scs = new Element("scs");
		expoUsuarios();
		expoBairros();
		//expoSegmento();
		//expoArea();
		//expoMicroAreas();
		expoRuas();
		expoResidencias();
		
		Document doc = new Document();
		doc.setRootElement(scs);
		FacesContext context = FacesContext.getCurrentInstance();

		try {

			XMLOutputter xout = new XMLOutputter();
			OutputStream out = new FileOutputStream(
					new File("C:\\scs.xml"));

			xout.output(doc, out);

			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		context.addMessage(null, new FacesMessage("Exportação Realizada com Sucesso!", null));	
	}
	
	private void expoUsuarios(){
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
	
	public void expoBairros(){
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
	
	public void expoRuas(){
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select mr.cod_rua,r.descricao,m.idagente,m.codigo_microarea,m.idarea,a.codigo_segmento,s.idbairro from microarea_ruas mr "+ 
								"join ruas r on mr.cod_rua=r.codigo_rua "+
								"join microarea m on mr.cod_microarea=m.codigo_microarea "+
								"join area a on a.codigo_area=m.idarea "+
								"join segmento s on s.codigo_segmento=a.codigo_segmento");

		List listaRuas = query.list();

		if (listaRuas.size() > 0) {
			for(Object[] obj: (List<Object[]>)listaRuas){ 
							
				Element dados = new Element("rua");
				Element codigo = new Element("codigoRua");
				Element descricao = new Element("descricaoRua");
				Element codigoAgente = new Element("codigoAgenteRua");
				Element codigoArea = new Element("codigoAreaRua");
				Element codigoMicroarea = new Element("codigoMicroareaRua");
				Element codigoSegmento = new Element("codigoSegmentoRua");
				Element codigoBairro = new Element("codigoBairroRua");
					
    			String codigoStr=(String) obj[0].toString(),
    				   descricaoStr=(String) obj[1].toString(),
    				   codigoAgenteStr=(String) obj[2].toString(),
    				   codigoMicroareaStr=(String) obj[3].toString(),
    				   codigoAreaStr=(String) obj[4].toString(),
    				   codigoSegmentoStr=(String) obj[5].toString(),
    				   codigoBairroStr=(String) obj[6].toString()
    				   ;
    				  
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
	
	public void expoResidencias(){
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
				Element abastecimentoagua = new Element("abastecimentoaguaResidencia");
				Element destfezes = new Element("destfezesResidencia");
				Element casodoenca = new Element("casodoencaResidencia");
				Element ourtocasodoenca = new Element("ourtocasodoencaResidencia");
				Element meiocomunicacao = new Element("meiocomunicacaoResidencia");
				Element participagrupo = new Element("participagrupoResidencia");
				Element meiotransporte = new Element("meiotransporteResidencia");
				Element nomeMunicipio = new Element("nomeMunicipioResidencia");
				Element codIBGE = new Element("codIBGEResidencia");

				codigoRua.setText(residencia.getEndereco().getCodigo_rua().toString());
				nomeRua.setText(residencia.getEndereco().getDescricao());
				num_residencia.setText(residencia.getNum_residencia().toString());
				codigoBairro.setText(residencia.getBairro().getCodigo_bairro().toString());
				nomeBairro.setText(residencia.getBairro().getDescricao());
				segmento.setText(residencia.getSegmento().getCodigo_segmento().toString());
				area.setText(residencia.getArea().getCodigo_area().toString());
				microarea.setText(residencia.getMicroarea().getCodigo_microarea().toString());
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
				meiotransporte.setText(residencia.getMeiotransporte());
				if(listMuni.size()>0){
					nomeMunicipio.setText(listMuni.get(0).getNome());
					codIBGE.setText(listMuni.get(0).getCodigo_ibge().toString());
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
				if(listMuni.size()>0){
					dados.addContent(nomeMunicipio);
					dados.addContent(codIBGE);
				}	


				scs.addContent(dados);
					
				
			}

		}	
		System.out.println("XML residencia com sucesso!");
	}
	
}
