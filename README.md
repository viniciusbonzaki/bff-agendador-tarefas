# BFF (Backend For Frontend) — Agendador de Tarefas

## 📌 Sobre o Projeto

O Agendador de Tarefas tem como objetivo permitir que usuários cadastrem tarefas com datas e horários específicos e recebam notificações por e-mail quando o momento programado se aproximar.

Para organizar as responsabilidades da aplicação, o sistema foi desenvolvido com base na **arquitetura de microserviços**, dividindo suas funcionalidades em serviços independentes e especializados.

O ecossistema é composto por quatro microserviços:

- **API de Usuários:** responsável pelo cadastro, consulta, atualização e exclusão de usuários, incluindo o gerenciamento de seus endereços e telefones.
- **API de Tarefas:** responsável pelo gerenciamento das tarefas cadastradas na aplicação.
- **API de Notificação:** responsável pelo processamento e envio de notificações por e-mail.
- **BFF (Backend for Frontend):** responsável por integrar os demais microserviços e centralizar a comunicação com a camada cliente.

Este repositório é dedicado exclusivamente ao **BFF (Backend for Frontend)**, um dos quatro microserviços que compõem o Agendador de Tarefas.

Desenvolvido com **Java 17 e Spring Boot**, o BFF atua como uma camada intermediária entre o cliente e os demais microserviços, disponibilizando endpoints para operações relacionadas aos usuários e às tarefas, além de coordenar a comunicação com os serviços responsáveis pelo gerenciamento de usuários, tarefas e notificações.

O microserviço utiliza **Maven** para gerenciamento de dependências e build, **Docker** para containerização da aplicação e **Spring Cloud OpenFeign** para realizar a comunicação com as APIs de Usuários, Tarefas e Notificação.

## 🎯 Funcionalidades

O BFF disponibiliza funcionalidades relacionadas à integração dos microserviços e à coordenação das operações do Agendador de Tarefas.

### Gerenciamento de usuários

- Cadastro de novos usuários.
- Autenticação de usuários.
- Consulta de dados cadastrais por e-mail.
- Atualização de dados cadastrais.
- Exclusão de usuários.
- Cadastro e atualização de endereços.
- Cadastro e atualização de telefones.
- Consulta de informações de endereço a partir de um CEP, por meio da funcionalidade ViaCEP.

### Gerenciamento de tarefas

- Cadastro de novas tarefas.
- Consulta de tarefas em um determinado período.
- Consulta de tarefas associadas a um usuário por meio de seu e-mail.
- Atualização das informações de uma tarefa.
- Exclusão de tarefas a partir de seu identificador.
- Atualização do status de uma tarefa específica.

### Integração entre microserviços

- Comunicação com a API de Usuários por meio do `UsuarioClient`.
- Comunicação com a API de Tarefas por meio do `TarefaClient`.
- Comunicação com a API de Notificação por meio do `EmailClient`.
- Utilização do Spring Cloud OpenFeign para simplificar as chamadas HTTP entre os microserviços.
- Tratamento de erros relacionados às chamadas remotas por meio dos componentes de infraestrutura.

### Regras de negócio e agendamento

- Centralização de operações de negócio nas classes de serviço.
- Coordenação das operações relacionadas ao envio de notificações por e-mail.
- Implementação de regras relacionadas ao agendamento por meio do `CronService`.

### Configuração e tratamento de erros

- Tratamento global de exceções por meio do `GlobalExceptionHandler`.
- Organização de exceções específicas da aplicação.
- Configuração de CORS para controlar as requisições de diferentes origens.
- Configuração de segurança da aplicação e do esquema de segurança utilizado na documentação Swagger.

## 🛠️ Tecnologias e Ferramentas Utilizadas

As principais tecnologias e ferramentas utilizadas no desenvolvimento deste microserviço são:

- **Java 17:** linguagem de programação utilizada no desenvolvimento.
- **Spring Boot:** framework utilizado para o desenvolvimento da aplicação backend e a exposição dos recursos REST.
- **Maven:** ferramenta utilizada para gerenciamento de dependências e automação do build.
- **Docker:** ferramenta utilizada para containerização da aplicação.
- **Spring Cloud OpenFeign:** recurso utilizado para facilitar a comunicação HTTP entre os microserviços por meio de clientes declarativos.
- **Spring Security:** utilizado na configuração de segurança da aplicação e do esquema de segurança disponibilizado para testes na documentação Swagger.
- **Swagger / OpenAPI:** utilizado para documentar e testar os endpoints da API, conforme a configuração do projeto.
- **CORS (Cross-Origin Resource Sharing):** mecanismo configurado por meio da classe `CorsConfig` para definir as regras de acesso às requisições originadas de diferentes domínios.
- **Cron:** mecanismo utilizado para definir ou coordenar a execução de rotinas relacionadas ao agendamento de tarefas, conforme as regras implementadas no `CronService`.

## 📂 Arquitetura e Organização do Projeto

O projeto utiliza uma organização em camadas para separar as responsabilidades da aplicação, facilitar a manutenção do código e promover a separação entre regras de negócio, exposição dos endpoints e comunicação com os demais microserviços.

### Business

Camada responsável por concentrar os serviços e as regras de negócio relacionadas às operações disponibilizadas pelo BFF.

- `UsuarioService`: responsável por coordenar as operações relacionadas aos usuários, integrando-se à API de Usuários.
- `TarefaService`: responsável por coordenar as operações relacionadas às tarefas, integrando-se à API de Tarefas.
- `EmailService`: responsável por coordenar as operações relacionadas ao envio de notificações por e-mail, integrando-se à API de Notificação.
- `CronService`: responsável pela implementação das regras de negócio relacionadas ao Cron e ao agendamento das operações previstas na aplicação.

### Controller

Camada responsável por expor os endpoints HTTP e receber as requisições dos clientes.

- `UsuarioController`: disponibiliza os endpoints relacionados ao gerenciamento de usuários, incluindo operações cadastrais, autenticação, endereços e telefones.
- `TarefaController`: disponibiliza os endpoints relacionados ao gerenciamento de tarefas, incluindo cadastro, consulta, atualização, exclusão e alteração de status.
- `GlobalExceptionHandler`: centraliza o tratamento de exceções geradas durante o processamento das requisições, permitindo organizar as respostas de erro da aplicação.

### Infrastructure

Camada responsável pelos componentes técnicos necessários à integração com outros microserviços, pelas configurações gerais da aplicação e pelos mecanismos de tratamento de erros e segurança.

#### Client

Contém os clientes Feign utilizados para realizar a comunicação HTTP com os demais microserviços.

- `UsuarioClient`: define as operações de comunicação com a API de Usuários.
- `TarefaClient`: define as operações de comunicação com a API de Tarefas.
- `EmailClient`: define as operações de comunicação com a API de Notificação.

##### Config

Diretório responsável pelas configurações e pelo tratamento de erros associados aos clientes Feign.

- `FeignConfig`: reúne as configurações utilizadas pelos clientes Feign.
- `FeignError`: componente responsável pelo tratamento ou pela interpretação de erros provenientes das chamadas realizadas pelos clientes Feign, conforme sua implementação.

#### Configs

Contém as classes responsáveis pelas configurações gerais da aplicação.

- `CorsConfig`: configura o Cross-Origin Resource Sharing (CORS), definindo as regras de acesso para requisições originadas de diferentes domínios.

#### Exceptions

Diretório destinado às exceções específicas utilizadas pela aplicação.

Essas exceções permitem representar situações de erro relacionadas às operações de negócio e à comunicação com outros serviços, podendo ser tratadas pelo `GlobalExceptionHandler`.

#### Security

Contém as configurações relacionadas à segurança da aplicação.

- `SecurityConfig`: define as configurações de segurança utilizadas pela aplicação e o esquema de segurança disponibilizado para testes na documentação Swagger.

## 🔐 Segurança e Configuração do Swagger

O projeto possui uma classe `SecurityConfig`, localizada na camada de infraestrutura, responsável pela configuração do esquema de segurança da aplicação para utilização na documentação Swagger.

Essa configuração permite que as operações da API sejam exploradas e testadas pela interface de documentação, conforme o mecanismo de autenticação definido no projeto.

A configuração efetiva de autenticação, autorização e proteção dos endpoints deve ser consultada na implementação da classe `SecurityConfig` e nas demais configurações de segurança eventualmente utilizadas pela aplicação.

## ⚠️ Tratamento de Exceções

O BFF possui uma classe `GlobalExceptionHandler`, responsável por centralizar o tratamento das exceções geradas durante o processamento das requisições.

Essa abordagem permite separar o tratamento de falhas da lógica dos controladores e organizar as respostas de erro disponibilizadas pela aplicação.

As exceções específicas estão organizadas no diretório `Infrastructure/Exceptions`, enquanto os componentes `FeignError` e `FeignConfig` dão suporte à configuração e ao tratamento de situações relacionadas à comunicação entre os microserviços.

Essa organização facilita a manutenção do código e a identificação de falhas originadas tanto nas operações da aplicação quanto nas chamadas aos serviços externos.

## 🌐 Endpoints da API

Esta seção apresenta as operações disponibilizadas pelo BFF para gerenciamento de usuários e tarefas.

Os caminhos exatos das rotas, os parâmetros, os corpos das requisições e os códigos HTTP de resposta devem ser confirmados na implementação dos controllers ou na documentação Swagger/OpenAPI.

### 👤 Endpoints de Usuários

| Método HTTP | Recurso | Descrição |
|---|---|---|
| `POST` | Usuário | Cadastrar um novo usuário. |
| `POST` | Login | Autenticar um usuário. |
| `GET` | Usuário | Buscar os dados de um usuário pelo e-mail. |
| `PUT` | Usuário | Atualizar os dados cadastrais de um usuário. |
| `DELETE` | Usuário | Excluir um usuário pelo e-mail. |
| `POST` | Endereço | Adicionar um endereço a um usuário. |
| `PUT` | Endereço | Atualizar o endereço de um usuário. |
| `GET` | ViaCEP | Consultar dados de endereço a partir de um CEP. |
| `POST` | Telefone | Adicionar um telefone a um usuário. |
| `PUT` | Telefone | Atualizar um telefone de um usuário. |

### 📝 Endpoints de Tarefas

| Método HTTP | Recurso | Descrição |
|---|---|---|
| `POST` | Tarefas | Cadastrar uma nova tarefa. |
| `GET` | Eventos | Consultar tarefas em um período. |
| `GET` | Tarefas por usuário | Buscar as tarefas associadas a um usuário por meio de seu e-mail. |
| `DELETE` | Tarefas | Excluir uma tarefa a partir de seu ID. |
| `PUT` | Tarefas | Atualizar as informações de uma tarefa existente. |
| `PATCH` | Status da tarefa | Atualizar o status de uma tarefa específica. |

### Exemplos de utilização

## 🔗 Integração com os Microserviços

O BFF utiliza o **Spring Cloud OpenFeign** para realizar a comunicação com os demais microserviços do Agendador de Tarefas.

Essa abordagem permite definir clientes declarativos para chamadas HTTP, mantendo os detalhes da comunicação organizados na camada de infraestrutura e separando as integrações da lógica de negócio.

### Integração com a API de Usuários

A comunicação com a API de Usuários é realizada por meio do `UsuarioClient`, utilizado pelo `UsuarioService` para coordenar as operações relacionadas ao cadastro, à autenticação e ao gerenciamento dos dados dos usuários.

Essa integração permite que o BFF disponibilize as funcionalidades de usuários sem concentrar nele a responsabilidade pelo gerenciamento dos dados pertencentes à API de Usuários.

### Integração com a API de Tarefas

A comunicação com a API de Tarefas é realizada por meio do `TarefaClient`, utilizado pelo `TarefaService` para coordenar as operações de gerenciamento das tarefas.

Por meio dessa integração, o BFF disponibiliza funcionalidades para cadastrar, consultar, atualizar e excluir tarefas, além de consultar eventos por período e atualizar o status de uma tarefa.

### Integração com a API de Notificação

A comunicação com a API de Notificação é realizada por meio do `EmailClient`, utilizado pelo `EmailService` para coordenar as operações relacionadas ao envio de notificações por e-mail.

Essa integração permite que o BFF encaminhe as solicitações necessárias ao microserviço responsável pelas notificações, mantendo separadas as responsabilidades de gerenciamento das tarefas e de envio dos e-mails.

### Fluxo geral de integração

O fluxo de comunicação entre o BFF e os demais microserviços pode ser representado pelas seguintes etapas:

1. O cliente envia uma requisição para um endpoint disponibilizado pelo BFF.
2. O controller responsável recebe a requisição e aciona o serviço de negócio correspondente.
3. O serviço coordena a operação conforme as regras de negócio implementadas.
4. Quando necessário, um cliente Feign realiza a chamada ao microserviço responsável pela funcionalidade.
5. A resposta recebida é processada pelo BFF.
6. O resultado da operação é retornado ao cliente por meio da API.

O fluxo exato pode variar de acordo com a operação solicitada e as regras implementadas em cada serviço.

## ⏰ Agendamento e CronService

O projeto possui a classe `CronService`, localizada na camada `Business`, responsável pelas regras de negócio relacionadas ao Cron.

O Cron é utilizado para definir ou coordenar a execução de rotinas programadas, permitindo automatizar operações de acordo com as regras de agendamento implementadas na aplicação.

No contexto do Agendador de Tarefas, o objetivo funcional do sistema é permitir que usuários recebam notificações por e-mail quando o horário programado para uma tarefa estiver se aproximando.

A execução efetiva dessas rotinas, os intervalos de verificação, os critérios de seleção das tarefas e as condições para o envio de notificações devem ser documentados de acordo com a implementação do `CronService`.

## 🌍 Configuração de CORS

O projeto possui a classe `CorsConfig`, localizada na camada `Infrastructure/Configs`, responsável pela configuração do **Cross-Origin Resource Sharing (CORS)**.

Por meio dessa configuração, é possível definir regras relacionadas às origens permitidas e aos métodos e cabeçalhos HTTP aceitos, conforme os parâmetros implementados no projeto.

A configuração adequada do CORS permite controlar a comunicação entre o frontend e o BFF, respeitando as políticas de acesso definidas para a aplicação.

## 🐳 Docker

O projeto utiliza Docker para containerização, facilitando a padronização do ambiente de execução e a implantação da aplicação.

A containerização permite empacotar o BFF e suas dependências de execução em uma imagem, simplificando sua inicialização em ambientes compatíveis com Docker.

## 📚 Conceitos Aplicados

O desenvolvimento deste microserviço envolve conceitos relevantes para a construção e integração de aplicações backend:

- Arquitetura de microserviços.
- Padrão Backend for Frontend (BFF).
- Desenvolvimento de APIs REST com Java 17 e Spring Boot.
- Separação de responsabilidades em camadas.
- Implementação de regras de negócio por meio de serviços.
- Comunicação entre microserviços com Spring Cloud OpenFeign.
- Integração entre APIs independentes.
- Centralização e tratamento global de exceções.
- Configuração de CORS (Cross-Origin Resource Sharing).
- Agendamento e execução de rotinas com Cron.
- Configuração de segurança com Spring Security.
- Documentação e testes de endpoints com Swagger/OpenAPI.
- Implementação de regras relacionadas ao agendamento de tarefas.
- Containerização de aplicações com Docker.
- Gerenciamento de dependências e automação de build com Maven.
