import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by Tejas on 21-10-2017.
 */
public class Commodity {


     static Scanner sc = new Scanner(System.in);

    public static String s = sc.next();

    private String stock_name;
    private BigDecimal stock_last;
    private BigDecimal stock_high;
    private BigDecimal stock_low;
    private BigDecimal stock_change;
    private int stock_quantity;
    private BigDecimal stock_price_bought;
    private BigDecimal stock_price_sold;
    private BigDecimal stock_profit;

    public Commodity(String stock_name) {
        StockInformation stockInformation=APICalls.GetPrice(stock_name);

        this.stock_name = stock_name;
        this.stock_last = stockInformation.currentPrice;
        this.stock_high = stockInformation.dayHigh;
        this.stock_low = stockInformation.dayLow;
        this.stock_change = stockInformation.change;
    }

    public Commodity(String stock_name, int stock_quantity, BigDecimal stock_price_bought) {
        StockInformation stockInformation=APICalls.GetPrice(stock_name);

        this.stock_name = stock_name;
        this.stock_quantity = stock_quantity;
        this.stock_price_bought = stock_price_bought;
        this.stock_last = stockInformation.currentPrice;
        this.stock_high = stockInformation.dayHigh;
        this.stock_low = stockInformation.dayLow;
        this.stock_change = stockInformation.change;
    }

    public String getStock_name() { return stock_name; }

    public BigDecimal getStock_last() { return stock_last; }

    public BigDecimal getStock_high() { return stock_high; }

    public BigDecimal getStock_low() { return stock_low; }

    public BigDecimal getStock_change() { return stock_change; }

    public int getStock_quantity() { return stock_quantity; }


    void buyCommodity(int stock_quantity){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/StockMarketHandler","root",s);
            Statement stmt=con.createStatement();
            String query = "insert into stocksbought (Stock, Quantity, Price) values ('"+stock_name+"','"+stock_quantity+"','"+stock_last+"');";
            if ((stmt.executeUpdate(query)== 0)){
                System.out.println("Cannot buy stock!");
            }

            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    static ObservableList<Commodity> boughtCommodity(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/StockMarketHandler","root",s);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from stocksbought where soldstatus = 0;");

            ObservableList<Commodity> list = FXCollections.observableArrayList();
            while(rs.next()) {
                list.add(new Commodity(rs.getString(2), rs.getInt(3), rs.getBigDecimal(4)));
            }

            con.close();
            return list;
        }catch(Exception e){ System.out.println(e);}
        return null;
    }

    static ObservableList<String> boughtCommodity(String only_stock_name){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/StockMarketHandler","root",s);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from stocksbought where soldstatus = 0;");

            ObservableList<String> list = FXCollections.observableArrayList();
            while(rs.next()) {
                list.add(rs.getString(2));
            }

            con.close();
            return list;
        }catch(Exception e){ System.out.println(e);}
        return null;
    }

    void sellCommodity(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/StockMarketHandler","root",s);
            Statement stmt=con.createStatement();
            String query;

            query = "select * from stocksbought where (Stock = '"+stock_name+"') and (soldstatus = 0);";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            int transID = rs.getInt(1);
            stock_quantity = rs.getInt(3);
            stock_price_bought = rs.getBigDecimal(4);
            stock_profit = (stock_last.subtract(stock_price_bought).multiply(new BigDecimal(stock_quantity)));

            query = "update stocksbought set soldstatus = 1 where TransID = "+transID+";";
            if ((stmt.executeUpdate(query)== 0)){
                System.out.println("Cannot mark stock as sold!");
            }

            query = "insert into stockssold (TransID, Stock, Quantity, Price, Profit) " +
                    "values ('"+transID+"','"+stock_name+"','"+stock_quantity+"','"+stock_last+"','"+stock_profit+"');";
            if ((stmt.executeUpdate(query)== 0)){
                System.out.println("Cannot sell stock!");
            }

            con.close();
        }catch(Exception e){ System.out.println(e);}

    }

    public static String getStocksValue(){
        double value=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/StockMarketHandler","root",s);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from stocksbought where soldstatus = 0;");

            while(rs.next()) {
                value += rs.getDouble(3) * rs.getDouble(4);
            }

            con.close();
            return String.valueOf(value);
        }catch(Exception e){ System.out.println(e);}
        return null;
    }

    public static String getTotalProfit(){
        String profit="";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/StockMarketHandler","root",s);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select sum(profit) from stockssold;");

            while(rs.next()) {
                profit = rs.getString(1);
            }

            con.close();
            return profit;
        }catch(Exception e){ System.out.println(e);}
        return null;
    }

    public static String getTopProfit(){
        String profit="";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/StockMarketHandler","root",s);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from stockssold order by profit desc;");

            while(rs.next()) {
                profit += rs.getString(2)+ "\n";
            }

            con.close();
            return profit;
        }catch(Exception e){ System.out.println(e);}
        return null;
    }

    public static String getBottomProfit(){
        String profit="";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/StockMarketHandler","root",s);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from stockssold order by profit asc limit 3;");

            while(rs.next()) {
                profit += rs.getString(2) + "\n";
            }

            con.close();
            return profit;
        }catch(Exception e){ System.out.println(e);}
        return null;
    }
}