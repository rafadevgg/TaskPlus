# TaskPlus API

API REST para gerenciamento de tarefas, desenvolvida com Spring Boot e PostgreSQL.

## Tecnologias

- Java 17
- Spring Boot 3.5.6
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Configuração

### Pré-requisitos
- JDK 17+
- PostgreSQL
- Maven

### Instalação

1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/taskplus-api.git
```

2. Crie o banco de dados no PostgreSQL
```sql
CREATE DATABASE taskplus_api;
```

3. Configure as credenciais em `application.properties` se necessário
```properties
spring.datasource.username=postgres
spring.datasource.password=postgres
```

4. Execute o projeto
```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`

## Endpoints

### Tarefas
- `POST /api/tarefas` - Criar tarefa
- `GET /api/tarefas` - Listar todas
- `GET /api/tarefas/{id}` - Buscar por ID
- `PUT /api/tarefas/{id}` - Atualizar
- `DELETE /api/tarefas/{id}` - Deletar
- `PUT /api/tarefas/concluir/{id}` - Concluir tarefa
- `PUT /api/tarefas/adiar/{id}` - Adiar tarefa
- `PUT /api/tarefas/{id}/categoria/{idCategoria}` - Atribuir categoria

### Categorias
- `POST /api/categorias` - Criar categoria
- `GET /api/categorias` - Listar todas
- `GET /api/categorias/{id}` - Buscar por ID
- `PUT /api/categorias/{id}` - Atualizar
- `DELETE /api/categorias/{id}` - Deletar
