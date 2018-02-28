package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	private Bank bank;

	@Before
	public void setUp() {
		bank = Bank.getInstance();
		bank.deleteAllAccount();
	}

	@Test
	public void should_be_singleton() {
		Bank sameBank = Bank.getInstance();
		assertThat(bank.hashCode()).isEqualTo(sameBank.hashCode());
	}

	@Test
	public void should_create_account() {
		Boolean resultToBeTrue = bank.createAccount("John Doe", 1000);
		assertThat(resultToBeTrue).isTrue();

		Account accountToBeCreated = new Account("Doe John", 2000);
		resultToBeTrue = bank.createAccount(accountToBeCreated);
		assertThat(resultToBeTrue).isTrue();

		List<Account> accountList = bank.getAccounts();
		assertThat(accountList).hasSize(2);
	}

	@Test
	public void should_delete_account() {
		bank.createAccount("John Doe", 1000);
		bank.createAccount("Doe John", 2000);
		bank.createAccount("user", 3000);

		Boolean resultToBeTrue = bank.deleteAccount("John Doe");
		assertThat(resultToBeTrue).isTrue();

		Account accountToBeDeleted = new Account("Doe John", 2000);
		resultToBeTrue = bank.deleteAccount(accountToBeDeleted);
		assertThat(resultToBeTrue).isTrue();

		Client clientToBeDeleted = new Client("user");
		resultToBeTrue = bank.deleteAccount(clientToBeDeleted);
		assertThat(resultToBeTrue).isTrue();

		List<Account> accountList = bank.getAccounts();
		assertThat(accountList).isEmpty();
	}

	@Test
	public void should_get_accounts() {
		bank.createAccount("John Doe", 1000);
		bank.createAccount("Doe John", 2000);

		List<Account> accountList = bank.getAccounts();
		assertThat(accountList).hasSize(2);

		Collections.sort(accountList);
		assertThat(accountList.get(0).getClient().getName()).isEqualTo("Doe John");
		assertThat(accountList.get(1).getClient().getName()).isEqualTo("John Doe");

		assertThat(accountList.get(0).getAmount().getValue()).isEqualTo(2000);
		assertThat(accountList.get(1).getAmount().getValue()).isEqualTo(1000);
	}

	@Test
	public void should_get_account() {
		bank.createAccount("John Doe", 1000);
		bank.createAccount("Doe John", 2000);

		Account expectedAccount = new Account("Doe John", 2000);
		Account account = bank.getAccount("Doe John");

		assertThat(account.getClient().getName()).isEqualTo("Doe John");
		assertThat(account.getAmount().getValue()).isEqualTo(2000);
		assertThat(account).isEqualByComparingTo(expectedAccount);
	}

	@Test
	public void should_not_get_account() {
		bank.createAccount("John Doe", 1000);
		bank.createAccount("Doe John", 2000);

		Account account = bank.getAccount("user");

		assertThat(account).isNull();
	}
}
