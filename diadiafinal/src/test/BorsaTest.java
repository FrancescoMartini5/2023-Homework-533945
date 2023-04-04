package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import diadia.attrezzi.Attrezzo;
import diadia.giocatore.Borsa;

public class BorsaTest {

	private Borsa borsa;
	private Borsa borsaLeggera;

	@Before
	public void setUp() {
		this.borsa = new Borsa();
		this.borsaLeggera = new Borsa(3,1);
	}

	@Test
	public void testAddAttrezzo() {
		Attrezzo a = new Attrezzo("osso",4);
		assertTrue(this.borsa.addAttrezzo(a));
		assertFalse(this.borsaLeggera.addAttrezzo(a));
		assertFalse(this.borsa.addAttrezzo(null));
		this.borsaLeggera.setPesoMax(10);
		assertTrue(this.borsaLeggera.addAttrezzo(a));
		assertFalse(this.borsaLeggera.addAttrezzo(new Attrezzo ("pollo",2)));
	}
	
	@Test
	public void testGetPeso() {
		assertEquals(0,this.borsa.getPeso());
		this.borsa.addAttrezzo(new Attrezzo("osso",3));
		assertEquals(3,this.borsa.getPeso());
		this.borsa.addAttrezzo(new Attrezzo("penna",1));
		assertEquals(4,this.borsa.getPeso());
	}
	
	@Test
	public void testRemoveAttrezzo() {
		assertNull(this.borsa.removeAttrezzo("osso"));
		Attrezzo a = new Attrezzo("osso",3);
		this.borsa.addAttrezzo(a);
		assertNull(this.borsa.removeAttrezzo("pollo"));
		assertNull(this.borsa.removeAttrezzo(null));
		assertSame(a,this.borsa.removeAttrezzo("osso"));
	}


}
