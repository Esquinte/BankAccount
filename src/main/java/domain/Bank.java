package domain;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Bank {
	List<Account> accounts;

	/** Constructor */
	private Bank() {
		this.accounts = new ArrayList<Account>();
	}

	private static class BankHolder {
		private final static Bank instance = new Bank();
	}

	public static Bank getInstance() {
		return BankHolder.instance;
	}

	/**
	 * Create an account to the bank
	 * @param clientName the client's name of the account to be created
	 * @param amountValue the amount's value of the account to be created
	 * @return true if the new account has been successfully created, false otherwise
	 */
	public boolean createAccount(String clientName, int amountValue) {
		return this.createAccount(new Account(clientName, amountValue));
	}

	/**
	 * Create an account to the bank
	 * @param account the account to be created
	 * @return true if the new account has been successfully created, false otherwise
	 */
	public boolean createAccount(Account account) {
		return this.accounts.add(account);
	}

	/**
	 * Delete an account to the bank
	 * @param client the client's account to be deleted
	 * @return true if the account has been successfully deleted, false otherwise
	 */
	public boolean deleteAccount(Client client) {
		Predicate<Account> accountPredicate = account -> account.getClient().equals(client);
		return this.accounts.removeIf(accountPredicate);
	}

	/**
	 * Delete an account to the bank
	 * @param account the account to be deleted
	 * @return true if the account has been successfully deleted, false otherwise
	 */
	public boolean deleteAccount(Account account) {
		Client client = account.getClient();
		return this.deleteAccount(client);
	}

	/**
	 * Delete an account to the bank
	 * @param clientName the client's name account to be deleted
	 * @return true if the account has been successfully deleted, false otherwise
	 */
	public boolean deleteAccount(String clientName) {
		Client client = new Client(clientName);
		return this.deleteAccount(client);
	}

	/**
	 * Delete all accounts from the bank
	 */
	public void deleteAllAccount() {
		this.accounts.clear();
	}

	/**
	 * Return the list of bank accounts
	 * @return the list of accounts
	 */
	public List<Account> getAccounts() {
		return this.accounts;
	}

	/**
	 * Return an account from the bank
	 * @param clientName the client name of the account to be returned
	 * @return if it exists, the account linked to the client's name, null otherwise
	 */
	public Account getAccount(String clientName) {
		for (Account account : accounts) {
			if (account.getClient().getName().equals(clientName)) {
				return account;
			}
		}
		return null;
	}
}
