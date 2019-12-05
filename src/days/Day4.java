package days;

import util.InputReader;

/**
 * --- Day 4: Secure Container ---
 * You arrive at the Venus fuel depot only to discover it's protected by a password. The Elves had written the password
 * on a sticky note, but someone threw it out.
 *
 * However, they do remember a few key facts about the password:
 *
 * - It is a six-digit number.
 * - The value is within the range given in your puzzle input.
 * - Two adjacent digits are the same (like 22 in 122345).
 * - Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
 *
 * Other than the range rule, the following are true:
 *
 * - 111111 meets these criteria (double 11, never decreases).
 * - 223450 does not meet these criteria (decreasing pair of digits 50).
 * - 123789 does not meet these criteria (no double).
 *
 * How many different passwords within the range given in your puzzle input meet these criteria?
 *
 * --- Part Two ---
 * An Elf just remembered one more important detail: the two adjacent matching digits are not part of a larger group of matching digits.
 *
 * Given this additional criterion, but still ignoring the range rule, the following are now true:
 *
 * - 112233 meets these criteria because the digits never decrease and all repeated digits are exactly two digits long.
 * - 123444 no longer meets the criteria (the repeated 44 is part of a larger group of 444).
 * - 111122 meets the criteria (even though 1 is repeated more than twice, it still contains a double 22).
 *
 * How many different passwords within the range given in your puzzle input meet all of the criteria?
 */

public class Day4 {

    public static void main(String[] args) {
        String[] rawInput = InputReader.getInput(4).split("-");
        int[] passwordRange = {Integer.valueOf(rawInput[0]), Integer.valueOf(rawInput[1])};

        System.out.println("Part 1: " + part1(passwordRange));
        System.out.println("Part 2: " + part2(passwordRange));
    }

    private static int part1(int[] passwordRange) {
        int combinations = 0;
        for(int i = passwordRange[0]; i < passwordRange[1]; i++) {
            if(increasingDigits(i) && duplicateDigits(i))
                combinations++;
        }

        return combinations;
    }

    private static int part2(int[] passwordRange) {
        int combinations = 0;
        for(int i = passwordRange[0]; i < passwordRange[1]; i++) {
            if(increasingDigits(i) && twoDuplicateDigits(i))
                combinations++;
        }

        return combinations;
    }

    private static boolean increasingDigits(int x) {
        int last = 10;
        do {
            int digit = x % 10;
            if(digit > last)
                return false;

            last = digit;
            x /= 10;
        }while(x > 0);

        return true;
    }

    private static boolean duplicateDigits(int x) {
        int last = 10;
        do {
            int digit = x % 10;
            if(digit == last)
                return true;

            last = digit;
            x /= 10;
        }while(x > 0);

        return false;
    }

    private static boolean twoDuplicateDigits(int x) {
        int[] digits = new int[6];
        for(int i = 0; i < 6; i++) {
            digits[i] = x % 10;
            x /= 10;
        }

        for(int digit : digits) {
            int count = 0;
            int startKey = -1;
            for(int j = 0; j < digits.length; j++) {
                if(digit == digits[j]) {
                    if(startKey == -1) {
                        startKey = j;
                        count++;
                    }else if(startKey - j < 2) {
                        count++;
                    }else if(startKey - j > 1) {
                        startKey = -1;
                        count = -10;
                    }
                }
            }

            if(count == 2) {
                return true;
            }
        }

        return false;
    }
}