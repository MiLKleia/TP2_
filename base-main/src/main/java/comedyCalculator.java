public class comedyCalculator implements calculator {
    @Override
    public int getAmount(Performance perf) {
        int amount_out = 0; 
        amount_out = 30000 + 300 * perf.audience;
            if (perf.audience > 20) {
              amount_out += 10000 + 500 * (perf.audience - 20);
            }
        return amount_out;
      }

      public int getVolume(Performance perf) {
        int volumeCredit_out  = Math.max(perf.audience - 30, 0);
        volumeCredit_out += Math.floor(perf.audience / 5);
        return volumeCredit_out;
      }
}
