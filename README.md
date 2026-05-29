# 🛒 Sistema de Gerenciamento de Pedidos

Este é um sistema de gerenciamento de pedidos robusto e estruturado em camadas, desenvolvido em **Java** com persistência de dados em banco de dados relacional **MySQL** utilizando a API **JDBC** (Java Database Connectivity).

O projeto foi construído aplicando boas práticas de programação, padrões de projeto e conceitos de Orientação a Objetos aprendidos ao longo da minha formação técnica e prática.

---

## 🛠️ Tecnologias e Ferramentas Utilizadas

* **Linguagem:** Java
* **Banco de Dados:** MySQL
* **Conectividade:** JDBC (Java Database Connectivity)
* **IDE utilizada:** VS Code

---

## 📐 Arquitetura do Projeto

O sistema foi modularizado utilizando o padrão **DAO (Data Access Object)**, garantindo a separação de responsabilidades entre as regras de negócio e o acesso ao banco de dados:

* 📁 `db`: Gerenciamento da conexão com o banco de dados via `ConnectionFactory`.
* 📁 `Model`: Classes de entidade que representam o modelo de dados (`Cliente`, `Produto`, `Pedido`, `ItemPedido`).
* 📁 `Dao`: Classes responsáveis por executar as operações de CRUD (Create, Read, Update, Delete) no MySQL.
* 📁 `Main`: Ponto de entrada do sistema onde os fluxos são executados e testados.

---

## 🚀 Como Executar o Projeto

1. Clone este repositório para a sua máquina local:
   ```bash
   git clone [https://github.com/MateusL27/sistema-gerenciamento-pedidos.git](https://github.com/MateusL27/sistema-gerenciamento-pedidos.git)
