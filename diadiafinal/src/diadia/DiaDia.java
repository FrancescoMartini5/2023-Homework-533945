package diadia;


import diadia.ambienti.Stanza;
import diadia.attrezzi.Attrezzo;

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
			"Per conoscere le istruzioni usa il comando 'aiuto'.\n"
			+ "#####################################################################################\n"
			+"Ora ti trovi in:";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa","borsa"};

	private Partita partita;
	private IOConsole console;

	public DiaDia() {
		this.partita = new Partita();
		this.console = new IOConsole();
	}

	public void gioca() {
		String istruzione; 

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		this.console.mostraMessaggio(this.partita.getLabirinto().getStanzaCorrente().toString());

		do		
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if(comandoDaEseguire.getNome()!=null)
			if (comandoDaEseguire.getNome().equals("fine")) {
				this.fine(); 
				return true;
			} else
				if (comandoDaEseguire.getNome().equals("vai")) {
					if(this.partita.getGiocatore().getCfu()==0) {
						this.console.mostraMessaggio("Hai finito i cfu\n");
						this.fine();					
					}
					else
						this.vai(comandoDaEseguire.getParametro());}
				else

					if (comandoDaEseguire.getNome().equals("prendi"))
						this.prendi(comandoDaEseguire.getParametro());
					else
						if (comandoDaEseguire.getNome().equals("posa"))
							this.posa(comandoDaEseguire.getParametro());
						else if (comandoDaEseguire.getNome().equals("aiuto"))
							this.aiuto();
						else if(comandoDaEseguire.getNome().equals("borsa"))
							this.contenutoBorsa();
						else
							this.console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	private void contenutoBorsa() {
		this.console.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
	}

	// implementazioni dei comandi dell'utente:

	public void posa(String nomeAttrezzo) {
		if(nomeAttrezzo!=null)
			if(this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
				Attrezzo a = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
				this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
				this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(a);
				System.out.println(a.getNome()+" posato");
			}else
				this.console.mostraMessaggio("Attrezzo non presente");
		else this.console.mostraMessaggio("Attrezzo non specificato");
	}

	public void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo!=null)
			if(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
				Attrezzo a = this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
				this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(a);
				this.partita.getGiocatore().getBorsa().addAttrezzo(a);
				this.console.mostraMessaggio(a.getNome()+" preso");
			}else {
				this.console.mostraMessaggio("Attrezzo non presente\n");
			}
		else this.console.mostraMessaggio("Attrezzo non specificato");
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			this.console.mostraMessaggio(elencoComandi[i]+" ");
		this.console.mostraMessaggio(" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.console.mostraMessaggio("Direzione inesistente, aggiungi una direzione");
		else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu-1);
		}
		this.console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}

	public IOConsole getConsole() {
		return console;
	}

	public void setConsole(IOConsole console) {
		this.console = console;
	}
}