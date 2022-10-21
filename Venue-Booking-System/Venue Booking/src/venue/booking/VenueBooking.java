/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venue.booking;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;
/**
 *
 * @author user
 */
public class VenueBooking {
    static Connection con;
    static Statement st1, st2;
    static ResultSet rs1,rs2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int option,afterloginoption, label = 0,flag = 1, adminLable = 0,customerid,bookingid,venueid;
        String temp,name,address,phoneno,venuetype,password,date;
        float venueprice,amount;
        Booking customer=new Booking();
        Venue venue=new Venue();
        Scanner S = new Scanner(System.in);
        // TODO code application logic here
        do {
            while (label == 0) {
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ooad", "root", "raja2017");

                } catch(Exception e)
                {
                    System.out.println(" Connection Failed");
                    System.exit(0);
                }
                System.out.print("\n  ~~~ Login ~~~\nEnter 1 if you want to login (or) Enter 0 if you want" +
                        " to create new account (or) 2 to exit: ");

                option = S.nextInt();
                switch (option) {
                    case 0 : {
                        try {
                            st2 = con.createStatement();
                            System.out.print("Enter your name: ");
                            name=S.next();
                            System.out.print("Enter your ID ");
                            customerid=S.nextInt();
                            customer.setCustID(customerid);
                            System.out.print("Enter your address: ");
                            address=S.next();
                            System.out.print("Enter your phone no: ");
                            phoneno=S.next();
                            System.out.print("Enter password: ");
                            password=S.next();
                            customer.setCustomer(name,customerid, address, phoneno,password);
                            st2.executeUpdate("insert into customer values(" + customer.getCustID() +
                                    ",'" + customer.getCustname() + "','" + customer.getCustaddr() +"', '" +customer.getCustphone()+ "','"+customer.getCustpwd()+"')");
                            System.out.println("Hai " + customer.getCustname() + ",");
                            label = 0;
                            st2.close();
                        } catch (Exception e) {
                            System.out.println("User Already exists");
                            label = 0;
                        }
                        break;
                    }
                    case 1:  {
                        System.out.println("Enter your ID");
                        customer.setCustID(S.nextInt());
                        System.out.print("Enter Password: ");
                        customer.setCustpwd(S.next());
                        try {
                            st1 = con.createStatement();
                            String temp2="select * from customer where customerid = "
                                    + customer.getCustID() + " and pwd = '" +
                                    customer.getCustpwd() + "'";
                            System.out.println(temp2);
                            rs2 = st1.executeQuery("select * from customer where customerid = "
                                    + customer.getCustID() + " and pwd = '" +
                                    customer.getCustpwd() + "'");

                            rs2.next();
                            System.out.println("Hai " + rs2.getString("name") + ",");
                            label = 1;
                            if (customer.getCustID()==16026 ) {
                                adminLable = 1;
                            }
                            st1.close();
                        } catch (Exception e) {
                            System.out.println("Enter valid credentials");
                            break;
                        }

                        //  after login
                        if(adminLable!=1){
                            while(true){
                                System.out.println("Menu\n 1. Available Venue details\n 2.Book new venue\n 3.Exit\n 4.MyBooking Details");
                                afterloginoption=S.nextInt();
                                switch(afterloginoption)
                                {
                                    case 1:
                                    {
                                        try {
                                            System.out.println(" ~~~ All Venue details ~~~");
                                            st1 = con.createStatement();
                                            rs1 = st1.executeQuery("select * from venue where  availability=1");

                                            System.out.println("venueid\taddress\ttype\tprice");
                                            while (rs1.next()) {
                                                System.out.print(rs1.getInt("venueid") + "\t");
                                                System.out.print(rs1.getString("addr") + "\t");
                                                System.out.print(rs1.getString("type") + "\t");
                                                System.out.println(rs1.getFloat("price") + "\t");

                                            }
                                            System.out.println();
                                            st1.close();
                                        } catch (Exception e) {
                                            System.out.println("Can't access venue details");
                                        }
                                        break;
                                    }
                                    case 2:
                                    {
                                        try {
                                            System.out.println(" ~~~ Book  new venue ~~~");
                                            System.out.print("Enter venue id: ");
                                            venueid=S.nextInt();
                                            System.out.print("Enter booking date: ");
                                            date=S.next();


                                            st2 = con.createStatement();
                                            st2.executeUpdate("update venue set  availability=0");
                                            st2.close();
                                            st2 = con.createStatement();
                                            st2.executeUpdate("insert into booking values(" +customer.getCustID()+ ","+venueid +
                                                    ",'" + date + "')");
                                            st2.close();
                                            System.out.println("Booked successfully ");
                                        } catch (Exception e) {
                                            System.out.println("Can't book");
                                        }
                                        break;
                                    }
                                    case 3:
                                    {
                                        label = 0;

                                        flag = 0;
                                        System.exit(0);
                                        break;
                                    }
                                    case 4:
                                    {
                                        try {
                                            System.out.println(" ~~~ All Venue details ~~~");
                                            st1 = con.createStatement();
                                            rs1 = st1.executeQuery("select * from booking where customerid="+customer.getCustID());

                                            System.out.println("venueid\tdate");
                                            while (rs1.next()) {
                                                System.out.print(rs1.getInt("venueid") + "\t");
                                                System.out.println(rs1.getString("date") + "\t");

                                            }
                                            System.out.println();
                                            st1.close();
                                        } catch (Exception e) {
                                            System.out.println("Can't access venue details");
                                        }
                                        break;

                                    }

                                    default: System.out.println("Enter valid option");

                                }
                            }}
                        while(true){
                            if(adminLable==1)
                            {
                                System.out.println("Menu\n 1. View Customer details\n2.view all venue details\n 3.View avialble venue details\n 4.add new venue\n 5.View Customer Booking Details\n 6.delete venue details\n 7.logout");
                                afterloginoption=S.nextInt();
                                switch(afterloginoption)
                                {
                                    case 1:
                                    {
                                        try {
                                            System.out.println(" ~~~ All Customer details ~~~");
                                            st1 = con.createStatement();
                                            rs1 = st1.executeQuery("select * from customer where customerid!=16026");

                                            System.out.println("customerid\tname\taddr\tphoneno");
                                            while (rs1.next()) {
                                                System.out.print(rs1.getInt("customerid") + "\t");
                                                System.out.print(rs1.getString("name")+ "\t");
                                                System.out.print(rs1.getString("addr")+ "\t");
                                                System.out.println(rs1.getString("phoneno"));

                                            }
                                            System.out.println();
                                            st1.close();
                                        } catch (Exception e) {
                                            System.out.println("Can't access customer details");
                                        }
                                        break;
                                    }
                                    case 2:
                                    {
                                        try {
                                            System.out.println(" ~~~ All venue details ~~~");
                                            st1 = con.createStatement();
                                            rs1 = st1.executeQuery("select * from venue  ");

                                            System.out.println("venueid\taddress\ttype\tprice");
                                            while (rs1.next()) {
                                                System.out.print(rs1.getInt("venueid") + " ");
                                                System.out.print(rs1.getString("addr")+ " ");
                                                System.out.print(rs1.getString("type")+ " ");
                                                System.out.println(rs1.getString("price"));

                                            }
                                            System.out.println();
                                            st1.close();
                                        } catch (Exception e) {
                                            System.out.println("Can't access venue details");
                                        }
                                        break;
                                    }
                                    case 3:
                                    {
                                        try {
                                            System.out.println(" ~~~ Available venue details ~~~");
                                            st1 = con.createStatement();
                                            rs1 = st1.executeQuery("select * from venue where availability=1 ");

                                            System.out.println("venueid\taddress\ttype\tprice");
                                            while (rs1.next()) {
                                                System.out.print(rs1.getInt("venueid") + " ");
                                                System.out.print(rs1.getString("addr")+ " ");
                                                System.out.print(rs1.getString("type")+ " ");
                                                System.out.println(rs1.getString("price"));

                                            }
                                            System.out.println();
                                            st1.close();
                                        } catch (Exception e) {
                                            System.out.println("Can't access venue details");
                                        }
                                        break;
                                    }
                                    case 4:
                                    {
                                        try {
                                            System.out.println(" ~~~ Adding new venue ~~~");
                                            System.out.print("Enter venue id: ");
                                            venueid=S.nextInt();
                                            System.out.print("Enter address: ");
                                            address=S.next();
                                            System.out.print("Enter type: ");
                                            venuetype=S.next();
                                            System.out.print("Enter Venue Price: ");
                                            venueprice=S.nextFloat();
                                            venue.setVenue(venueid, address, venuetype, venueprice);
                                            st2 = con.createStatement();
                                            st2.executeUpdate("insert into venue values(" + venue.getVenueID() +
                                                    ",'" + venue.getVenueadd() + "','" + venue.getVenuetype() + "',"+venue.getVenueprice()+",1)");
                                            st2.close();
                                            System.out.println("venue successfully added");
                                        } catch (Exception e) {
                                            System.out.println("Can't add venue1");
                                        }
                                        break;
                                    }
                                    case 5:
                                    {
                                        try {
                                            System.out.println(" ~~~ Customers' Booking details ~~~");
                                            st1 = con.createStatement();
                                            rs1 = st1.executeQuery("select * from booking ");

                                            System.out.println("customerid\tvenueid\tdate");
                                            while (rs1.next()) {
                                                System.out.print(rs1.getInt("customerid") + " ");
                                                System.out.print(rs1.getInt("venueid") + " ");
                                                System.out.println(rs1.getString("date") + " ");

                                            }
                                            System.out.println();
                                            st1.close();
                                        } catch (Exception e) {
                                            System.out.println("Can't access booking details");
                                        }
                                        break;
                                    }
                                    case 6:
                                    {
                                        try {
                                            System.out.println(" ~~~ venue deletion ~~~");
                                            System.out.print("Enter venue  id: ");
                                            int vid = S.nextInt();

                                            st1 = con.createStatement();
                                            rs1 = st1.executeQuery("select * from venue where venueid = " +
                                                    vid);
                                            rs1.next();
                                            System.out.println(rs1.getString("venueid") + " ");
                                            st1.close();
                                            st1 = con.createStatement();
                                            st1.executeUpdate("delete from booking where venueid = " +vid);

                                            st1.close();
                                            st1 = con.createStatement();
                                            st1.executeUpdate("delete from venue where venueid = " +vid);
                                            System.out.println("venue successfully deleted");
                                            st1.close();
                                        } catch (Exception e) {
                                            System.out.println("no such venue is available..");
                                        }
                                        break;
                                    }
                                    case 7:
                                    {
                                        adminLable=0;
                                        break;
                                    }
                                    default: System.out.println("Enter valid option");

                                }
                            }else{
                                label = 0;
                                flag = 0;
                                break;
                            }
                        }
                        break;
                    }
                    case 2 : {
                        System.out.println("bye...");
                        System.exit(0);
                    }
                    default : System.out.println("Enter valid input");
                }
            }

        }while( flag == 0);
    }
}
