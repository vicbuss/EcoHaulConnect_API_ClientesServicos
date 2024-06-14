# EcoHaulConnect API ClientesServicos

## Como testar:

### Configurações de varíaveis de ambiente:

1. Para rodar o serviço localmente, é preciso configurar algumas variáveis de ambiente:
- DB_URL -> A url de um Banco de Dados Oracle
- DB_USERNAME -> O nome de usuário de um Banco de Dados Oracle
- DB_PWD -> A senha de um Banco de Dados Oracle
- TOKEN_SECRET -> Uma string para gerar o token JWT

As variáveis de ambiente podem ser setadas no seu próprio sistema, ou você pode usar uma feature de sua IDE para passar as variáveis para a aplicação.

No Intellij é possível fazê-lo em Application -> Edit Configurations -> Environment variables 

2. Rode a API e ela estará disponível na porta 8080.

3. Se as migrations falharem, pode ser preciso ir até o seu banco de dados e deletar as tabelas criadas, incluindo a tabela de migrations do flyway e tentar novamente.

4. A aplicação está protegida com Spring Security, então um token JWT válido é necessário para as requisições.
- Use a rota `/auth/signup` para registrar um usuário e senha com o corpo: `{login: {string}, senha: {string}}`
- Use a rota `/auth/login` para fazer login com o usuário e senha cadastrados e receber o token JWT.
- Para as demais requisições, passe o token no Header no parâmetro Authorization. Tentativas de acessar os demais endpoints sem um token válido retornarão código 403.
