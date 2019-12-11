package RegraNegocio;

import dados.*;
import dao.*;
import java.sql.SQLException;

public class RegrasNegocio {
    static RegrasNegocio instancia = null;
    
    private RegrasNegocio() throws Exception{
        init();
    }
    
    private void init() throws Exception{
        PessoaDAO est = new PessoaDAO();
    }

    //Listar todos aqui
    public static Pessoa pesquisarPessoa(int num){
        Pessoa est = PessoaDAO.getInstance().Select(num);
        return est;
    }
    
    public static boolean inserirPessoa(int codigo, String nome, int idade, String email) throws SQLException{
        PessoaDAO d = new PessoaDAO();
        
        //A ideia aqui o intermediário entre usuário e sistema, permitindo também o tratamento caso
        //houvesse alguma restrição, o que aqui não é o caso.
        boolean i = d.Insert(codigo, nome, idade, email);
        return i;
    }
    
    public static boolean EditarPessoa(int codigo, String nome, int idade, String email) throws SQLException{
        PessoaDAO d = new PessoaDAO();
        boolean edit = d.Edit(codigo, nome, idade, email);
        return edit;
    }
    
    public static boolean RemoverPessoa(int codigo) throws SQLException{
        PessoaDAO d = new PessoaDAO();
        boolean rem = d.Remove(codigo);
        return rem;
    }
    
    
    //Falta poder editar e remover também

}
