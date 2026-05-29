package Main;

import Dao.ClienteDAO;
import Dao.PedidoDAO;
import Dao.ProdutoDAO;
import Model.Cliente;
import Model.ItemPedido;
import Model.Pedido;
import Model.Produto;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ClienteDAO clienteDAO = new ClienteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();

        int menuPrincipal = -1;

        while(menuPrincipal != 0){

            System.out.println("\n===== SISTEMA =====");
            System.out.println("1 - Clientes");
            System.out.println("2 - Produtos");
            System.out.println("3 - Pedidos");
            System.out.println("0 - Sair");

            System.out.print("Escolha: ");
            menuPrincipal = sc.nextInt();
            sc.nextLine();

            switch(menuPrincipal){

                case 1:

                    int menuCliente = -1;

                    while(menuCliente != 0){

                        System.out.println("\n===== CLIENTES =====");
                        System.out.println("1 - Cadastrar cliente");
                        System.out.println("2 - Listar clientes");
                        System.out.println("3 - Buscar cliente");
                        System.out.println("4 - Deletar cliente");
                        System.out.println("0 - Voltar");

                        System.out.print("Escolha: ");
                        menuCliente = sc.nextInt();
                        sc.nextLine();

                        try {

                            switch(menuCliente){

                                case 1:

                                    System.out.print("Nome: ");
                                    String nome = sc.nextLine();

                                    System.out.print("Sobrenome: ");
                                    String sobrenome = sc.nextLine();

                                    System.out.print("Email: ");
                                    String email = sc.nextLine();

                                    Cliente cliente = new Cliente(nome, sobrenome, email);

                                    clienteDAO.salvar(cliente);
                                    break;

                                case 2:

                                    for(Cliente c : clienteDAO.listarClientes()){
                                        System.out.println(c);
                                    }
                                    break;

                                case 3:

                                    System.out.print("ID: ");
                                    int idBusca = sc.nextInt();
                                    sc.nextLine();

                                    Cliente clienteBuscado = clienteDAO.buscarPorId(idBusca);

                                    if(clienteBuscado != null){
                                        System.out.println(clienteBuscado);
                                    } else {
                                        System.out.println("Cliente não encontrado.");
                                    }
                                    break;

                                case 4:

                                    System.out.print("ID: ");
                                    int idDelete = sc.nextInt();
                                    sc.nextLine();

                                    clienteDAO.deletar(idDelete);
                                    break;

                                case 0:
                                    System.out.println("Voltando...");
                                    break;

                                default:
                                    System.out.println("Opção inválida.");
                            }

                        } catch (Exception e){
                            System.out.println("ERRO: " + e.getMessage());
                        }
                    }
                    break;

                case 2:

                    int menuProduto = -1;

                    while(menuProduto != 0){

                        System.out.println("\n===== PRODUTOS =====");
                        System.out.println("1 - Cadastrar produto");
                        System.out.println("2 - Listar produtos");
                        System.out.println("3 - Buscar produto");
                        System.out.println("4 - Deletar produto");
                        System.out.println("0 - Voltar");

                        System.out.print("Escolha: ");
                        menuProduto = sc.nextInt();
                        sc.nextLine();

                        try {

                            switch(menuProduto){

                                case 1:

                                    System.out.print("Nome: ");
                                    String nomeProduto = sc.nextLine();

                                    System.out.print("Preço: ");
                                    double preco = sc.nextDouble();
                                    sc.nextLine(); 

                                    System.out.print("Quantidade: ");
                                    int quantidade = sc.nextInt();

                                    System.out.print("Descrição: ");
                                    String descricao = sc.nextLine();

                                    Produto produto = new Produto(nomeProduto, preco, quantidade, descricao);

                                    produtoDAO.salvar(produto);
                                    break;

                                case 2:

                                    for(Produto p : produtoDAO.listarProdutos()){
                                        System.out.println(p);
                                    }
                                    break;

                                case 3:

                                    System.out.print("ID: ");
                                    int idBuscaProduto = sc.nextInt();
                                    sc.nextLine();

                                    Produto produtoBuscado = produtoDAO.buscarPorId(idBuscaProduto);

                                    if(produtoBuscado != null){
                                        System.out.println(produtoBuscado);
                                    } else {
                                        System.out.println("Produto não encontrado.");
                                    }
                                    break;

                                case 4:

                                    System.out.print("ID: ");
                                    int idDeleteProduto = sc.nextInt();
                                    sc.nextLine();

                                    produtoDAO.deletar(idDeleteProduto);
                                    break;

                                case 0:
                                    System.out.println("Voltando...");
                                    break;

                                default:
                                    System.out.println("Opção inválida.");
                            }

                        } catch (Exception e){
                            System.out.println("ERRO: " + e.getMessage());
                        }
                    }
                    break;

                case 3:

                    int menuPedido = -1;

                    while(menuPedido != 0){

                        System.out.println("\n===== PEDIDOS =====");
                        System.out.println("1 - Criar pedido (Novo carrinho)");
                        System.out.println("2 - Adicionar item no pedido");
                        System.out.println("3 - Ver itens do pedido");
                        System.out.println("4 - Deletar pedido completo");
                        System.out.println("0 - Voltar");

                        System.out.print("Escolha: ");
                        menuPedido = sc.nextInt();
                        sc.nextLine();

                        try {

                            switch(menuPedido){

                                case 1:
                                    System.out.print("ID do cliente: ");
                                    int idCliente = sc.nextInt();
                                    sc.nextLine();

                                    Cliente cliente = clienteDAO.buscarPorId(idCliente);

                                    if(cliente == null){
                                        System.out.println("Cliente não encontrado!");
                                        break;
                                    }

                                    Pedido pedido = new Pedido();
                                    pedido.setIdCliente(idCliente);

                                    int idPedido = pedidoDAO.salvar(pedido);

                                    System.out.println("Pedido criado com sucesso! Guarde este ID para adicionar itens: " + idPedido);
                                    break;

                                case 2:
                                    System.out.print("ID do pedido existente: ");
                                    int pedidoId = sc.nextInt();
                                    sc.nextLine();

                                    System.out.print("ID do produto: ");
                                    int produtoId = sc.nextInt();
                                    sc.nextLine();

                                    Produto produto = produtoDAO.buscarPorId(produtoId);
                                    if(produto == null){
                                        System.out.println("Produto não encontrado!");
                                        break;
                                    }

                                    System.out.print("Quantidade: ");
                                    int qtd = sc.nextInt();
                                    sc.nextLine();

                                    ItemPedido item = new ItemPedido(produto, qtd, produto.getPrecoProduto());

                                    pedidoDAO.salvarItemAvulso(item, pedidoId);
                                    break;

                                case 3:
                                    System.out.print("ID do pedido: ");
                                    int idPedidoBusca = sc.nextInt();
                                    sc.nextLine();

                                    List<ItemPedido> itens = pedidoDAO.buscarItensDoPedido(idPedidoBusca);

                                    if(itens.isEmpty()){
                                        System.out.println("Pedido vazio ou não encontrado.");
                                    } else {
                                        double total = 0;
                                        System.out.println("\n--- Itens do Pedido " + idPedidoBusca + " ---");
                                        for(ItemPedido i : itens){
                                            System.out.println(i);
                                            total += i.getPrecoUnitario() * i.getQuantidadeDeProdutos();
                                        }
                                        System.out.println("-------------------------------------");
                                        System.out.println("TOTAL DO PEDIDO: R$" + total);
                                    }
                                    break;

                                case 4:
                                    System.out.print("ID do pedido a ser removido: ");
                                    int idDeletePedido = sc.nextInt();
                                    sc.nextLine();

                                    pedidoDAO.deletar(idDeletePedido);
                                    break;

                                case 0:
                                    System.out.println("Voltando...");
                                    break;

                                default:
                                    System.out.println("Opção inválida.");
                            }

                        } catch (Exception e){
                            System.out.println("ERRO: " + e.getMessage());
                        }
                    }
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }
}