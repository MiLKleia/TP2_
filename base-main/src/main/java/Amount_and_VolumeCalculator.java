import java.util.HashMap;
import java.util.Map;

public class Amount_and_VolumeCalculator {

    private int volume;
    private int amount;


    // TODO GET pas ouf

    public Integer getAmount() {
            return amount;
        }

    public Integer getVolume() {
        return volume;
    }

    public Amount_and_VolumeCalculator(String type, Performance perf){
        Map<String, calculator> types = new HashMap<>();
        types.put("tragedy", new tragedyCalculator());
        types.put("comedy", new comedyClaculator());

        if (!types.containsKey(type)) {
            throw new Error("Unknown type: " + type);
        } else {
            this.amount = types.get(type).getAmount(perf);
            this.volume = types.get(type).getVolume(perf);
        }

    }
}
    


