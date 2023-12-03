package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(new PokerHand("2C 3C AC 4C 5C"));
        Collections.sort(hands);
        System.out.println(hands.get(0).getFirstGrade());
    }
}