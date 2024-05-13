# Nome do Projeto: API 

Descrição curta do projeto.

## Estórias de Usuário

1. Como usuario, eu quero salvar usuários que possuam carros para ter um alista de usuários e os respectivos carros.
2. Como usuario, eu quero cadastrarcarros para usuários.
3. ...

## Solução

### Justificativas

Explicação das decisões de design, arquitetura e tecnologias utilizadas na solução.

### Defesa Técnica

Swagger: Utilizado para documentação e teste de APIs.

JWT como Token
O JSON Web Token (JWT) é uma escolha comum para autenticação e autorização em aplicações web por diversas razões:

Segurança: JWTs podem ser assinados digitalmente para garantir a integridade dos dados e podem ser criptografados para proteger informações sensíveis.
Escalabilidade: JWTs são autocontidos, o que significa que não há necessidade de consultar um banco de dados para validar tokens, tornando-os ideais para sistemas distribuídos.
Facilidade de Integração: Muitas bibliotecas e frameworks oferecem suporte nativo para JWTs, facilitando a integração em diferentes tecnologias.
Servidor Embutido (Tomcat, Undertow, ou Jetty)
Utilizar um servidor embutido oferece várias vantagens:

Simplicidade: Não é necessário configurar e gerenciar um servidor externo separado.
Portabilidade: A aplicação pode ser facilmente executada em diferentes ambientes de desenvolvimento e produção.
Eficiência de Recursos: Servidores embutidos consomem menos recursos em comparação com servidores externos dedicados.
Processo de Build via Maven
O Maven é uma ferramenta de automação de construção amplamente utilizada que oferece várias vantagens:

Gerenciamento de Dependências: O Maven simplifica o gerenciamento de dependências, facilitando a inclusão e atualização de bibliotecas.
Convenções de Projeto: O Maven segue convenções de projeto bem definidas, o que facilita a compreensão e manutenção do código.
Build Padronizado: O Maven fornece um processo de build padronizado, garantindo consistência entre diferentes projetos e equipes.
Banco de Dados em Memória (H2)
Um banco de dados em memória como o H2 oferece benefícios específicos para desenvolvimento e testes:

Facilidade de Configuração: O H2 pode ser configurado facilmente para ser executado em modo de memória, eliminando a necessidade de configuração adicional.
Rápido Ciclo de Desenvolvimento: Bancos de dados em memória são rápidos de configurar e não exigem instalação, acelerando o ciclo de desenvolvimento.
Isolamento dos Testes: Cada execução de teste pode começar com um banco de dados limpo, garantindo a previsibilidade dos resultados dos testes.
Framework Spring
O Spring é amplamente adotado na indústria por várias razões:

Modularidade: O Spring é modular, permitindo que os desenvolvedores escolham e usem apenas os componentes necessários para seus projetos.
Inversão de Controle (IoC): O Spring oferece um modelo de IoC que facilita a configuração e gerenciamento de objetos em uma aplicação.
Ecossistema Abundante: O Spring possui um vasto ecossistema de projetos relacionados que facilitam o desenvolvimento de diferentes tipos de aplicações.
Persistência com JPA/Hibernate
O JPA (Java Persistence API) em conjunto com o Hibernate é uma escolha popular para persistência de dados em aplicações Java por várias razões:

Mapeamento Objeto-Relacional (ORM): O JPA/Hibernate simplifica o mapeamento entre objetos Java e tabelas de banco de dados, reduzindo a quantidade de código necessário.
Portabilidade: O JPA permite que as aplicações sejam independentes do fornecedor de banco de dados, facilitando a migração entre diferentes sistemas de banco de dados.
Consulta Orientada a Objetos: O Hibernate oferece suporte a consultas orientadas a objetos, o que pode simplificar o desenvolvimento e manutenção do código.
Disponibilizar a API em um Host (Heroku, AWS, Digital Ocean, etc.)
Disponibilizar a API em um host externo oferece várias vantagens:

Acessibilidade: A API fica acessível pela Internet, permitindo que os usuários acessem de qualquer lugar.
Escalabilidade: Serviços de hospedagem em nuvem geralmente oferecem opções de dimensionamento automático, facilitando a escalabilidade conforme a demanda aumenta.
Confiabilidade: Os provedores de hospedagem em nuvem geralmente garantem alta disponibilidade e confiabilidade dos serviços.
Testes Unitários
Os testes unitários são fundamentais para garantir a qualidade e estabilidade do código por várias razões:

Identificação Precoce de Problemas: Os testes unitários identificam bugs e problemas de integração no estágio inicial do desenvolvimento, reduzindo o custo e o esforço necessário para corrigi-los.
Documentação Viva: Testes unitários servem como documentação viva do comportamento esperado de cada componente da aplicação.
Facilitação de Refatorações: Testes unitários fornecem uma rede de segurança ao refatorar o código, garantindo que as mudanças não quebrem o comportamento existente.

## Como Executar

Instruções sobre como executar o projeto, incluindo:
- como fazer para instalar
  npm install

- Como fazer o build do projeto
  npm start


## Licença

Este projeto é licenciado por PAtrick Azevedo.
