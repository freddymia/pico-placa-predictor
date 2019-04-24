package pico.placa.predictor;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int exit = -1;

		System.out.println("****** WELCOME PICO PLACA PREDICTOR ******");

		while (exit < 0) {

			String inputExit;

			try {

				String plateNumber;
				String date;
				String time;

				System.out.print("Enter your plate number (4 digits numbers):\t");
				plateNumber = in.nextLine();

				System.out.print("Enter your date (8 digits numbers yyyyMMdd):\t");
				date = in.nextLine();

				System.out.print("Enter your time (4 digits numbers HHmm):\t");
				time = in.nextLine();

				Predictor predictor = new Predictor(plateNumber, date, time);
				System.out.print("Can be on the road:\t\t\t\t" + predictor.canBeOnTheRoad() + "\n");

			} catch (RuntimeException e) {
				System.err.print(e.getMessage() + " - Press enter to continue");
				in.nextLine();
			}

			System.out.print("For exit input \"x\" or enter to continue...\n");
			inputExit = in.nextLine();
			if (StringUtils.isNotBlank(inputExit) && inputExit.equals("x")) {
				exit = 1;
			}
		}

		System.out.println("******** EXIT PICO PLACA PREDICTOR *******");
		in.close();
		System.exit(0);

	}

}
