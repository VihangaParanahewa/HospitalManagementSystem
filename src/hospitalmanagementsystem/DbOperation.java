
package hospitalmanagementsystem;
import java.sql.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.WindowConstants;


public class DbOperation {  
    String url="jdbc:mysql://localhost:3306/hospital";
    String username="root";
    String passWord="";
    Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    int rowCount;
    int rOwcount;

    boolean checkUser (String userName,String password,String table){
        try{
    con=(Connection)DriverManager.getConnection(url, username, passWord);//get conection 
    String query = null;
    switch(table.toLowerCase()){
        case "adm":
             query="select userName from admin where userName=? and password=?;";
            break;
        case "doc":
             query="select userName from doctor where userName=? and password=?;";
            break;
        case "pat":
            query="select userName from patient where userName=? and password=?;";
            break;
        case "nur":
            query="select userName from nurse where userName=? and password=?;";
            break;
        case "con":
            query="select userName from consultant where userName=? and password=?;";
            break; 
    }
   
        pst=(PreparedStatement)con.prepareStatement(query);
        pst.setString(1, userName);
        pst.setString(2, password);
        rs=pst.executeQuery();
        boolean result=rs.next();
        if(result){
        return true;
        }else{
        return false;
        }
        }catch(Exception e){
        System.out.println(e);
        return false;
        }
        }
    
    
    String[] getProfiledetails(String userName,String password){
        try{
    con=(Connection)DriverManager.getConnection(url, username, passWord);//get conection 
    String query="select * from Admin where userName=? and password=?";
    pst=(PreparedStatement)con.prepareStatement(query);
    pst.setString(1, userName);
    pst.setString(2, password);
    rs=pst.executeQuery();
    rs.next();
    ResultSetMetaData rsMeta=rs.getMetaData();
    int colomunCount=rsMeta.getColumnCount();
    String value[]=new String[colomunCount];
    for(int i=1;i<=colomunCount;i++){
        value[i-1]=rs.getString(i);
        }
    return value;
    }catch(Exception e){
        System.out.println(e);
        return null;
    }
    }
    
    
     void createUser(CreateProfile cp){
        try{
        con=(Connection)DriverManager.getConnection(url, username, passWord);//get connection
         String user=cp.getUserName();
         switch(user.substring(0,3)){
        case "pat":
             String query="insert into waitinglist values(?,?,?,?,?,?,?,?,?);";
             pst=(PreparedStatement)con.prepareStatement(query);
            break;
             
        case "doc":
             String queryDoc="insert into doctor values(?,?,?,?,?,?,?,?,?,?,?);";
             pst=(PreparedStatement)con.prepareStatement(queryDoc);
             pst.setInt(10, cp.getWardNum());
             pst.setInt(11, cp.getSalary());
            break;
        case "nur":
             String queryNur="insert into nurse values(?,?,?,?,?,?,?,?,?,?,?);";
             pst=(PreparedStatement)con.prepareStatement(queryNur);
             pst.setInt(10, cp.getWardNum());
             pst.setInt(11, cp.getSalary());
            break;
        case "con": 
             String queryCon="insert into consultant values(?,?,?,?,?,?,?,?,?,?,?);";
             pst=(PreparedStatement)con.prepareStatement(queryCon);
             pst.setInt(10, cp.getWardNum());
             pst.setInt(11, cp.getSalary());
            break;   
         
         }    
        pst.setInt(1, cp.getRegId());
        pst.setString(2, cp.getFirstName());
        pst.setString(3, cp.getLastName());
        pst.setInt(4, cp.getAge());
        pst.setString(5, cp.getGender());
        pst.setString(6, cp.getPhoneNum());
        pst.setString(7, cp.getEmail());
        pst.setString(8, cp.getUserName());
        pst.setString(9, cp.getPassword());
       
        
        pst.executeUpdate();
        
       
    }catch(Exception e){
    System.out.println(e);
    }
}
     
     void confirm(CreateProfile cp){
         try{
         con=(Connection)DriverManager.getConnection(url, username, passWord);
         String querycon="insert into patient values(?,?,?,?,?,?,?,?,?,?)";
         pst=(PreparedStatement)con.prepareStatement(querycon);
        pst.setInt(1, cp.getRegId());
        pst.setString(2, cp.getFirstName());
        pst.setString(3, cp.getLastName());
        pst.setInt(4, cp.getAge());
        pst.setString(5, cp.getGender());
        pst.setString(6, cp.getPhoneNum());
        pst.setString(7, cp.getEmail());
        pst.setString(8, cp.getUserName());
        pst.setString(9, cp.getPassword());
        pst.setInt(10, cp.getWardNum());
        
        pst.executeUpdate();
         
         }catch(Exception e){
    System.out.println(e);
         }
     }
     
     String[] getConsultantdetails(String userName,String password){
         try{
    con=(Connection)DriverManager.getConnection(url, username, passWord);//get conection 
    String query="select * from consultant where userName=? and password=?";
    pst=(PreparedStatement)con.prepareStatement(query);
    pst.setString(1, userName);
    pst.setString(2, password);
    rs=pst.executeQuery();
    rs.next();
    ResultSetMetaData rsMeta=rs.getMetaData();
    int colomunCount=rsMeta.getColumnCount();
    String value[]=new String[colomunCount];
    for(int i=1;i<=colomunCount;i++){
        value[i-1]=rs.getString(i);
        }
    return value;
    }catch(Exception e){
        System.out.println(e);
        return null;
    }
    
     
     
     }
     
     String[] getConOppointment(){
         try{
    con=(Connection)DriverManager.getConnection(url, username, passWord);//get conection 
    String query="select watId from waitinglist";
    pst=(PreparedStatement)con.prepareStatement(query);
    rs=pst.executeQuery();
    rs.next();
    int row=1;
    
    for(int j=0;j<=row;j++){
    rowCount=rs.getRow();
    rs.next();
    row=rs.getRow();
    }
    
    
    for(int j=0;j<rowCount;j++){
    rs.previous();
    }
    
  
    String value[]=new String[rowCount];
    for(int i=0;i<rowCount;i++){
        value[i]=rs.getString(1);
        rs.next();
        }
    return value;
    }catch(Exception e){
        String value[]=new String[1];
        value[0]="No Oppoinments";
        return value ;
    }
     }
     
    String[] getDetailsToConfirm(int id){
    try{
    con=(Connection)DriverManager.getConnection(url, username, passWord);//get conection 
    String query="select * from waitinglist where watId=?";
    pst=(PreparedStatement)con.prepareStatement(query);
    pst.setInt(1, id);
    rs=pst.executeQuery();
    rs.next();
    ResultSetMetaData rsMeta=rs.getMetaData();
    int colomunCount=rsMeta.getColumnCount();
    String value[]=new String[colomunCount];
    for(int i=1;i<=colomunCount;i++){
        value[i-1]=rs.getString(i);
        }
    return value;
    }catch(Exception e){
        System.out.println(e);
        return null;
    }
    
    }
    
    
    void removeUser(int Id){
    try{
    con=(Connection)DriverManager.getConnection(url, username, passWord);//get conection 
    String query="delete from waitinglist where watId=?";
    pst=(PreparedStatement)con.prepareStatement(query);
    pst.setInt(1, Id);
    pst.executeUpdate();
    }catch(Exception e){
        System.out.println(e);
    }
}
    
    void makeTestOppointment(Createtestlist ctl){
      try{
         con=(Connection)DriverManager.getConnection(url, username, passWord);
         String querycon="insert into testlist values(?,?)";
         pst=(PreparedStatement)con.prepareStatement(querycon);
        pst.setInt(1, ctl.getPatid());
        pst.setString(2, ctl.getTestname());
        
        
        pst.executeUpdate();
         
         }catch(Exception e){
    System.out.println(e);
         }
    
    }
    
    String[] getDoctordetails(String userName,String password){
      try{
    con=(Connection)DriverManager.getConnection(url, username, passWord);//get conection 
    String query="select * from doctor where userName=? and password=?";
    pst=(PreparedStatement)con.prepareStatement(query);
    pst.setString(1, userName);
    pst.setString(2, password);
    rs=pst.executeQuery();
    rs.next();
    ResultSetMetaData rsMeta=rs.getMetaData();
    int colomunCount=rsMeta.getColumnCount();
    String value[]=new String[colomunCount];
    for(int i=1;i<=colomunCount;i++){
        value[i-1]=rs.getString(i);
        }
    return value;
    }catch(Exception e){
        System.out.println(e);
        return null;
    }
    }
    
    String[] getdocOppointment(){
    try{
    con=(Connection)DriverManager.getConnection(url, username, passWord);//get conection 
    String query="select PatId from testlist";
    pst=(PreparedStatement)con.prepareStatement(query);
    rs=pst.executeQuery();
    rs.next();
    int row=1;
    
    for(int j=0;j<=row;j++){
    rowCount=rs.getRow();
    rs.next();
    row=rs.getRow();
    }
    
    
    for(int j=0;j<rowCount;j++){
    rs.previous();
    }
    
  
    String value[]=new String[rowCount];
    for(int i=0;i<rowCount;i++){
        value[i]=rs.getString(1);
        rs.next();
        }
    return value;
    }catch(Exception e){
        String value[]=new String[1];
        value[0]="No Oppoinments";
        return value ;
    }
    
    }
    
    void testconfirm(Createtestlist ctl){
        try{
         con=(Connection)DriverManager.getConnection(url, username, passWord);
         String querycon="insert into test values(?,?,?)";
         pst=(PreparedStatement)con.prepareStatement(querycon);
        pst.setInt(1, ctl.getPatid());
        pst.setString(2, ctl.getTestname());
        pst.setString(3, ctl.getTestresult());
        
        
        pst.executeUpdate();
         
         }catch(Exception e){
    System.out.println(e);
         }
    
    }
    
    void removeTest(int id){
    try{
    con=(Connection)DriverManager.getConnection(url, username, passWord);//get conection 
    String query="delete from testlist where PatId=?";
    pst=(PreparedStatement)con.prepareStatement(query);
    pst.setInt(1, id);
    pst.executeUpdate();
    }catch(Exception e){
        System.out.println(e);
    }
    }
    
    String[] getDetailsToConfirmtest(int id){
    try{
    con=(Connection)DriverManager.getConnection(url, username, passWord);//get conection 
    String query="select * from testlist where PatId=?";
    pst=(PreparedStatement)con.prepareStatement(query);
    pst.setInt(1, id);
    rs=pst.executeQuery();
    rs.next();
    ResultSetMetaData rsMeta=rs.getMetaData();
    int colomunCount=rsMeta.getColumnCount();
    String value[]=new String[colomunCount];
    for(int i=1;i<=colomunCount;i++){
        value[i-1]=rs.getString(i);
        }
    return value;
    }catch(Exception e){
        System.out.println(e);
        return null;
    }
}
}