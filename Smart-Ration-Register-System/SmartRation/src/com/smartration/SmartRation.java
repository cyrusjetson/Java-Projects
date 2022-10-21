package com.smartration;
import java.sql.*;
import java.util.*;

public class SmartRation {
    static Connection con;
    static Statement st1, st2;
    static ResultSet rs1;
    public static void main(String[] args){
        System.out.println("------ Smart Ration Card -----");
        Scanner S = new Scanner(System.in);
        Scanner P = new Scanner(System.in);

        UserCard user = new UserCard();
        CardRequest r = new CardRequest();

        int id;
        int status;
        int option, lable = 0,flag = 1, adminLable = 0;
        String temp;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ration", "root", "");
            System.out.println("connected");
            st1 = con.createStatement();
            rs1 = st1.executeQuery("select * from consumer");
            st1.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        do {
            while (lable == 0) {
                try {

                    System.out.print("\n  ~~~ Login ~~~\nEnter 1 if you want to login " +
                            " (or) 2 to exit: ");
                    option = S.nextInt();
                    switch (option) {
                        case 1 : {
                            System.out.println("Enter your Username");
                            String agentUsername = S.next();
                            System.out.print("Enter Password: ");
                            String agentPassword = S.next();
                            if(Objects.equals(agentUsername, "16194") && Objects.equals(agentPassword, "12345")){
                                lable = 1;
                                adminLable = 0;
                            }
                            else if(Objects.equals(agentUsername, "admin") && Objects.equals(agentPassword, "admin")){
                                adminLable = 1;
                                lable = 1;
                            }
                            else{
                                System.out.println("Enter valid credentials");
                                adminLable =0;
                                lable = 0;
                                flag = 0;
                            }
                            break;
                        }
                        case 2 : {
                            System.out.println("bye...");
                            System.exit(0);
                        }
                        default : System.out.println("Enter valid input");
                    }
                } catch (Exception e) {
                    System.out.println("bye");
                    System.exit(0);
                }
            }

            // ----- After Login for User ------
            while (lable == 1 && adminLable == 0) {
                System.out.println("\n  ---- Agent login -----");
                System.out.print("\nMenu\n1. Add new consumer details\n2. View status\n" +
                        "3. Update consumer details\n4. Logout\n5. Exit\n" +
                        "Enter your option: ");
                option = S.nextInt();
                switch (option) {
                    case 1: {
                        try {
                            System.out.println(" ~~~ Add new consumer details ~~~");
//                            String rationID;
//                            String familyHeadName;
//                            float age;
//                            String DOB;
//                            String address;
//                            int familyMemberCount;
//                            int noOfChildren;
                            System.out.print("Enter family head name: ");
                            user.setFamilyHeadName(P.nextLine());
                            System.out.print("Enter age: ");
                            user.setAge(S.nextFloat());
                            if(user.getAge() < 18){
                                System.out.println("Age is less than 18");
                                throw new RuntimeException();
                            }
                            System.out.print("Enter DOB: ");
                            user.setDOB(S.next());
                            System.out.println("Enter Aathaar number: ");
                            user.setAn(S.next());
                            System.out.println("Enter address: ");
                            user.setAddress(P.nextLine());
                            System.out.println("Enter family member count: ");
                            user.setFamilyMemberCount(S.nextInt());
                            System.out.println("Enter no of children: ");
                            user.setNoOfChildren(S.nextInt());
//                            int a = 12;
//                            String string = String.valueOf(a);


                            st2 = con.createStatement();
                            st2.executeUpdate("insert into agentrequest values('" + user.getFamilyHeadName() +"','"+user.getAn()+
                                    "'," + user.getAge() + ",'" + user.getDOB() + "','"+ user.getAddress() +"',"+
                                    user.getFamilyMemberCount()+","+user.getNoOfChildren()+",0"+")");
                            st2.close();
                            System.out.println("Add request successful");
                        } catch (Exception e) {
                            System.out.println("Can't add request..");
                        }
                        break;
                    }
                    case 2: {
                        try {
                            System.out.println(" ~~~ View status ~~~");
                            System.out.print("Enter Aathaar number: ");
                            temp = S.next();
                            st1 = con.createStatement();
                            rs1 = st1.executeQuery("select * from agentrequest where an = '" +
                                    temp +"'" );
                            rs1.next();
                            status = rs1.getInt("status") ;
                            st1.close();
                            rs1.close();
                            if(status == 2) {
                                st2 = con.createStatement();
                                rs1 = st2.executeQuery("select * from consumer where an = '" +
                                        temp +"'" );
                                rs1.next();
                                id = rs1.getInt("id") ;
                                st1.close();
                                System.out.println("\nYour ID: "+id);
                            }
                            else if(status == 0)
                                System.out.println("Add is not yet done");
                            else if(status == 1)
                                System.out.println("Update not yet done");
                            else System.out.println("Rejected or not request not available");
                        } catch (Exception e) {
                            System.out.println("no such consumer is available..");
                        }
                        break;
                    }
                    case 3: {
                        try {
                            System.out.println(" ~~~ Update consumer details ~~~");

                            System.out.print("Enter family head name: ");
                            user.setFamilyHeadName(P.nextLine());
                            System.out.print("Enter age: ");
                            user.setAge(S.nextFloat());
                            System.out.print("Enter DOB: ");
                            user.setDOB(S.next());
                            System.out.println("Enter Aathaar number: ");
                            user.setAn(S.next());
                            System.out.println("Enter address: ");
                            user.setAddress(P.nextLine());
                            System.out.println("Enter family member count: ");
                            user.setFamilyMemberCount(S.nextInt());
                            System.out.println("Enter no of children: ");
                            user.setNoOfChildren(S.nextInt());
//                            int a = 12;
//                            String string = String.valueOf(a);


                            st2 = con.createStatement();
                            st2.executeUpdate("insert into agentrequest values('" + user.getFamilyHeadName() +"','"+user.getAn()+
                                    "'," + user.getAge() + ",'" + user.getDOB() + "','"+ user.getAddress() +"',"+
                                    user.getFamilyMemberCount()+","+user.getNoOfChildren()+",1"+")");
                            st2.close();
                            System.out.println("Update request successful");
                        } catch (Exception e) {
                            System.out.println("Update request failed");
                        }
                        break;
                    }
                    case 4: {
                        adminLable =0;
                        lable = 0;
                        flag = 0;
                        break;
                    }
                    case 5: {
                        System.out.println("bye");
                        System.exit(0);
                        break;
                    }

                    default: System.out.println("Enter valid option");
                }
            }


            /// After login for admin ---
            if(adminLable == 1){
                do {
                    System.out.println("\nMenu for Admin\n1.View request list\n2.Accept or reject new consumer request\n3.Accept or" +
                            " reject update request\n" +"4.View all consumer details\n"+
                            "5.Logout\n6.Exit");
                    System.out.print("Enter option: ");
                    int adminOption = S.nextInt();
                    switch (adminOption) {
                        case 1 : {
                            try {
                                System.out.println(" ~~~ View request list ~~~");
                                st1 = con.createStatement();
                                rs1 = st1.executeQuery("select * from agentrequest where status!=2");
                                System.out.println("Aathaar number   Name\tage\tdob  address\tfamily member count   No.of child  status");
                                while (rs1.next()) {
                                    System.out.print(rs1.getString("an")+"  ");
                                    System.out.print(rs1.getString("name") + "\t");
                                    System.out.print(rs1.getFloat("age") + "\t");
                                    System.out.print(rs1.getString("dob") + "\t");
                                    System.out.print(rs1.getString("addr") + "   ");
                                    System.out.print(rs1.getInt("familymembercount") + "\t\t");
                                    System.out.print(rs1.getInt("noofchild") + "  ");
                                    status = rs1.getInt("status");
                                    if(status == 0)
                                        System.out.print("add ");
                                    else if(status == 1)
                                        System.out.print("update");
                                    System.out.println();
                                }
                                st1.close();
                            } catch (Exception e) {
                                System.out.println("Can't access request details");
                            }
                            break;
                        }
                        case 2 : {
                            try {
                                System.out.println(" ~~~ Accept or reject new consumer request ~~~");
                                System.out.print("Enter Aathaar number: ");
                                String aathaar = S.next();
                                System.out.print("Enter 1 for accept 2 for reject: ");
                                status = S.nextInt();
                                if (status == 1){
                                    st1 = con.createStatement();
                                    System.out.print("Enter new user id: ");
                                    id = S.nextInt();
                                    st1.executeUpdate("insert into consumer values("+id+",'"+aathaar+"')");
                                    System.out.println("inserted");
                                    st1.executeUpdate("update agentrequest set status = 2 where status = 0 and an ='"+aathaar+"'");
                                    System.out.println("Consumer added...");
                                    st1.close();
                                }
                                else if(status == 2) {
                                    st1 = con.createStatement();
                                    st1.executeUpdate("delete from agentrequest where status = 0 and an = '" + aathaar + "'");
                                    st1.close();
                                    System.out.println("Request deleted...");
                                }
                                else{
                                    System.out.println("not a valid option");
                                }
                            } catch (Exception e) {
                                System.out.println("failed");
                            }
                            break;
                        }
                        case 3 : {
                            try {
                                System.out.println(" ~~~ Accept or reject update request ~~~");
                                System.out.print("Enter Aathaar number: ");
                                String aathaar = S.next();
                                System.out.print("Enter 1 for accept 2 for reject: ");
                                status = S.nextInt();
                                if (status == 1){
                                    st1 = con.createStatement();
                                    st1.executeUpdate("delete from agentrequest where status = 2 and an ='"+aathaar+"'");
                                    st1.executeUpdate("update agentrequest set status = 2 where status = 1 and an ='"+aathaar+"'");
                                    System.out.println("Consumer details updated...");
                                    st1.close();
                                }
                                else if(status == 2) {
                                    st1 = con.createStatement();
                                    st1.executeUpdate("delete from agentrequest where status = 1 and an = '" + aathaar + "'");
                                    st1.close();
                                    System.out.println("Request deleted...");
                                }
                                else{
                                    System.out.println("not a valid option");
                                }
                            } catch (Exception e) {
                                System.out.println("failed");
                            }
                            break;
                        }
                        case 4: {
                            try {
                                System.out.println("----- All available consumers ----");
                                st1 = con.createStatement();


                                rs1 = st1.executeQuery("select * from agentrequest where status=2");
                                System.out.println("Aathaar number   Name\tage\tdob  address\tfamily member count   No.of child ");
                                while (rs1.next()) {
                                    System.out.print(rs1.getString("an") + "  ");
                                    System.out.print(rs1.getString("name") + "\t");
                                    System.out.print(rs1.getFloat("age") + "\t");
                                    System.out.print(rs1.getString("dob") + "\t");
                                    System.out.print(rs1.getString("addr") + "   ");
                                    System.out.print(rs1.getInt("familymembercount") + "\t\t");
                                    System.out.print(rs1.getInt("noofchild") + "  ");
                                    System.out.println();
                                }
                                st1.close();
                            }catch (Exception e){
                                System.out.println("error");
                            }
                            break;
                        }
                        case 5 : {
                            adminLable =0;
                            lable = 0;
                            flag = 0;
                            break;
                        }
                        case 6 : {
                            System.out.println("bye");
                            System.exit(0);
                        }
                        default : System.out.println("Enter valid input");
                    }
                }while (adminLable == 1);
            }
        }while( flag == 0);
    }
}
