package Threading;

import Subject.Stock;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class File_handling {

    HashMap readFile() throws FileNotFoundException {
        File file = new File("src/input.txt");
        Scanner scanner = new Scanner(file);
        HashMap<String, Stock> stock_trading = new HashMap<>();
        while(scanner.hasNextLine())
        {
            String text = scanner.nextLine();
            StringTokenizer Tokenizer = new StringTokenizer(text," ");
            Vector<String> tokens = new Vector<>();
            while (Tokenizer.hasMoreTokens())
            {
                tokens.add(Tokenizer.nextToken());
            }
            String stock_name = tokens.elementAt(0);
            Integer count = Integer.parseInt(tokens.elementAt(1));
            Float costs = Float.parseFloat(tokens.elementAt(2));
            stock_trading.put(stock_name,new Stock(stock_name,count,costs));
        }
        return stock_trading;
    }
}
