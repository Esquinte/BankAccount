package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class OperationTest {
	private Operation addOperation;
	private Operation substractOperation;
	private Date date1;
	private Date date2;

	@Before
	public void setUp() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date1String = "27/02/2018";
		String date2String = "28/02/2018";
		date1 = simpleDateFormat.parse(date1String);
		date2 = simpleDateFormat.parse(date2String);
		addOperation = new Operation(date1, OperationType.ADD, 500, 500);
		substractOperation = new Operation(date2, OperationType.SUBSTRACT, 800, 1000);
	}

	@Test
	public void should_have_date() {
		assertThat(addOperation.getDate()).isEqualTo(date1);
		assertThat(substractOperation.getDate()).isEqualTo(date2);
	}

	@Test
	public void should_have_operation_type() {
		assertThat(addOperation.getOperationType()).isEqualTo(OperationType.ADD);
		assertThat(substractOperation.getOperationType()).isEqualTo(OperationType.SUBSTRACT);
	}

	@Test
	public void should_have_value() {
		assertThat(addOperation.getValue()).isEqualTo(500);
		assertThat(substractOperation.getValue()).isEqualTo(800);
	}

	@Test
	public void should_have_total() {
		assertThat(addOperation.getTotal()).isEqualTo(500);
		assertThat(substractOperation.getTotal()).isEqualTo(1000);
	}

	@Test
	public void should_compare_to_other_operation() {
		assertThat(addOperation.compareTo(substractOperation)).isEqualTo(-1);
		assertThat(substractOperation.compareTo(addOperation)).isEqualTo(1);
		assertThat(addOperation.compareTo(addOperation)).isEqualTo(0);
		assertThat(substractOperation.compareTo(substractOperation)).isEqualTo(0);
	}
}
