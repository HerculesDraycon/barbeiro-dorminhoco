/* ***************************************************************
 * Autor............: Hercules Sampaio Oliveira
 * Matricula........: 202310486
 * Inicio...........: 05/06/2024
 * Ultima alteracao.: 15/06/2024
 * Nome.............: Principal.java
 * Funcao...........: A classe Principal eh a que carrega o metodo main() com
 o metodo launch() e tambem faz o metodo start() iniciar o Stage, iniciando a
 primeira tela que eh indexada no arquivo fxml.
 *************************************************************** */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import controller.ControllerPrincipal;
import controller.ControllerAnimacao;

public class Principal extends Application {

  /* ***************************************************************
 * Metodo: main
 * Funcao: Eh o metodo responsavel por reunir todas as classes que serao compiladas e carregar o metodo launch() para que o JavaFX seja executado
 * Parametros: String[] args
 * Retorno: void
 *************************************************************** */
  public static void main(String[] args) {
    launch(args);
  }

  /* ***************************************************************
 * Metodo: start
 * Funcao: Eh o metodo responsavel por rodar o JavaFX, iniciando o Stage e a primeira scene posicionada
 * Parametros: Stage primaryStage, que chama os metodos de exibicao no Stage
 * Retorno: void
 *************************************************************** */
  @Override
  public void start(Stage primaryStage) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("/view/fxmlPrincipal.fxml"));  //Carrega o arquivo fxml
    Scene scene = new Scene(root);            //Instancia a primeira scene
    Image icon = new Image("/img/icone.png");     //Instancia o icone do projeto

    primaryStage.getIcons().add(icon);             //Adiciona o icone no Stage
    primaryStage.setTitle("Projeto do Barbeiro Dorminhoco");   //Adiciona um titulo no Stage
    primaryStage.setScene(scene);                  //Adiciona a scene no Stage
    primaryStage.setResizable(false);             //Bloqueia a capacidade de redimensionar a tela
    primaryStage.show();                         //Inicia o Stage
    primaryStage.setOnCloseRequest(e -> {System.exit(0);});  //Faz com que a acao de fechar a janela pare a execucao do programa

  }

}