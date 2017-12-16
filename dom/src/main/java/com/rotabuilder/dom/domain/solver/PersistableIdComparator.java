package com.rotabuilder.dom.domain.solver;

import java.io.Serializable;
import java.util.Comparator;

public class PersistableIdComparator implements Comparator<AbstractPersistable>, Serializable {

    public int compare(AbstractPersistable a, AbstractPersistable b) {
        return -1;
//        return new CompareToBuilder().append(a.getId(), b.getId()).toComparison();
    }

}
