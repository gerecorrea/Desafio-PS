package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    static private Connection connection = null;
	
    static public Connection getConnection(){
        if(connection == null){
            String url = "jdbc:postgresql://localhost:5432/pontoSistemas";
            String username = "postgres";
            String password = "udesc";
            try{
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, username, password);
            }
            catch(SQLException e)    {
                e.printStackTrace();
            }catch(ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        return connection;
    }
}

