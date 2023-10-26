import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.*;


public class StatementPrinter {

  private Play playForPerformance(Performance perf, Map<String, Play> plays) {
    return plays.get(perf.playID); }



  private String string_creator(String Customer, List<String> volume_credit, List<String> play_audience){
    String result = String.format("Statement for %s\n", Customer);
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    int compteur = 0;

    for (int i = 0 ; i<(volume_credit.size() - 2); i++){
      result += String.format("  %s: %s (%s seats)\n", play_audience.get(compteur), frmt.format(Integer.parseInt(volume_credit.get(i))), play_audience.get(compteur + 1));
      compteur += 2;
    }
    result += String.format("Amount owed is %s\n", frmt.format(Integer.parseInt(volume_credit.get(volume_credit.size() - 2))));
    result += String.format("You earned %s credits\n", volume_credit.get(volume_credit.size() - 1));

    return result;

  }


  public String print(Invoice invoice, HashMap<String, Play> plays) {
    int totalAmount = 0;
    int volumeCredits = 0;
    List<String> volume_credit = new ArrayList<>();
    List<String> play_audience = new ArrayList<>();


    for (Performance perf : invoice.performances) {
      Play play = playForPerformance(perf, plays);
      play_audience.add(play.name);
      play_audience.add(String.valueOf(perf.audience));


      Amount_and_VolumeCalculator curent_play = new Amount_and_VolumeCalculator(play.type, perf);
      int thisAmount = curent_play.getAmount();
      totalAmount += thisAmount;
      volume_credit.add(String.valueOf(thisAmount/100));

      volumeCredits += curent_play.getVolume();
      
    }

    volume_credit.add(String.valueOf(totalAmount/100));
    volume_credit.add(String.valueOf(volumeCredits));

    String result = string_creator(invoice.customer, volume_credit, play_audience );
    
    return result;
  }

}
