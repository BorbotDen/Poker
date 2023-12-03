package org.example;

public enum Grad {
    ROYAL_FLUSH(0),
    STRAIGHT_FLUSH(1),
    FOUR_OF_A_KIND(2),
    FULL_HOUSE(3),
    FLUSH(4),
    STRAIGHT(5),
    THREE_OF_A_KIND(6),
    TWO_PAIR(7),
    ONE_PAIR(8),
    HIGH_CARD(9),
    DEFAULT(10);

    private final int value;

    Grad(Integer value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
