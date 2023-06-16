package com.hans.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hans.enums.LivelloPericolosita;
import com.hans.service.AllarmeService;

public class GestioneProcessoSonda {
	private List<ProcessoSonda> listaProcessi=new ArrayList<ProcessoSonda>();
	
	@Autowired AllarmeService dbAllarme;

	
	public void aggiungiProcesso(ProcessoSonda p) {
		listaProcessi.add(p);
	}
	
	public void rimuoviProcesso(ProcessoSonda p) {
		listaProcessi.remove(p);
	}

	public void allertaProcesso(Segnalazione s) {
		for(ProcessoSonda processo : listaProcessi) {
			if(s.getLivelloFumo()>=5)
				if(s.getLivelloFumo()>=5) {
					Allarme a=new Allarme();
					a.setSegnalazione(s);
					if(s.getLivelloFumo()>=5 &&s.getLivelloFumo()<=7) {
						a.setLivelloPeriocolosita(LivelloPericolosita.Medio);
						dbAllarme.salvaAllarme(a);
						processo.update(a);
					}else if(s.getLivelloFumo()>7) {
						a.setLivelloPeriocolosita(LivelloPericolosita.Alto);
						dbAllarme.salvaAllarme(a);
						processo.update(a);
					}
				}
			
		} 
	}
}
