package br.com.amazon.steps;

import br.com.amazon.base.BasePage;
import br.com.amazon.driver.DriverWeb;
import br.com.amazon.utils.GeradorPDF;
import br.com.amazon.variables.amazonVariaveis;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class amazonSteps extends DriverWeb {
    public BasePage page = new BasePage();
    public amazonVariaveis v = new amazonVariaveis();
    public GeradorPDF geradorPDF;

    public String chrome = "chrome";
    public String link = "https://www.amazon.com.br/";
    private Scenario cenario;
    private String nomeDoCenario;

    public String navegador = chrome;

    @Before("@Amazon")
    public void setUp(Scenario cenario) throws Exception {
        this.cenario = cenario;
        nomeDoCenario = this.cenario.getName();
        geradorPDF = new GeradorPDF(this.cenario, this.cenario.getName());
        page.criarDriverWeb(navegador,link);
    }

    @After("@Amazon")
    public void tearDown() throws Exception {
        geradorPDF.finishPdf();
        page.fecharDriverWeb();
    }

    @Dado("Que eu acessei o menu Todos - Comprar Por Categoria - Livros")
    public void queEuAcesseiOMenuTodosComprarPorCategoriaLivros() throws MalformedURLException, InterruptedException {
        geradorPDF.evidenciaElemento("Clicar em Menu Todos", page.getCurrentRunningDriver());
        page.clicar(By.xpath(v.btnMenuTodos));

        page.scrollDownClick(By.xpath(v.txtComprarPorCategoria));

        geradorPDF.evidenciaElemento("Clicar em Ver Tudo", page.getCurrentRunningDriver());
        page.scrollDownClick(By.xpath(v.btnComprarPorCategoria_VerTudo));
        page.moverParaElemento(By.xpath(v.btnComprarPorCategoria_Livros));

        geradorPDF.evidenciaElemento("Clicar em Livros", page.getCurrentRunningDriver());
        page.clicar(By.xpath(v.btnComprarPorCategoria_Livros));
    }

    @Dado("Abrir a opcao HQs e Mangas")
    public void abrirAOpcaoHQsEMangas() throws MalformedURLException, InterruptedException {
        geradorPDF.evidenciaElemento("Clicar em HQs e Mangas", page.getCurrentRunningDriver());
        page.clicar(By.xpath(v.btnComprarPorCategoria_HQSMANGAS));
    }

    @Entao("Devo validar o titulo da pagina")
    public void devoValidarOTituloDaPagina() throws MalformedURLException, InterruptedException {
        String titulo = "Livros de HQs, Mangás, Graphic Novels, Quadrinhos | Amazon.com.br";
        page.validarTextoPaginaWeb(titulo);
        geradorPDF.evidenciaElemento("Validar titulo da página", page.getCurrentRunningDriver());
        page.esperar(4000);
    }
}
