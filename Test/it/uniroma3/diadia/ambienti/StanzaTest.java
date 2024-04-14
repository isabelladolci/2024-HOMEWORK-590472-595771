package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
		
		private Stanza creaStanza(int numeroAttrezziPresenti) {
			
			Stanza s = new Stanza("Test");
			boolean inserimento = true;
			
			for(int i=0; i<numeroAttrezziPresenti && inserimento; i++) {
				inserimento = s.addAttrezzo(new Attrezzo("Attrezzo"+i,i));
			}
			
			return s; 	
		}
		
		@Test
		public void testAddAttrezzoStanzaVuota() {
			assertTrue(creaStanza(0).addAttrezzo(new Attrezzo("Lancia",10)));
		}

		@Test
		public void testAddAttrezzoStanzaPiena() {
			assertFalse(creaStanza(10).addAttrezzo(new Attrezzo("Lancia",10)));
		}
		
		@Test
		public void testAddAttrezzoStanzaContenteDegliAttrezzi() {
			assertTrue(creaStanza(5).addAttrezzo(new Attrezzo("Lancia",10)));
		}
		
		@Test
		public void testRemoveAttrezzoEsistente() {
			assertTrue(creaStanza(7).removeAttrezzo(new Attrezzo("Attrezzo"+0,0)));
		}
		@Test
		public void testRemoveAttrezzoNonContenuto() {
			assertFalse(creaStanza(3).removeAttrezzo(new Attrezzo("NonContenuto",7)));
		}
		@Test
		public void testRemoveAttrezzoStanzaVuota() {
			assertFalse(creaStanza(0).removeAttrezzo(new Attrezzo("Luce",1)));
		}
		@Test
		public void testHasAttrezziEsiste() {
			assertTrue(creaStanza(3).hasAttrezzo("Attrezzo"+1));
		}
		@Test
		public void testHasAttrezziNonContenuto() {
			assertFalse(creaStanza(5).hasAttrezzo("Attrezzo"+8));
		}
		@Test
		public void testHasAttrezziNonEsiste() {
			assertFalse(creaStanza(5).hasAttrezzo("Lancia"));
		}
		@Test
		public void testGetAttrezzo_stanzaVuota() {
			Stanza vuota = new Stanza("vuota");
			assertNull(vuota.getAttrezzo("inesistente"));
		}
		@Test
		public void testGetAttrezzo_stanzaNonVuota_Presente() {
			Stanza stanza = new Stanza("stanza");
			Attrezzo attrezzo = new Attrezzo("attrezzo",0);
			stanza.addAttrezzo(attrezzo);
			assertNotNull(stanza.getAttrezzo("attrezzo"));
		}
		@Test
		public void testGetAttrezzo_stanzaNonVuota_Assente() {
			Stanza stanza = new Stanza("stanza");
			Attrezzo attrezzo = new Attrezzo("attrezzo",0);
			stanza.addAttrezzo(attrezzo);
			assertNull(stanza.getAttrezzo("nome attrezzo non presente"));
		}
		
}
