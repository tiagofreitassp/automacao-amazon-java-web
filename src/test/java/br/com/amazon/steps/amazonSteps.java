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

import java.io.IOException;
import java.net.MalformedURLException;

public class amazonSteps extends DriverWeb {
    public BasePage page = new BasePage();
    public amazonVariaveis v = new amazonVariaveis();
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
        geradorPDF = new GeradorPDF(this.cenario, this.nomeDoCenario);
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
    public void devoValidarOTituloDaPagina() throws IOException, InterruptedException {
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
        this.nomeDoLivro = page.obterTexto(By.xpath(v.txtOsMaisDesejados_NomeLivro));
        this.nomeDoAutor = page.obterTexto(By.xpath(v.txtOsMaisDesejados_NomeAutor));
        System.out.println("Nome do livro: "+this.nomeDoLivro);
        System.out.println("Nome do autor: "+this.nomeDoAutor);

        geradorPDF.evidenciaElemento("Nome do Livro: "+this.nomeDoLivro, page.getCurrentRunningDriver());
        geradorPDF.evidenciaElemento("Nome do Autor: "+this.nomeDoAutor, page.getCurrentRunningDriver());
    }

    @Entao("Obter o CNPJ da Amazon")
    public void obterOCNPJDaAmazon() throws InterruptedException, IOException {
        page.scroll(999);
        page.moverParaElemento(By.xpath(v.txtAmazonCNPJ));
        geradorPDF.evidenciaElemento("CNPJ Amazon: "+page.obterTexto(By.xpath(v.txtAmazonCNPJ)), page.getCurrentRunningDriver());
        page.validarTexto(By.xpath(v.txtAmazonCNPJ),"Amazon Serviços de Varejo do Brasil Ltda. | CNPJ 15.436.940/0001-03");
        this.amazonaCNPJ = page.obterTexto(By.xpath(v.txtAmazonCNPJ));
    }

    //////////////////

    @Dado("Que eu acessei o menu Ofertas do Dia")
    public void queEuAcesseiOMenuOfertasDoDia() throws MalformedURLException, InterruptedException {
        geradorPDF.evidenciaElemento("Clicar em Ofertas do Dia", page.getCurrentRunningDriver());
        page.clicar(By.xpath(v.btnOfertasDoDia));
        page.validarElementoExibido(By.xpath(v.txtOfertasDoDia));
    }

    @Quando("Eu clicar em uma das ofertas do dia")
    public void euClicarEmUmaDasOfertasDoDia() throws InterruptedException, MalformedURLException {
        page.scroll(120);

        geradorPDF.evidenciaElemento("Clicar em Ofertas 1", page.getCurrentRunningDriver());
        page.clicar(By.xpath(v.lblOfertas1));
        page.esperar(2500);
        geradorPDF.evidenciaElemento("Resultado Ofertas 1", page.getCurrentRunningDriver());

        page.esperar(1000);

        geradorPDF.evidenciaElemento("Clicar em Ofertas 2", page.getCurrentRunningDriver());
        page.clicar(By.xpath(v.lblOfertas2));
        page.esperar(2500);
        geradorPDF.evidenciaElemento("Resultado Ofertas 2", page.getCurrentRunningDriver());

        page.esperar(1000);

        geradorPDF.evidenciaElemento("Clicar em Ofertas 3", page.getCurrentRunningDriver());
        page.clicar(By.xpath(v.lblOfertas3));
        page.esperar(2500);
        geradorPDF.evidenciaElemento("Resultado Ofertas 3", page.getCurrentRunningDriver());
    }

    @Entao("Devo visualizar a lista dos produtos")
    public void devoVisualizarAListaDosProdutos() throws MalformedURLException, InterruptedException {
        geradorPDF.evidenciaElemento("Opções selecionadas", page.getCurrentRunningDriver());
    }
}
