# Projeto API Marca Ponto

## A documentação dos endpoints da API foi criada com springdoc-openapi (Swagger-UI) e está disponível no link abaixo: 
<>
*É necessário subir a aplicação primeiro para acessar a documentação da API*

## Descrição Básica da API marca-ponto

## Usuário
EndPoints da API do Usuário

* #### GET
      Usuário por Id
      /api/usuarios/{id} 
      

* #### GET
      /api/usuarios - Obtém todos os usuários cadastrados
      
* #### POST
      /api/usuarios - Adiciona novo usuário
      
* #### PUT
      /api/usuarios/{id} - Atualiza informações do usuário

**Corpo da requisição(Request Body)**
```json
{
  "nomeCompleto": "Dorime",
  "cpf": "123.456.789-0",
  "email": "thi.carsil@gmail.com"
}
```

**Exemplo de Request Body**
```json
{
  "nomeCompleto": "Dorime Ameno",
  "cpf": "000.000.000-00",
  "email": "dorime@gmail.com",
  "dataCadastro": "2020-02-20T23:00:00"
}
```

---

## Marcação de Ponto
API Controle de Ponto

Todos os pontos registrados dos Usuarios
* #### GET
      /api/ponto/lista 

Restra a marcação de um novo ponto
* #### POST
      /api/ponto - Adiciona novo registro de ponto

**Exemplo do Request Body**
```json
{
  usuario: {
    "id": 1
           }
}
```

      
      
 # Teste Sistema de Ponto

Swagger para visualização e testes da API: http://<span></span>localhost:[port]/swagger-ui.html

Na configuração padrão da aplicação, caso a porta não seja alterada no application.properties: [http://localhost:9876/swagger-ui.html](http://localhost:9876/swagger-ui.html)
