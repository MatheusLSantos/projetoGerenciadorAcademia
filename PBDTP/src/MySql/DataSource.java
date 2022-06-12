package MySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    
    private String hostname;
    private int port;
    private String database;
    private String username;
    private String password;
    
    private Connection connection;
    
    public DataSource() {
        try{
            hostname = "";
            port = 3306;
            database = "";
            username = "";
            password = "";
            
            String url = "jdbc:mysql://"+hostname+":"+port+"/"+database;
            
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(url, username, password);
            
            System.out.println("deu certo..");
        }
        catch(SQLException ex){
            System.err.println("ERRO na Conexão "+ex.getMessage());
        }
        catch (Exception ex) {
            System.err.println("ERRO geral "+ex.getMessage());
        }
    }
    
    public Connection getConnection(){
        return this.connection;
    }
    
    public void closeDataSource() {
        try{
            connection.close();
        }
        catch(Exception ex){
            System.out.println("Erro ao desconectar "+ex.getMessage());
        }
    }
    
}
