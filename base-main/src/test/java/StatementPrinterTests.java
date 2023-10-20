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
    @DisplayName("file writer")
    void write_a_file() {

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("as-like",  new Play("As You Like It", "comedy"));
        
        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("as-like", 15)));
        StatementPrinter statementPrinter = new StatementPrinter();
     
        int result = statementPrinter.html_print(invoice, plays);

       assert result == 0; 
    }

   


}