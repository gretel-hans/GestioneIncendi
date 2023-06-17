package com.hans.runner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hans.model.Edificio;
import com.hans.model.GestioneProcessoSonda;
import com.hans.model.ProcessoSonda;
import com.hans.model.ProcessoSondaConcreto;
import com.hans.model.Segnalazione;
import com.hans.model.Sonda;
import com.hans.service.EdificioService;
import com.hans.service.SegnalazioneService;
import com.hans.service.SondaService;


@Component
public class GestioneIncendiRunner implements CommandLineRunner{

	@Autowired EdificioService edificioService;
	@Autowired SondaService sondaService;
	@Autowired SegnalazioneService segnalazioneService;
	
	
	@Override
	public void run(String... args) throws Exception {
    System.out.println("Runner Gestione Incendi...");
    
    	Edificio e=new Edificio(null, 10.53, 20.90);
    	edificioService.salvaEdificio(e);
    	
    	
    	List<Sonda> listaSonde=new ArrayList<Sonda>();
    	listaSonde.add(new Sonda(null,"cucina",e));
    	listaSonde.add(new Sonda(null,"salotto",e));
    	listaSonde.add(new Sonda(null,"bagno",e));
    	listaSonde.add(new Sonda(null,"camera",e));
    	listaSonde.forEach(s->sondaService.salvaSonda(s));
    	
    	
    	List<Segnalazione> listaSegnalazioni=new ArrayList<Segnalazione>();
    	listaSegnalazioni.add(new Segnalazione(sondaService.trovaSonda(1l), 3,LocalDateTime.now()));
    	listaSegnalazioni.add(new Segnalazione(sondaService.trovaSonda(3l), 5,LocalDateTime.now()));
    	listaSegnalazioni.add(new Segnalazione(sondaService.trovaSonda(2l), 7,LocalDateTime.now()));
    	listaSegnalazioni.add(new Segnalazione(sondaService.trovaSonda(4l), 2,LocalDateTime.now()));
    	listaSegnalazioni.forEach(s->segnalazioneService.salvaSegnalazioni(s));
    	
    	
    	GestioneProcessoSonda gPS=new GestioneProcessoSonda();
    	
    	ProcessoSonda p1= new ProcessoSondaConcreto();
    	gPS.aggiungiProcesso(p1);
    	
    	gPS.allertaProcesso(segnalazioneService.trovaSegnalazione(1l));
    
	}

}
