package br.com.amazon.pageobject;

import br.com.amazon.base.BasePage;
import br.com.amazon.driver.DriverWeb;
import br.com.amazon.utils.GeradorPDF;
import br.com.amazon.utils.InfraUtils;
import br.com.amazon.variables.amazonVariaveis;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.MalformedURLException;

public class amazonPageObject {
    public GeradorPDF geradorPDF;
    public BasePage page;
    public amazonVariaveis v = new amazonVariaveis();
    private WebDriver driver;
    private String nomeDoLivro;
    private String nomeDoAutor;
    private String amazonaCNPJ;

    public amazonPageObject(WebDriver driver,Scenario cenario, String nomeTeste) {
        this.driver=driver;
        this.page = new BasePage(this.driver);
        this.geradorPDF = new GeradorPDF(this.driver,cenario, nomeTeste);
    }

    public void fecharPDF(){
        this.geradorPDF.finishPdf();
    }

    public void queEuAcesseiOMenuTodosComprarPorCategoriaLivros() throws MalformedURLException, InterruptedException {
        geradorPDF.evidenciaElemento("Clicar em Menu Todos");
        page.clicar(By.xpath(v.btnMenuTodos));

        page.esperar(1600);
        page.moverParaElemento(By.xpath(v.txtComprarPorCategoria));
        page.clicar(By.xpath(v.txtComprarPorCategoria));

        geradorPDF.evidenciaElemento("Clicar em Ver Tudo");
        page.moverParaElemento(By.xpath(v.btnComprarPorCategoria_VerTudo));
        page.clicar(By.xpath(v.btnComprarPorCategoria_VerTudo));

        page.moverParaElemento(By.xpath(v.btnComprarPorCategoria_Livros));
        geradorPDF.evidenciaElemento("Clicar em Livros");
        page.clicar(By.xpath(v.btnComprarPorCategoria_Livros));
    }

    public void abrirAOpcaoHQsEMangas() throws MalformedURLException, InterruptedException {
        geradorPDF.evidenciaElemento("Clicar em HQs e Mangas");
        page.clicar(By.xpath(v.btnComprarPorCategoria_HQSMANGAS));
    }

    public void devoValidarOTituloDaPagina() throws IOException, InterruptedException {
        String titulo = "Livros de HQs, Mangás, Graphic Novels, Quadrinhos | Amazon.com.br";
        page.validarTextoPaginaWeb(titulo);
        geradorPDF.evidenciaElemento("Validar titulo da página");
    }

    /////

    public void abrirAOpcaoHQsEMangasEmNovaAba() throws InterruptedException {
        page.esperar(2000);

        page.abrirNovaAbaNavegador();
        geradorPDF.evidenciaElemento("Abrir nova aba do navegador");

        String os = InfraUtils.getOsName();
        if (os.equalsIgnoreCase("Mac") || os.equalsIgnoreCase("Unix") ||
                os.equalsIgnoreCase("Mac OS X")) {
            page.alterarAbaNavegador(0);
        }else if (os.equalsIgnoreCase("Windows")){
            page.alterarAbaNavegador(1);
        }

        page.esperar(2000);

        geradorPDF.evidenciaElemento("Acessar link HQs e Mangas");
        page.abrirPaginaBrowser("https://www.amazon.com.br/gp/browse.html?node=7842710011&ref_=nav_em__books_hqs_0_2_22_10");
    }

    public void irNaOpcaoOsMaisDesejados() throws InterruptedException {
        page.scroll(150);
        page.moverParaElemento(By.xpath(v.txtOsMaisDesejados));
        geradorPDF.evidenciaElemento("Acessar Os mais desejados");
    }

    public void devoObterNomeDoLivroEAutor() throws MalformedURLException {
        this.nomeDoLivro = page.obterTexto(By.xpath(v.txtOsMaisDesejados_NomeLivro));
        this.nomeDoAutor = page.obterTexto(By.xpath(v.txtOsMaisDesejados_NomeAutor));
        System.out.println("Nome do livro: "+this.nomeDoLivro);
        System.out.println("Nome do autor: "+this.nomeDoAutor);

        geradorPDF.evidenciaElemento("Nome do Livro: "+this.nomeDoLivro);
        geradorPDF.evidenciaElemento("Nome do Autor: "+this.nomeDoAutor);
    }

    public void obterOCNPJDaAmazon() throws InterruptedException, IOException {
        page.scroll(999);
        page.moverParaElemento(By.xpath(v.txtAmazonCNPJ));
        geradorPDF.evidenciaElemento("CNPJ Amazon: "+page.obterTexto(By.xpath(v.txtAmazonCNPJ)));
        page.validarTexto(By.xpath(v.txtAmazonCNPJ),"Amazon Serviços de Varejo do Brasil Ltda. | CNPJ 15.436.940/0001-03");
        this.amazonaCNPJ = page.obterTexto(By.xpath(v.txtAmazonCNPJ));
    }

    ////

    public void queEuAcesseiOMenuOfertasDoDia() throws MalformedURLException, InterruptedException {
        geradorPDF.evidenciaElemento("Clicar em Ofertas do Dia");
        page.clicar(By.xpath(v.btnOfertasDoDia));
        page.validarElementoExibido(By.xpath(v.txtOfertasDoDia));
    }

    public void euClicarEmUmaDasOfertasDoDia() throws InterruptedException, MalformedURLException {
        page.scroll(120);

        geradorPDF.evidenciaElemento("Clicar em Ofertas 1");
        page.clicar(By.xpath(v.lblOfertas1));
        page.esperar(2500);
        geradorPDF.evidenciaElemento("Resultado Ofertas 1");

        page.esperar(1000);

        geradorPDF.evidenciaElemento("Clicar em Ofertas 2");
        page.clicar(By.xpath(v.lblOfertas2));
        page.esperar(2500);
        geradorPDF.evidenciaElemento("Resultado Ofertas 2");

        page.esperar(1000);

        geradorPDF.evidenciaElemento("Clicar em Ofertas 3");
        page.clicar(By.xpath(v.lblOfertas3));
        page.esperar(2500);
        geradorPDF.evidenciaElemento("Resultado Ofertas 3");
    }

    public void devoVisualizarAListaDosProdutos() throws MalformedURLException, InterruptedException {
        geradorPDF.evidenciaElemento("Opções selecionadas");
    }
}
