package Model;

public class Cliente {

    private int clienteID;
    private String nomeCliente;
    private String sobrenomeCliente;
    private String emailCliente;

    public Cliente(String nomeCliente, String sobrenomeCliente, String emailCliente){
        setNomeCliente(nomeCliente);
        setSobrenomeCliente(sobrenomeCliente);
        setEmailCliente(emailCliente);
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setNomeCliente(String nomeCliente) {
        if(nomeCliente == null || nomeCliente.trim().length() < 3){
            throw new IllegalArgumentException("O nome não deve ser vazio e deve possuir mais de 3 caractéres!");
        }
        this.nomeCliente = nomeCliente.trim();
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setSobrenomeCliente(String sobrenomeCliente) {
        if(sobrenomeCliente == null || sobrenomeCliente.trim().length() < 3){
            throw new IllegalArgumentException("O sobrenome não deve ser vazio e deve possuir mais de 3 caractéres!");
        }
        this.sobrenomeCliente = sobrenomeCliente.trim();
    }

    public String getSobrenomeCliente() {
        return sobrenomeCliente;
    }

    public void setEmailCliente(String emailCliente) {
        if(emailCliente == null ||!emailCliente.matches("^[\\w.+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
            throw new IllegalArgumentException("Email inválido!");
        }
        this.emailCliente = emailCliente.trim().toLowerCase();
    }
    
    public String getEmailCliente() {
        return emailCliente;
    }

    @Override
    public String toString(){
    return "ID: " + clienteID +
           " | Nome: " + nomeCliente +
           " | Sobrenome: " + sobrenomeCliente +
           " | Email: " + emailCliente;
    }
    
}
