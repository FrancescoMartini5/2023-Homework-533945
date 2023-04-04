package diadia.giocatore;

import diadia.attrezzi.Attrezzo;

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	public final static int DEFAULT_ATTREZZI_MAX_IN_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
		this.attrezzi = new Attrezzo[DEFAULT_ATTREZZI_MAX_IN_BORSA]; // speriamo che bastino...
		this.numeroAttrezzi = 0;
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[DEFAULT_ATTREZZI_MAX_IN_BORSA]; // speriamo che bastino...
		this.numeroAttrezzi = 0;
	}
	
	public Borsa(int pesoMax, int attMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[attMax]; // speriamo che bastino...
		this.numeroAttrezzi = 0;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null)
			return false;
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi== this.attrezzi.length)
			return false;
		else {
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;}
	}

	public int getPesoMax() {
		return pesoMax;
	}
	
	public void setPesoMax(int pesoMax) {
		this.pesoMax = pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo!=null)
			for(Attrezzo attrezzo : this.attrezzi)
				if(attrezzo!= null && nomeAttrezzo.equals(attrezzo.getNome()))
					return attrezzo;
		return null;
	}

	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if(this.attrezzi[i]!=null)
				peso += this.attrezzi[i].getPeso();
		return peso;
	}

	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}


//	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
//		Attrezzo a = null;
//		if(nomeAttrezzo!=null)
//			for(int i=0; i < this.attrezzi.length;i++) {
//				if(this.attrezzi[i] != null && nomeAttrezzo.equals(this.attrezzi[i].getNome())) {
//
//					a = this.attrezzi[i];
//					this.attrezzi[i] = null;	
//					this.numeroAttrezzi--;
//				}
//			}
//		return a;
//	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = this.getAttrezzo(nomeAttrezzo);
		if(a!=null)
			for(int i=0; i < this.attrezzi.length;i++) {
				if(this.attrezzi[i] != null && a.equals(this.attrezzi[i])) {
					this.attrezzi[i] = null;	
					this.numeroAttrezzi--;
					break;
				}
			}
		return a;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}


}
