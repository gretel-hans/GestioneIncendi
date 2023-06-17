package com.hans.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GestioneProcessoSonda {
	private List<ProcessoSonda> listaProcessi=new ArrayList<ProcessoSonda>();
	private static List<Segnalazione> listaSegnalazioni=new ArrayList<Segnalazione>();
	
	public void aggiungiProcesso(ProcessoSonda p) {
		listaProcessi.add(p);
	}
	
	public void rimuoviProcesso(ProcessoSonda p) {
		listaProcessi.remove(p);
	}

	boolean presente;
	public void allertaProcesso(Segnalazione s) {
		presente=false;
		listaSegnalazioni.forEach(segnalazione->{
			System.out.println(segnalazione);
			listaSegnalazioni.add(segnalazione);
			if(segnalazione.getId()==s.getId()) {
				presente=true;
				System.out.println("gia presente con id:"+segnalazione.getId());
			}
		});
		for(ProcessoSonda processo : listaProcessi) {
			if(s.getLivelloFumo()>=5 && !presente) {
				processo.update(s);								
			}
			
		} 
	}
}
