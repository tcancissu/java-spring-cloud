# java-spring-cloud
Arquitetura de microserviços com Java, Spring Cloud e AWS CDK. 

- Usar a dependência do ModelMapper para converter os dados do model para o DTO e vice versa;
- Criação de uma classe de configuração, usando as anotações @Configuration e @Bean do Spring, para que possamos usar o ModelMapper na classe de serviço com injeção de dependência;
- O conceito de service discovery, que é o mecanismo de descoberta do endereço do microsserviço pelo nome, desacoplando um microsserviço do outro pelo IP ou porta;
- O conceito de service registry, que é um servidor central onde os microsserviços ficam cadastrados para permitir a descoberta;
- Implementação do service registry através do Eureka Server, que faz parte do conjunto de ferramentas do Spring Cloud;
- Configuração dos serviços com o Eureka Client, para que consigam fazer corretamente o self registration (auto-registro);
- Incluir um API Gateway na arquitetura do projeto, para criar um ponto único de acesso à nossa aplicação;
- Alterar a forma com que os microsserviços são acessados, passando o endereço do Gateway e o nome do microsserviço;
- Criar um método que retorna a porta na qual a instância que está recebendo a requisição está sendo executada;
- Fazer a integração do Gateway com o Eureka, balanceando às requisições entre as instâncias que estão disponíveis;
- Utilizar o Open Feign do Spring Cloud para conseguir projetar a comunicação síncrona entre microsserviços;
https://resilience4j.readme.io/docs/getting-started
- O conceito de circuit breaker, que interrompe um chamado a um serviço quando as requisições com falha de comunicação atingirem um número específico;
- Implementar o fallback, que é a forma com que um microsserviço vai tratar a falha de comunicação, ou seja, uma estratégia para que a inoperabilidade de um serviço não afete o outro;
- Usar ferramentas para IaC, como Cloudformation e AWS CDK e iniciar um projeto de infraestrura na AWS usando Java;
https://docs.aws.amazon.com/cdk/v2/guide/getting_started.html
- Usar o AWS ECS (Elastic Container Service) e como funciona estruturalmente a hierarquia de container, definição de tarefa, serviço, cluster e VPC no processo de deploy de uma aplicação na AWS;
- Criar e a fazer o deploy das stacks de VPC, Cluster e Service pelo CDK, disponibilizando uma aplicação (a partir de uma imagem do Docker Hub) num endereço público da AWS;
- Definir um serviço já com Load Balancer, para que haja distribuição de carga entre as tarefas iguais em execução simultânea no cluster.
- Criar e configurar uma stack RDS para instanciar um banco de dados MySQL pelo Cloud Development Kit, criando parâmetros Cloudformation (CfnParameter) para incluir a senha do banco no momento do deploy, outputs (CfnOutput) para expor o endpoint do banco e a senha, de forma que a nossa aplicação possa utilizá-los e as variáveis de ambiente para que a aplicação Spring consiga se comunicar com o banco na AWS;
- Criar um repositório para subir uma imagem para esse ambiente no ECR (Elastic Container Registry), mantendo o repositório privado, com mais controle de quem tem permissão de acesso à imagem;
- Utilizar o AWS Cloudwatch, que atua como um concentrador de todos os logs, configurando o Log Group na stack para conseguir visualizar os logs de todas as instâncias de uma tarefa de forma aglutinada;
- Configurar métricas para o Auto Scaling do serviço, indicando qual deve ser o número mínimo e máximo de instâncias, bem como os critérios para criar ou destruir uma instância.


Principais comandos da AWS CDK
	cdk bootstrap aws://123456789012/us-east-1
	cdk init --language java
	cdk list
	cdk deploy Vpc
	cdk deploy --parameters Rds:senha=12345678 Rds
	cdk destroy Cluster


Comando para subir várias instancias de um microserviço
& "D:\Thiago\workspace\alura\java-myfood\pagamentos\mvnw.cmd" spring-boot:run -f "D:\Thiago\workspace\alura\java-myfood\pagamentos\pom.xml"
