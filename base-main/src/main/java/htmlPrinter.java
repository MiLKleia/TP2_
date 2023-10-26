import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class htmlPrinter {

    private String location;

    private String string2html(String statement_str) {
        String lines[] = statement_str.split("\\r?\\n");
        String result = String.format("<!doctype html> <html lang=\"en-US\"> <head> <meta charset=\"utf-8\" /> <title> Statement </title> </head> <body>");
        for (String i : lines){
          result += String.format("<p> %s </p>", i );
        }
         result += String.format("</body> </html>"); 
         return result; 
        }


    public void html_print(String statement_str) {
        String result = string2html(statement_str); 

        String lines[] = statement_str.split("\\r?\\n");
        String file_name = location;
        file_name += String.format("%s.html", lines[0]);
        File file = new File(file_name);

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
  }

  public htmlPrinter(){
    this.location = "";
  }

  public htmlPrinter(String location_given){
    this.location = location_given;
  }



    
}
