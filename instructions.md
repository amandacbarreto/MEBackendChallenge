# Mercado Eletrônico - Backend Challenge

 ## Pré-requisitos
 - [ ] JDK 11;
 - [ ] Maven;

 ## Primeios passos

1. Se necessário, instale [Maven](http://maven.apache.org/install.html)

2. Faça o `clone` do projeto com o seguinte comando: 

```bash
git clone https://github.com/amandacbarreto/MEBackendChallenge.git
```

3. Na pasta raiz do projeto, inicialize o servidor através do comando 
``` 
mvn spring-boot:run
``` 

## Utilizando a aplicação

Utilize o projeto através de alguma aplicação cliente (Ex: Postman).

### Endpoint - Pedido (GET, POST, PUT, DELETE)

O Endpoint de Pedido está disponível em:

```
http://localhost:8080/api/pedido
```
- Criar pedido
```http request
POST http://locahost:8080/api/pedido
content-type: application/json
{
  "pedido":"123456",
  "itens": [
      {
        "descricao": "Item A",
        "precoUnitario": 10,
        "qtd": 1
      },
      {
        "descricao": "Item B",
        "precoUnitario": 5,
        "qtd": 2
      }
  ]
}
```

- Listar todos os pedidos
```http request
GET http://locahost:8080/api/pedido
```

- Buscar um pedido
```http request
GET http://locahost:8080/api/pedido/{pedido}
```

- Alterar um pedido
```http request
PUT http://locahost:8080/api/pedido/{pedido}
content-type: application/json
{
  "pedido": "{pedido}",
  "itens": [
      {
        "descricao": "Item A",
        "precoUnitario": 10,
        "qtd": 1
      },
      {
        "descricao": "Item B",
        "precoUnitario": 5,
        "qtd": 2
      }
  ]
}
```

- Deletar um pedido
```http request
DELETE http://locahost:8080/api/pedido/{pedido}
```

#### Endpoint - Mudança de Status de Pedido

O Endpoint de Mudança de Status de Pedido está disponível em:

```
http://localhost:8080/api/status
```

- Informar status de um pedido


```http request
POST http://localhost:8080/api/status/
content-type: application/json
{
  "status":"XXX",
  "itensAprovados":0,
  "valorAprovado":0,
  "pedido":"XXX"
}
```

#### Database H2

O banco de dados em memória H2 também pode ser consultado através do endereço:

```
http://localhost:8080/h2-console
```

Utilize os seguintes dados:

- User Name:  **sa** 
- Não possui senha.


