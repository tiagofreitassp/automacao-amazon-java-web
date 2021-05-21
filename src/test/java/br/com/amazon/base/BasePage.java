package br.com.amazon.base;

import br.com.amazon.driver.DriverWeb;
import br.com.amazon.utils.InfraUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasePage {
    public Boolean isPresent;
    public WebDriverWait wait;
    private WebDriver driver;
    private static String nomePasta;
    private File pastaEvidencias;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clicarRadioButton(int posicao) throws InterruptedException {
        esperar(800);
        //Monta uma lista com todos os elementos de nome radio
        List<WebElement> radio = driver.findElements(By.name("radio"));
        //Pega a posicao 2 da lista (Equivalente a palavra Boa) e clicar nela
        radio.get(posicao).click();
    }

    public void clicarCheckBox(int posicao) throws InterruptedException {
        esperar(800);
        //Monta uma lista com todos os elementos de nome checkbox
        List<WebElement> checkbox = driver.findElements(By.cssSelector("input[type='checkbox']"));
        //Pega a posicao 2 da lista (Equivalente a palavra Boa) e clicar nela
        checkbox.get(posicao).click();
    }

    public void clicarViewBox(int posicao, By by) throws InterruptedException {
        esperar(800);
        //Monta uma lista com todos os elementos de nome svg
        List<WebElement> svg = driver.findElements(by);
        //Pega a posicao 2 da lista (Equivalente a palavra Boa) e clicar nela
        svg.get(posicao).click();
    }

    public void clicarSemEsperar(By by) throws MalformedURLException, InterruptedException {
        esperar(800);
        selecionarElemento(by);
        driver.findElement(by).click();
    }

    public void escrever(By by, String texto) throws MalformedURLException, InterruptedException {
        aguardarElemento(by);
        selecionarElemento(by);
        driver.findElement(by).sendKeys(texto);
    }

    public void clicar(By by) throws MalformedURLException, InterruptedException {
        esperar(1000);
        selecionarElemento(by);
        driver.findElement(by).click();
    }

    public void esperar(long tempo) throws InterruptedException {
        Thread.sleep(tempo);
    }

    public void validarElementoExibido(By by){
        aguardarElemento(by);
        selecionarElemento(by);
        driver.findElement(by).isDisplayed();
    }

    public void moverParaElemento(By by){
        WebElement elemento = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemento);
    }

    public void validarTexto(By by, String texto) throws MalformedURLException {
        aguardarElemento(by);
        selecionarElemento(by);
        System.out.println("Texto esperado: "+texto);
        System.out.println("Texto obtido: "+obterTexto(by));
        Assert.assertEquals(texto, obterTexto(by));
    }

    public void validarTextoPaginaWeb(String texto) throws MalformedURLException {
        System.out.println("Texto esperado: "+texto);
        System.out.println("Texto obtido: "+obterTituloDaPagina());
        Assert.assertEquals(texto, obterTituloDaPagina());
    }

    public String obterTituloDaPagina() throws MalformedURLException {
        String titulo = driver.getTitle();
        System.out.println("Titulo da Pagina: "+titulo);
        return titulo;
    }

    public void waitUntilPageLoadComplete() {
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete'"));
    }

    public WebDriver waitFrameAndSwitch(By frame) {
        aguardarElemento(frame);
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    public void switchToFrame(By by){
        aguardarElemento(by);
        WebElement el = driver.findElement(by);
        driver.switchTo().frame(el);
    }

    public WebElement getListClick(By by, int posicao){
        List<WebElement> listaElementos = driver.findElements(by);
        WebElement p = listaElementos.get(0);
        return p;
    }

    public String obterTexto(By by) throws MalformedURLException {
        aguardarElemento(by);
        return driver.findElement(by).getText();
    }

    public void aguardarElemento(By by) {
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected WebElement waitAndFindElement(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void limparCampo(By by) throws MalformedURLException, InterruptedException {
        aguardarElemento(by);
        driver.findElement(by).sendKeys(Keys.CONTROL+"a");
        driver.findElement(by).sendKeys(Keys.DELETE);
        esperar(1000);
    }

    public Object executarJS(String cmd, Object... param) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(cmd, param);
    }

    public void scrollUp() throws InterruptedException {
        Thread.sleep(1500);
        JavascriptExecutor jse1 = (JavascriptExecutor)driver;
        jse1.executeScript("window.scrollBy(0,-200)");
        Thread.sleep(1500);
    }

    public void scrollDown() throws InterruptedException {
        Thread.sleep(1500);
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("window.scrollBy(0,200)");
        Thread.sleep(1500);
    }

    public void scroll(long t) throws InterruptedException {
        Thread.sleep(1500);
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("window.scrollBy(0,"+t+")");
        Thread.sleep(1500);
    }

    public void scrollDownClick(By by) throws MalformedURLException, InterruptedException {
        isPresent = driver.findElements(by).size() > 0;
        System.out.println("SIZE FORA DO WHILE:" + isPresent);
        while (isPresent == false) {
            JavascriptExecutor jse2 = (JavascriptExecutor)driver;
            jse2.executeScript("window.scrollBy(0,30)");
            System.out.println("SIZE DENTRO DO WHILE:" + isPresent);
            isPresent = driver.findElements(by).size() > 0;
        }
        clicar(by);
        Thread.sleep(1600);
    }

    public void voltarPaginaBrowser() {
        driver.navigate().back();
    }

    public void atualizarPaginaBrowser() {
        driver.navigate().refresh();
    }

    public void abrirPaginaBrowser(String site) {
        driver.navigate().to(site);
    }

    public void abrirNovaAbaNavegador() {
        String os = InfraUtils.getOsName();
        System.out.println("Sistema Operacional: "+os);

        if (os.equalsIgnoreCase("Mac") || os.equalsIgnoreCase("Unix") ||
                os.equalsIgnoreCase("Mac OS X")) {
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND +"t");
        }else if (os.equalsIgnoreCase("Windows")){
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
        }
    }

    public void alterarAbaNavegador(int aba) {
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(aba));
    }

    public void selecionarElemento(By by){
        WebElement elemento = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border=arguments[1]", elemento, "solid 4px red");
    }
}
