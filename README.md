# Projeto Vet - Challenge 

Sistema web desenvolvido em **Java + Spring Boot** para gerenciamento de responsáveis, pets, veterinários, consultas e monitoramento de dados de sensores.

A aplicação integra **Spring Security**, **Oracle Database**, **Flyway**, **Thymeleaf** e uma arquitetura IoT baseada em **ESP32/Wokwi + MQTT + Node-RED**, permitindo que dados de sensores sejam persistidos no Oracle e visualizados pela aplicação.

---

## Estrutura do repositório

```text
Java-Challenge/
│
├── README.md
│
├── projeto-vet/
│   └── versão anterior do projeto
│
├── projeto-vet-v2/
│   ├── pom.xml
│   ├── mvnw
│   ├── mvnw.cmd
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   └── resources/
│       └── test/
│
```

A pasta `projeto-vet/` foi mantida como histórico da evolução do projeto.

A versão utilizada na entrega atual está em:

```text
projeto-vet-v2/
```

---

## 1. Objetivo do projeto

O projeto foi desenvolvido para representar uma plataforma de acompanhamento veterinário e monitoramento de pets.

O sistema permite:

- cadastro de responsáveis;
- cadastro de pets;
- criação automática de sensores para novos pets;
- recebimento e visualização de leituras IoT;
- cadastro de veterinários por um administrador;
- agendamento de consultas;
- visualização das consultas pelo veterinário responsável;
- finalização da consulta com observação clínica;
- controle de acesso baseado no perfil do usuário;
- versionamento do banco Oracle utilizando Flyway.

---

## 2. Arquitetura geral

```text
ESP32 / Wokwi
      │
      ▼
     MQTT
      │
      ▼
   Node-RED
      │
      ▼
Oracle Database
      │
      ▼
 Spring Boot
      │
      ▼
  Thymeleaf
      │
      ▼
    Usuário
```

O fluxo IoT é utilizado para registrar leituras de sensores associadas aos pets.

Exemplos de tópicos MQTT:

```text
pet/{idPet}/temperatura
pet/{idPet}/atividade
```

Para acompanhar todos os pets durante os testes:

```text
pet/#
```

---

## 3. Tecnologias utilizadas

- Java 17
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- Hibernate
- Spring Security
- Spring Validation
- Thymeleaf
- Oracle Database
- Flyway
- Maven
- BCrypt
- Node-RED
- MQTT
- HiveMQ Public Broker
- ESP32 / Wokwi
- Git
- GitHub

---

## 4. Organização da aplicação Java

A aplicação segue uma organização em camadas:

```text
src/main/java/br/com/fiap/projeto_vet_v2/
│
├── config/
├── control/
├── dto/
├── model/
├── repository/
├── security/
└── service/
```

### `config`

Contém configurações gerais da aplicação, incluindo:

```text
SecurityConfig.java
AdminInitializer.java
```

### `control`

Controllers responsáveis pelas rotas web:

```text
AdminController
AuthController
HomeController
ResponsavelController
VeterinarioController
```

### `dto`

Objetos utilizados para receber e validar dados dos formulários.

### `model`

Entidades JPA que representam as principais estruturas persistidas no banco.

### `repository`

Interfaces do Spring Data JPA utilizadas para comunicação com o Oracle.

### `security`

Implementação da autenticação dos usuários através do Spring Security.

### `service`

Camada que concentra as principais regras de negócio.

---

## 5. Perfis de usuário

A aplicação possui três perfis.

| Perfil | Função |
| --- | --- |
| `ADMIN` | cadastrar veterinários |
| `RESPONSAVEL` | cadastrar pets, visualizar sensores e agendar consultas |
| `VETERINARIO` | visualizar e finalizar consultas atribuídas ao seu perfil |

---

## 6. Spring Security

As rotas são protegidas de acordo com o perfil autenticado.

```text
/admin/**         -> ADMIN
/responsavel/**   -> RESPONSAVEL
/veterinario/**   -> VETERINARIO
```

Rotas públicas:

```text
/login
/cadastro
/css/**
/js/**
/img/**
```

As senhas cadastradas pela aplicação são armazenadas utilizando **BCrypt**.

O Spring Security também impede que um perfil acesse rotas destinadas a outro tipo de usuário.

---

## 7. Fluxo do administrador

O administrador acessa o sistema e realiza o cadastro dos veterinários.

Fluxo:

```text
ADMIN
  │
  ▼
Cadastro de veterinário
  │
  ├── dados pessoais
  ├── CRV
  ├── especialidade
  └── clínica
  │
  ▼
USUARIO + VETERINARIO
```

O veterinário cadastrado recebe o perfil:

```text
VETERINARIO
```

e passa a ter acesso às rotas:

```text
/veterinario/**
```

---

## 8. Fluxo do responsável

O responsável pode criar sua própria conta através da página pública de cadastro.

Após o login, pode:

```text
Cadastrar Pet
      │
      ▼
Visualizar Pet
      │
      ▼
Visualizar Sensores
      │
      ▼
Visualizar Leituras
      │
      ▼
Agendar Consulta
```

O cadastro público sempre cria um usuário do tipo:

```text
RESPONSAVEL
```

---

## 9. Cadastro de pet e criação automática de sensores

Ao cadastrar um novo pet, a aplicação também cria automaticamente dois sensores associados ao animal.

```text
PET
 │
 ├── TEMPERATURA
 │
 └── ATIVIDADE
```

Exemplo de unidades utilizadas:

```text
TEMPERATURA -> C
ATIVIDADE   -> METROS
```

Isso permite que o pet já esteja preparado para receber dados do fluxo IoT imediatamente após o cadastro.

---

## 10. Integração IoT

A arquitetura utilizada para monitoramento é:

```text
ESP32 / Wokwi
      │
      ▼
broker.hivemq.com
      │
      ▼
   Node-RED
      │
      ▼
    Oracle
```

O dispositivo publica dados utilizando o ID do pet.

Exemplo:

```text
pet/100/temperatura
pet/100/atividade
```

O Node-RED identifica:

```text
ID do pet
Tipo do sensor
Valor recebido
```

e registra a leitura no sensor correspondente no Oracle.

---

## 11. Fluxo de consulta

O responsável agenda uma consulta selecionando:

```text
Pet
Veterinário
Data e hora
Motivo
```

A consulta é inicialmente criada com o status:

```text
AGENDADA
```

Fluxo:

```text
RESPONSAVEL
     │
     ▼
Agenda consulta
     │
     ▼
Oracle
     │
     ▼
VETERINARIO
     │
     ▼
Visualiza consulta
```

---

## 12. Finalização da consulta

O veterinário responsável pode abrir a consulta e visualizar:

- informações do pet;
- motivo da consulta;
- dados do atendimento;
- sensores associados ao pet;
- leituras registradas;
- campo para observação clínica.

Ao finalizar:

```text
AGENDADA
   │
   ▼
Observação clínica
   │
   ▼
REALIZADA
```

A observação é persistida na coluna:

```text
CONSULTA.OBSERVACAO
```

e o status passa para:

```text
REALIZADA
```

---

## 13. Banco de dados

O banco utilizado pela aplicação é o **Oracle Database**.

Principais tabelas:

```text
USUARIO
RESPONSAVEL
VETERINARIO
CLINICA
PET
CONSULTA
VACINA
APLICACAO_VACINA
LEMBRETE
SENSOR
LEITURA
AUDITORIA
LOG_ERRO
```

A documentação completa da modelagem e dos objetos de banco será disponibilizada em:

```text
docs/banco-de-dados.pdf
```

---

## 14. Flyway

O banco é versionado através do Flyway.

As migrations estão em:

```text
projeto-vet-v2/src/main/resources/db/migration/
```

Migrations existentes:

| Migration | Finalidade |
| --- | --- |
| `V1__create_tables.sql` | criação das tabelas e constraints |
| `V2__insert_initial_data.sql` | carga inicial de dados |
| `V3__create_plsql_objects.sql` | criação dos objetos PL/SQL |
| `V4__fix_plsql_procedures.sql` | ajustes nos objetos PL/SQL |
| `V5__add_admin_user_type.sql` | inclusão do perfil `ADMIN` |

O histórico do Flyway é armazenado em:

```text
FLYWAY_SCHEMA_HISTORY_VET
```

---

## 15. PL/SQL

O banco também utiliza objetos PL/SQL para implementar regras e operações específicas.

Entre os objetos utilizados no projeto estão:

- functions;
- procedures;
- tratamento de exceptions;
- trigger de auditoria.

Esses objetos são criados e versionados através das migrations do Flyway.

---

## 16. Configuração do banco

O arquivo principal de configuração está em:

```text
projeto-vet-v2/src/main/resources/application.properties
```

As credenciais não devem ser armazenadas diretamente no repositório.

A aplicação utiliza:

```properties
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

No PowerShell:

```powershell
$env:DB_USERNAME = "SEU_USUARIO"
$env:DB_PASSWORD = "SUA_SENHA"
```

As variáveis existem apenas no ambiente local utilizado para executar a aplicação.

---

## 17. Executando o projeto

Clone o repositório:

```powershell
git clone https://github.com/Challenge-CLYVO/Java-Challenge.git
```

Entre na versão atual:

```powershell
cd Java-Challenge\projeto-vet-v2
```

Configure as credenciais:

```powershell
$env:DB_USERNAME = "SEU_USUARIO"
$env:DB_PASSWORD = "SUA_SENHA"
```

Execute:

```powershell
.\mvnw.cmd spring-boot:run
```

---

## 18. Acesso à aplicação

A aplicação é executada na porta:

```text
8087
```

Acesso local:

```text
http://localhost:8087
```

Página de login:

```text
http://localhost:8087/login
```

Cadastro público de responsável:

```text
http://localhost:8087/cadastro
```

---

## 19. Fluxo completo demonstrado

O funcionamento principal da solução pode ser resumido em:

```text
ADMIN
  │
  ▼
Cadastra VETERINARIO
  │
  ▼

RESPONSAVEL
  │
  ├── cria conta
  ├── cadastra PET
  │       │
  │       └── sensores criados automaticamente
  │
  ├── recebe leituras IoT
  └── agenda CONSULTA
             │
             ▼
        VETERINARIO
             │
             ├── visualiza consulta
             ├── visualiza sensores
             ├── visualiza leituras
             └── finaliza atendimento
                       │
                       ▼
                  REALIZADA
```

---

## 20. Vídeo de demonstração

A demonstração completa do sistema pode ser assistida no YouTube:

### ▶ YouTube

**Link:**

```text
https://youtu.be/SEU_LINK_AQUI
```

O vídeo demonstra:

- autenticação;
- cadastro de veterinário pelo administrador;
- cadastro e login de responsável;
- cadastro de pet;
- criação automática dos sensores;
- integração com dados IoT;
- agendamento de consulta;
- acesso do veterinário;
- visualização das leituras;
- finalização da consulta;
- validação dos dados diretamente no Oracle.

---

## 21. Validações

Os formulários utilizam Jakarta Validation para impedir entradas inválidas.

Exemplos:

```text
@NotBlank
@NotNull
@Email
@Size
@Past
@Future
```

Entre os comportamentos validados estão:

- email válido;
- campos obrigatórios;
- tamanho mínimo de senha;
- nascimento do pet no passado;
- consulta somente em data futura;
- observação obrigatória na finalização.

---

## 22. Segurança

A solução utiliza diferentes camadas de segurança:

```text
Spring Security
BCrypt
Controle de acesso por perfil
Validação de propriedade dos registros
Variáveis de ambiente para credenciais
CSRF
```

Um responsável não pode utilizar rotas de veterinário.

Um veterinário não pode utilizar rotas administrativas ou de responsável.

Além da autorização por perfil, consultas também são verificadas para impedir que um veterinário manipule consultas pertencentes a outro profissional.

---

## 23. Versionamento

O projeto utiliza Git e GitHub para controle de versão.

Branch principal:

```text
master
```

Repositório:

```text
https://github.com/Challenge-CLYVO/Java-Challenge
```

---

## 24. Documentação complementar

A documentação do banco de dados será incluída em:

```text
docs/banco-de-dados.pdf
```

Ela contém informações sobre:

- modelagem relacional;
- tabelas;
- relacionamentos;
- constraints;
- carga de dados;
- functions;
- procedures;
- exceptions;
- triggers;
- auditoria;
- consultas utilizadas para validação.

---

## 25. Considerações finais

A versão atual do projeto integra aplicação web, segurança, persistência, versionamento de banco e monitoramento IoT em um único fluxo.

O principal cenário implementado conecta:

```text
PET
│
├── RESPONSAVEL
├── SENSORES
├── LEITURAS
└── CONSULTAS
       │
       └── VETERINARIO
```

Com isso, a solução permite acompanhar o animal desde o cadastro e monitoramento até o atendimento veterinário e registro da conclusão da consulta.
