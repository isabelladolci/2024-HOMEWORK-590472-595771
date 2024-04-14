package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	
	
	private Partita creaPartita(Stanza stanzaCorrente, int cfu, boolean finita) {
		Labirinto labirinto = new Labirinto();
		Partita partita = new Partita(labirinto);
		
		if(finita)
			partita.setFinita();
		
		partita.getGiocatore().setCfu(cfu);
		partita.setStanzaCorrente(stanzaCorrente);
		
		return partita;
	}

	@Test
	public void testVinta_InizioPartita_StanzaIniziale() {
		assertFalse(creaPartita(new Stanza("Atrio-StanzaIniziale"),20,false).vinta());
	}
	@Test
	public void testVinta_PartitaIniziata_StanzaIntermedia_NonVinta() {
		assertFalse(creaPartita(new Stanza("N11-StanzaIntermedia"),10,false).vinta());
	}
	@Test
	public void testVinta_PartitaIniziata_StanzaVincente() {
		assertTrue(creaPartita(new Stanza("Biblioteca"),19,false).vinta());
	}

	@Test
	public void testIsFinita_InizioPartita() {
		assertFalse(new Partita(new Labirinto()).isFinita());
	}
	@Test
	public void testIsFinita_VerificataPerCFUterminati() {
		assertTrue(creaPartita(new Stanza("Laboratorio Campus"),0,false).isFinita());
	}
	@Test
	public void testIsFinita_VerificataPerVittoria() {
		assertTrue(creaPartita(new Stanza("Biblioteca"),16,false).isFinita());
	}
	

	@Test
	public void testIsFinita_VerificataPerPartitaImpostateAfinita() {
		assertTrue(creaPartita(new Stanza("N10"),16,true).isFinita());
	}
	
	

}
