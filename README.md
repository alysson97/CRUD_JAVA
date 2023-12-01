Projeto CRUD em Java usando Swing
Este é um projeto simples de CRUD (Create, Read, Update, Delete) em Java utilizando a biblioteca Swing para a interface gráfica. O projeto consiste em um sistema de gerenciamento de produtos com funcionalidades básicas.

📁 Arquivos do Projeto
1. Main
O arquivo Main.java é a classe principal que contém a interface gráfica do sistema. Ele utiliza o Java Swing para criar uma aplicação de desktop com funcionalidades CRUD para produtos.

Métodos:

🔄 refreshTable(): Atualiza a tabela de produtos.
➕ addProduct(): Adiciona um novo produto.
✏️ updateProduct(): Atualiza um produto existente.
❌ deleteProduct(): Exclui um produto.
2. DBConnection
O arquivo DBConnection.java é responsável pela conexão com o banco de dados MySQL. Ele utiliza JDBC para estabelecer uma conexão e fornece métodos para configurar as informações de conexão.

Métodos:

🔄 doConnection(): Estabelece a conexão com o banco de dados.
3. ProductDAO
O arquivo ProductDAO.java é a classe de acesso a dados que realiza operações no banco de dados relacionadas aos produtos. Ele utiliza a conexão fornecida pela classe DBConnection.

Métodos:

🔄 getAllProducts(): Obtém todos os produtos do banco de dados.
➕ addProduct(Product product): Adiciona um novo produto ao banco de dados.
✏️ updateProduct(Product product): Atualiza as informações de um produto no banco de dados.
🔍 getProductById(int productId): Obtém um produto pelo ID.
❌ deleteProduct(int productId): Exclui um produto do banco de dados.
4. Product
O arquivo Product.java contém a definição da classe Product, que representa a estrutura de dados de um produto.

Atributos:

idProduto, fabricante, nome, marca, modelo, idCategoria, descricao, unidadeMedida, largura, altura, profundidade, peso, cor: Atributos que representam as características de um produto.
🚀 Utilização
Compile e execute a classe Main.java para iniciar a aplicação.
Utilize os botões na parte inferior para adicionar, atualizar, excluir ou atualizar a tabela de produtos.
Observação: Antes de executar o projeto, certifique-se de ter um servidor MySQL em execução.

📄 Configuração do Banco de Dados
Para configurar o banco de dados, execute o script SQL fornecido no arquivo lojinha.sql para criar as tabelas necessárias.
