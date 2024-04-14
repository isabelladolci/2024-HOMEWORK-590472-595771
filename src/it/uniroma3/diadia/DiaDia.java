package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.util.Scanner;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa"};
	
	
	private Partita partita;
	private Labirinto labirinto;
	private IOConsole inputOutput;

	public DiaDia(IOConsole inputOutput) {
		this.inputOutput=inputOutput;
		this.labirinto=new Labirinto();
		this.partita = new Partita(labirinto);
	}

	public void gioca() {
		String istruzione; 
		
		inputOutput.mostraMessaggio(MESSAGGIO_BENVENUTO);
		
		do 
			istruzione =inputOutput.leggiRiga(); 	
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		if(istruzione.isEmpty())
			return false;
		    
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			inputOutput.mostraMessaggio("\nComando sconosciuto\n");
		if (this.partita.vinta()) {
			inputOutput.mostraMessaggio("\nHai vinto!");
			return true;
		/*} else if(this.partita.isFinita()) {
			inputOutput.mostraMessaggio("\nCFU terminati . . . "
					+"Hai perso !");
			return true;*/
		}else
			return false;
	}   

	
	
	
	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			inputOutput.mostraMessaggio(elencoComandi[i]+" ");
		inputOutput.mostraMessaggio("");
	}

	
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione!=null) {
			//if(this.partita.getGiocatore().cfuCheck()) {
				Stanza prossimaStanza = null;
				prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
				if (prossimaStanza == null)
					inputOutput.mostraMessaggio("Direzione inesistente\n"
							+ "Dove vuoi andare ?\n");
				else {
					this.partita.setStanzaCorrente(prossimaStanza);
					int cfu = this.partita.getGiocatore().getCfu();
					this.partita.getGiocatore().setCfu(cfu-1);
				}
			
			/*}else
				inputOutput.mostraMessaggio("CFU terminati !  . . . Non puoi cambiare stanza\n");*/
				
		}else
			inputOutput.mostraMessaggio("Dove vuoi andare ?\n");
		inputOutput.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
		
	
	/**
	 * Comando "Prendi"
	 * 
	 * Cerca di prendere un oggetto dalla stanza,se presente lo posa nella borsa,
	 * altrimenti stampa un messaggio di errore
	 *  
	 * @param nomeAttrezzo
	 */
	//gli attrezzi presi vengono rimossi dalla stanza e aggiunti alla borsa
	public void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null)
			inputOutput.mostraMessaggio("Quale attrezzo vuoi prendere ?");
		else {
			Attrezzo attrezzo=this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if(attrezzo!=null) {
				if(this.partita.getGiocatore().addAttrezzoBorsa(attrezzo)) {
					inputOutput.mostraMessaggio("Attrezzo inserito nella borsa !");
					this.partita.getStanzaCorrente().removeAttrezzo(attrezzo);
				}else
					inputOutput.mostraMessaggio("La borsa è piena !");
			}else
				inputOutput.mostraMessaggio("Attrezzo non presente nella stanza !");
		}
		inputOutput.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
	
	
	/**
	 * Comando "Posa"
	 * 
	 * Cerca di prendere un oggetto dalla borsa,se presente lo posa nella stanza,
	 * altrimenti stampa un messaggio di errore
	 *  
	 * @param nomeAttrezzo
	 */
	//gli attrezzi posati vengono rimossi dalla borsa e aggiunti alla stanza
	public void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null)
			inputOutput.mostraMessaggio("Quale attrezzo vuoi posare ?");
		else {
			Attrezzo attrezzo=this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if(attrezzo!=null) {
				if(this.partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
					this.partita.getGiocatore().removeAttrezzoBorsa(nomeAttrezzo);
					inputOutput.mostraMessaggio("Attrezzo posato nella stanza !");
				}else
					inputOutput.mostraMessaggio("Capienza stanza raggiunta,impossibile aggiungere l'attrezzo !");
			}else
				inputOutput.mostraMessaggio("Attrezzo non presente nella borsa !");
		}
		inputOutput.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
	

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		inputOutput.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	
	
	public static void main(String[] argc) {
		IOConsole inputOutput=new IOConsole();
		DiaDia gioco = new DiaDia(inputOutput);
		gioco.gioca();
	}

	
}