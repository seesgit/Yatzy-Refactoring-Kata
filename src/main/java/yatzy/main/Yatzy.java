package yatzy.main;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

		if (Arrays.stream(dice).allMatch(x -> x == compare)) {
			return 50;
		} else {
			return 0;
		}

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

	public static int two_pair(int d1, int d2, int d3, int d4, int d5) {
		List<Integer> list = construct_counter(d1, d2, d3, d4, d5);

		return list.stream().filter(x -> Collections.frequency(list, x) >= 2).distinct().map(val -> val * 2)
				.collect(Collectors.summingInt(Integer::intValue));
	}

	public static int score_pair(int d1, int d2, int d3, int d4, int d5) {
		return x_of_a_kind(2, construct_counter(d1, d2, d3, d4, d5));
	}

	public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
		return x_of_a_kind(4, construct_counter(d1, d2, d3, d4, d5));
	}

	public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
		return x_of_a_kind(3, construct_counter(d1, d2, d3, d4, d5));
	}

	public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {

		List<Integer> withoutDuplicate = construct_counter_for_straight(d1, d2, d3, d4, d5);

		if (withoutDuplicate.size() == 5 && withoutDuplicate.get(withoutDuplicate.size() - 1) == 5) {
			return 15;
		} else {
			return 0;
		}
	}

	public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {

		List<Integer> withoutDuplicate = construct_counter_for_straight(d1, d2, d3, d4, d5);

		if (withoutDuplicate.size() == 5 && withoutDuplicate.get(withoutDuplicate.size() - 1) == 6) {
			return 20;
		} else {
			return 0;
		}
	}

	public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {

		List<Integer> dice = construct_counter(d1, d2, d3, d4, d5);
		Optional<Integer> twoTime = dice.stream().filter(x -> Collections.frequency(dice, x) == 2).findFirst();
		Optional<Integer> threeTime = dice.stream().filter(x -> Collections.frequency(dice, x) == 3).findFirst();

		if (twoTime.isPresent() && threeTime.isPresent()) {
			return twoTime.get() * 2 + threeTime.get() * 3;
		} else {
			return 0;
		}

	}

	/**
	 * Method use to add a given number each time we get it
	 * 
	 * @param dice_value
	 * @param dice
	 * @return the result of the addition
	 */
	private static int numbers_family(int dice_value, int[] dice) {
		List<Integer> collect = Arrays.stream(dice).boxed().collect(Collectors.toList());
		return Collections.frequency(collect, dice_value) * dice_value;
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
	private static List<Integer> construct_counter(int d1, int d2, int d3, int d4, int d5) {
		return IntStream.of(d1, d2, d3, d4, d5).boxed().collect(Collectors.toList());

	}

	/**
	 * Method used to construct an array relative to how many times a dice value is
	 * find, for the combination straight we have to eliminate duplicate and sort
	 * the elements
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return an array that matches the dice values
	 */
	private static List<Integer> construct_counter_for_straight(int d1, int d2, int d3, int d4, int d5) {

		return IntStream.of(d1, d2, d3, d4, d5).boxed().distinct().sorted().collect(Collectors.toList());
	}

	/**
	 * Method used to calculate a value which we get "pair_value" times
	 * 
	 * @param pair_value
	 * @param dice
	 * @return the result of the addition
	 */
	private static int x_of_a_kind(int pair_value, List<Integer> dice) {

		int max = dice.stream().filter(x -> Collections.frequency(dice, x) >= pair_value)
				.max(Comparator.comparing(Integer::valueOf)).get();

		return max * pair_value;
	}
}
