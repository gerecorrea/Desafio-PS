package dao;

import dados.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;

public class PessoaDAO {
    private static PessoaDAO instance = null;
    private PreparedStatement select;
    //private PreparedStatement selectNum;
    
    public static PessoaDAO getInstance(){
        if (instance == null)
            instance = new PessoaDAO();
        return instance;
    }
    
    public PessoaDAO(){
        Connection conn = Conexao.getConnection();
        try{
            select = conn.prepareStatement("select * from " + "pessoa where codigo = ?");
            //selectNum = conn.prepareStatement("select codigo from " + "pessoa where codigo = ?");
	  }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public Pessoa Select(int num){
        Pessoa est = null;
        try{
            select.setInt(1, num);
            ResultSet rs = select.executeQuery();
            if(rs.next()){
                est = new Pessoa();
                est.setCodigo(rs.getInt("codigo"));
                est.setNome(rs.getString("nome"));
                est.setIdade(rs.getInt("idade"));
                est.setEmail(rs.getString("email"));         
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return est;
    }
   
    //Inserção no banco de dados
    public boolean Insert(int codigo, String nome, int idade, String email) throws SQLException{
        try{
            Connection connection = Conexao.getConnection();
            String sql = "insert into pessoa (codigo, nome, idade, email) values (?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, codigo);
            st.setString(2, nome);
            st.setInt(3, idade);
            st.setString(4, email);
            st.executeUpdate();
            
        }catch(PSQLException e){
            return false;
        }
        return true;
    }
    
        public boolean Edit(int codigo, String nome, int idade, String email) throws SQLException{
            try{
                Connection connection = Conexao.getConnection();
                String sql1 = "delete from pessoa where codigo=" +codigo +"";
                PreparedStatement stt = connection.prepareStatement(sql1);
                stt.executeUpdate();
                String sql = "insert into pessoa (codigo, nome, idade, email) values (?,?,?,?)";
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1,codigo);
                st.setString(2, nome);
                st.setInt(3, idade);
                st.setString(4, email);
                st.executeUpdate();

            }catch(PSQLException e){
                return false;
            }
            return true;
        }
        
        public boolean Remove(int codigo) throws SQLException{
            try{
                Connection connection = Conexao.getConnection();
                String sql1 = "delete from pessoa where codigo=" +codigo +"";
                PreparedStatement stt = connection.prepareStatement(sql1);
                stt.executeUpdate();
            }catch(PSQLException e){
                return false;
            }
            return true;
        }
}
