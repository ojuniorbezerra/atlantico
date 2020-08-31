# atlantico
Projeto do teste do atlântico.

## Usage

```git
## Download Project
git clone 
cd atlantico
```

# start containers
```docker
docker-compose up -d

Por não ter um gerenciamento de dependencia é preciso executar:
docker-compose restart atlantico-eureka
docker-compose restart atlantico-email atlantico-api
```

## Aplications

# RabbitMq

http://localhost:15672
user: guest
password: guest


# Redis
Caso queira ver as chaves
docker exec -it "nome-container" redis-cli
KEYS *


# atlantico-server
Servidor de configuração usando eureka
Todas as config dos servidores estão sendo acessadas do meu github pessoal.
https://github.com/ojuniorbezerra/atlantico-config

Actuator do atlantico-server
http://localhost:9090/actuator

Format
http://localhost:9090/{file-server}/{profile}

FILES:
http://localhost:9090/atlantico-api-server/prod
http://localhost:9090/atlantico-api-server/default
http://localhost:9090/atlantico-email-server/default


# atlantico-email
Servidor consumer do rabbitmq para envio de email.
Servidor vai mostrar erro no log até ser criado a queue.

http://localhost:9093/actuator

# atlantico-eureka
Servicor com o monitoramento dos seridores
As vezes o servidor fica sem conseguir se auto registrar, precisa da um reload no container.
docker restart atlantico-eureka

http://localhost:9091/
http://localhost:9091/actuator


# atlantico-api
Servidor de autenticação e crud de usuário usando oauth2.
Eu iria criar outro servidor só com a função de autenticação, seria o atlantico-auth.
Não foi criado pelo curto período e também porque não foi solicitado a separação.

Através das migrations já foram criado os seguintes acesso:
Usuário: admin@admin.com password: 123456 authorise: admin, user
Usuário: user1@user.com password: 123456 authorise: admin
Usuário: user2@user.com password: 123456 authorise: user

Postman

Swagger
Para usar é preciso se autenticar através do botaão "Authorize" no canto superior direito e deve escolhe o tipo de recurso que vai usar "read" ou "write".
login e senha é dos usuários cadastrados.
http://localhost:9094/swagger-ui.html

H2
Para agilidade do processo de desenvolvimento usei o banco a h2, facilmente poderia ter usado outros bancos mas o prazo foi curto.
http://localhost:9094/h2


Email RabbitMQ
Pode ser feito pelo swagger a requisição.

# atlantico-web
Foi o que menos implementei por questão de tempo.

http://localhost:4200
