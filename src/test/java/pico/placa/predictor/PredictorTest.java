package pico.placa.predictor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.Test;

public class PredictorTest {

	// General Input Test
	@Test
	public void testPredictorInputEmptyParameters() {
		RuntimeException exception = null;
		try {
			Predictor tester = new Predictor("", "", "");
			tester.validateInput();
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The input parameters aren't correct!", exception.getMessage());
	}

	@Test
	public void testPredictorInputNullParameters() {
		RuntimeException exception = null;
		try {
			Predictor tester = new Predictor(null, null, null);
			tester.validateInput();
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

			Predictor tester = new Predictor("789798789789798798", "00", "00");
			tester.isValidPlateNumber();
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The plate number size entered isn't correct", exception.getMessage());
	}

	@Test
	public void testValidateNumberPlateFormat() {
		RuntimeException exception = null;
		try {
			Predictor tester = new Predictor("AXX8", "00", "00");
			tester.isValidPlateNumber();
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The plate number format entered isn't correct", exception.getMessage());
	}

	@Test
	public void testOneGetLastPlateNumber() {
		Predictor tester = new Predictor("0095", "00", "00");
		assertEquals(5, tester.isValidPlateNumber());
	}

	@Test
	public void testTwoGetLastPlateNumber() {
		Predictor tester = new Predictor("9890", "00", "00");
		assertEquals(0, tester.isValidPlateNumber());
	}

	// Date Test
	@Test
	public void testValidateDateSize() {
		RuntimeException exception = null;
		try {
			Predictor tester = new Predictor("990", "78451254778979878879", "00");
			tester.isValidDate();
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The date size entered isn't correct", exception.getMessage());
	}

	@Test
	public void testValidateDateFormat() {
		RuntimeException exception = null;
		try {
			Predictor tester = new Predictor("990", "E#F&YH)K", "00");
			tester.isValidDate();
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The date format entered isn't correct", exception.getMessage());
	}

	@Test
	public void testValidateDateFormatYear() {
		RuntimeException exception = null;
		try {
			Predictor tester = new Predictor("990", "01014401", "00");
			tester.isValidDate();
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The year entered is not correct", exception.getMessage());
	}

	@Test
	public void testValidateDateFormatMonth() {
		RuntimeException exception = null;
		try {
			Predictor tester = new Predictor("990", "20199923", "00");
			tester.isValidDate();
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The month entered is not correct", exception.getMessage());
	}

	@Test
	public void testValidateDateFormatDay() {
		RuntimeException exception = null;
		try {
			Predictor tester = new Predictor("990", "20440232", "00");
			tester.isValidDate();
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The day entered is not correct", exception.getMessage());
	}

	@Test
	public void testValidateDateInputOne() {
		RuntimeException exception = null;
		try {
			Predictor tester = new Predictor("990", "20440231", "00");
			tester.isValidDate();
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The date entered is not correct", exception.getMessage());
	}

	@Test
	public void testValidateDateInputTwo() {
		RuntimeException exception = null;
		try {
			Predictor tester = new Predictor("990", "20190401", "00");
			tester.isValidDate();
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The date entered must be greater than the current one", exception.getMessage());
	}

	@Test
	public void testGetDayOfTheWeekBirthday() {
		Predictor tester = new Predictor("990", "20190604", "00");
		assertEquals(DayOfWeek.TUESDAY.name(), tester.isValidDate());// Tuesday (day of the week 2)
	}

	@Test
	public void testGetDayOfTheWeekMonday() {
		Predictor tester = new Predictor("990", "20191230", "00");
		assertEquals(DayOfWeek.MONDAY.name(), tester.isValidDate());// Monday (day of the week 1)
	}

	// Time Test
	@Test
	public void testValidateTimeSize() {
		RuntimeException exception = null;
		try {
			Predictor tester = new Predictor("990", "20191230", "789789798789798789798");
			tester.isValidTime();
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The time size entered is not correct", exception.getMessage());
	}

	@Test
	public void testValidateTimeFormat() {
		RuntimeException exception = null;
		try {
			Predictor tester = new Predictor("990", "20191230", "99EX");
			tester.isValidTime();
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The time format entered is not correct", exception.getMessage());
	}

	@Test
	public void testValidateTimeFormatHour() {
		RuntimeException exception = null;
		try {
			Predictor tester = new Predictor("990", "20191230", "6500");
			tester.isValidTime();
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The hour entered is not correct", exception.getMessage());
	}

	@Test
	public void testValidateDateFormatMinute() {
		RuntimeException exception = null;
		try {
			Predictor tester = new Predictor("990", "20191230", "0089");
			tester.isValidTime();
		} catch (RuntimeException e) {
			exception = e;
		}
		assertEquals("The minutes entered are not correct", exception.getMessage());
	}

	@Test
	public void testValidateDateInput() {
		Predictor tester = new Predictor("990", "20191230", "0000");
		assertEquals(LocalTime.of(00, 00), tester.isValidTime());
	}

	@Test
	public void testValidatePredictorMonday() {
		Predictor tester = new Predictor("9091", "20190902", "0701");
		assertFalse(tester.canBeOnTheRoad());
	}

	@Test
	public void testValidatePredictorFriday() {
		Predictor tester = new Predictor("4507", "20191227", "0800");
		assertTrue(tester.canBeOnTheRoad());
	}

	@Test
	public void testValidatePredictorWeekend() {
		Predictor tester = new Predictor("0010", "20190803", "0800");
		assertTrue(tester.canBeOnTheRoad());
	}

}
