package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella il labirinto del gioco
 *
 * @author  Asia & Isabella
 * @version base
 */


public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente; 


	public Labirinto() {
		 creaStanze();
	}
	
	
	/**
     * Creazione del labirinto: crea tutte le stanze e le porte di collegamento
     */
    private void creaStanze() {
    	
    	this.stanzaIniziale = new Stanza("Atrio"); //il gioco cominicia nell'atrio
		this.stanzaVincente = new Stanza("Biblioteca"); //il gioco termina nella biblioteca


		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	
		/* crea stanze del labirinto */

		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		
		
		/* collega le stanze */
		stanzaIniziale.impostaStanzaAdiacente("nord", this.stanzaVincente);
		stanzaIniziale.impostaStanzaAdiacente("est", aulaN11);
		stanzaIniziale.impostaStanzaAdiacente("sud", aulaN10);
		stanzaIniziale.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", this.stanzaIniziale);
		aulaN10.impostaStanzaAdiacente("nord", this.stanzaIniziale);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", this.stanzaIniziale);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		stanzaVincente.impostaStanzaAdiacente("sud", this.stanzaIniziale);
		
		

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		stanzaIniziale.addAttrezzo(osso);

		
    }

	
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	

}
