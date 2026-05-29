package Dao;

import db.ConnectionFactory;
import Model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void salvar(Produto produto){

        String sql = "INSERT INTO produtos(produto_nome, preco, quantidade, descricao) VALUES (?, ?, ?, ?)";

        try(
                Connection conn = ConnectionFactory.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){

            stmt.setString(1, produto.getNomeProduto());
            stmt.setDouble(2, produto.getPrecoProduto());
            stmt.setInt(3, produto.getQuantidadeProduto());
            stmt.setString(4, produto.getDescricao());

            stmt.executeUpdate();

            System.out.println("Produto salvo com sucesso!");

        } catch (SQLException e){
            throw new RuntimeException("Erro ao salvar produto", e);
        }
    }

    public Produto buscarPorId(int id){

        String sql = "SELECT * FROM produtos WHERE produto_id = ?";

        try(
                Connection conn = ConnectionFactory.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                Produto produto = new Produto(
                        rs.getString("produto_nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        rs.getString("descricao")
                );

                produto.setProdutoId(rs.getInt("produto_id"));

                return produto;
            }

        } catch (SQLException e){
            throw new RuntimeException("Erro ao buscar produto", e);
        }

        return null;
    }

    public List<Produto> listarProdutos(){

        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produtos";

        try(
                Connection conn = ConnectionFactory.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
        ){

            while(rs.next()){

                Produto produto = new Produto(
                        rs.getString("produto_nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        rs.getString("descricao")
                );

                produto.setProdutoId(rs.getInt("produto_id"));

                produtos.add(produto);
            }

        } catch (SQLException e){
            throw new RuntimeException("Erro ao listar produtos", e);
        }

        return produtos;
    }

    public void atualizar(Produto produto){

        String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ?, descricao = ? WHERE produto_id = ?";

        try(
                Connection conn = ConnectionFactory.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){

            stmt.setString(1, produto.getNomeProduto());
            stmt.setDouble(2, produto.getPrecoProduto());
            stmt.setInt(3, produto.getQuantidadeProduto());
            stmt.setString(4, produto.getDescricao());
            stmt.setInt(5, produto.getProdutoId());

            stmt.executeUpdate();

            System.out.println("Produto atualizado!");

        } catch (SQLException e){
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    public void deletar(int id){

        String sql = "DELETE FROM produtos WHERE produto_id = ?";

        try(
                Connection conn = ConnectionFactory.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){

            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Produto deletado!");

        } catch (SQLException e){
            throw new RuntimeException("Erro ao deletar produto", e);
        }
    }
}