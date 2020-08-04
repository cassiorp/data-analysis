 #data-analysis:

O projeto foi iniciado com Spring Boot, configurado com Java 8 e Gradle como gerenciador de dependências.
 
##Como executar:

Para usar o data-analysis você precisar baixar ou clonar esse repositório entrar na pasta do projeto e executar o comando java -jar ./data-analysis-0.0.1-SNAPSHOT.jar ou importar na sua IDE de preferência e executar o método main. 

##Como utilizar:

Após a execução do projeto será criado automaticamente dois diretórios no seu computador
%Homepath%/data/in e %Homepath%/data/out. Dentro do diretório de entrada (in) você poderá adicionar três tipos de dados em arquivos no formato .dat.

- Vendedor: 001çCPFçNameçSalary 
- Cliente:  002çCNPJçNameçBusiness Area
- Venda: 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name 

###Sobre o processamento dos dados:

O código verificador dos vendedores será o CPF, a o cadastrar cada vendedor será verificado se já existe outro vendedor com o mesmo cpf, se houver será lançado um log informando e o vendedor não será processado. O código verificador dos clientes será o CNPJ, e a regra será a mesma que a dos vendedores. As vendas terão um id como identificador, e seguindo o formato terá um vendedor ao fim da linha. Esse vendedor será verificado entre todos os vendedores e será retornado a primeira ocorrência do nome, caso não haja nenhum vendedor com o nome correspondente ou o id já esteja cadastrado não será concluída a compra.

Após o processamento será gerado um relatório do diretório de saída (out) com o nome de processed.done.dat onde terá as seguintes informações, todos sobre o arquivo de entrada:
O número de clientes, número de vendedores,  id da melhor venda e o nome e cpf do pior vendedor. Caso haja vendedores sem venda será retornado será retornado a primeira ocorrência de vendedor sem vendas.
