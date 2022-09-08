package br.com.amazon.variables;

import org.openqa.selenium.support.FindBy;

public class amazonVariaveis {
    @FindBy public String btnMenuTodos = "//header/div[@id='navbar']/div[@id='nav-main']/div[1]/a[1]/i[1]";//xpath
    @FindBy public String btnComprarPorCategoria_VerTudo = "//body/div[@id='hmenu-container']/div[@id='hmenu-canvas']/div[@id='hmenu-content']/ul[1]/li[19]/a[1]";//xpath
    @FindBy public String btnComprarPorCategoria_Livros = "//div[contains(text(),'Livros')]";//xpath
    @FindBy public String btnComprarPorCategoria_HQSMANGAS = "//a[contains(text(),'HQs e Mangás')]";//xpath
    @FindBy public String btnOfertasDoDia = "//a[contains(text(),'Ofertas do Dia')]";//xpath

    @FindBy public String txtComprarPorCategoria = "//div[contains(text(),'comprar por categoria')]";//xpath
    @FindBy public String txtOsMaisDesejados = "//span[contains(text(),'Os mais desejados')]";//xpath
    @FindBy public String txtConheçaAsSeriesDeHQsDeMangas = "/h2[contains(text(),'Conheça as séries de HQs e de Mangás')]";//xpath
    @FindBy public String txtOsMaisDesejados_NomeLivro = "//*[@id=\"acs-product-block-0\"]/a/span/span[2]";//xpath
    @FindBy public String txtOsMaisDesejados_NomeAutor = "//*[@id=\"acs-product-block-0\"]/span/span[2]";//xpath
    @FindBy public String txtAmazonCNPJ = "//li[contains(text(),'Amazon Serviços de Varejo do Brasil Ltda. | CNPJ 1')]";//xpath
    @FindBy public String txtOfertasDoDia = "//h1[contains(text(),'Ofertas e Promoções')]";//xpath

    @FindBy public String lblOfertas1 = "//*[@id=\"anonCarousel1\"]/ol/li[2]/div/span[1]/img";//xpath
    @FindBy public String lblOfertas2 = "//*[@id=\"anonCarousel1\"]/ol/li[3]/div/span[1]/img";//xpath
    @FindBy public String lblOfertas3 = "//*[@id=\"anonCarousel1\"]/ol/li[4]/div/span[1]/img";//xpath
}
