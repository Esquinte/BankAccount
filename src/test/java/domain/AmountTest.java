package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AmountTest {
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	private Amount amount;

	@Before
	public void setUp() {
		System.setErr(new PrintStream(errContent));
		amount = new Amount(1234);
	}

	@After
	public void restoreStreams() {
	    System.setErr(System.err);
	}

	@Test
	public void should_have_value() {
		assertThat(amount.getValue()).isEqualTo(1234);
	}

	@Test
	public void should_add_value() {
		Boolean resultToBeTrue = amount.add(4321);
		assertThat(resultToBeTrue).isTrue();
		assertThat(amount.getValue()).isEqualTo(5555);
	}

	@Test
	public void should_substract_value() {
		Boolean resultToBeTrue = amount.subtract(874);
		assertThat(resultToBeTrue).isTrue();
		assertThat(amount.getValue()).isEqualTo(360);
	}

	@Test
	public void should_cap_add() {
		Boolean resultToBeFalse = amount.add(Integer.MAX_VALUE);
		String expectedPrint = "Amount too high!";

		assertThat(resultToBeFalse).isFalse();
		assertThat(amount.getValue()).isEqualTo(1234);
		assertThat(expectedPrint).isEqualTo(errContent.toString());
	}

	@Test
	public void should_cap_substract() {
		Boolean resultToBeFalse = amount.subtract(Integer.MIN_VALUE);
		String expectedPrint = "Balance too low!";

		assertThat(resultToBeFalse).isFalse();
		assertThat(amount.getValue()).isEqualTo(1234);
		assertThat(expectedPrint).isEqualTo(errContent.toString());

		errContent.reset();
		resultToBeFalse = amount.subtract(1235);

		assertThat(resultToBeFalse).isFalse();
		assertThat(amount.getValue()).isEqualTo(1234);
		assertThat(expectedPrint).isEqualTo(errContent.toString());
	}
}
