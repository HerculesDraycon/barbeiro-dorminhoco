/* ***************************************************************
* Autor............: Hercules Sampaio Oliveira
* Matricula........: 202310486
* Inicio...........: 05/06/2024
* Ultima alteracao.: 15/06/2024
* Nome.............: Cliente.java
* Funcao...........: A classe extende Thread e todos os parametros para instanciar os
clientes sao criados nela.
*************************************************************** */

package model;

import controller.ControllerAnimacao;
import javafx.application.Platform;

public class Cliente extends Thread {

  private ControllerAnimacao controller;  //Declaracao do controller

  /* ***************************************************************
 * Metodo: Construtor
 * Funcao: Define a passagem de parametros para que o objeto seja instanciado e manipulado
 * Parametros: ControllerAnimacao controller
 * Retorno: Nao ha retorno
 *************************************************************** */

  public Cliente(ControllerAnimacao controller) {
    this.controller = controller;
  }

  /* ***************************************************************
 * Metodo: run
 * Funcao: Metodo responsavel pela sequencia de comandos e chamadas de metodos do objeto animado
 * Parametros: Nao ha parametros
 * Retorno: void
 *************************************************************** */
  public void run(){

    try{

      ControllerAnimacao.mutex.acquire();  //Adquire o mutex

      if(ControllerAnimacao.naFila < ControllerAnimacao.vagas){

        ControllerAnimacao.naFila++;  //Com vaga disponive, incrementa um na fila de espera
        ocuparCadeira();              //Chama animacao de ocupar uma cadeira livre

        ControllerAnimacao.clientes.release();    //Libera o semaforo dos clientes
        ControllerAnimacao.mutex.release();       //Libera o mutex
        ControllerAnimacao.barbeiro.acquire();    //Adquire o semaforo do barbeiro, indicando interesse

        sleep(10);    //Sleep para suavizar a visibilidade na GUI
        livrarCadeira();     //Chama a animacao de liberar uma cadeira

      } else {
        ControllerAnimacao.mutex.release();  //Na condicao de vagas lotadas, libera o mutex adquirido
        salaoLotado();                       //Chama a animacao de salao lotado
      }

    } catch(Exception e){
      e.printStackTrace();
    }

  }

  /* ***************************************************************
 * Metodo: ocuparCadeira
 * Funcao: Percorre os elementos visuais dos clientes e baseado no lugar disponivel, ocupa
 * a cadeira correspondente. Caso contrario, nao ocupa nenhum lugar.
 * Parametros: Nao ha parametros
 * Retorno: void
 *************************************************************** */
  private void ocuparCadeira(){

    if(!controller.getSentado1().isVisible()){
      controller.getSentado1().setVisible(true);
    } else if(!controller.getSentado2().isVisible()){
      controller.getSentado2().setVisible(true);
    } else if(!controller.getSentado3().isVisible()){
      controller.getSentado3().setVisible(true);
    } else if(!controller.getSentado4().isVisible()){
      controller.getSentado4().setVisible(true);
    } else if(!controller.getSentado5().isVisible()){
      controller.getSentado5().setVisible(true);
    }

  }

  /* ***************************************************************
 * Metodo: livrarCadeira
 * Funcao: Percorre os elementos visuais dos clientes e baseado no ultimo lugar
 * ocupado, desocupa cadeira correspondente, mandando o cliente para o atendimento.
 * Parametros: Nao ha parametros
 * Retorno: void
 *************************************************************** */
  private void livrarCadeira(){
    //Dado o valor maximo do Slider, o while mantem a thread sem executar nada especifico, simulando um pause
    if(controller.getVeloCorte() == 600){

      while(controller.getVeloCorte() == 600){

        try{
          sleep(1);
        } catch(Exception e){
          e.printStackTrace();
        }

      }

    }
    //Verificacao dos lugares ocupados para a executar a animacao de fila de espera prosseguindo
    if(controller.getSentado5().isVisible()){

      controller.getSentado5().setVisible(false);

      try{
        controller.getClienteEmPe4().setVisible(true);
        sleep(150);
        controller.getClienteEmPe4().setVisible(false);
      } catch(Exception e){
        e.printStackTrace();
      }

      try{
        controller.getClienteEmPe3().setVisible(true);
        sleep(150);
        controller.getClienteEmPe3().setVisible(false);
      } catch(Exception e){
        e.printStackTrace();
      }

      try{
        controller.getClienteEmPe2().setVisible(true);
        sleep(150);
        controller.getClienteEmPe2().setVisible(false);
      } catch(Exception e){
        e.printStackTrace();
      }

      try{
        controller.getClienteEmPe1().setVisible(true);
        sleep(150);
        controller.getClienteEmPe1().setVisible(false);
      } catch(Exception e){
        e.printStackTrace();
      }

    } else if(controller.getSentado4().isVisible()){

      controller.getSentado4().setVisible(false);

      try{
        controller.getClienteEmPe3().setVisible(true);
        sleep(150);
        controller.getClienteEmPe3().setVisible(false);
      } catch(Exception e){
        e.printStackTrace();
      }

      try{
        controller.getClienteEmPe2().setVisible(true);
        sleep(150);
        controller.getClienteEmPe2().setVisible(false);
      } catch(Exception e){
        e.printStackTrace();
      }

      try{
        controller.getClienteEmPe1().setVisible(true);
        sleep(150);
        controller.getClienteEmPe1().setVisible(false);
      } catch(Exception e){
        e.printStackTrace();
      }

    } else if(controller.getSentado3().isVisible()){

      controller.getSentado3().setVisible(false);

      try{
        controller.getClienteEmPe2().setVisible(true);
        sleep(150);
        controller.getClienteEmPe2().setVisible(false);
      } catch(Exception e){
        e.printStackTrace();
      }

      try{
        controller.getClienteEmPe1().setVisible(true);
        sleep(150);
        controller.getClienteEmPe1().setVisible(false);
      } catch(Exception e){
        e.printStackTrace();
      }

    } else if(controller.getSentado2().isVisible()){

      controller.getSentado2().setVisible(false);

      try{
        controller.getClienteEmPe1().setVisible(true);
        sleep(150);
        controller.getClienteEmPe1().setVisible(false);
      } catch(Exception e){
        e.printStackTrace();
      }

    } else if(controller.getSentado1().isVisible()){

      controller.getSentado1().setVisible(false);
      
      try{
        controller.getClienteEmPe5().setVisible(true);
        sleep(250);
        controller.getClienteEmPe5().setVisible(false);
      } catch(Exception e){
        e.printStackTrace();
      }

    }

    controller.cortando();    //Chama a animacao de cortar o cabelo do cliente

  }

  /* ***************************************************************
 * Metodo: salaoLotado
 * Funcao: Basea-se na visibilidade do ultimo cliente na fila e nesse caso mantem
 * a Thread em laco de sleep ate que um lugar fique disponivel novamente.
 * Parametros: Nao ha parametros
 * Retorno: void
 *************************************************************** */
  private void salaoLotado(){
    //Deteccao do cliente que se depara com o salao lotado e faz com que o while simule um pause na thread
    if(controller.getClienteFalando().isVisible()){

      while(controller.getClienteFalando().isVisible()){

        try{
          sleep(1);
        } catch(Exception e){
          e.printStackTrace();
        }

      }

    }
    
    //Coordenacao da animacao do cliente que se depara com o salao lotado
    Platform.runLater(() -> {controller.clienteFalando();});

    try{
      sleep(400);
    } catch(Exception e){
      e.printStackTrace();
    }

    Platform.runLater(() -> {controller.clienteDesiste();});

    try{
      sleep(400);
    } catch(Exception e){
      e.printStackTrace();
    }

  }

  /* ***************************************************************
 * Metodo: getController
 * Funcao: Retorna a variavel da classe
 * Parametros: Nao ha parametros
 * Retorno: ControllerAnimacao controller
 *************************************************************** */
  public ControllerAnimacao getController() {
    return controller;
  }

}
