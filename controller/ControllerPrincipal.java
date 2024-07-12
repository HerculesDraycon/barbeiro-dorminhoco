/* ***************************************************************
* Autor............: Hercules Sampaio Oliveira
* Matricula........: 202310486
* Inicio...........: 05/06/2024
* Ultima alteracao.: 15/06/2024
* Nome.............: ControllerPrincipal.java
* Funcao...........: Essa classe eh o controller da primeira scene iniciada
e faz com que o botao transicione para a proxima scene.
*************************************************************** */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ControllerPrincipal implements Initializable{
  // Criacao dos Stages utilizados na primeira tela
  Stage stage; 
  Stage stageSobre = new Stage();

  Scene scene, sceneSobre; // Criacao das scenes utilizadas no primeiro Stage
  Parent root, rootSobre; // Criacao dos Parents dos Loaders de transicao para novas scenes

  @FXML
  private Button botaoIniciar;  // Criacao do botao Iniciar da primeira scene

  @FXML
  private Button botaoSobre;  //Criacao do botao da secao Sobre na primeira scene

  /*
   * ***************************************************************
   * Metodo: initialize
   * Funcao: Inicializar os objetos na tela apos receber os metodos de construcao da animacao
   * Parametros: URL location e ResourceBundle resources
   * Retorno: void
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
      
  }

  /*
   * ***************************************************************
   * Metodo: botaoIniciar
   * Funcao: Suporta o clique para que a acao de transicao de scene seja executada e o arquivo fxml seja carregado
   * Parametros: ActionEvent event, para que a acao seja suportada e execute as linhas de codigo corretamente
   * Retorno: void
   */
  @FXML
  public void botaoIniciar(ActionEvent e) throws IOException{

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmlAnimacao.fxml")); // Carrega o arquivo fxml
    root = loader.load(); // Carrega a segunda scene
    scene = new Scene(root); // Passa o valor do Parent para a segunda scene
    stage = (Stage) botaoIniciar.getScene().getWindow(); // Atribui a acao do botao a iniciar a proxima scene no Stage
    stage.setScene(scene); // Adiciona a scene no Stage
    stage.show(); // Inicia o Stage

  }

  /*
   * ***************************************************************
   * Metodo: botaoSobre
   * Funcao: Suporta o clique para que a acao que carrega a tela de apresentacao de informacoes
   * Parametros: ActionEvent event, para que a acao seja suportada e execute as linhas de codigo corretamente
   * Retorno: void
   */
  @FXML
  public void botaoSobre(ActionEvent e) throws IOException{

    Image icon = new Image("/img/infoIcon.png");    //Instancia o icone do projeto

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmlSobre.fxml"));  // Carrega o arquivo fxml
    rootSobre = loader.load();             // Carrega a segunda scene
    sceneSobre = new Scene(rootSobre);    // Passa o valor do Parent para a segunda scene
    stageSobre.getIcons().add(icon);     //Adiciona o icone no Stage
    stageSobre.setTitle("Encarte");     //Adiciona um titulo no Stage
    stageSobre.setScene(sceneSobre);   // Adiciona a scene no Stage
    stageSobre.setResizable(false);   //Definicao pelo autor do codigo que a tela nao mudara de tamanho
    stageSobre.show();               // Inicia o Stage

  }

}
