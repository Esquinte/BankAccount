package application;

import domain.Account;
import domain.Bank;

public class Application {

	public static void main(String[] args) {
		Bank bank = Bank.getInstance();
		bank.createAccount("John Doe", 5000);

		Account account = bank.getAccount("John Doe");
		account.deposit(157);
		account.withdrawal(364);
		account.withdrawal(438);
		account.deposit(691);
		account.operations();
	}
}
