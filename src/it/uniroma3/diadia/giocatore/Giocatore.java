package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe gestisce i CFU del giocatore e memorizza gli attrezzi in un oggetto Borsa.
 *
 * @author  docente di POO, Asia & Isabella
 * @version base
 */

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;

	
	private int cfu;
	private Borsa borsa;
	


	public Giocatore() {
		
		this.borsa = new Borsa();
		this.cfu = CFU_INIZIALI;
	}
	
	
	//getters e setters
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
	public Borsa getBorsa() {
		return borsa;
	}
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}

	
	/**
	 *Inserisce un attrezzo nella borsa del giocatore
	 * 
	 * @param l'attrezzo da inserire
	 * @return true se l'attrezzo da inserire è stato inserito, false altrimenti
	 */
	
	public boolean addAttrezzoBorsa(Attrezzo aDaInserire) {
		
		return this.borsa.addAttrezzo(aDaInserire); 
	}

	/**
	 * Rimuove un attrezzo dalla borsa del giocatore
	 * 
	 * @param  il nome dell'attrezzo da rimuovere
	 * @return l'attrezzo rimosso
	 */
	
	
	public Attrezzo removeAttrezzoBorsa(String nomeAttrezzo) {

		return this.borsa.removeAttrezzo(nomeAttrezzo); 
	}
	
	
}
