import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    @Test
    @DisplayName("exampleStatement")
    void exampleStatement() {

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet",  new Play("Hamlet", "tragedy"));
        plays.put("as-like",  new Play("As You Like It", "comedy"));
        plays.put("othello",  new Play("Othello", "tragedy"));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

        verify(result);
    }

    @Test
    @DisplayName("statementWithNewPlayTypes")
    void statementWithNewPlayTypes() {

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("henry-v",  new Play("Henry V", "history"));
        plays.put("as-like",  new Play("As You Like It", "pastoral"));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("henry-v", 53),
                new Performance("as-like", 55)));

        StatementPrinter statementPrinter = new StatementPrinter();
        Assertions.assertThrows(Error.class, () -> {
            statementPrinter.print(invoice, plays);
        });
    }

    
     @Test
    @DisplayName("test tragedy and over 30 pers")
    void tragedy_over_30() {

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet",  new Play("Hamlet", "tragedy"));
        
        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

        verify(result);

    }

     @Test
    @DisplayName("test tragedy and less than 30 pers")
    void tragedy_lt_30() {
     

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet",  new Play("Hamlet", "tragedy"));
        
        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 25)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

        verify(result);

    }


    @Test
    @DisplayName("test Comedy and over 20 pers")
    void comedy_over_20() {

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("as-like",  new Play("As You Like It", "comedy"));
        
        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("as-like", 55)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

        verify(result);

    }

     @Test
    @DisplayName("test comedy and less than 20 pers")
    void comedy_lt_20() {
     

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("as-like",  new Play("As You Like It", "comedy"));
        
        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("as-like", 15)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

        verify(result);

    }


    @Test
    @DisplayName("html  no link")
    void write_a_html_into_the_file() {

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet",  new Play("Hamlet", "tragedy"));
        plays.put("as-like",  new Play("As You Like It", "comedy"));
        plays.put("othello",  new Play("Othello", "tragedy"));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var not_html_str = statementPrinter.print(invoice, plays);

        htmlPrinter to_html = new htmlPrinter();

        to_html.html_print(not_html_str);
        Assertions.assertDoesNotThrow(() -> to_html.html_print(not_html_str), "erreur lors de la creation du ficheir html ");

    }

    @Test
    @DisplayName("html with directory")
    void write_a_html_with_a_directory() {

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet",  new Play("Hamlet", "tragedy"));
        plays.put("as-like",  new Play("As You Like It", "comedy"));
        plays.put("othello",  new Play("Othello", "tragedy"));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var not_html_str = statementPrinter.print(invoice, plays);

        // ADAPT DIRECTORY FOR YOUR COMPUTER
        htmlPrinter to_html = new htmlPrinter("/home/nes/tmp/");

     
        to_html.html_print(not_html_str);

        Assertions.assertDoesNotThrow(() -> to_html.html_print(not_html_str), "erreur lors de la creation du ficheir html avec une direction");

    }


   


}