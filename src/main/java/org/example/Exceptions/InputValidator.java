package org.example.Exceptions;

public class InputValidator {
    private static final int CORRECT_LENGTH = 14;

    public static void validateInput(String hand) {
        if (!InputValidator.isValidInput(hand)) {
            throw new IllegalArgumentException("Invalid input: Hand cannot be null or empty.");
        }
        if (!InputValidator.hasValidCardCount(hand)) {
            throw new IllegalArgumentException("Invalid input: Hand must contain exactly 5 cards.");
        }
        if (!InputValidator.hasValidHandLength(hand)) {
            throw new IllegalArgumentException("Invalid input: Hand must have 14 characters.");
        }
        if (!InputValidator.testHandWithInvalidCard(hand)) {
            throw new IllegalArgumentException("Invalid input: Hand contains invalid card.");
        }
    }

    private static boolean testHandWithInvalidCard(String hand) {
        String[] cards = hand.split(" ");
        for (String card : cards) {
            if (!card.matches("[2-9TJQKA][CDHS]"))
                return false;
        }
        return true;
    }

    private static boolean hasValidHandLength(String hand) {
        return hand.length() == CORRECT_LENGTH;
    }


    private static boolean hasValidCardCount(String hand) {
        String[] cards = hand.split(" ");
        return cards.length == 5;
    }


    private static boolean isValidInput(String hand) throws NullPointerException {
        return hand != null && !hand.isEmpty();
    }

}
