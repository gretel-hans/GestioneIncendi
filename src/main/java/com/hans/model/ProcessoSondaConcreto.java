package com.hans.model;

public class ProcessoSondaConcreto implements ProcessoSonda{

	@Override
	public void update(Segnalazione s) {
		System.out.println("id Segnalazione: "+s.getId()+" Partito l'allarme nell'edificio con coordinate latitudine: "+s.getLat()+" longetudine: "+s.getLon()+" livello pericolosità: "+s.getLivelloFumo());
		
	}

}
