package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import diadia.ambienti.Stanza;
import diadia.attrezzi.Attrezzo;

public class StanzaTest {
	private Stanza stanza;
	
	@Before
	public void setUp() {
		this.stanza = new Stanza("n11");
	}
	
	@Test
	public void testAddAttrezzo() {
		Attrezzo a = new Attrezzo("osso",3);
		assertTrue(this.stanza.addAttrezzo(a));
		assertFalse(this.stanza.addAttrezzo(null));
		this.stanza.setNumeroMax();
		assertFalse(this.stanza.addAttrezzo(a));
	}
	
	@Test
	public void testHasAttrezzo() {
		Attrezzo a = new Attrezzo("osso",3);
		this.stanza.addAttrezzo(a);
		assertTrue(this.stanza.hasAttrezzo(a.getNome()));
		assertFalse(this.stanza.hasAttrezzo("pollo"));
		assertFalse(this.stanza.hasAttrezzo(null));

	}
	
	@Test
	public void testRemoveAttrezzo() {
		Attrezzo a = new Attrezzo("osso",3);
		this.stanza.addAttrezzo(a);
		assertFalse(this.stanza.removeAttrezzo(new Attrezzo("pollo",5)));
		assertFalse(this.stanza.removeAttrezzo(null));
		assertTrue(this.stanza.removeAttrezzo(a));
	}
	
	@Test
	public void testGetAttrezzo() {
		Attrezzo a = new Attrezzo("osso",3);
		this.stanza.addAttrezzo(a);
		assertSame(a,this.stanza.getAttrezzo("osso"));
		assertNull(this.stanza.getAttrezzo("pollo"));
		assertNull(this.stanza.getAttrezzo(null));
	}
	
	

}
