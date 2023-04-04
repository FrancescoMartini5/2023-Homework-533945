package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import diadia.giocatore.Giocatore;

public class GiocatoreTest {

	Giocatore g = new Giocatore();
	
	@Test
	public void testGetCfuDefault() {
		assertEquals(20, g.getCfu());
	}
	
	@Test
	public void testSetCfu() {
		g.setCfu(3);
		assertEquals(3, g.getCfu());
	}

	@Test
	public void testGetBorsaDefault() {
		assertNotNull(g.getBorsa());
	}

}
