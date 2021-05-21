package br.com.amazon.steps;

import br.com.amazon.base.BasePage;
import br.com.amazon.driver.DriverWeb;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

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

        page.moverParaElemento(By.xpath(v.txtComprarPorCategoria));
        page.clicar(By.xpath(v.txtComprarPorCategoria));

        geradorPDF.evidenciaElemento("Clicar em Ver Tudo", page.getCurrentRunningDriver());
        page.moverParaElemento(By.xpath(v.btnComprarPorCategoria_VerTudo));
        page.clicar(By.xpath(v.btnComprarPorCategoria_VerTudo));

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
    }

    //////////////////

    @Quando("Abrir a opcao HQs e Mangas em nova aba")
    public void abrirAOpcaoHQsEMangasEmNovaAba() throws InterruptedException {
        page.esperar(2000);

        page.abrirNovaAbaNavegador();
        geradorPDF.evidenciaElemento("Abrir nova aba do navegador", page.getCurrentRunningDriver());

        String os = InfraUtils.getOsName();
        if (os.equalsIgnoreCase("Mac") || os.equalsIgnoreCase("Unix") ||
                os.equalsIgnoreCase("Mac OS X")) {
            page.alterarAbaNavegador(0);
        }else if (os.equalsIgnoreCase("Windows")){
            page.alterarAbaNavegador(1);
        }

        page.esperar(2000);

        geradorPDF.evidenciaElemento("Acessar link HQs e Mangas", page.getCurrentRunningDriver());
        page.abrirPaginaBrowser("https://www.amazon.com.br/gp/browse.html?node=7842710011&ref_=nav_em__books_hqs_0_2_22_10");
    }

    @Quando("Ir na Opcao Os Mais Desejados")
    public void irNaOpcaoOsMaisDesejados() throws InterruptedException {
        page.scroll(150);
        page.moverParaElemento(By.xpath(v.txtOsMaisDesejados));
        geradorPDF.evidenciaElemento("Acessar Os Mais Desejados", page.getCurrentRunningDriver());
    }

    @Entao("Devo obter Nome do livro e autor")
    public void devoObterNomeDoLivroEAutor() throws MalformedURLException {
        String nomeDoLivro = page.obterTexto(By.xpath(v.txtOsMaisDesejados_NomeLivro));
        String nomeDoAutor = page.obterTexto(By.xpath(v.txtOsMaisDesejados_NomeAutor));
        System.out.println("Nome do livro: "+nomeDoLivro);
        System.out.println("Nome do autor: "+nomeDoAutor);

        geradorPDF.evidenciaElemento("Nome do Livro: "+nomeDoLivro, page.getCurrentRunningDriver());
        geradorPDF.evidenciaElemento("Nome do Autor: "+nomeDoAutor, page.getCurrentRunningDriver());
    }

    @Entao("Obter o CNPJ da Amazon")
    public void obterOCNPJDaAmazon() throws InterruptedException, MalformedURLException {
        page.scroll(999);
        page.moverParaElemento(By.xpath(v.txtAmazonCNPJ));
        geradorPDF.evidenciaElemento("CNPJ Amazon: "+page.obterTexto(By.xpath(v.txtAmazonCNPJ)), page.getCurrentRunningDriver());
        page.validarTexto(By.xpath(v.txtAmazonCNPJ),"Amazon Serviços de Varejo do Brasil Ltda. | CNPJ 15.436.940/0001-03");
    }
}
