package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class OperationTypeTest {
	@Test
	public void should_have_add_name() {
		OperationType addOperation = OperationType.ADD;
		assertThat(addOperation.toString()).isEqualTo("deposit");
	}

	@Test
	public void should_have_substract_name() {
		OperationType substractOperation = OperationType.SUBSTRACT;
		assertThat(substractOperation.toString()).isEqualTo("withdrawal");
	}
}
