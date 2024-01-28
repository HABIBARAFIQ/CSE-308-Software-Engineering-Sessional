package Threading;

import Observer.User;
import Subject.Stock;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.logging.FileHandler;

public class Server {

    HashMap<String, Stock> stocks = null;
    Vector<User>users = new Vector<>();

    Server() throws IOException {

        File_handling fileHandler = new File_handling();

        stocks = fileHandler.readFile();

        System.out.println("Stock Details : "+stocks);

        Thread consoleStream = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {

                    System.out.println("Console Stream : ");
                    Scanner sc = new Scanner(System.in);
                    String msg = sc.nextLine();

                    System.out.println("You typed : " + msg);

                    StringTokenizer stringTokenizer = new StringTokenizer(msg, " ");
                    Vector<String> tokens = new Vector<>();

                    while (stringTokenizer.hasMoreTokens()) {
                        tokens.add(stringTokenizer.nextToken());
                    }

                    String stockName = tokens.elementAt(1);
                    String changeAmount = tokens.elementAt(2);
                    String updateText = new String();

                    if (tokens.elementAt(0).equals("I")) {

                        Stock cur = stocks.get(stockName);
                        cur.Increase_price(Float.parseFloat(changeAmount));
                        stocks.put(stockName,cur);

                        updateText = "Price of "+stockName+" increased by "+changeAmount;

                    } else if (tokens.elementAt(0).equals("D")) {

                        Stock cur = stocks.get(stockName);
                        cur.Decrease_price(Float.parseFloat(changeAmount));
                        stocks.put(stockName,cur);

                        updateText = "Price of "+stockName+" decreased by "+changeAmount;

                    } else if (tokens.elementAt(0).equals("C")) {

                        Stock cur = stocks.get(stockName);
                        cur.Change_in_count(Integer.parseInt(changeAmount));
                        stocks.put(stockName,cur);

                        updateText = "Count of "+stockName+" changed to "+changeAmount;
                    }

                    System.out.println("Updated Stock Details : "+stocks);

                    for(Map.Entry m:stocks.entrySet())
                    {
                        String name = (String) m.getKey();
                        Stock stock = stocks.get(name);

                        if(stock.getStock_name().equals(stockName))
                        {
                            stock.notifyObservers(updateText);
                        }
                    }
                }
            }
        });

        consoleStream.start();

        ServerSocket welcomeSocket = new ServerSocket(6788);
        int id = 0;

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();

            DataInputStream dis = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(connectionSocket.getOutputStream());

            User curUser = new User(id,dis,dos);
            users.add(curUser);

            int finalId = id;
            Thread workerThread = new Thread(new Runnable() {

                @Override
                public void run() {

                    try {
                        dos.writeUTF("Stock Details : "+stocks.toString());
                        dos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    while (true) {
                        String textFromClient;
                        try {

                            textFromClient = dis.readUTF();
                            System.out.println("Text from client "+ finalId +" : "+textFromClient);

                            StringTokenizer stringTokenizer = new StringTokenizer(textFromClient," ");
                            Vector<String>tokens = new Vector<>();

                            while (stringTokenizer.hasMoreTokens())
                            {
                                tokens.add(stringTokenizer.nextToken());
                            }

                            if(tokens.elementAt(0).equals("S"))
                            {
                                String stockName = tokens.elementAt(1);
                                boolean found_product=false;
                                for(Map.Entry m:stocks.entrySet())
                                {
                                    String name = (String) m.getKey();
                                    Stock stock = stocks.get(name);

                                    if(stock.getStock_name().equals(stockName))
                                    {
                                        //stock.registerObserver(curUser);
                                        found_product=true;
                                        break;
                                    }
                                }
                                if(found_product==true){
                                    dos.writeUTF("Subscribed to " + stockName);
                                    dos.flush();
                                    for(Map.Entry m:stocks.entrySet())
                                    {
                                        String name = (String) m.getKey();
                                        Stock stock = stocks.get(name);

                                        if(stock.getStock_name().equals(stockName))
                                        {
                                            stock.registerObserver(curUser);
                                        }
                                    }
                                }
                                else
                                {
                                    dos.writeUTF("Can't subscribe to a product which doesn't exist");
                                    dos.flush();
                                }
                            }
                            else if(tokens.elementAt(0).equals("U"))
                            {
                                boolean unsubscribed=false;
                                String temp="Unsubscribed from " + tokens.elementAt(1);
                                String stockName = tokens.elementAt(1);
                                for(Map.Entry m:stocks.entrySet()) {
                                    Stock stocktemp = stocks.get(stockName);
                                if (stocktemp!=null){
                                if(stocktemp.isObserverRegistered(curUser)) {
                                    stocktemp.unregisterObserver(curUser);
                                    unsubscribed=true;
                                }
                                }}
                                if(unsubscribed==false){
                                    temp="Can't unsubscribe to a product which is not subscribed or does not exist ";
                                }
                                dos.writeUTF(temp);
                                dos.flush();
                            }
                            else if(tokens.elementAt(0).equals("N"))
                            {
                                dos.writeUTF(stocks.toString());
                                dos.flush();
                            }
                            else if (tokens.elementAt(0).equals("V")) {
                                StringBuilder subscribedStocks = new StringBuilder("Subscribed Stocks: ");

                                for (Map.Entry m : stocks.entrySet()) {
                                    String name = (String) m.getKey();
                                    Stock stock = stocks.get(name);

                                    if (stock.isObserverRegistered(curUser)) {
                                        subscribedStocks.append(stock.getStock_name()).append(", ");
                                    }
                                }

                                String result = subscribedStocks.toString().trim();
                                if (result.endsWith(",")) {
                                    result = result.substring(0, result.length() - 1);
                                }

                                dos.writeUTF(result);
                                dos.flush();
                            }

// ... (remaining code)
                            else if (tokens.elementAt(0).equals("exit"))
                            {
                                System.out.println("Client "+finalId+" exited");
                                Thread.currentThread().interrupt();//preserve the message

                                for(Map.Entry m:stocks.entrySet())
                                {
                                    String name = (String) m.getKey();
                                    Stock stock = stocks.get(name);
                                    stock.unregisterObserver(curUser);
                                }
                                return;
                            }


                        } catch (Exception e) {
//                            e.printStackTrace();
                            System.out.println(e);
                            System.out.println("Client "+finalId+" exited");
                            Thread.currentThread().interrupt();

                            for(Map.Entry m:stocks.entrySet())
                            {
                                String name = (String) m.getKey();
                                Stock stock = stocks.get(name);
                                stock.unregisterObserver(curUser);
                            }
                            return;
                        }
                    }
                }
            });

            workerThread.start();

            int count = id+1;
            System.out.println("Client [" + id + "] is now connected. No. of worker threads = " + count);
            id++;
        }
    }

    public static void main(String argv[]) throws Exception {

        Server server = new Server();
    }
}