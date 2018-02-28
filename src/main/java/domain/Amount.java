package domain;

public class Amount {
	private int value;

	/** Constructor */
	public Amount(int value) {
		this.value = value;
	}

	/** Getter */
	public int getValue() {
		return this.value;
	}

	/**
	 * Add a value to the amount
	 * @param value the value to be added
	 * @return false if an exception is caught, true otherwise
	 */
	public boolean add(int value) {
		try {
			this.value = Math.addExact(this.value, value);
		} catch (ArithmeticException e) {
			System.err.printf("Amount too high!");
			return false;
		}
		return true;
	}

	/**
	 * Subtract a value to the value
	 * @param value the value to be subtracted
	 * @return false if an exception is caught, true otherwise
	 */
	public boolean subtract(int value) {
		try {
			if (Math.subtractExact(this.value, value) >= 0) {
				this.value = Math.subtractExact(this.value, value);
			} else {
				System.err.printf("Balance too low!");
				return false;
			}
		} catch (ArithmeticException e) {
			System.err.printf("Balance too low!");
			return false;
		}
		return true;
	}
}