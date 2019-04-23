package pico.placa.predictor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.apache.commons.lang3.StringUtils;

public class Validation {

	public static void validateInput(String plateNumber, String date, String time) {
		if (StringUtils.isBlank(plateNumber) || StringUtils.isBlank(date) || StringUtils.isBlank(time)) {
			throw new RuntimeException("The input parameters aren't correct!");
		}
	}

	public static int isValidPlateNumber(String plateNumber) {

		if (plateNumber.length() != 3) {
			throw new RuntimeException("The plate number size entered isn't correct");
		}

		if (!plateNumber.matches("[0-9]*")) {
			throw new RuntimeException("The plate number format entered isn't correct");
		}

		return Integer.parseInt(plateNumber.substring(2, 3));
	}

	public static int isValidDate(String date) {

		LocalDate localDate = null;

		if (date.length() != 8) {
			throw new RuntimeException("The date size entered isn't correct");
		}

		if (!date.matches("[0-9]*")) {
			throw new RuntimeException("The date format entered isn't correct");
		}

		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int dayOfMonth = Integer.parseInt(date.substring(6, 8));

		// PREDICTOR : SOMETHING WILL HAPPEN IN THE FUTURE
		if (year < LocalDateTime.now().getYear()) {
			throw new RuntimeException("The year entered is not correct");
		}

		if (!(month > 0 && month < 13)) {
			throw new RuntimeException("The month entered is not correct");
		}

		if (!(dayOfMonth > 0 && dayOfMonth < 32)) {
			throw new RuntimeException("The day entered is not correct");
		}

		try {
			localDate = LocalDate.of(year, month, dayOfMonth);
		} catch (Exception e) {
			throw new RuntimeException("The date entered is not correct");
		}

		if (localDate != null && localDate.isBefore(LocalDate.now())) {
			throw new RuntimeException("The date entered must be greater than the current one");
		}

		return localDate.getDayOfWeek().getValue();

	}

	public static LocalTime isValidTime(String time) {

		LocalTime localTime = null;

		if (time.length() != 4) {
			throw new RuntimeException("The time size entered is not correct");
		}

		if (!time.matches("[0-9]*")) {
			throw new RuntimeException("The time format entered is not correct");
		}

		int hour = Integer.parseInt(time.substring(0, 2));
		int minute = Integer.parseInt(time.substring(2, 4));

		if (!(hour > -1 && hour < 24)) {
			throw new RuntimeException("The hour entered is not correct");
		}

		if (!(minute > -1 && minute < 60)) {
			throw new RuntimeException("The minutes entered are not correct");
		}

		try {
			localTime = LocalTime.of(hour, minute);
		} catch (Exception e) {
			throw new RuntimeException("The time entered is not correct");
		}

		return localTime;
	}

}
