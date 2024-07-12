/* ***************************************************************
* Autor............: Hercules Sampaio Oliveira
* Matricula........: 202310486
* Inicio...........: 05/06/2024
* Ultima alteracao.: 15/06/2024
* Nome.............: ControllerAnimacao.java
* Funcao...........: Essa classe faz a coordenacao da cena da animacao, 
estando referenciada no arquivo fxml da animacao, ela inicializa a animacao
e instancia os objetos. Seus metodos executam as instrucoes dos modulos interativos
na tela, como o botao Voltar e o Reset.
*************************************************************** */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Barbeiro;
import model.ChegadaClientes;

public class ControllerAnimacao implements Initializable{

  Stage stage; // Criacao do Stage da segunda scene
  Scene scene; // Criacao da segunda scene
  Parent root; // Criacao do Parent do Loader de transicao da segunda para a primeira scene

  @FXML
  private ImageView sentado1, sentado2, sentado3, sentado4, sentado5;  //Declaracao dos ImageViews dos clientes sentados

  @FXML
  private ImageView clienteEmPe1, clienteEmPe2, clienteEmPe3, clienteEmPe4, clienteEmPe5;  //Declaracao dos ImageViews dos clientes trocando de cadeira

  @FXML
  private ImageView clienteAtendido;  //Declaracao da ImageView do cliente sendo atendido

  @FXML
  private ImageView clienteSaindo;  //Declaracao da ImageView do cliente saindo com corte feito

  @FXML
  private ImageView clienteFalando;  //Declaraco da ImageView do cliente que se depara com o salao lotado

  @FXML
  private ImageView barbeiroDormindo;  //Declaracao da ImageView do barbeiro dormindo na cadeira

  @FXML
  private ImageView barbeiroAcordado;  //Declaracao da ImageView do barbeiro acordado e atendendo

  @FXML
  private Slider velocidadeChegada, velocidadeCorte;  //Declaracao dos Slider de controle das velocidades

  @FXML
  private Button botaoVoltar, botaoReset;    // Criacao do botao voltar e reset da segunda scene com referencia FXML

  Barbeiro barbeiroObj;  //Declaracao da variavel da classe Barbeiro que sera responsavel por passar a instancia startar a Thread
  ChegadaClientes chegadaClientesObj;  //Declaracao da variavel da classe ChegadaClientes que sera responsavel por passar a instancia startar a Thread

  public static final int vagas = 5;    //Declaracao de final int com as 5 vagas disponibilizadas
  public static Semaphore mutex;        //Declaracao do semaforo mutex
  public static Semaphore clientes;     //Declaracao do semaforo de clientes
  public static Semaphore barbeiro;     //Declaracao do semaforo do barbeiro
  public static int naFila;             //Variavel que manipula a quantidade de clientes esperando

  /*
   * ***************************************************************
   * Metodo: initialize
   * Funcao: Inicializar os objetos na tela apos receber os metodos de construcao da animacao
   * Parametros: URL location e ResourceBundle resources
   * Retorno: void
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //Predefinindo a visibilidade e posicao inicial das ImageViews
    sentado1.setVisible(false);
    sentado1.setLayoutX(476); 
    sentado1.setLayoutY(217);  

    sentado2.setVisible(false);
    sentado2.setLayoutX(377);
    sentado2.setLayoutY(217);

    sentado3.setVisible(false);
    sentado3.setLayoutX(267);
    sentado3.setLayoutY(217);

    sentado4.setVisible(false);
    sentado4.setLayoutX(159);
    sentado4.setLayoutY(217);

    sentado5.setVisible(false);
    sentado5.setLayoutX(55);
    sentado5.setLayoutY(217);

    clienteEmPe1.setVisible(false);
    clienteEmPe1.setLayoutX(421);
    clienteEmPe1.setLayoutY(213);
    
    clienteEmPe2.setVisible(false);
    clienteEmPe2.setLayoutX(315);
    clienteEmPe2.setLayoutY(213);

    clienteEmPe3.setVisible(false);
    clienteEmPe3.setLayoutX(212);
    clienteEmPe3.setLayoutY(213);

    clienteEmPe4.setVisible(false);
    clienteEmPe4.setLayoutX(104);
    clienteEmPe4.setLayoutY(213);

    clienteFalando.setVisible(false);
    clienteFalando.setLayoutX(-72);
    clienteFalando.setLayoutY(280);
    clienteFalando.setRotate(35);

    clienteEmPe5.setVisible(false);
    clienteEmPe5.setLayoutX(575);
    clienteEmPe5.setLayoutY(247);

    barbeiroDormindo.setVisible(true);
    barbeiroDormindo.setLayoutX(751);
    barbeiroDormindo.setLayoutY(266);

    barbeiroAcordado.setVisible(false);
    barbeiroAcordado.setLayoutX(603);
    barbeiroAcordado.setLayoutY(225);
    //Instanciando Barbeiro e ChegadaClientes
    barbeiroObj = new Barbeiro(this);
    chegadaClientesObj = new ChegadaClientes(this);
    //Posicoes inicias dos semaforos
    mutex = new Semaphore(1);
    barbeiro = new Semaphore(0);
    clientes = new Semaphore(0);


    barbeiroObj.start();           //Startando a thread a partir do objeto Barbeiro instanciado
    chegadaClientesObj.start();    //Startando a thread a partir do objeto ChegadaClientes instanciado

  }

  /* ***************************************************************
 * Metodo: cortando
 * Funcao: Transforma a visibilidade das ImageViews de acordo o cenario especifico
 * Parametros: Nao ha parametros
 * Retorno: void
 *************************************************************** */
  public void cortando(){
    barbeiroDormindo.setVisible(false);
    barbeiroAcordado.setVisible(true);
    clienteAtendido.setVisible(true);
    clienteSaindo.setVisible(false);
  }
  
  /* ***************************************************************
 * Metodo: corteFinalizado
 * Funcao: Transforma a visibilidade das ImageViews de acordo o cenario especifico
 * Parametros: Nao ha parametros
 * Retorno: void
 *************************************************************** */
  public void corteFinalizado(){
    clienteAtendido.setVisible(false);
    clienteSaindo.setVisible(true);
  }

  /* ***************************************************************
 * Metodo: salaoVazio
 * Funcao: Transforma a visibilidade das ImageViews de acordo o cenario especifico
 * Parametros: Nao ha parametros
 * Retorno: void
 *************************************************************** */
  public void salaoVazio(){
    barbeiroAcordado.setVisible(false);
    barbeiroDormindo.setVisible(true);
    clienteAtendido.setVisible(false);
    clienteSaindo.setVisible(false);
  }

  /* ***************************************************************
 * Metodo: clienteFalando
 * Funcao: Transforma a visibilidade das ImageViews de acordo o cenario especifico
 * Parametros: Nao ha parametros
 * Retorno: void
 *************************************************************** */
  public void clienteFalando(){
    clienteFalando.setVisible(true);
  }

  /* ***************************************************************
 * Metodo: clienteDesiste
 * Funcao: Transforma a visibilidade das ImageViews de acordo o cenario especifico
 * Parametros: Nao ha parametros
 * Retorno: void
 *************************************************************** */
  public void clienteDesiste(){
    clienteFalando.setVisible(false);
  }

  /*
   * ***************************************************************
   * Metodo: botaoVoltar
   * Funcao: Suporta o clique para que a acao de transicao de scene seja executada e o arquivo fxml seja
   * carregado, bem como reinicia os parametros de operacao dos metodos utilizados.
   * Parametros: ActionEvent event, para que a acao seja suportada e execute as linhas de codigo corretamente
   * Retorno: void
   */
  @FXML
  public void botaoVoltar(ActionEvent e) throws IOException{

    Barbeiro.threadRun = false;           //Modificacao da flag para encerrar a thread do barbeiro
    ChegadaClientes.threadRun = false;    //Modificacao da flag para encerrar as threads de clientes

    //Redefinicao dos semaforos e variaveis de permissao
    barbeiroObj = new Barbeiro(this);
    chegadaClientesObj = new ChegadaClientes(this);
    mutex = new Semaphore(1);
    clientes = new Semaphore(0);
    barbeiro = new Semaphore(0);
    naFila = 0;

    velocidadeChegada.setValue(800);  //Redefinicao da velocidade da chegada de clientes
    velocidadeCorte.setValue(400);    //Redefinicao da velocidade do corte de cabelo

    //Redefinicao da visibilidade das ImageViews
    barbeiroDormindo.setVisible(true);
    sentado1.setVisible(false);
    sentado2.setVisible(false);
    sentado3.setVisible(false);
    sentado4.setVisible(false);
    sentado5.setVisible(false);
    clienteEmPe1.setVisible(false);
    clienteEmPe2.setVisible(false);
    clienteEmPe3.setVisible(false);
    clienteEmPe4.setVisible(false);
    clienteEmPe5.setVisible(false);
    clienteSaindo.setVisible(false);
    clienteAtendido.setVisible(false);
    clienteFalando.setVisible(false);
    barbeiroAcordado.setVisible(false);

    Barbeiro.threadRun = true;           //Modificacao da flag para iniciar a thread do barbeiro
    ChegadaClientes.threadRun = true;    //Modificacao da flag para iniciar as threas dos clientes

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmlPrincipal.fxml")); // Carrega o arquivo fxml
    root = loader.load();  // Carrega a primeira scene
    scene = new Scene(root);  // Passa o valor do Parent para a primeira scene
    stage = (Stage) botaoVoltar.getScene().getWindow();  // Atribui a acao do botao a iniciar a scene anterior no Stage
    stage.setScene(scene);  // Adiciona a scene no Stage
    stage.show();  // Inicia o Stage

  }

  /*
   * ***************************************************************
   * Metodo: botaoReset
   * Funcao: Reinicia os parametros de operacao dos metodos utilizados
   * Parametros: ActionEvent event, para que a acao seja suportada e execute as linhas de codigo corretamente
   * Retorno: void
   */
  @FXML
  public void botaoReset(ActionEvent e){

    Barbeiro.threadRun = false;           //Modificacao da flag para encerrar a thread do barbeiro
    ChegadaClientes.threadRun = false;    //Modificacao da flag para encerrar as threads de clientes

    //Redefinicao dos semaforos e variaveis de permissao
    barbeiroObj = new Barbeiro(this);
    chegadaClientesObj = new ChegadaClientes(this);
    mutex = new Semaphore(1);
    clientes = new Semaphore(0);
    barbeiro = new Semaphore(0);
    naFila = 0;

    velocidadeChegada.setValue(800);  //Redefinicao da velocidade da chegada de clientes
    velocidadeCorte.setValue(400);    //Redefinicao da velocidade do corte de cabelo

    //Redefinicao da visibilidade das ImageViews
    barbeiroDormindo.setVisible(true);
    sentado1.setVisible(false);
    sentado2.setVisible(false);
    sentado3.setVisible(false);
    sentado4.setVisible(false);
    sentado5.setVisible(false);
    clienteEmPe1.setVisible(false);
    clienteEmPe2.setVisible(false);
    clienteEmPe3.setVisible(false);
    clienteEmPe4.setVisible(false);
    clienteEmPe5.setVisible(false);
    clienteSaindo.setVisible(false);
    clienteAtendido.setVisible(false);
    clienteFalando.setVisible(false);
    barbeiroAcordado.setVisible(false);

    Barbeiro.threadRun = true;           //Modificacao da flag para iniciar a thread do barbeiro
    ChegadaClientes.threadRun = true;    //Modificacao da flag para iniciar as threas dos clientes

  }

  /* ***************************************************************
 * Metodo: getVeloChegada
 * Funcao: Extrai o valor passado pelo Slider referido, transforma-o em valor numerico e depois
 * para o tipo int para que possa ser passado no metodo sleep()S
 * Parametros: nao ha parametros
 * Retorno: int conversion, que eh o valor do Slider finalmente convertido em int
 *************************************************************** */

  public int getVeloChegada() {
    double get = velocidadeChegada.getValue();  //Extrai e armazena um valor numerico tipo double
    int conversion = (int)get;  //Converte o valor de double para int para que possa ser passado no metodo sleep()
    return conversion;
  }

  /* ***************************************************************
 * Metodo: getVeloCorte
 * Funcao: Extrai o valor passado pelo Slider referido, transforma-o em valor numerico e depois
 * para o tipo int para que possa ser passado no metodo sleep()
 * Parametros: nao ha parametros
 * Retorno: int conversion, que eh o valor do Slider finalmente convertido em int
 *************************************************************** */

  public int getVeloCorte() {
    double get = velocidadeCorte.getValue();  //Extrai e armazena um valor numerico tipo double
    int conversion = (int)get;  //Converte o valor de double para int para que possa ser passado no metodo sleep()
    return conversion;
  }

  /* ***************************************************************
 * Metodo: get
 * Funcao: Retorna o valor da variavel no objeto referido
 * Parametros: nao ha parametros
 * Retorno: ImageView do respectivo objeto
 *************************************************************** */

  public ImageView getClienteAtendido() {
    return clienteAtendido;
  }

  public ImageView getClienteSaindo() {
    return clienteSaindo;
  }

  public ImageView getBarbeiroDormindo() {
    return barbeiroDormindo;
  }

  public ImageView getBarbeiroAcordado() {
    return barbeiroAcordado;
  }

  public ImageView getSentado1() {
    return sentado1;
  }

  public ImageView getSentado2() {
    return sentado2;
  }

  public ImageView getSentado3() {
    return sentado3;
  }

  public ImageView getSentado4() {
    return sentado4;
  }

  public ImageView getSentado5() {
    return sentado5;
  }

  public ImageView getClienteEmPe1() {
    return clienteEmPe1;
  }

  public ImageView getClienteEmPe2() {
    return clienteEmPe2;
  }

  public ImageView getClienteEmPe3() {
    return clienteEmPe3;
  }

  public ImageView getClienteEmPe4() {
    return clienteEmPe4;
  }

  public ImageView getClienteEmPe5() {
    return clienteEmPe5;
  }

  public ImageView getClienteFalando() {
    return clienteFalando;
  }

}