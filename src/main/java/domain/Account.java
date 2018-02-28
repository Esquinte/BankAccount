package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Account implements Comparable<Account> {
	private Client client;
	private Amount amount;
	private List<Operation> operations;

	/** Constructor */
	public Account(Client client, Amount amount) {
		this.client = client;
		this.amount = amount;
		this.operations = new ArrayList<Operation>();
	}

	public Account(String clientName, int amountValue) {
		this.client = new Client(clientName);
		this.amount = new Amount(amountValue);
		this.operations = new ArrayList<Operation>();
	}

	/** Getter */
	public Client getClient() {
		return this.client;
	}

	public Amount getAmount() {
		return this.amount;
	}

	public List<Operation> getOperations() {
		return this.operations;
	}

	/**
	 * Add an amount to this account
	 * @param amount the value of the deposit
	 * @return false if an exception is caught, true otherwise
	 */
	public boolean deposit(int amount) {
		Operation operation = new Operation(new Date(), OperationType.ADD, amount, this.amount.getValue());
		this.operations.add(operation);
		return this.amount.add(amount);
	}

	/**
	 * Subtract an amount to this account
	 * @param amount the value of the withdrawal
	 * @return false if an exception is caught, true otherwise
	 */
	public boolean withdrawal(int amount) {
		Operation operation = new Operation(new Date(), OperationType.SUBSTRACT, amount, this.amount.getValue());
		this.operations.add(operation);
		return this.amount.subtract(amount);
	}

	/**
	 * Print the previous operations of this account
	 */
	public void operations() {
		System.out.printf("OPERATION\t|\tDATE\t\t\t\t|\tAMOUNT\t|\tBALANCE\n");
		Collections.sort((List<Operation>) this.operations);
		for (Operation operation : this.operations) {
			System.out.printf(operation.getOperationType() + " \t|\t" + operation.getDate().toString() + "\t|\t" + operation.getValue() + "\t|\t" + operation.getTotal() + "\n");
		}
		System.out.printf("\nTOTAL = " + this.getAmount().getValue());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof Account) {
			Account account = (Account) obj;
			return this.getClient().equals(account.getClient()) && this.getAmount().getValue() == account.getAmount().getValue();
		}
		return false;
	}

	@Override
	public int compareTo(Account o) {
		return this.getClient().getName().compareTo(o.getClient().getName());
	}
}
