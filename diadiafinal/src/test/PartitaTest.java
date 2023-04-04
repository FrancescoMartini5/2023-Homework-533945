package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import diadia.Partita;

public class PartitaTest {

	private Partita partita;

	@Before
	public void setUp() {
		this.partita = new Partita();
	}

	@Test
	public void testVinta() {
		assertFalse(this.partita.vinta());
		this.partita.getLabirinto().setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());

	}
	
	//	return finita || vinta() || (this.getGiocatore().getCfu() == 0);

	@Test
	public void testIsFinita() {
		assertFalse(this.partita.isFinita());
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
		this.partita.getGiocatore().setCfu(6);
		this.partita.getLabirinto().setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
		this.partita.setFinita();
		this.partita.getLabirinto().setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente().getStanzaAdiacente("sud"));
		assertTrue(this.partita.isFinita());
	}
	
	

}
