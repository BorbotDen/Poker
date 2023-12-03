package org.example;

import java.util.*;

import org.example.Exceptions.InputValidator;

import static org.example.Grad.*;


public class PokerHand implements Comparable<PokerHand> {
    String hand;
    Grad firstGrade = DEFAULT;
    List<Integer> kickers = new ArrayList<>(5);

    public Grad getFirstGrade() {
        return firstGrade;
    }

    List<CardWeight> cardWeightList = new ArrayList<>();
    List<Integer> uniqueCards = new ArrayList<>();
    boolean isSequential = false;
    boolean flushIndicator = true;
    int maxIdentic = 0;

    public PokerHand(String hand) {
        InputValidator.validateInput(hand);
        this.hand = hand;
        calculateGrade();
    }

    private void calculateGrade() {
        analyzeHand(splitAndParsing());
        int uniqueNumbersCount = uniqueCards.size();
        if (isSequential && flushIndicator) {
            if (uniqueCards.contains(14)) {
                firstGrade = ROYAL_FLUSH;
                kickers.addAll(uniqueCards);
            } else {
                firstGrade = STRAIGHT_FLUSH;
                kickers.addAll(uniqueCards);
            }
        } else if (maxIdentic == 4||maxIdentic == 5) {
            firstGrade = FOUR_OF_A_KIND;
            kickers.addAll(getCardsInWeightOrder());
        } else if (maxIdentic == 3 && uniqueNumbersCount == 2) {
            firstGrade = FULL_HOUSE;
            kickers.addAll(getCardsInWeightOrder());
        } else if (flushIndicator) {
            firstGrade = FLUSH;
            kickers.addAll(uniqueCards);
        } else if (isSequential) {
            firstGrade = STRAIGHT;
            kickers.addAll(uniqueCards);
        } else if (maxIdentic == 3) {
            firstGrade = THREE_OF_A_KIND;
            kickers.addAll(getCardsInWeightOrder());
        } else if (uniqueNumbersCount == 3) {
            firstGrade = TWO_PAIR;
            kickers.addAll(getCardsInWeightOrder());
        } else if (uniqueNumbersCount == 4) {
            firstGrade = ONE_PAIR;
            kickers.addAll(getCardsInWeightOrder());
        } else if (uniqueNumbersCount == 5) {
            firstGrade = HIGH_CARD;
            kickers.addAll(uniqueCards);
        }

    }

    private Collection<Integer> getCardsInWeightOrder() {
        List<Integer> cardsValue = new ArrayList<>();
        cardWeightList.forEach(val -> cardsValue.add(val.value));
        return cardsValue;
    }

    private void analyzeHand(List<Integer> hand) {
        // Уникальные номера карт по убыванию
        uniqueCards = new ArrayList<>(new HashSet<>(hand));
        uniqueCards.sort(Comparator.reverseOrder());
        // Проверка на последовательность
        if (uniqueCards.size() == 5) {
            int min = Collections.min(hand);
            int max = Collections.max(hand);
            isSequential = (max - min) == 4;
        }
        // Считаем колличество одинаковых карт и отслеживаем максимальную очередь
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : hand) {
            counter.compute(num, (key, value) -> (value == null) ? 1 : value + 1);
        }
        maxIdentic = Collections.max(counter.values());
        // Формируем список колличества карт
        List<Integer> cardsCounter = new ArrayList<>();
        for (Integer card : uniqueCards) {
            cardsCounter.add(counter.get(card));
            cardWeightList.add(new CardWeight(card, counter.get(card)));
        }
        //Отсортируем колличества одинаковых карт по убыванию
        cardsCounter.sort(Collections.reverseOrder());
        //Отсортируем карты по значимости
        cardWeightList.sort(Collections.reverseOrder());
        maxIdentic = cardsCounter.get(0);
    }

    private List<Integer> splitAndParsing() {
        String[] cards = hand.split(" ");
        char suit = cards[0].charAt(1);
        List<Integer> cardsValue = new ArrayList<>();
        for (String card : cards) {
            char firstChar = card.charAt(0);
            if (Character.isDigit(firstChar)) {
                cardsValue.add(Character.getNumericValue(firstChar));
            } else {
                cardsValue.add(getIntOfCard(firstChar));
            }
            if (card.charAt(1) != suit) {
                flushIndicator = false;
            }
        }
        return cardsValue;
    }

    private Integer getIntOfCard(char rank) {
        return switch (rank) {
            case 'T' -> 10;
            case 'J' -> 11;
            case 'Q' -> 12;
            case 'K' -> 13;
            case 'A' -> 14;
            default -> -1;
        };
    }


    @Override
    public String toString() {
        return "Grade=" + firstGrade + "'" + hand + '\'' +
                '}';
    }

    @Override
    public int compareTo(PokerHand hand) {
        if (firstGrade.getValue() > hand.getFirstGrade().getValue()) {
            return 1;
        }
        if (firstGrade.getValue() < hand.getFirstGrade().getValue()) {
            return -1;
        }
        for (int i = 0; i < kickers.size(); i++) {
            Integer kickOne = kickers.get(i);
            Integer kickTwo = hand.kickers.get(i);

            if (kickOne > kickTwo) {
                return -1;
            }
            if (kickOne < kickTwo) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand hand1 = (PokerHand) o;
        return Objects.equals(hand, hand1.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hand);
    }
}


