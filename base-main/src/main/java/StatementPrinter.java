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


  public String print(Invoice invoice, HashMap<String, Play> plays) {
    int totalAmount = 0;
    int volumeCredits = 0;
    
    String result = String.format("Statement for %s\n", invoice.customer);

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      Amount_and_VolumeCalculator curent_play = new Amount_and_VolumeCalculator();

      int thisAmount = curent_play.AmountforTypeAndAudience(play.type, perf);
      totalAmount += thisAmount;

      volumeCredits += curent_play.VolumeforTypeAndAudience(play.type, perf);

      result += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount / 100), perf.audience);
      
    }
    result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
    result += String.format("You earned %s credits\n", volumeCredits);
    return result;
  }

}
