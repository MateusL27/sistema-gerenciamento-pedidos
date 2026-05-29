package Model;

public class ItemPedido {

    private int idItemPedido;
    private Produto produto;
    private int quantidadeDeProduto;
    private double precoUnitario;

    public ItemPedido(Produto produto, int quantidadeDeProdutos, double precoUnitario){

        setProduto(produto);
        setQuantidadeDeProdutos(quantidadeDeProdutos);
        setPrecoUnitario(precoUnitario);
    }

    public void setIdItemPedido(int idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public int getIdItemPedido() {
        return idItemPedido;
    }

    public void setProduto(Produto produto) {
        if(produto == null){
            throw new IllegalArgumentException("O produto não pode ser nulo");
        }

        if(produto.getProdutoId() <= 0){
            throw new IllegalArgumentException("O valor do ID deve ser superior a 0");
        }
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    } 

    public void setQuantidadeDeProdutos(int quantidadeDeProduto) {
        if(quantidadeDeProduto <= 0){
            throw new IllegalArgumentException("A quantidade deve ser maior que 0");
        }
        this.quantidadeDeProduto = quantidadeDeProduto;
    }

    public int getQuantidadeDeProdutos() {
        return quantidadeDeProduto;
    }


    public void setPrecoUnitario(double precoUnitario) {
        if(precoUnitario <= 0 || precoUnitario >50000){
            throw new IllegalArgumentException("deve ser maior que 0 e menor ou igual a 50000");
        }
        this.precoUnitario = precoUnitario;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    @Override
    public String toString(){
    return "Produto: " + produto.getNomeProduto() +
           " | ID Produto: " + produto.getProdutoId() +
           " | Quantidade: " + quantidadeDeProduto +
           " | Preço Unitário: R$" + precoUnitario;
    }
    
}
