## Code Challenge: Ganho de Capital

O objetivo deste exercício é implementar um programa que calcula o imposto a ser pago sobre lucros ou prejuízos de operações no mercado financeiro de ações.

## Explicações 

Para resolver o desafio eu separei a aplicação em 5 modulos principais (listados abaixo), foi utilizado 3 design patterns (Facade, Factory e Strategy) para facilitar a expansão e manutenção do sistema futuramente.

1. Controller - Utilizado para junto com a classe Scanner do java para realizar a entrada via `stdin`, Após a aplicação subir o json deve ser colado no terminal. Obs: O Json deve ficar uma unica linha.
2. Summary - Utilizado para armazenar o valor medio das operações de compra, valor total da operação, valor de ganho ou perda nas operações
3. Calculator - Responsável por calcular se a operação resultou em ganho ou perda
4. Tribute - Responsável por aplicar o percentual de desconto quando necessário. nesse pacote eu dividi as estrategias de tributo por tipo de operação, deixando aberto para expansão caso exista novas taxas.
5. Converter - Utilizado para converter as taxas no formato solicitado.

## Frameworks
* Springboot 2.6.1
* lombok


## Iniciando com o Docker

#####Build image
`docker build -t nubank .`
#####Run app
`docker run -i --name nubank nubank`
##### Stop app
`docker stop nubank`
##### delete container
`docker rm nubank`
##### delete image
`docker rmi nubank`

## Executar na IDE com Maven

#####Build image
Instalar as Dependências do Maven <br>
`.\mvnw.cmd clean install` <br>

Executar com Maven <br>
`mvn spring-boot:run -Dspring-boot.run.profiles=local`

Executar com java <br>
`java -jar  target/nubank-1.0.0.jar`



## Casos de Testes

### Case #1

#### Input
`[{"operation":"buy", "unit-cost":10, "quantity": 100},{"operation":"sell", "unit-cost":15, "quantity": 50},{"operation":"sell", "unit-cost":15, "quantity": 50}]`

#### Output
`[{"tax": 0},{"tax": 0},{"tax": 0}]`

### Case #2

#### Input
`[{"operation":"buy", "unit-cost":10, "quantity": 10000},{"operation":"sell", "unit-cost":20, "quantity": 5000},{"operation":"sell", "unit-cost":5, "quantity": 5000}]`

#### Output
`[{"tax": 0},{"tax": 10000},{"tax": 0}]`

### Case #3

#### Input
`[{"operation":"buy", "unit-cost":10, "quantity": 10000},{"operation":"sell","unit-cost":5, "quantity": 5000},{"operation":"sell", "unit-cost":20, "quantity":5000}]`

#### Output
`[{"tax": 0},{"tax": 0},{"tax": 5000}]`

### Case #4

#### Input
`[{"operation":"buy", "unit-cost":10, "quantity": 10000},{"operation":"buy","unit-cost":25, "quantity": 5000},{"operation":"sell", "unit-cost":15,"quantity": 10000}]`

#### Output
`[{"tax": 0},{"tax": 0},{"tax": 0}]`

### Case #5

#### Input
`[{"operation":"buy", "unit-cost":10, "quantity": 10000},{"operation":"buy","unit-cost":25, "quantity": 5000},{"operation":"sell", "unit-cost":15,"quantity": 10000},{"operation":"sell", "unit-cost":25, "quantity": 5000}]`

#### Output
`[{"tax": 0},{"tax": 0},{"tax": 0},{"tax": 10000}]`

### Case #6

#### Input
`[{"operation":"buy", "unit-cost":10, "quantity": 10000},{"operation":"sell","unit-cost":2, "quantity": 5000},{"operation":"sell", "unit-cost":20, "quantity":2000},{"operation":"sell", "unit-cost":20, "quantity": 2000},{"operation":"sell","unit-cost":25, "quantity": 1000}]`

#### Output
`[{"tax": 0},{"tax": 0},{"tax": 0},{"tax": 0},{"tax": 3000}]`


## Testes Unitários (Necessário o Java instalado na maquina)

Windows
`.\mvnw.cmd clean install -Pdev`

Linux/Mac
`.\mvnw clean install -Pdev`