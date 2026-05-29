package Dao;

import db.ConnectionFactory;
import Model.ItemPedido;
import Model.Pedido;
import Model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public int salvar(Pedido pedido) {
        String sql = "INSERT INTO pedidos (cliente_id, data_pedido) VALUES (?, NOW())";
        
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, pedido.getIdCliente());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    pedido.setIdPedido(idGerado); 
                    return idGerado;              
                }
            }
            throw new SQLException("Falha ao criar pedido, nenhum ID retornado pelo banco.");
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar pedido: " + e.getMessage(), e);
        }
    }


    public void salvarItemAvulso(ItemPedido item, int idPedido) {
      
        String sql = "INSERT INTO itens_pedido (id_pedido, produto_id, quantidade_de_prod_pedidos, preco_unitario) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idPedido);
            stmt.setInt(2, item.getProduto().getProdutoId()); 
            stmt.setInt(3, item.getQuantidadeDeProdutos()); 
            stmt.setDouble(4, item.getPrecoUnitario());     
            
            stmt.executeUpdate();
            System.out.println("Produto adicionado ao pedido com sucesso!");
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir item no pedido: " + e.getMessage(), e);
        }
    }

    public List<ItemPedido> buscarItensDoPedido(int idPedido) {
        String sql = "SELECT ip.itens_pedido_id, ip.produto_id, ip.quantidade_de_prod_pedidos, ip.preco_unitario, " +
                     "       p.produto_nome, p.preco AS preco_produto, p.quantidade AS estoque_produto, p.descricao " +
                     "FROM itens_pedido ip " +
                     "INNER JOIN produtos p ON ip.produto_id = p.produto_id " +
                     "WHERE ip.id_pedido = ?";
                     
        List<ItemPedido> itens = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    
                    Produto produto = new Produto(
                        rs.getString("produto_nome"),
                        rs.getDouble("preco_produto"),
                        rs.getInt("estoque_produto"), 
                        rs.getString("descricao")
                    );
                    produto.setProdutoId(rs.getInt("produto_id"));

                    ItemPedido item = new ItemPedido(
                        produto,
                        rs.getInt("quantidade_de_prod_pedidos"), 
                        rs.getDouble("preco_unitario")
                    );
                    
                    item.setIdItemPedido(rs.getInt("itens_pedido_id")); 

                    itens.add(item);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar itens do pedido: " + e.getMessage(), e);
        }
        return itens;
    }


    public void deletar(int idPedido) {
        String sqlDeletarItens = "DELETE FROM itens_pedido WHERE id_pedido = ?";
        String sqlDeletarPedido = "DELETE FROM pedidos WHERE id_pedido = ?";

        try (Connection conn = ConnectionFactory.getConexao()) {
            conn.setAutoCommit(false); 

            try (PreparedStatement stmtItens = conn.prepareStatement(sqlDeletarItens);
                 PreparedStatement stmtPedido = conn.prepareStatement(sqlDeletarPedido)) {

                stmtItens.setInt(1, idPedido);
                stmtItens.executeUpdate();

                stmtPedido.setInt(1, idPedido);
                int linhasAfetadas = stmtPedido.executeUpdate();

                if (linhasAfetadas > 0) {
                    conn.commit(); 
                    System.out.println("Pedido e seus itens foram completamente removidos.");
                } else {
                    conn.rollback();
                    System.out.println("Aviso: Pedido não foi localizado para remoção.");
                }

            } catch (SQLException e) {
                conn.rollback(); 
                throw e;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar pedido: " + e.getMessage(), e);
        }
    }
}