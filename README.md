# E-Commerce API 🧺

Esta é uma API de e-commerce desenvolvida em Java, utilizando Spring Boot. A API permite operações CRUD (Create, Read, Update, Delete) para usuários, produtos, categorias e pedidos.

## Funcionalidades 🛠️

- **Usuários:** CRUD completo para gerenciar usuários, incluindo registro sem autenticação. 👤
- **Produtos:** CRUD de produtos, incluindo upload de imagens. 🛒
- **Categorias:** CRUD de categorias de produtos. 📂
- **Pedidos:** CRUD de pedidos e gerenciamento de carrinho de compras. 🛍️
- **Autenticação e Autorização:** Implementação de autenticação JWT para proteger endpoints, exceto o registro de usuários. 🔐

## Tecnologias Utilizadas 🚀

- Java ☕
- Spring Boot 🏗️
- JWT para autenticação 🔑
- Banco de dados MySQL 💾

## Como Rodar o Projeto 🖥️

1. **Clone o Repositório** 📥
   - Use o comando `git clone https://github.com/Maycon-Pereira/E-Commerce-API.git` para clonar o repositório em sua máquina local.

2. **Instale as Dependências** 🔄
   - Navegue até o diretório do projeto clonado e execute `mvn install` para baixar e instalar todas as dependências necessárias.

3. **Configure o Banco de Dados** ⚙️
   - Configure o banco de dados no arquivo de configuração (`application.properties` ou `application.yml`) conforme as instruções no projeto.

4. **Inicie o Servidor** 🚀
   - **Via Linha de Comando:**
     - Execute o comando `mvn spring-boot:run` para iniciar o servidor Spring Boot.

   - **No Eclipse:**
     - Importe o projeto como um projeto Maven:
       - Vá em `File` > `Import` > `Existing Maven Projects`.
       - Selecione o diretório do projeto e conclua a importação.
     - Clique com o botão direito no arquivo principal da aplicação (normalmente anotado com `@SpringBootApplication`) e selecione `Run As` > `Spring Boot App`.

   - **No IntelliJ IDEA:**
     - Importe o projeto como um projeto Maven:
       - Vá em `File` > `Open` e selecione o diretório do projeto.
     - Clique com o botão direito no arquivo principal da aplicação (normalmente anotado com `@SpringBootApplication`) e selecione `Run 'ApiApplication.main()'`.

   - **No Visual Studio Code:**
     - Instale a extensão [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack).
     - Abra o projeto na raiz do diretório do projeto.
     - Use o comando `./mvnw spring-boot:run` no terminal integrado para iniciar 🧺o servidor.

Isso iniciará o servidor localmente e a API estará disponível para uso em `http://localhost:8080` (ou outra porta configurada).

## Contribuição 🤝

Para contribuir, faça um fork do projeto, crie uma branch para sua feature ou bugfix, e envie um pull request.
