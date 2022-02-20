package com.amogh.affirm.ms.facility.business.covenant;

import com.amogh.affirm.ms.common.model.Facility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CovenantFactory {
    public static CovenantStrategy getCovenants (Facility facility) {
        List<CovenantStrategy> covenantList = new ArrayList<>();

        addBannedStateCovenantStrategy (facility, covenantList);

        covenantList.add (new MaxAmountCovenantStrategy (facility));

        addMaxDefaultLikelyHoodCovenantStrategy (facility, covenantList);

        return new CovenantCompositeStrategy(covenantList);
    }

    private static void addBannedStateCovenantStrategy (Facility facility, List<CovenantStrategy> covenantList) {
        //If there are no Banned States at Facility level use the bank level Banned State list
        Set<String> bannedStates = facility.getBannedStates();
        if (bannedStates == null || bannedStates.isEmpty()) {
            bannedStates = facility.getBank().getBannedStates();
        }

        if (bannedStates != null && !bannedStates.isEmpty()) {
            covenantList.add (new BannedStateCovenantStrategy (bannedStates));
        }
    }

    private static void addMaxDefaultLikelyHoodCovenantStrategy (Facility facility, List<CovenantStrategy> covenantList) {
        //If there are no Max Default Likelihood at Facility level use the bank level
        BigDecimal maxDefault = facility.getMaxDefaultLikelihood();
        if (facility.getMaxDefaultLikelihood() == null) {
            maxDefault = facility.getBank().getMaxDefaultLikelihood();
        }

        if (maxDefault != null) {
            covenantList.add (new MaxDefaultLikelyHoodCovenantStrategy (maxDefault));
        }
    }
}
