package pico.placa.predictor;

import java.time.LocalTime;

public final class Constants {

	public static final LocalTime INITIAL_MORNING = LocalTime.of(6, 59);
	public static final LocalTime FINAL_MORNING = LocalTime.of(9, 31);

	public static final LocalTime INITIAL_EVENING = LocalTime.of(15, 59);
	public static final LocalTime FINAL_EVENING = LocalTime.of(19, 31);

	private Constants() {
	}
}
