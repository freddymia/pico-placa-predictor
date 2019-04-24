package pico.placa.predictor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author freddy
 *
 */
public class Predictor {

	private String plateNumber;
	private String date;
	private String time;

	/**
	 * Constructor
	 * 
	 * @param plateNumber plate number
	 * @param date        year month day
	 * @param time        hour minute
	 */
	public Predictor(String plateNumber, String date, String time) {
		super();
		this.plateNumber = plateNumber;
		this.date = date;
		this.time = time;
	}

	/**
	 * Validate input
	 */
	public void validateInput() {
		if (StringUtils.isBlank(plateNumber) || StringUtils.isBlank(date) || StringUtils.isBlank(time)) {
			throw new RuntimeException("The input parameters aren't correct!");
		}
	}

	/**
	 * @return last plate number
	 */
	public int isValidPlateNumber() {

		if (plateNumber.length() != 4) {
			throw new RuntimeException("The plate number size entered isn't correct");
		}

		if (!plateNumber.matches("[0-9]*")) {
			throw new RuntimeException("The plate number format entered isn't correct");
		}

		return Integer.parseInt(plateNumber.substring(3, 4));
	}

	/**
	 * @return day of the week
	 */
	public String isValidDate() {

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

		return localDate.getDayOfWeek().name();

	}

	/**
	 * @return time
	 */
	public LocalTime isValidTime() {

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

	/**
	 * @return process if is possible be on the road
	 */
	public Boolean canBeOnTheRoad() {

		this.validateInput();

		int lastNumberPlate = this.isValidPlateNumber();

		String dayOfTheWeek = this.isValidDate();
		LocalTime localTime = this.isValidTime();

		// Weekends
		List<String> restrictionDayList = new ArrayList<>();
		for (RestrictionDay restrictionDay : RestrictionDay.values()) {
			restrictionDayList.add(restrictionDay.name());
		}

		if (!restrictionDayList.contains(dayOfTheWeek)) {
			return Boolean.TRUE;
		}

		// Restrictions after hours
		if ((localTime.isAfter(Constants.INITIAL_MORNING) && localTime.isBefore(Constants.FINAL_MORNING))
				|| localTime.isAfter(Constants.INITIAL_EVENING) && localTime.isBefore(Constants.FINAL_EVENING)) {

			// Day restriction
			if (dayOfTheWeek.equals(RestrictionDay.MONDAY.name()) && (lastNumberPlate == 1 || lastNumberPlate == 2)) {
				return Boolean.FALSE;
			}

			if (dayOfTheWeek.equals(RestrictionDay.TUESDAY.name()) && (lastNumberPlate == 3 || lastNumberPlate == 4)) {
				return Boolean.FALSE;
			}

			if (dayOfTheWeek.equals(RestrictionDay.WEDNESDAY.name())
					&& (lastNumberPlate == 5 || lastNumberPlate == 6)) {
				return Boolean.FALSE;
			}

			if (dayOfTheWeek.equals(RestrictionDay.THURSDAY.name()) && (lastNumberPlate == 7 || lastNumberPlate == 8)) {
				return Boolean.FALSE;
			}

			if (dayOfTheWeek.equals(RestrictionDay.FRIDAY.name()) && (lastNumberPlate == 9 || lastNumberPlate == 0)) {
				return Boolean.FALSE;
			}
		}

		return Boolean.TRUE;
	}

}