package scs.web;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import scs.agendamento.Agendamento;
import scs.agendamento.AgendamentoDAO;
import scs.agendamento.AgendamentoRN;
import scs.familiar.Familiar;
import scs.familiar.FamiliarRN;
import scs.usuario.Usuario;
import scs.util.HibernateUtil;


@ManagedBean(name="agendamentoBean")
@RequestScoped

public class AgendamentoBean {
	
	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent(); 
	 private ScheduleModel lazyEventModel;
	private AgendamentoDAO agendamentoDAO;
	private String idAgendamento;
	private String idFamiliar;
	private List<SelectItem> familiarSelect;
	
	private Agendamento agendamento = new Agendamento();
	private List<Agendamento> lista;
	
	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	

	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();		
		AgendamentoRN agendamentoRN = new AgendamentoRN();
		Integer codigo = agendamento.getId();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir Agendamento ", ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar Agendamento ", ""));
			
		}
		agendamento.setIdfamiliar(ResidenciasBean.familiarSelecionado.getIdMD5());
		agendamento.setAgendada(true);
		agendamentoRN.salvar(this.agendamento);
		
		return "/restrito/lista_agendamento";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		boolean a=true;
		
		return a;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((agendamento == null) ? 0 : agendamento.hashCode());
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
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
		AgendamentoBean other = (AgendamentoBean) obj;
		if (agendamento == null) {
			if (other.agendamento != null)
				return false;
		} else if (!agendamento.equals(other.agendamento))
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		return true;
	}

	public List<Agendamento> getLista() {
		Agendamento agen;
		List<Agendamento> listAgendamento = new ArrayList<>();
		if(this.lista==null){
			AgendamentoRN agendamentoRN = new AgendamentoRN();
			for (int i = 0; i < agendamentoRN.listar().size(); i++) {
				if(agendamentoRN.listar().get(i).getDtagendamento()==null){
					agen = agendamentoRN.listar().get(i);
					listAgendamento.add(agen);
				}
				
			}
			if(listAgendamento.size()>0){
				lista = listAgendamento;
			}
			
		}
		return lista;
	}

	public void setLista(List<Agendamento> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.agendamento = new Agendamento();
		return "/restrito/cadagendamento";
	}
	
	public String editar(){
		return "/restrito/cadagendamento";
	}
	
	public boolean verificaRenderedSpiner(String a){
		if(agendamento.getId()!=null){
			return false;
		} else {
			return true;
		}
		
	}
	public boolean verificaRenderedEdit(String a){
		if(agendamento.getId()!=null){
			return true;
		} else {
			return false;
		}
		
	}
	
	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir Agendamento ", ""));
		AgendamentoRN agendamentoRN = new AgendamentoRN();
		agendamentoRN.excluir(this.agendamento);
		this.lista = null;
		return null;
	}

	public ScheduleModel getEventModel() {
		AgendamentoRN agenRN = new AgendamentoRN();
		List<Agendamento> listA = agenRN.listar();
		if (listA.size() > 0) {
			eventModel = new DefaultScheduleModel(); 
			for (Agendamento agendamento : listA) {
				//verifica se o agendamento já foi marcado, verificando pela data
				if(agendamento.getDtagendamento()!=null){
					//adiciona um dia a data, o componente do prime faces esta com problemas, tirando um dia da data
					Calendar calendar = Calendar.getInstance();  
					calendar.setTime(agendamento.getDtagendamento());
					calendar.add(Calendar.DAY_OF_MONTH, 1);
										
					//adiciona um evento ao caledário
					idAgendamento=agendamento.getId().toString();
					eventModel.addEvent(new DefaultScheduleEvent( agendamento.getDescricao()+" | "+
							prcFamiliar(agendamento.getIdfamiliar())+" | "+agendamento.getTpconsulta()+" | "+
							agendamento.getSeeUrgente(), 
							calendar.getTime(),calendar.getTime()));
					
				}
				
			}
		}
		
		return eventModel;
	}
	
	public String prcFamiliar(String idfamiliar){
		//procura o nome do familiar na tabela familiara, para retornar seu nome para mostrar no calendário
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session
				.createQuery("From Familiar where idMD5='"
						+idfamiliar+"'");
		List<Familiar> famil = query.list();
		
		return famil.get(0).getNome();
	}
	
	public void addEvent(ActionEvent actionEvent) {  
		//método que adiciona o evento no calendário
        event.setId(idAgendamento);
		if(event.getId() == null)  
            eventModel.addEvent(event);  
        else  
            eventModel.updateEvent(event);  
          
        event = new DefaultScheduleEvent();  
    }  

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}
	public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {  
        event = selectEvent.getScheduleEvent();  
        String tal = event.getId(); 
        int cont = event.getTitle().length();
        int posiReal=0;
        
        for(int i = 0;i<cont;i++){  
	        if (event.getTitle().substring(i,i+1).equals("__")){  
	            int posicao = i+1;  
	            posiReal=posicao;  
	         }  
        }
        //System.out.println(String.valueOf(posiReal));*
    }  
      
    public void onDateSelect(DateSelectEvent selectEvent) {  
        event = new DefaultScheduleEvent(Math.random() + "", selectEvent.getDate(), selectEvent.getDate());  
    }  
      
    public void onEventMove(ScheduleEntryMoveEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  
    }  
      
    public void onEventResize(ScheduleEntryResizeEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  
    } 
    
    private void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 
    
   
	
		

}

