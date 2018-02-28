package domain;

import java.util.Date;

public class Operation implements Comparable<Operation> {
	private Date date;
	private OperationType operationType;
	private int value;
	private int total;

	/** Constructor */
	public Operation(Date date, OperationType operationType, int value, int total) {
		this.date = date;
		this.operationType = operationType;
		this.value = value;
		this.total = total;
	}

	/** Getter */
	public Date getDate() {
		return this.date;
	}

	public OperationType getOperationType() {
		return this.operationType;
	}

	public int getValue() {
		return this.value;
	}

	public int getTotal() {
		return this.total;
	}

	@Override
	public int compareTo(Operation o) {
		if (this.getDate().compareTo(o.getDate()) > 0) {
			return 1;
		} else if (this.getDate().compareTo(o.getDate()) < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}