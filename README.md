# EcoHaulConnect API ClientesServicos

## Como testar:

### Configurações de varíaveis de ambiente:

1. Para rodar o serviço localmente, crie um arquivo chamado `secrets.yml` na pasta `src/resources`
2. Nesse arquivo, você vai inserir os segredos de conexão com o banco de dados e apis externas, como no exemplo:

```yml
spring:
	datasource:
		url: urldobanco.com
		username: nomedeusuario
		password: senhadobanco
```
3. Esse arquivo está sendo ignorado no `.gitingore`, para que os segredos de configurações não sejam commitados.
