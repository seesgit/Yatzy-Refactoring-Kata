package yatzy.main;

public class Yatzy {
	protected int[] dice;

	private static final int DICE_VALUE_ONE = 1;
	private static final int DICE_VALUE_TWO = 2;
	private static final int DICE_VALUE_THREE = 3;
	private static final int DICE_VALUE_FOUR = 4;
	private static final int DICE_VALUE_FIVE = 5;
	private static final int DICE_VALUE_SIX = 6;

	public Yatzy(int d1, int d2, int d3, int d4, int d5) {
		dice = new int[5];
		dice[0] = d1;
		dice[1] = d2;
		dice[2] = d3;
		dice[3] = d4;
		dice[4] = d5;
	}

	/**
	 * Method use to construct an array relative to parameters (dice values)
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return an array that matches the dice
	 */
	private static int[] construct_dice(int d1, int d2, int d3, int d4, int d5) {
		int[] dice = new int[5];
		dice[0] = d1;
		dice[1] = d2;
		dice[2] = d3;
		dice[3] = d4;
		dice[4] = d5;

		return dice;
	}

	public static int chance(int d1, int d2, int d3, int d4, int d5) {
		return d1 + d2 + d3 + d4 + d5;
	}

	public static int yatzy(int... dice) {
		int compare = dice[0];

		for (int i = 1; i < dice.length; i++) {
			if (dice[i] != compare) {
				return 0;
			}
		}
		return 50;

	}

	public static int ones(int d1, int d2, int d3, int d4, int d5) {
		int[] counts = construct_dice(d1, d2, d3, d4, d5);
		return numbers_family(DICE_VALUE_ONE, counts);
	}

	public static int twos(int d1, int d2, int d3, int d4, int d5) {
		int[] counts = construct_dice(d1, d2, d3, d4, d5);
		return numbers_family(DICE_VALUE_TWO, counts);
	}

	public static int threes(int d1, int d2, int d3, int d4, int d5) {
		int[] counts = construct_dice(d1, d2, d3, d4, d5);
		return numbers_family(DICE_VALUE_THREE, counts);
	}

	public int fours() {
		return numbers_family(DICE_VALUE_FOUR, dice);
	}

	public int fives() {
		return numbers_family(DICE_VALUE_FIVE, dice);
	}

	public int sixes() {
		return numbers_family(DICE_VALUE_SIX, dice);
	}

	/**
	 * Method use to add a given each time we get it
	 * 
	 * @param dice_value
	 * @param dice
	 * @return the result of the addition
	 */
	private static int numbers_family(int dice_value, int[] dice) {
		int result = 0;

		for (int value : dice) {
			if (value == dice_value) {
				result += dice_value;
			}
		}

		return result;

	}

	/**
	 * Method used to construct an array relative to how many times a dice value is
	 * find
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return an array that matches the dice values
	 */
	private static int[] construct_counter(int d1, int d2, int d3, int d4, int d5) {
		int[] counts = new int[6];
		counts[d1 - 1]++;
		counts[d2 - 1]++;
		counts[d3 - 1]++;
		counts[d4 - 1]++;
		counts[d5 - 1]++;
		return counts;
	}

	public static int two_pair(int d1, int d2, int d3, int d4, int d5) {
		int[] counts = construct_counter(d1, d2, d3, d4, d5);

		int result = 0;
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] >= 2) {
				result += (i + 1) * 2;
			}
		}

		return result;
	}

	/**
	 * Method used to calculate a value which we get "pair_value" times
	 * 
	 * @param pair_value
	 * @param dice
	 * @return the result of the addition
	 */
	private static int x_of_a_kind(int pair_value, int[] dice) {

		for (int i = 5; i > 0; i--) {
			if (dice[i] >= pair_value) {
				return (i + 1) * pair_value;
			}
		}

		return 0;
	}

	public static int score_pair(int d1, int d2, int d3, int d4, int d5) {
		int[] counts = construct_counter(d1, d2, d3, d4, d5);

		return x_of_a_kind(2, counts);
	}

	public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
		int[] counts = construct_counter(d1, d2, d3, d4, d5);
		return x_of_a_kind(4, counts);
	}

	public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
		int[] counts = construct_counter(d1, d2, d3, d4, d5);
		return x_of_a_kind(3, counts);
	}

	public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
		int[] counts = construct_counter(d1, d2, d3, d4, d5);
		if (counts[0] == 1 && counts[1] == 1 && counts[2] == 1 && counts[3] == 1 && counts[4] == 1)
			return 15;
		return 0;
	}

	public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
		int[] counts = construct_counter(d1, d2, d3, d4, d5);
		if (counts[1] == 1 && counts[2] == 1 && counts[3] == 1 && counts[4] == 1 && counts[5] == 1)
			return 20;
		return 0;
	}

	public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {

		int[] counts = construct_counter(d1, d2, d3, d4, d5);
		int result = 0;

		for (int i = 0; i < counts.length; i++) {
			if (counts[i] == 2) {
				result += (i + 1) * 2;
			}

			if (counts[i] == 3) {
				result += (i + 1) * 3;
			}
		}

		return result;

	}
}
