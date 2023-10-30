package it.unibo.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int ELEMS_ADD = 100_000;
    private static final int ELEMS_READ = 1000;
    private static final int START_INS = 1000;
    private static final int END_INS = 2000;
    private static final long AFRICA_POPULATION = 1_110_635_000L;
    private static final long AMERICAS_POPULATION = 972_005_000;
    private static final long ANTARTICA_POPULATION = 0;
    private static final long ASIA_POPULATION = 4_298_723_000L;
    private static final long EUROPE_POPULATION = 742_452_000;
    private static final long OCEANIA_POPULATION = 38_304_000;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = START_INS; i < END_INS; i++) {
            a.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        LinkedList<Integer> l = new LinkedList<>(a);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        var temp = a.get(a.size()-1);
        a.set(a.size()-1, a.get(0));
        a.set(0, temp);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (int it : a) {
            System.out.println(it);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long timeA = System.nanoTime();
        for (int i = 1; i <= ELEMS_ADD; i++) {
            a.add(0, i);
        }
        timeA = System.nanoTime() - timeA;
        long timeL = System.nanoTime();
        for (int i = 1; i <= ELEMS_ADD; i++) {
            l.add(0, i);
        }
        timeL = System.nanoTime() - timeL;
        final var millisA = TimeUnit.NANOSECONDS.toMillis(timeA);
        final var millisL = TimeUnit.NANOSECONDS.toMillis(timeL);
        System.out.println("Converting " + a.size() + " ints to String and inserting them in a ArrayList took " + timeA + "ns (" + millisA + "ms)");
        System.out.println("Converting " + l.size() + " ints to String and inserting them in a LinkedList took " + timeL + "ns (" + millisL + "ms)");
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        long time1A = System.nanoTime();
        for (int i = 1; i <= ELEMS_READ; i++) {
            a.get(a.size()/2);
        }
        time1A = System.nanoTime() - time1A;
        long time1L = System.nanoTime();
        for (int i = 1; i <= ELEMS_READ; i++) {
            l.get(l.size()/2);
        }
        time1L = System.nanoTime() - time1L;
        final var millis1A = TimeUnit.NANOSECONDS.toMillis(time1A);
        final var millis1L = TimeUnit.NANOSECONDS.toMillis(time1L);
        System.out.println("Reading " + a.size() + " ints to String and inserting them in a ArrayList took " + time1A + "ns (" + millis1A + "ms)");
        System.out.println("Reading " + l.size() + " ints to String and inserting them in a LinkedList took " + time1L + "ns (" + millis1L + "ms)");
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        Map<String, Long> m = new TreeMap<>();
        m.put("Africa", AFRICA_POPULATION);
        m.put("Americas", AMERICAS_POPULATION);
        m.put("Antartica", ANTARTICA_POPULATION);
        m.put("Asia", ASIA_POPULATION);
        m.put("Europe", EUROPE_POPULATION);
        m.put("Oceania", OCEANIA_POPULATION);
        /*
         * 8) Compute the population of the world
         */
        long tot_pop = 0;
        for (final long c : m.values()) {
            tot_pop += c;
        }
        System.out.println("Total population: " + tot_pop); //7_162_119_000
    }
}
