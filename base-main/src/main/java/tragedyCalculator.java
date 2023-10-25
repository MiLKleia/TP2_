public class tragedyCalculator implements calculator {
    @Override

    public int getAmount(Performance perf) {
        int amount_out = 40000 + (1000 * Math.max(0,perf.audience - 30));
        return amount_out;
    }

    public int getVolume(Performance perf) {
        int volumeCredit_out = Math.max(perf.audience - 30, 0);
        return volumeCredit_out;
      }
    
}
