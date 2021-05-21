#language: pt

  @Amazon
  Funcionalidade: Automação na página da Amazon

    @Cenario1
    Cenario: Validar titulo da pagina de HQs e mangas da Amazon
      Dado Que eu acessei o menu Todos - Comprar Por Categoria - Livros
      E Abrir a opcao HQs e Mangas
      Entao Devo validar o titulo da pagina


    @Cenario2
    Cenario: Validar nome do livro e autor da pagina de HQs e mangas da Amazon
      Dado Que eu acessei o menu Todos - Comprar Por Categoria - Livros
      Quando Abrir a opcao HQs e Mangas em nova aba
      E Ir na Opcao Os Mais Desejados
      Entao Devo obter Nome do livro e autor
      E Obter o CNPJ da Amazon


    @Cenario3
    Cenario: Validar lista de produtos Ofertas do Dia da Amazon
      Dado Que eu acessei o menu Ofertas do Dia
      Quando Eu clicar em uma das ofertas do dia
      Entao Devo visualizar a lista dos produtos
