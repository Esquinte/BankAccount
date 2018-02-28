package domain;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	private Client client;
	private Amount amount;

	@Before
	public void setUpClient() {
		System.setOut(new PrintStream(outContent));

		client = new Client("John Doe");
		amount = new Amount(1000); 
	}

	@After
	public void restoreStreams() {
	    System.setOut(System.out);
	}

	@Test
	public void should_make_deposit() {
		Account account = new Account(client, amount);
		Boolean resultToBeTrue = account.deposit(500);

		assertThat(resultToBeTrue).isTrue();
		assertThat(account.getAmount().getValue()).isEqualTo(1500);
	}

	@Test
	public void should_make_withdrawal() {
		Account account = new Account(client, amount);
		Boolean resultToBeTrue = account.withdrawal(326);

		assertThat(resultToBeTrue).isTrue();
		assertThat(account.getAmount().getValue()).isEqualTo(674);
	}

	@Test
	public void should_show_empty_operations() {
		Account account = new Account(client, amount);
		account.operations();
		String expectedPrint = "OPERATION\t|\tDATE\t\t\t\t|\tAMOUNT\t|\tBALANCE\n";
		expectedPrint += "\nTOTAL = " + account.getAmount().getValue();
		assertThat(expectedPrint).isEqualTo(outContent.toString());
	}

	@Test
	public void should_show_operations() {
		Account account = new Account(client, amount);
		account.deposit(500);
		account.withdrawal(150);
		account.operations();
		String expectedPrint = "OPERATION\t|\tDATE\t\t\t\t|\tAMOUNT\t|\tBALANCE\n";
		List<Operation> operations = account.getOperations();
		for (Operation operation : operations) {
			expectedPrint += operation.getOperationType() + " \t|\t" + operation.getDate().toString() + "\t|\t" + operation.getValue() + "\t|\t" + operation.getTotal() + "\n";
		}
		expectedPrint += "\nTOTAL = " + account.getAmount().getValue();
		assertThat(expectedPrint).isEqualTo(outContent.toString());
	}

	@Test
	public void should_be_equal() {
		Account a = new Account(client, amount);
		Account b = new Account("John Doe", 1000);

		assertThat(a).isEqualTo(b);
		assertThat(a).isEqualTo(a);
	}

	@Test
	public void should_not_be_equal() {
		Account a = new Account(client, amount);
		Account b = new Account("Doe John", 1000);
		Account c = new Account("John Doe", 500);

		assertThat(a).isNotEqualTo(b);
		assertThat(a).isNotEqualTo(c);
		assertThat(a).isNotEqualTo(null);
		assertThat(a).isNotEqualTo("account");
	}
}
