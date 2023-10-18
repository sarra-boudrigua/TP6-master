package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}
	@Test
	//S3 : On n'imprime pas le ticket si le montant inséré est insuffisament.
	public void nImprimePasSIMontantInsuffisant() {
		machine.insertMoney(PRICE-1);
		assertFalse(machine.printTicket(),"Pas de ticket, montant insuffisant!!");
	}

	@Test
	//S4 On imprime le ticket si le montant inséré est suffisant
	public void imprimeSiSuffisant(){
		machine.insertMoney(PRICE);
		assertTrue(machine.printTicket(), "Imprime ticket");
	}

	@Test
	//S5 Quand on imprime un ticket la balance est décrémentée du prix du ticket
	public void imprimeEtDecremente(){
		machine.insertMoney(PRICE);
		machine.printTicket();
		assertEquals(machine.getBalance(),0);
	}

	@Test
	//S6 Le montant collecté est mis à jour quand on imprime un ticket(pas avant)
	public void imprimeEtMetaJour(){
		machine.insertMoney(PRICE);
		assertEquals(machine.getTotal(),0);
	}
	@Test
	//S7 refund() rend correctement la monnaie
	public void refundRenduCorrect(){
		machine.insertMoney(10);
		assertEquals(machine.refund(),10);
	}

	@Test
	//S8 refund() remet la balance à zéro
	public void refundRemiseAZero(){
		machine.insertMoney(10);
		machine.refund();
		assertEquals(machine.getBalance(),0);
	}

	@Test
	//S9 on peut pas inserer un montant negatif
	public void NegativeAmount(){
		assertThrows(IllegalArgumentException.class, () -> { machine.insertMoney(-50); });
	}

	@Test
	//S10 on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	public void NegativeTicket(){
		assertThrows(IllegalArgumentException.class, () -> {machine = new TicketMachine(-20); });

	}
}
