# connect - API Backend (CRM)

<br />

<div align="center">
   <img src="https://i.imgur.com/D0AwwmT.png" title="source: imgur.com" /> 
</div>

<br /><br />

## Equipe Desenvolvedora

* [**Lucas Manrick Teodoro da Fonseca**](https://github.com/lucasmanrick)
* [**Luiz Henrique dos Santos**](https://github.com/luizsantos7)
* [**Pablo Casagrande**](https://github.com/Pablo-Casagrande)
* [**Carlos Moroni**](https://github.com/carlosmoronisud)
* [**Bruno Alves**](https://github.com/BrunoAlves-tech)
* [**Natan Macedo**](https://github.com/natanmac)
* [**Murilo Mattos**](https://github.com/Matttosz)

---

## 1. Descrição

O **Connect** é um sistema (API) desenvolvido para otimizar a gestão de informações de clientes dentro da organização. Sua principal função é armazenar e organizar dados essenciais, como informações pessoais, histórico de interações e perfil de consumo, proporcionando uma visão completa para melhorar o relacionamento e atendimento ao cliente.

---

## 2. Sobre esta API

A API desenvolvida atua como o **núcleo** da aplicação **Connect**, realizando a gestão eficiente de dados dos clientes, oportunidades e usuários. Com um conjunto completo de operações CRUD (Create, Read, Update, Delete), ela permite que as informações trafeguem pelo sistema de maneira organizada e segura, garantindo que os setores de vendas, marketing e suporte tenham total controle sobre os registros e possam tomar decisões baseadas em dados.

### 2.1. Principais Funcionalidades

**🔍 CONSULTAS (GET)**

1. **Todos os clientes** — permite consultar todos os clientes registrados no sistema.
2. **Cliente pelo ID** — permite consultar um cliente específico com base em seu ID (identificador único).
3. **Cliente pelo nome** — permite consultar um cliente pelo seu nome.
4. **Todas as oportunidades** — permite consultar todas as oportunidades de negócio.
5. **Oportunidade pelo ID** — permite consultar uma oportunidade específica.
6. **Todos os usuários** — permite consultar todos os usuários que utilizam o sistema.

   <br>

**✏️ REGISTROS (POST)**

1. **Cadastro de cliente** — permite o cadastro de novos clientes.
2. **Cadastro de oportunidade** — permite o registro de novas oportunidades de negócio.
3. **Cadastro de usuário** — permite adicionar novos usuários ao sistema.

**🔄 ATUALIZAÇÕES (PUT)**

1. **Atualizar dados de um cliente pelo ID** — permite a atualização de dados de um cliente através de seu ID.
2. **Atualizar dados de uma oportunidade pelo ID** — permite a atualização de informações de uma oportunidade.
3. **Atualizar dados de um usuário pelo ID** — permite a atualização de dados de um usuário.

**🗑️ DELETAR (DELETE)**

1. **Excluir um cliente pelo ID** — permite remover um cliente do sistema com base em seu ID.
2. **Excluir uma oportunidade pelo ID** — permite remover uma oportunidade do sistema.
3. **Excluir um usuário pelo ID** — permite remover um usuário do sistema.

---

## 3. Diagrama de Classes

Adicione a imagem do Diagrama de Classes (se houver):

<div align="center">
   <img src="https://github.com/user-attachments/assets/18b74884-260e-4dad-9f9b-60f0895608cf" alt="Diagrama de Classe"/>
</div>

---

## 4. Tecnologias Utilizadas

| Item                          | Descrição |
| ----------------------------- | --------- |
| **Servidor**                  | JAVA      |
| **Linguagem de Programação**  | JAVA      |
| **Framework**                 | SPRING    |
| **ORM**                       | HIBERNATE |
| **Banco de Dados Relacional** | MySQL     |

---

## 5. Configuração e Execução

1. Clone o repositório:

   ```bash
   git clone https://github.com/Grupo05-Java82/crm_connect.git
   ```
