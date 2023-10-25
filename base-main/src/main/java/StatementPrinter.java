import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StatementPrinter {

  private Play playForPerformance(Performance perf, Map<String, Play> plays) {
    return plays.get(perf.playID); }



  public String html_str_convert(String statement_str) {
    String lines[] = statement_str.split("\\r?\\n");
    String result = String.format("<!doctype html> <html lang=\"en-US\"> <head> <meta charset=\"utf-8\" /> <title> Statement </title> </head> <body>");
    for (String i : lines){
      result += String.format("<p> %s </p>", i );
    }
     result += String.format("</body> </html>"); 
     return result; 
    }


  public int html_print(String statement_str) {
    String lines[] = statement_str.split("\\r?\\n");
    String file_name = String.format("%s.html", lines[0]);
    File file = new File(file_name);
    String result = html_str_convert( statement_str); 

    // Creation and writing of the file
    try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }try {
            PrintWriter out = new PrintWriter(file);
            out.println(result);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
  
    return 0;
  }

  private String print_string(String Customer, List<String> volume_credit, List<String> play_audience){
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

    String result = print_string(invoice.customer, volume_credit, play_audience );
    
    return result;
  }

}
