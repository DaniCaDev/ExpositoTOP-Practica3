package es.ull.esit.utilities;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PowerSetTest {
    @Test
    void supportsIndependentIteratorsAndEnumeratesAllSubsets() {
        PowerSet<Integer> powerSet = new PowerSet<>(Set.of(1, 2));
        Iterator<Set<Integer>> first = powerSet.iterator();
        Iterator<Set<Integer>> second = powerSet.iterator();
        assertEquals(Set.of(), first.next());
        assertEquals(Set.of(), second.next());
        Set<Set<Integer>> subsets = new HashSet<>();
        subsets.add(Set.of());
        while (first.hasNext()) {
            subsets.add(first.next());
        }
        assertEquals(Set.of(Set.of(), Set.of(1), Set.of(2), Set.of(1, 2)), subsets);
        assertTrue(second.hasNext(), "El segundo iterador debe conservar su estado independiente");
        while (second.hasNext()) {
            assertTrue(subsets.contains(second.next()));
        }
        assertFalse(second.hasNext());
    }
}
