package Dao;

import db.ConnectionFactory;
import Model.ItemPedido;
import Model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDAO {


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
}