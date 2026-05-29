package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int idPedido;
    private int idCliente;
    private LocalDateTime dataPedido = LocalDateTime.now();
    private List<ItemPedido> itens = new ArrayList<>();

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }


    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void adicionarItem(ItemPedido item){
        if(item == null){
            throw new IllegalArgumentException("O item não pode ser nulo");
        }

        for(int i = 0; i < itens.size(); i++){
            if(item.getProduto().getProdutoId()== itens.get(i).getProduto().getProdutoId()){
                throw new IllegalArgumentException("O produto já existe");
            }
        }

        itens.add(item);
    }

    public double calcularTotal(){
        double total = 0;

        for(ItemPedido item:itens){
            total += item.getPrecoUnitario() * item.getQuantidadeDeProdutos();
        }

        return total;
    }

    public ItemPedido buscarItemPorProduto(int produtoId){
        for(ItemPedido item:itens){
            if(item.getProduto().getProdutoId() == produtoId){
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return "ID Pedido: " + idPedido +
            " | Cliente: " + idCliente +
            " | Data: " + dataPedido +
            " | Total: R$" + calcularTotal() +
            " | Itens: " + itens.size();
    }

}
