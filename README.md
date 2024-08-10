# automacao-amazon-java-web
Scripts de automação web desenvolvido com Java, Cucumber, jUnit e Selenium.

### Cobertura dos testes:  ###

* Realizar consultas na amazon

## Tecnologias:
* [Java JDK 8+](https://www.oracle.com/br/java/technologies/javase-downloads.html)
* [Maven](https://maven.apache.org)
* [Maven dependency](https://mvnrepository.com)
* [WebDriver Manager](https://github.com/bonigarcia/webdrivermanager)
* [Selenium Webdriver](https://www.selenium.dev/projects/)
* [Intellij Idea](https://www.jetbrains.com/pt-br/idea/)
* [jUnit](https://junit.org/junit5/)
* [Cucumber](https://cucumber.io)
* [ItextPDF](https://itextpdf.com/en)

## Dependências:
* Selenium-java
* WebDriver Manager
* jUnit 4
* Commons-io
* Cucumber-junit
* Cucumber-java
* Cucumber-picocontainer
* Jxl
* Poi-ooxml
* Itextpdf

## Instruções de execução:

###  - Plataforma
*Importante: 
O projeto foi criado para executar MacOS e Windows. Recomendado utilizar o Intellij Idea, mas pode usar o Eclipse IDE, Visual Studio Code ou Spring Tools Suite.

###  - Evidencias
*Descricao:
Evidencias sao armazenadas num arquivo pdf na pasta evidencias

###  - Inicializar a automação
*Descricao: 
Classe para executar esta na pasta runner/RunnerTest.

Não é necessário baixar o WebDriver para cada navegador, o projeto utiliza o WebDriver Manager que faz o download adequado independente do Sistema Operacional ou versão do Navegador. Porém, é preciso que o navegador esteja instalado na máquina.

O webdriver esta setado para o Chrome, para os outros navegadores nao esqueca de alterar na instanciação.
