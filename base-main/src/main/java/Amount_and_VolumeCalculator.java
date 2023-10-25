import java.util.HashMap;
import java.util.Map;

public class Amount_and_VolumeCalculator {


    // TODO REDONDANCE PBM

    public Integer AmountforTypeAndAudience(String type, Performance perf) {
        Map<String, calculator> types = new HashMap<>();
        types.put("tragedy", new tragedyCalculator());
        types.put("comedy", new comedyClaculator());

        if (!types.containsKey(type)) {
            throw new Error("Unknown type: " + type);
        } else {
            return types.get(type).getAmount(perf);
        }
    }

    public Integer VolumeforTypeAndAudience(String type, Performance perf) {
        Map<String, calculator> types = new HashMap<>();
        types.put("tragedy", new tragedyCalculator());
        types.put("comedy", new comedyClaculator());

        if (!types.containsKey(type)) {
            throw new Error("Unknown type: " + type);
        } else {
            return types.get(type).getVolume(perf);
        }
    }
}
    


