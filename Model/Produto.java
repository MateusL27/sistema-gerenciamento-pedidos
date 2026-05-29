package Model;

public class Produto {

    private int produtoId;
    private String nomeProduto;
    private double precoProduto;
    private int quantidadeProduto;
    private String descricao;

    public Produto() {
        
}

    public Produto(String nomeProduto,double precoProduto, int quantidadeProduto,String descricao){
        setNomeProduto(nomeProduto);
        setPrecoProduto(precoProduto);
        setQuantidadeProduto(quantidadeProduto);
        setDescricao(descricao);
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setNomeProduto(String nomeProduto) {
        if(nomeProduto == null || nomeProduto.trim().length() < 3){
            throw new IllegalArgumentException("O nome do produto não deve ser vazio e deve possuir mais de 3 caractéres!");
        }
        this.nomeProduto = nomeProduto.trim();
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        if(precoProduto <= 0 || precoProduto >50000){
            throw new IllegalArgumentException("deve ser maior que 0 e menor ou igual a 50000");
        }
        this.precoProduto = precoProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        if(quantidadeProduto < 0 || quantidadeProduto > 10000){
            throw new IllegalArgumentException("A quantidade deve ser maior ou igual 0 e inferior a 10000");
        }
        this.quantidadeProduto = quantidadeProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setDescricao(String descricao) {
        if(descricao == null || descricao.trim().length() < 15 || descricao.trim().length() > 150){
            throw new IllegalArgumentException("A descrição não pode ser vazia e deve possuir entre 15 e 150 caractéres!");
        }
        this.descricao = descricao.trim();
    }
    
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString(){
    return "ID: " + produtoId +
           " | Nome: " + nomeProduto +
           " | Preço: R$" + precoProduto +
           " | Quantidade: " + quantidadeProduto +
           " | Descrição: " + descricao;
    }

}
