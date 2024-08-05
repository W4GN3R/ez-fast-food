# EZ-Fast-Food

## Descrição

O EZ-Fast-Food é um sistema desenvolvido para solucionar os desafios enfrentados por uma lanchonete que está se expandindo devido ao seu sucesso.

## Funcionalidades

O sistema de autoatendimento oferece as seguintes funcionalidades:

- **Cadastro de Clientes**: Os clientes podem se cadastrar para facilitar o processo de pedido.
- **Gerenciamento e acompanhamento de Pedidos**: Permite o controle eficiente dos pedidos, desde a seleção dos itens até a entrega final.
- **Pagamentos**: Integração com sistemas de pagamento para facilitar transações seguras e rápidas.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.1**
- **Hibernate**
- **PostgreSQL**
- **Docker e Docker Compose**
- **OpenApi**
- **Apache JMeter**

## Arquitetura

### Arquitetura Hexagonal

A arquitetura hexagonal foi a proposta pela FIAP, ela foi escolhida para isolar a lógica de negócios dos detalhes de infraestrutura, promovendo uma separação clara de responsabilidades. Essa abordagem facilita a manutenção e evolução da aplicação, permitindo a introdução de novas tecnologias e adaptações sem impacto significativo nas funcionalidades principais.

### Estrutura de Diretórios

- `src/main/java/br/com/fiap/ez/fastfood`: Código-fonte da aplicação.
  - `adapters/in`: Adaptadores de entrada, como controllers e outros endpoints.
  - `adapters/out`: Adaptadores de saída, como repositórios e integrações com sistemas externos.
  - `application`: Contém a lógica de aplicação, como serviços e casos de uso.
  - `config`: Configurações gerais da aplicação.
  - `domain`: Modelo de domínio e interfaces de repositórios.
- `src/main/resources`: Recursos estáticos e arquivos de configuração.
- `docker`: Scripts de inicialização e configuração do Docker e Docker Compose.

## Instruções de Configuração e Execução

### Pré-requisitos

- Docker instalado.

### Clonar o Repositório

```sh
git clone https://github.com/tchfer/ez-fast-food.git
cd ez-fast-food
```

### Execução com Docker Compose
Para iniciar a aplicação e o banco de dados PostgreSQL, execute o seguinte comando na raiz desse projeto:

```sh
docker-compose up --build
```

Isso irá:

1. Baixar as imagens necessárias, caso ainda não estejam disponíveis.
2. Construir as imagens da aplicação e do banco de dados.
3. Iniciar os contêineres, incluindo as duas intâncias necessárias, a aplicação Spring Boot e o banco de dados PostgreSQL.

## Acessando a aplicação
A aplicação estará disponível em http://localhost:8080

## Documentação de APIs
As APIs foram documentadas utilizando OpenAPI e estão disponíveis em http://localhost:8080/swagger-ui/index.html<br>
Já há uma massa de dados inicial para facilitar os testes dos endpoints.

## Desenvolvido por:
@tchfer, @ThaynaraDaSilva e @W4GN3R.
