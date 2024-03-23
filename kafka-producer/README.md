# Como rodar o ambiente de lista de tarefas com Docker

Este repositório contém um ambiente Dockerizado para uma aplicação de lista de tarefas, utilizando diversas ferramentas como Kafka, Zookeeper, MySQL e Nginx. Abaixo estão as instruções para configurar e executar o ambiente.

## Requisitos

Certifique-se de ter o Docker e o Docker Compose instalados em seu sistema antes de prosseguir.

- Docker: [Instalação do Docker](https://docs.docker.com/get-docker/)
- Docker Compose: [Instalação do Docker Compose](https://docs.docker.com/compose/install/)

## Passos para executar

1. **Clone o repositório:**

```
git clone <url_do_repositorio>
```


2. **Navegue até o diretório do projeto:**

```
cd <nome_do_diretorio>
```

3. **Execute o Docker Compose:**
```
docker-compose up -d
```

Este comando irá construir e iniciar todos os serviços definidos no arquivo `docker-compose.yml` em segundo plano (flag `-d`).

4. **Verifique se os contêineres estão em execução:**
```
docker ps
```


Isso deve listar todos os contêineres em execução, incluindo `todo-list`, `zookeeper`, `kafka`, `kafdrop`, `mysql` e `nginx`.

5. **Acesse o aplicativo de lista de tarefas:**

- O aplicativo está disponível em http://localhost:8080 para `todo-list`, http://localhost:8081 para `todo-list1` e http://localhost:8082 para `todo-list2`.

6. **Acesse o Kafdrop para monitorar o Kafka:**

- O painel Kafdrop está disponível em http://localhost:10000.


6. **Acesse o servidor Nginx:**

- O servidor Nginx está disponível em http://localhost:7070.

## Parando e Removendo os Contêineres

Para parar e remover os contêineres, você pode executar:

```
docker-compose down
```

Este comando irá parar e remover todos os contêineres definidos no arquivo `docker-compose.yml`.

Agora você deve ter o ambiente da lista de tarefas em execução localmente em seu sistema. Se encontrar problemas durante o processo de execução, verifique os logs dos contêineres para obter informações adicionais sobre quaisquer erros que possam ter ocorrido.


