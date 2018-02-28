package domain;
import org.junit.Test;

import domain.Client;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest {
	@Test
	public void should_have_name() {
		Client client = new Client("John Doe");
		assertThat(client.getName()).isNotNull();
	}

	@Test
	public void should_be_equal() {
		Client a = new Client("John Doe");
		Client b = new Client("John Doe");

		assertThat(a).isEqualTo(b);
		assertThat(a).isEqualTo(a);
	}

	@Test
	public void should_not_be_equal() {
		Client a = new Client("John Doe");
		Client b = new Client("Doe John");

		assertThat(a).isNotEqualTo(b);
		assertThat(a).isNotEqualTo(null);
		assertThat(a).isNotEqualTo("John Doe");
	}
}
