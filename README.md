#  Projeto Vet

API REST desenvolvida para gerenciamento de uma clínica veterinária, permitindo o controle de tutores, pets, consultas, vacinas, histórico de saúde, clínicas e aplicações de vacina.

---

##  Sobre o projeto

O **Projeto Vet** foi desenvolvido com o objetivo de simular um sistema backend para uma clínica veterinária, aplicando conceitos de:

- Arquitetura em camadas
- API REST
- Persistência com JPA
- DTOs e Projections
- Paginação
- Cache
- Documentação com Swagger
- Banco em memória H2

---

##  Tecnologias utilizadas

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- Spring Cache
- H2 Database
- Lombok
- Swagger / OpenAPI
- Maven

---

##  Estrutura do projeto

```bash
src/main/java/br/com/projetoVet/projeto_vet/
│
├── config/         # Configurações (Swagger)
├── control/        # Controllers da API
├── dto/            # Objetos de transferência de dados
├── model/          # Entidades
├── projection/     # Projeções
├── repository/     # Repositórios JPA
├── service/        # Regras de negócio
└── validations/    # Validações customizadas
```

---

##  Entidades principais

O sistema possui as seguintes entidades:

- Tutor
- Pet
- Consulta
- Vacina
- Aplicacao_vacina
- Historico_saude
- Clinica

---

##  Configuração do projeto

### 1. Clonar repositório

```bash
git clone <url-do-repositorio>
cd projeto-vet
```

---

### 2. Rodar aplicação

Usando Maven:

```bash
./mvnw spring-boot:run
```

ou no Windows:

```bash
mvnw.cmd spring-boot:run
```

---

## 🌐 Porta da aplicação

A aplicação roda em:

```bash
http://localhost:8083
```

---

## Banco de dados H2

Acesso ao console:

```bash
http://localhost:8083/h2-console
```

### Credenciais:

```txt
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password:
```

---

## 📘 Swagger

Documentação interativa disponível em:

```bash
http://localhost:8083/swagger-ui.html
```

ou

```bash
http://localhost:8083/swagger-ui/index.html
```

---

##  Funcionalidades

✔ Cadastro de tutores  
✔ Cadastro de pets  
✔ Controle de consultas  
✔ Histórico médico dos pets  
✔ Gestão de vacinas  
✔ Registro de aplicação de vacinas  
✔ Cadastro de clínicas  
✔ Paginação de resultados  
✔ Cache para otimização  
