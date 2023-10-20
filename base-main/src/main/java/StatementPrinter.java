import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {

  private int getAmount(Performance perf, String type) {
    int amount_out = 0; 
    switch (type) {
      default : 
        throw new Error("unknown type: ${play.type}");

      case "tragedy":
        amount_out = 40000 + (1000 * Math.max(0,perf.audience - 30));
        break;

      case "comedy":
        amount_out = 30000 + 300 * perf.audience;
        if (perf.audience > 20) {
          amount_out += 10000 + 500 * (perf.audience - 20);
        }
        break;
    }
    return amount_out;
  }

  private int getVolumeCredit(Performance perf, String type) {
    int volumeCredit_out = 0; 
    
    volumeCredit_out += Math.max(perf.audience - 30, 0);
    switch (type){
      case "comedy":
        volumeCredit_out += Math.floor(perf.audience / 5);
    }
    return volumeCredit_out;
  }






  public String print(Invoice invoice, HashMap<String, Play> plays) {
    int totalAmount = 0;
    int volumeCredits = 0;
    String result = String.format("Statement for %s\n", invoice.customer);

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);

      int thisAmount = getAmount( perf,  play.type);

      volumeCredits += getVolumeCredit( perf,  play.type);

      // print line for this order
      result += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount / 100), perf.audience);
      totalAmount += thisAmount;
    }
    result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
    result += String.format("You earned %s credits\n", volumeCredits);
    return result;
  }

}
