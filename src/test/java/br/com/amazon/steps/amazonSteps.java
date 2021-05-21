package br.com.amazon.steps;

import br.com.amazon.base.BasePage;
import br.com.amazon.driver.DriverWeb;
import br.com.amazon.pageobject.amazonPageObject;
import br.com.amazon.utils.GeradorPDF;
import br.com.amazon.utils.InfraUtils;
import br.com.amazon.variables.amazonVariaveis;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;

import java.io.IOException;
import java.net.MalformedURLException;

public class amazonSteps extends DriverWeb {
    public BasePage page;
    public amazonVariaveis v = new amazonVariaveis();
    public amazonPageObject amazonPageObject;
    public GeradorPDF geradorPDF;

    public String chrome = "chrome";
    public String link = "https://www.amazon.com.br/";
    private Scenario cenario;
    private String nomeDoCenario;
    private String nomeDoLivro;
    private String nomeDoAutor;
    private String amazonaCNPJ;

    public String navegador = chrome;

    @Before("@Amazon")
    public void setUp(Scenario cenario) throws Exception {
        this.cenario = cenario;
        nomeDoCenario = this.cenario.getName();
        criarDriverWeb(navegador,link);
    }

    @After("@Amazon")
    public void tearDown() throws Exception {
        this.amazonPageObject.fecharPDF();
        fecharDriverWeb();
    }

    @Dado("Que eu acessei o menu Todos - Comprar Por Categoria - Livros")
    public void queEuAcesseiOMenuTodosComprarPorCategoriaLivros() throws MalformedURLException, InterruptedException {
        amazonPageObject = new amazonPageObject(getCurrentRunningDriver(),this.cenario,this.nomeDoCenario);
        amazonPageObject.queEuAcesseiOMenuTodosComprarPorCategoriaLivros();
    }

    @Dado("Abrir a opcao HQs e Mangas")
    public void abrirAOpcaoHQsEMangas() throws MalformedURLException, InterruptedException {
        amazonPageObject.abrirAOpcaoHQsEMangas();
    }

    @Entao("Devo validar o titulo da pagina")
    public void devoValidarOTituloDaPagina() throws IOException, InterruptedException {
        amazonPageObject.devoValidarOTituloDaPagina();
    }

    //////////////////

    @Quando("Abrir a opcao HQs e Mangas em nova aba")
    public void abrirAOpcaoHQsEMangasEmNovaAba() throws InterruptedException {
        amazonPageObject.abrirAOpcaoHQsEMangasEmNovaAba();
    }

    @Quando("Ir na Opcao Os Mais Desejados")
    public void irNaOpcaoOsMaisDesejados() throws InterruptedException {
        amazonPageObject.irNaOpcaoOsMaisDesejados();
    }

    @Entao("Devo obter Nome do livro e autor")
    public void devoObterNomeDoLivroEAutor() throws MalformedURLException {
        amazonPageObject.devoObterNomeDoLivroEAutor();
    }

    @Entao("Obter o CNPJ da Amazon")
    public void obterOCNPJDaAmazon() throws InterruptedException, IOException {
        amazonPageObject.obterOCNPJDaAmazon();
    }

    //////////////////

    @Dado("Que eu acessei o menu Ofertas do Dia")
    public void queEuAcesseiOMenuOfertasDoDia() throws MalformedURLException, InterruptedException {
        amazonPageObject = new amazonPageObject(getCurrentRunningDriver(),this.cenario,this.nomeDoCenario);
        amazonPageObject.queEuAcesseiOMenuOfertasDoDia();
    }

    @Quando("Eu clicar em uma das ofertas do dia")
    public void euClicarEmUmaDasOfertasDoDia() throws InterruptedException, MalformedURLException {
        amazonPageObject.euClicarEmUmaDasOfertasDoDia();
    }

    @Entao("Devo visualizar a lista dos produtos")
    public void devoVisualizarAListaDosProdutos() throws MalformedURLException, InterruptedException {
        amazonPageObject.devoVisualizarAListaDosProdutos();
    }
}
