package application;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApplicationTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(System.out);
	}

	@Test
	public void should_start_app() {
		Date date = new Date();

		String[] args = null;
		Application.main(args);

		String expectedPrint = "OPERATION\t|\tDATE\t\t\t\t|\tAMOUNT\t|\tBALANCE\n";
		expectedPrint += "deposit \t|\t" + date.toString() + "\t|\t157\t|\t5000\n";
		expectedPrint += "withdrawal \t|\t" + date.toString() + "\t|\t364\t|\t5157\n";
		expectedPrint += "withdrawal \t|\t"  + date.toString() + "\t|\t438\t|\t4793\n";
		expectedPrint += "deposit \t|\t"  + date.toString() + "\t|\t691\t|\t4355\n";
		expectedPrint += "\nTOTAL = 5046";
		assertThat(expectedPrint).isEqualTo(outContent.toString());
	}
}
