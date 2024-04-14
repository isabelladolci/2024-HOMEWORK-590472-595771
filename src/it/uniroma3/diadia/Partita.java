package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {


	private Stanza stanzaCorrente;
	private boolean finita;
	private Giocatore giocatore;	
	

	private Labirinto labirinto;
	
	
	public Partita(Labirinto labirinto){
		this.labirinto = labirinto;
		this.giocatore = new Giocatore();
		this.stanzaCorrente =this.labirinto.getStanzaIniziale();
		this.finita = false;
	}
	
	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}
	
	public int getCfu() {
		return giocatore.getCfu();
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);		
	}
	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return stanzaCorrente== labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
}
