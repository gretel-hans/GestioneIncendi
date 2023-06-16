package com.hans.model;

public class ProcessoSondaConcreto implements ProcessoSonda{

	@Override
	public void update(Allarme a) {
		System.out.println("Partito l'allarme nell'edificio con coordinate latitudine: "+a.getSegnalazione().getLat()+" longetudine: "+a.getSegnalazione().getLon()+" livello pericolosità: "+a.getLivelloPeriocolosita());
		
	}

}
