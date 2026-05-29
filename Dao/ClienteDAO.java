package Dao;

import Model.Cliente;
import db.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, sobrenome, email) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getSobrenomeCliente());
            stmt.setString(3, cliente.getEmailCliente());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    cliente.setClienteID(idGerado); 
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente no banco: " + e.getMessage(), e);
        }
        }

    public List<Cliente> listarClientes() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getString("email")
                );
                cliente.setClienteID(rs.getInt("cliente_id"));
                
                lista.add(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
        }
        
        return lista; 
    }

    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE cliente_id = ?";

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("email")
                    );
                    cliente.setClienteID(rs.getInt("cliente_id"));
                    return cliente; 
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID: " + e.getMessage(), e);
        }
        
        return null; 
    }

    public void deletar(int id) {
        String sql = "DELETE FROM clientes WHERE cliente_id = ?";

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum cliente foi encontrado com o ID: " + id);
            }

            System.out.println("Cliente com ID " + id + " deletado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente. Verifique se ele não possui pedidos vinculados: " + e.getMessage(), e);
        }
    }

    public void atualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, sobrenome = ?, email = ? WHERE cliente_id = ?";

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getSobrenomeCliente());
            stmt.setString(3, cliente.getEmailCliente());
            
            stmt.setInt(4, cliente.getClienteID());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("Nenhum cliente foi atualizado. O ID existe no banco?");
            }
            

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage(), e);
        }
    }
}
