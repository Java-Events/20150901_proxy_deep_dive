package org.rapidpm.event.opench2015.v002.p05;

import org.rapidpm.event.opench2015.v002.AddAdapterBWL;
import org.rapidpm.event.opench2015.v002.Taschenrechner;
import org.rapidpm.event.opench2015.v002.TaschenrechnerSci;

/**
 * @author Dominik Menzi
 */
class Main {

    public static void main(String... args) {
        final Taschenrechner t = OverridingProxy.create(Taschenrechner.class, new TaschenrechnerSci(),
                new AddAdapterBWL(),
                new MultAdapterIdiotic());

        System.out.println(t.mult(2, 2));
        System.out.println(t.add(2, 2));
    }
}
