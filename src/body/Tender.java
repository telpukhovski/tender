package body;

import exception.UnsuitableOfferException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Tender {
    private final BigDecimal price;
    private final LinkedHashMap<Skills, Integer> requirements;

    public Tender(BigDecimal price) {
        this.price = price;
        requirements = new LinkedHashMap<>();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LinkedHashMap<Skills, Integer> getRequirements() {
        return requirements;
    }

    public void addRequirements(Skills skills, Integer count) {
        requirements.put(skills, count);

    }

    private HashMap<Skills, Integer> brigadeAllCapabilities(ArrayList<Brigade> brigades, int i) {
        HashMap<Skills, Integer> brigadeCapabilities = new HashMap<>();
        for (Employee employee : brigades.get(i).getWorkers()) {
            for (Skills skills : employee.getSkills()) {
                if (!brigadeCapabilities.containsKey(skills)) brigadeCapabilities.put(skills, 1);
                else brigadeCapabilities.put(skills, brigadeCapabilities.get(skills) + 1);

            }
        }
        return brigadeCapabilities;
    }

    public boolean brigadeCapabilitiesCheck(HashMap<Skills, Integer> brigadeCapabilities, LinkedHashMap<Skills, Integer> requirements) {
        for (Map.Entry<Skills, Integer> entry : requirements.entrySet()) {
            Skills key = entry.getKey();
            Integer value = entry.getValue();
            if (!brigadeCapabilities.containsKey(key))
                return false;
            else {
                if (brigadeCapabilities.get(key) < value) {
                    return false;
                }
            }
        }
        return true;
    }

    public Brigade chooseBrigade(ArrayList<Brigade> brigades) throws UnsuitableOfferException {
        BigDecimal minPrice = price;
        int suitableBrigades = -1;
        for (int i = 0; i < brigades.size(); i++) {
            HashMap<Skills, Integer> brigadeCapabilities = brigadeAllCapabilities(brigades, i);
            boolean check = brigadeCapabilitiesCheck(brigadeCapabilities, requirements);
            if ((check) & ((brigades.get(i).getCost().compareTo(minPrice) < 0) | (brigades.get(i).getCost().compareTo(minPrice) == 0))) {
                minPrice = brigades.get(i).getCost();
                suitableBrigades = i;
            }
        }
        if (suitableBrigades == -1) throw new UnsuitableOfferException("There is no suitable brigade");
        return brigades.get(suitableBrigades);

    }
}
