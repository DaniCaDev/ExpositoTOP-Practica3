package es.ull.esit.utilities;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

/**
 * \brief Utilidad de generación de subconjuntos.
 *
 * Permite repetir recorridos y utilizar varios iteradores independientes sobre
 * un mismo conjunto. Cada llamada a iterator() crea su propio estado.
 *
 * Los elementos deben admitir orden natural, como en la versión original que
 * devolvía conjuntos TreeSet.
 */
public class PowerSet<E> implements Iterable<Set<E>> {

    private final List<E> elements;

    public PowerSet(Set<E> set) {
        this.elements = new ArrayList<>(set);
    }

    @Override
    public Iterator<Set<E>> iterator() {
        return new Iterator<Set<E>>() {
            private final BitSet bits = new BitSet(elements.size() + 1);

            @Override
            public boolean hasNext() {
                return !bits.get(elements.size());
            }

            @Override
            public Set<E> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No quedan subconjuntos");
                }
                Set<E> result = new TreeSet<>();
                for (int i = 0; i < elements.size(); i++) {
                    if (bits.get(i)) {
                        result.add(elements.get(i));
                    }
                }
                // Incrementa el contador binario representado por BitSet.
                for (int i = 0; i <= elements.size(); i++) {
                    if (!bits.get(i)) {
                        bits.set(i);
                        break;
                    }
                    bits.clear(i);
                }
                return result;
            }
        };
    }
}
