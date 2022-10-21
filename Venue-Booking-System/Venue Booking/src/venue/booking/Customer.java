/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venue.booking;

/**
 *
 * @author user
 */
public class Customer {
    private String Custname;
    private int CustID;
    private String address;
    private String Phoneno;
    private String password;
    void setCustomer(String name,int id,String add,String phone,String pwd)
    {
        CustID=id;
        Custname=name;
        address=add;
        Phoneno=phone;
        password=pwd;
    }
    
    void setCustID(int id)
    {
        CustID=id;
    }
    void setCustpwd(String pwd)
    {
        password=pwd;
    }
     String getCustpwd()
    {
        return password; 
    }
    String getCustname()
    {
        return Custname; 
    }
    String getCustphone()
    {
        return Phoneno; 
    }
    String getCustaddr()
    {
        return address; 
    }
    int getCustID()
    {
        return CustID; 
    }
}
