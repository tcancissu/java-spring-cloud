Arquitetura de microserviços com Java, Spring Cloud, RabbitMQ e AWS CDK.

Java e Spring Cloud
- Usando o conceito de service discovery, que é o mecanismo de descoberta do endereço do microsserviço pelo nome, desacoplando um microsserviço do outro pelo IP ou porta. E o conceito de service registry, que é um servidor central onde os microsserviços ficam cadastrados para permitir a descoberta;
- Implementei o service registry através do Eureka Server, que faz parte do conjunto de ferramentas do Spring Cloud. E configurei os microserviços com o Eureka Client, para que consigam fazer corretamente o self registration (auto-registro);
- Inclui um API Gateway na arquitetura do projeto, para criar um ponto único de acesso à nossa aplicação, alterando a forma com que os microsserviços são acessados, passando o endereço do Gateway e o nome do microsserviço;
- Fiz a integração do Gateway com o Eureka, balanceando às requisições entre as instâncias que estão disponíveis;
Comando para subir várias instancias de um microserviço:
& "D:\Thiago\workspace\alura\java-myfood\pagamentos\mvnw.cmd" spring-boot:run -f "D:\Thiago\workspace\alura\java-myfood\pagamentos\pom.xml"
- Utilizei o Open Feign do Spring Cloud para conseguir projetar a comunicação síncrona entre microsserviços;
https://resilience4j.readme.io/docs/getting-started
- Usando o conceito de circuit breaker, que interrompe um chamado a um serviço quando as requisições com falha de comunicação atingirem um número específico;
- Implementei o fallback, que é a forma com que um microsserviço vai tratar a falha de comunicação, ou seja, uma estratégia para que a inoperabilidade de um serviço não afete o outro;

AWS CDK (Cloud Developer Kit)
- Usando ferramentas para IaC, como Cloudformation e AWS CDK, iniciei um projeto de infraestrura na AWS usando Java;
https://docs.aws.amazon.com/cdk/v2/guide/getting_started.html
- Usei o AWS ECS (Elastic Container Service) e como funciona estruturalmente a hierarquia de container, definição de tarefa, serviço, cluster e VPC no processo de deploy de uma aplicação na AWS;
- Criei e fez o deploy das stacks de VPC, Cluster e Service pelo CDK, disponibilizando uma aplicação (a partir de uma imagem do Docker Hub) num endereço público da AWS;
- Definir um serviço já com Load Balancer, para que haja distribuição de carga entre as tarefas iguais em execução simultânea no cluster.
- Criei e configurei uma stack RDS para instanciar um banco de dados MySQL pelo Cloud Development Kit, criando parâmetros Cloudformation (CfnParameter) para incluir a senha do banco no momento do deploy, outputs (CfnOutput) para expor o endpoint do banco e a senha, de forma que a nossa aplicação possa utilizá-los e as variáveis de ambiente para que a aplicação Spring consiga se comunicar com o banco na AWS;
- Criei um repositório para subir uma imagem para esse ambiente no ECR (Elastic Container Registry), mantendo o repositório privado, com mais controle de quem tem permissão de acesso à imagem;
- Usei o AWS Cloudwatch, que atua como um concentrador de todos os logs, configurando o Log Group na stack para conseguir visualizar os logs de todas as instâncias de uma tarefa de forma aglutinada;
- Configurei métricas para o auto scaling do serviço, indicando qual deve ser o número mínimo e máximo de instâncias, bem como os critérios para criar ou destruir uma instância.

Principais comandos da AWS CDK
	cdk bootstrap aws://123456789012/us-east-1
	cdk init --language java
	cdk list
	cdk deploy Vpc
	cdk deploy --parameters Rds:senha=12345678 Rds
	cdk destroy Cluster


RabbitMQ
- Executei o RabbitMQ a partir de um container Docker, configurando um arquivo docker-compose-yml;
- Inclui a dependência spring-boot-starter-amqp para utilizar recursos de mensageria baseado no protocolo AMQP. E configurei as propriedades do projeto para conexão com o RabbitMQ;
- Criei a classe de configuração com os beans para criação da fila e conexão com o RabbitMQ nos microserviços;
- Configurei os microserviços consumidores usando a anotação @RabbitListener para indicar a fila devo consumir mensagens;
- Usei a biblioteca Jackson2JsonMessageConverter para converter objetos para o formato JSON e enviá-los ou recebê-los como mensagem;
- Utilizei o RabbitMQ Simulator para projetar o modelo de implementação dos produtores, consumidores, tipos de exchanges e os bindings com as filas;
https://tryrabbitmq.com/
- Implementei uma exchange do tipo fanout, demonstrando o envio de uma mensagem para essa exchange e vendo o conteúdo ser direcionado para todas as filas de pedidos e avaliação que têm vínculo com ela, e com isso validei consumidores diferentes recebendo essa mesma mensagem.
- Implementei o tratamento de retentativas, incluindo no application.properties algumas configurações como: max-attempts, initial-interval, multiplier e max-interval;
- Usei O conceito de dead letter queues e dead letter exchanges, que são formas de direcionar mensagens “mortas” a outra fila para que possam ser processadas posteriormente, sem que ocorra a perda de mensagens ou travamento no fluxo.
- Movi manualmente as mensagens de uma fila DLQ para outra utilizando o plugin de shovel, que ativei através do terminal do container com o comando:
rabbitmq-plugins enable rabbitmq_shovel rabbitmq_shovel_management
- Subi várias instâncias do RabbitMQ e agrupei elas num cluster usando o Docker. Configurei políticas de alta disponibilidade para que seja possível a replicação das filas e mensagens caso uma das instâncias fique inoperante.