package domain;

public class Client {
	private String name;

	/** Constructor */
	public Client(String name) {
		this.name = name;
	}

	/** Getter */
	public String getName() {
		return this.name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof Client) {
			Client client = (Client) obj;
			return this.getName().equals(client.getName());
		}
		return false;
	}
}