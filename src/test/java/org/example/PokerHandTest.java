package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.example.Grad.*;
import static org.junit.jupiter.api.Assertions.*;

public class PokerHandTest {

    @Test
    public void testRoyalFlush() {
        PokerHand hand = new PokerHand("TS JS QS KS AS");
        assertEquals(ROYAL_FLUSH, hand.firstGrade);
    }

    @Test
    public void testStraightFlush() {
        PokerHand hand = new PokerHand("2H 3H 4H 5H 6H");
        assertEquals(STRAIGHT_FLUSH, hand.firstGrade);
    }


    @Test
    public void testFourOfAKind() {
        PokerHand hand = new PokerHand("AH AD AC AS 2H");
        assertEquals(FOUR_OF_A_KIND, hand.firstGrade);
    }

    @Test
    public void testFullHouse() {
        PokerHand hand = new PokerHand("KD KH KC 2S 2D");
        assertEquals(FULL_HOUSE, hand.firstGrade);
    }

    @Test
    public void testFlush() {
        PokerHand hand = new PokerHand("2C 4C 6C 8C TC");
        assertEquals(FLUSH, hand.firstGrade);
    }

    @Test
    public void testStraight() {
        PokerHand hand = new PokerHand("2C 3D 4H 5S 6C");
        assertEquals(STRAIGHT, hand.firstGrade);
    }

    @Test
    public void testThreeOfAKind() {
        PokerHand hand = new PokerHand("AH AD AC 2S 3D");
        assertEquals(THREE_OF_A_KIND, hand.firstGrade);
    }

    @Test
    public void testTwoPair() {
        PokerHand hand = new PokerHand("KD KH 2C 2S 3D");
        assertEquals(TWO_PAIR, hand.firstGrade);
    }

    @Test
    public void testOnePair() {
        PokerHand hand = new PokerHand("QS QH 4C 7S 8D");
        assertEquals(ONE_PAIR, hand.firstGrade);
    }

    @Test
    public void testHighCard() {
        PokerHand hand = new PokerHand("KS 2H 4C 7S 8D");
        assertEquals(HIGH_CARD, hand.firstGrade);
    }

    @Test
    public void testSortPokerHands() {
        ArrayList<PokerHand> hands = new ArrayList<>();

        hands.add(new PokerHand("2C 3C 4C 5C 6C"));
        hands.add(new PokerHand("3C 4C 5C 6C 7C"));
        hands.add(new PokerHand("KS 2H 5C JD TD"));
        hands.add(new PokerHand("2C 3C AC 4C 5C"));
        hands.add(new PokerHand("KS 3H 5C JD TD"));

        Collections.sort(hands);

        ArrayList<PokerHand> expectedOrder = new ArrayList<>();
        expectedOrder.add(new PokerHand("3C 4C 5C 6C 7C"));
        expectedOrder.add(new PokerHand("2C 3C 4C 5C 6C"));
        expectedOrder.add(new PokerHand("2C 3C AC 4C 5C"));
        expectedOrder.add(new PokerHand("KS 3H 5C JD TD"));
        expectedOrder.add(new PokerHand("KS 2H 5C JD TD"));

        Assertions.assertIterableEquals(hands, expectedOrder);
    }

    @Test
    public void testSortPokerHandsWithDuplicationOfCards() {
        ArrayList<PokerHand> hands = new ArrayList<>();

        hands.add(new PokerHand("AS AS 3S 4S 5S"));
        hands.add(new PokerHand("4H 4C 4H 4C 7C"));

        Collections.sort(hands);

        ArrayList<PokerHand> expectedOrder = new ArrayList<>();
        expectedOrder.add(new PokerHand("4H 4C 4H 4C 7C"));
        expectedOrder.add(new PokerHand("AS AS 3S 4S 5S"));

        Assertions.assertIterableEquals(hands, expectedOrder);
    }
    @Test
    public void testSortPokerHandsWithDuplicationOfCardsFourOfAKindPlusFlash() {
        ArrayList<PokerHand> hands = new ArrayList<>();

        hands.add(new PokerHand("2S 2S 2D 5S 5S"));
        hands.add(new PokerHand("KD KD KD KD AD"));

        Collections.sort(hands);

        ArrayList<PokerHand> expectedOrder = new ArrayList<>();
        expectedOrder.add(new PokerHand("KD KD KD KD AD"));
        expectedOrder.add(new PokerHand("2S 2S 2D 5S 5S"));

        Assertions.assertIterableEquals(hands, expectedOrder);
    }


    @Test
    public void testEmptyHand() {
        assertThrows(IllegalArgumentException.class, () -> new PokerHand(""));
    }

    @Test
    public void testHandWithLessThanFiveCards() {
        assertThrows(IllegalArgumentException.class, () -> new PokerHand("2C 3C 4C 5C"));
    }

    @Test
    public void testHandWithMoreThanFiveCards() {
        assertThrows(IllegalArgumentException.class, () -> new PokerHand("2C 3C 4C 5C 6C 7C"));
    }

    @Test
    public void testHandWithInvalidCard() {
        assertThrows(IllegalArgumentException.class, () -> new PokerHand("2C 3C 4C 5C XY"));
    }

    @Test
    public void testSortingEmptyList() {
        ArrayList<PokerHand> hands = new ArrayList<>();
        Collections.sort(hands);
        assertTrue(hands.isEmpty());
    }

}


