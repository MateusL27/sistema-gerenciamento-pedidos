package db;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {
        
        Connection conn = ConnectionFactory.getConexao();
        
        if(conn != null){
            System.out.println("Funcionou");
        }

    }
    
}
