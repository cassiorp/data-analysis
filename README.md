# data-analysis

O projeto foi iniciado com Spring Boot, configurado com Java 8 e Gradle como gerenciador de dependências.
 
Para usar o data-analysis basta importar na sua IDE de preferência, executar o método main e adicionar os arquivos em formato .dat no arquivo de entrada: %Homepath%/data/in. Caso aconteça erros no processamento dos dados será gerado logs no console informando qual arquivo e linha o erro aconteceu.

Após a entrada de cada arquivo será gerado um relatório na pasta %Homepath%/data/out, com o formato “processed.done.dat” informando a quantidade de vendedores, quantidade de clientes, a melhor venda e o pior vendedor. Todos do arquivo de entrada.
