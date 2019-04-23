package pico.placa.predictor;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Test;

public class ValidationTest {

	// General Input Test
	@Test
	public void testPredictorInputEmptyParameters() {
		RuntimeException exception = null;
		try {
			Validation.validateInput("", "", "");
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The input parameters aren't correct!", exception.getMessage());
	}

	@Test
	public void testPredictorInputNullParameters() {
		RuntimeException exception = null;
		try {
			Validation.validateInput(null, null, null);
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The input parameters aren't correct!", exception.getMessage());
	}

	// Plate Number Test
	@Test
	public void testValidateNumberPlateSize() {
		RuntimeException exception = null;
		try {
			Validation.isValidPlateNumber("789798789789798798");
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The plate number size entered isn't correct", exception.getMessage());
	}

	@Test
	public void testValidateNumberPlateFormat() {
		RuntimeException exception = null;
		try {
			Validation.isValidPlateNumber("AX8");
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The plate number format entered isn't correct", exception.getMessage());
	}

	@Test
	public void testOneGetLastPlateNumber() {
		assertEquals(5, Validation.isValidPlateNumber("005"));
	}

	@Test
	public void testTwoGetLastPlateNumber() {
		assertEquals(0, Validation.isValidPlateNumber("990"));
	}

	// Date Test
	@Test
	public void testValidateDateSize() {
		RuntimeException exception = null;
		try {
			Validation.isValidDate("78451254778979878879");
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The date size entered isn't correct", exception.getMessage());
	}

	@Test
	public void testValidateDateFormat() {
		RuntimeException exception = null;
		try {
			Validation.isValidDate("E#F&YH)K");
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The date format entered isn't correct", exception.getMessage());
	}

	@Test
	public void testValidateDateFormatYear() {
		RuntimeException exception = null;
		try {
			Validation.isValidDate("01014401");
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The year entered is not correct", exception.getMessage());
	}

	@Test
	public void testValidateDateFormatMonth() {
		RuntimeException exception = null;
		try {
			Validation.isValidDate("20199923");
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The month entered is not correct", exception.getMessage());
	}

	@Test
	public void testValidateDateFormatDay() {
		RuntimeException exception = null;
		try {
			Validation.isValidDate("20440232");
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The day entered is not correct", exception.getMessage());
	}

	@Test
	public void testValidateDateInputOne() {
		RuntimeException exception = null;
		try {
			Validation.isValidDate("20440231");
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The date entered is not correct", exception.getMessage());
	}

	@Test
	public void testValidateDateInputTwo() {
		RuntimeException exception = null;
		try {
			Validation.isValidDate("20190401");
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The date entered must be greater than the current one", exception.getMessage());
	}

	@Test
	public void testGetDayOfTheWeekBirthday() {
		assertEquals(2, Validation.isValidDate("20190604"));// Tuesday (day of the week 2)
	}

	@Test
	public void testGetDayOfTheWeekMonday() {
		assertEquals(1, Validation.isValidDate("20191230"));// Monday (day of the week 1)
	}

	// Time Test
	@Test
	public void testValidateTimeSize() {
		RuntimeException exception = null;
		try {
			Validation.isValidTime("789789798789798789798");
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The time size entered is not correct", exception.getMessage());
	}

	@Test
	public void testValidateTimeFormat() {
		RuntimeException exception = null;
		try {
			Validation.isValidTime("99EX");
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The time format entered is not correct", exception.getMessage());
	}

	@Test
	public void testValidateTimeFormatHour() {
		RuntimeException exception = null;
		try {
			Validation.isValidTime("6500");
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The hour entered is not correct", exception.getMessage());
	}

	@Test
	public void testValidateDateFormatMinute() {
		RuntimeException exception = null;
		try {
			Validation.isValidTime("0089");
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The minutes entered are not correct", exception.getMessage());
	}

	@Test
	public void testValidateDateInput() {
		assertEquals(LocalTime.of(00, 00), Validation.isValidTime("0000"));
	}
}
