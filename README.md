# Pós Java - SOA

## Como executar os projetos com Spring Cloud:
- ***posjava1-eureka***
	Basta executar o arquivo principal que o servidor de descoberta será iniciado na porta ***'8761'***. Basta acessar no navegador http://127.0.0.1:8761
	
- ***posjava2-common***
	Basta executar o arquivo principal que a aplicação será  iniciada na porta ***'8200'***. Essa aplicacão não está disponibilizando nenhum recurso.
	
- ***posjava3-auth***
	Basta executar o arquivo principal que a aplicação será iniciada na porta ***'8300'***. Essa aplicação será acessada via o *gateway* criado na aplicação 'posjava6-zull'.
	Caso queira testar a autenticação sem utilizar o *gateway*, basta realizar um **POST** na url http://127.0.0.1:8300/auth
	contendo no corpo da requisição um json com o conteúdo: ***{"username":"admin", "password":"123"}***

- ***posjava4-api***
-> Para executar a aplicação utilizando uma porta padrão: basta configurar a porta no arquivo 'application.properties' e comentar as configurações no arquivo 'bootstrap.properties'.
-> Caso queira rodar mais de uma API informando a porta dinamicamente, basta executar o arquivo utilizando a opção Run Configuration e na Aba arguments adicionar a propriedade em VM arguments: -Dserver.port=8400
ou executar o arquivo jar gerado na pasta target da aplicação executando:
```sh 
	java -jar nome-do-jar-do-projeto-posjava4-api.jar --server.port=8400
```
   Caso queira testar a autenticação sem utilizar o *gateway* basta realizar um **GET** na url http://127.0.0.1:8400/genero

- ***posjava5-client***
Basta executar o arquivo principal que a aplicação será iniciada na porta ***'8500'***. 
Caso queira testar a autenticação sem utilizar o *gateway* basta realizar um **GET** na url http://127.0.0.1:8500/genero

- ***posjava6-zull***
Basta executar o arquivo principal que a aplicação será iniciada na porta ***'8600'***.
Por meio do proxy é possível acessar a aplicação de Autenticação, a API e o Client.
Caso queira testar a autenticação, basta realizar um **POST** na url http://127.0.0.1:8600/auth
	contendo no corpo da requisição um json com o conteúdo: ***{"username":"admin", "password":"123"}**, o resultado da requisição será um token, que poderá ser utilizado para acessar os recursos das outras aplicações.
	Agora, como o Proxy filtra as requisições e permite apenas usuários autenticados acessar as URLs das apis, para acessar a 'api' o usuário deve realizar um **GET** na url http://127.0.0.1:8600/api/genero , porém deve adicionar o token na requisição. Para isso basta adicionar um token do tipo Bearer antes de realizar a requisição.
 
 
## Descrição do conteúdo dos Projetos com Spring Cloud:

Spring Cloud: é um conjunto de ferramentas que prove uma série de padrões e frameworks para a acelerar a construções de aplicações distribuídas.

### 1 - posjava1-eureka
O projeto Eureka: é uma solução de *service discovery*, que em conjunto com outras ferramentas possibilita gerenciamento dinâmico e escalabilidade para as aplicações, o Eureka também faz do sub-projeto spring-cloud-netflix.
O projeto 'posjava1-eureka' contém o servidor de descoberta Eureka, para o servidor funcionar basta configurar o arquivo de proprieades: 'application.properties' e o arquivo principal da aplicação: 'Posjava1Application.java'.
```properties
#nome do servidor
spring.application.name=eureka-server
#porta do servidor, por padrão o eureka utiliza a 8761
server.port=8761
#por padrão o eureka server registra-se como cliente
#para evitar, setamos as propriedades abaixo como false
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

Para o eureka server ser habilitado basta a anotação '@EnableEurekaServer' estar presente no Arquivo PosJava1Application.java.
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Posjava1Application {

	public static void main(String[] args) {
		SpringApplication.run(Posjava1Application.class, args);
	}

}
```
### 2 - posjava2-common
Projeto com as classes de configuração do Spring Security. A autenticação será implementada no projeto 'posjava3-auuth' e o filtro dos usuários autorizados será realizado no projeto 'posjava6-zull'.
Ao executar essa aplicação também registra-se no servidor de descoberta, como pode ser observado no arquivo de configuração 'application.porterties':
```properties
#nome da aplicação registrada no servidor Eureka
spring.application.name=common-service
server.port=8200
#Endereço da aplicação Eureka
eureka.client.service-url.default-zone=http://localhost:8761/eureka
```
Além disso é necessário também a anotação '@EnableEurekaClient' no arquivo principal da aplicação.

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Posjava2CommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(Posjava2CommonApplication.class, args);
	}

}
```
Nessa aplicação na classe JwtConfig estão as configurações necessárias para criação do token do tipo ***jwt***.
```java
import org.springframework.beans.factory.annotation.Value;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtConfig {

	@Value("${security.jwt.uri:/auth/**}") // url que será utilizada para autenticação
	private String uri;
	
	@Value("${security.jwt.header:Authorization}") // header de autenticação
	private String header;
	
	@Value("${security.jwt.prefix:Bearer }") // prefixo adicionado ao enviar, e ao receber o token
	private String prefix;
	
	@Value("${security.jwt.expiration:#{24*60*60}}") // tempo de validade do token
	private long expiration;
	
	@Value("${security.jwt.secret:utfpr}") // senha utilizada para gerar o token
	private String secret;
}
```
### 3 - posjava3-auth
O projeto ***posjava3-auht*** é responsável pelo serviço de autenticação. Esse projeto utiliza o Spring Security para a autenticação do usuário. É gerado um ***Json Web Token*** (***jwt***) que será gerado caso ocorra uma autenticação com sucesso.

### 4 - posjava4-api
O projeto ***posjava4-api*** é uma api REST, desenvolvida com o framework Spring com end points retornando Gêneros, Produtoras, Séries e Episódios.

### 5 - posjava5-client
O projeto ***posjava5-client*** utiliza o Ribbon para Balanceamento de carga no lado cliente. Nesse projeto também é utilizado o Hystrix que é utilizado para tolerância à falhas. O Feign também está presente no projeto e é utilizado para auxiliar no consumo de outros Serviços.

### 6 - posjava6-zull
O Zuul é uma solução de roteamento dinâmico que possibilita monitoramento, resiliência e segurança para aplicações, que também pode ser encontrada no sub-projeto spring-cloud-netflix.
