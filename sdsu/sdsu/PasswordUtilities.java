package sdsu;

import java.security.*;
import java.util.*;
import java.io.*;

public class PasswordUtilities {
        
    public static boolean isValidLogin(String username, String password) {
       Vector<String[]> userData;
       String encryptedPassword = getEncryptedPassword(password);   
       System.out.println(encryptedPassword);	   
       String query = "SELECT password FROM users WHERE username='" + username + "';";
	   System.out.println(username);
       userData = DBHelper.runQuery(query);
       try {
       		if(userData.elementAt(0)[0].equals(encryptedPassword)){
			return true;
			}
		}
	catch(Exception e) {
		return false;
		}
		return false;
        }
        
    public static String getEncryptedPassword(String str) {   
        try {  
            MessageDigest d = MessageDigest.getInstance("MD5");
            byte [] b = str.getBytes();     
            d.update(b);
            return  byteArrayToHexString(d.digest());
            }
        catch(Exception e) {
            e.printStackTrace();               
            }
    return null;
    }          
    
    private static String byteArrayToHexString(byte[] b){
        String str = "";
        for(int i=0; i < b.length; i++) {
            int value = b[i] & 0xFF;
            if(value < 16)
                str += "0";
            str += Integer.toHexString(value);
            }
        return str.toUpperCase();
        }

	public static Vector<String[]> isValidSKU(String sku) {
       Vector<String[]> skuData;
          
       //System.out.println(encryptedPassword);	   
       String query = "SELECT * FROM product WHERE sku='" + sku + "';";
	   //System.out.println(username);
       skuData = DBHelper.runQuery(query);
       try {
       		if(skuData.elementAt(0)[0].equals(sku))
			return skuData;
		}
	catch(Exception e) {
		return null;
		}
		return null;
        }
		
		public static Vector<String[]> isValidSKUForOnhand(String sku) {
       Vector<String[]> skuData;
         
       //System.out.println(encryptedPassword);	
	   
       String query = "SELECT * FROM on_hand WHERE sku='" + sku + "';";
	   //System.out.println(username);
       skuData = DBHelper.runQuery(query);
       try {
       		if(skuData.elementAt(0)[0].equals(sku))
			return skuData;
		}
	catch(Exception e) {
		return null;
		}
		return null;
        }
		public static String getCategory(int category) {
       Vector<String[]> skuData;
          
       //System.out.println(encryptedPassword);	   
       String query = "SELECT name FROM category WHERE id=" + category + ";";
	   //System.out.println(username);
       skuData = DBHelper.runQuery(query);
       try {
       		/*if(skuData.elementAt(0)[0].equals(sku))
			return skuData;*/
		return skuData.elementAt(0)[0];
		}
	catch(Exception e) {
		return null;
		}
		//return null;
        }
		public static String getVendor(int vendor) {
       Vector<String[]> skuData;
          
       //System.out.println(encryptedPassword);	   
       String query = "SELECT name FROM vendor WHERE id=" + vendor + ";";
	   //System.out.println(username);
       skuData = DBHelper.runQuery(query);
       try {
       		/*if(skuData.elementAt(0)[0].equals(sku))
			return skuData;*/
		return skuData.elementAt(0)[0];
		}
	catch(Exception e) {
		return null;
		}
		//return null;
        }
		public static boolean addInData(String sku, String date,int quantity) {
       int noOfRows;
       //String encryptedPassword = getEncryptedPassword(password);   
       //System.out.println(encryptedPassword);	   
       String query = "INSERT INTO merchandise_in VALUES ('" + sku + "','"+date+"',"+quantity+");";
	   //System.out.println(username);
       noOfRows = DBHelper.executeCommand(query);
       try {
       		if(noOfRows==1){
			if(addOnHandData(sku,date,quantity)){
			return true;
			}
			else{
				return false;
			}
			}
		}
	catch(Exception e) {
		return false;
		}
		return false;
        }
		public static boolean addOnHandData(String sku, String date,int quantity) {
       int noOfRows;
	   String query=null;
	    Vector<String[]> skuData=PasswordUtilities.isValidSKUForOnhand(sku);
	   if(skuData !=null){
		   quantity=quantity+Integer.parseInt(skuData.elementAt(0)[2]);
       query = "UPDATE on_hand SET last_date_modified='"+date+"',on_hand_quantity="+quantity+" where sku='"+sku+"';";
        }
		else{
		query = "INSERT INTO on_hand VALUES ('" + sku + "','"+date+"',"+quantity+");";
		}
		noOfRows = DBHelper.executeCommand(query);
       try {
       		if(noOfRows==1){
				
			return true;
			}
		}
	catch(Exception e) {
		return false;
		}	
		return false;
		}
		public static String addOutData(String sku, String date,int quantity) {
       int noOfRows;
       //String encryptedPassword = getEncryptedPassword(password);   
       //System.out.println(encryptedPassword);	   
       
	   //System.out.println(username);
	   String result=PasswordUtilities.subtractOnHandData(sku,date,quantity);
       
       try {
       		if(result.equals("Insertion Sucessful")){
			String query = "INSERT INTO merchandise_out VALUES ('" + sku + "','"+date+"',"+quantity+");";
			noOfRows = DBHelper.executeCommand(query);
			if(noOfRows==1){
				return result;
			}
			return result;
			}
		}
	catch(Exception e) {
		return "Error while Updation";
		}
		return result;
        }
		public static String subtractOnHandData(String sku, String date,int quantity) {
       int noOfRows;
	   String query=null;
	    Vector<String[]> skuData=PasswordUtilities.isValidSKUForOnhand(sku);
	   if(skuData !=null){
		   if(quantity<=Integer.parseInt(skuData.elementAt(0)[2]) && quantity>0){
				quantity=Integer.parseInt(skuData.elementAt(0)[2])-quantity;
				query = "UPDATE on_hand SET last_date_modified='"+date+"',on_hand_quantity="+quantity+" where sku='"+sku+"';";
				noOfRows = DBHelper.executeCommand(query);
			   try {
					if(noOfRows==1){
						
					return "Insertion Sucessful";
					}
				}
			catch(Exception e) {
				return "Error while updation";
				}
		   }
		   else{
			   return "The quantity in the warehouse is "+Integer.parseInt(skuData.elementAt(0)[2])+"while the requested quantity is "+quantity+" .Please enter the valid quantity";
		   }
        }
		else{
		return "No Sock for this product in WareHouse";
		}
		
		return "Error while updation";
		}
        		
		
}            
