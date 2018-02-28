package domain;

public enum OperationType {
	ADD("deposit"),
	SUBSTRACT("withdrawal");

	private String name = "";

	/** Constructor */
	OperationType(String name){
		this.name = name;
	}

	/** Getter */
	public String toString(){
		return name;
	}
}
