import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class htmlPrinter {

    private String directory;

    private String string2html_better(String statement_str) {
        String lines[] = statement_str.split("\\r?\\n");
        String result = String.format("<!doctype html> <html lang=\"en-US\"> <head> <meta charset=\"utf-8\" /> <title> Statement </title> </head> <body>");
        int lenght_ = lines.length;
        
        result += String.format("<h2>%s</h2>  <ul style=\"list-style-type:circle;\">", lines[0] );
        
        for (int i = 1 ;  i<= lenght_ - 2 ;  i +=2){
          result += String.format("<li> %s </li>", lines[i] );
        }

        result += String.format("</ul>  <hr> <p> %s <br>  %s <p/>",  lines[lenght_ -2], lines[lenght_ -1]);
        result += String.format("</body> </html>"); 
        return result; 
        }


    public void html_print(String statement_str) {
        String result = string2html_better(statement_str);

        String lines[] = statement_str.split("\\r?\\n");
        String file_name = directory;
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
    this.directory = "";
  }

  public htmlPrinter(String directory_given){
    this.directory = directory_given;
  }



    
}
