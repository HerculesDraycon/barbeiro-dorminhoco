/* ***************************************************************
* Autor............: Hercules Sampaio Oliveira
* Matricula........: 202310486
* Inicio...........: 05/06/2024
* Ultima alteracao.: 15/06/2024
* Nome.............: Barbeiro.java
* Funcao...........: A classe extende Thread e todos os parametros para instanciar o
barbeiro sao criados nela.
*************************************************************** */

package model;

import controller.ControllerAnimacao;

public class Barbeiro extends Thread {

  public static boolean threadRun = true;  //Flag com acesso global para controle da Thread
  private ControllerAnimacao controller;  //Declaracao do controller

  /* ***************************************************************
 * Metodo: Construtor
 * Funcao: Define a passagem de parametros para que o objeto seja instanciado e manipulado
 * Parametros: ControllerAnimacao controller
 * Retorno: Nao ha retorno
 *************************************************************** */

  public Barbeiro(ControllerAnimacao controller){
    this.controller = controller;
  }

  /* ***************************************************************
 * Metodo: run
 * Funcao: Metodo responsavel pela sequencia de comandos e chamadas de metodos do objeto animado
 * Parametros: Nao ha parametros
 * Retorno: void
 *************************************************************** */
  public void run() {

    while (threadRun) {

      try {
        //Na condicao de salao vazio, chama a animacao do barbeiro dormindo
        if(ControllerAnimacao.naFila == 0){
          controller.salaoVazio();
        }

        sleep(controller.getVeloChegada());

        ControllerAnimacao.clientes.acquire();  //O acquire do cliente eh coordenado pela classe Barbeiro
        ControllerAnimacao.mutex.acquire();     //O barbeiro adquire o mutex
        ControllerAnimacao.naFila--;            //Ao puxar um cliente, o numero de clientes na fila de espera eh decrementado
        ControllerAnimacao.barbeiro.release();  //O barbeiro eh liberado
        ControllerAnimacao.mutex.release();     //O mutex eh liberado

        cortar();                               //A animacao do processo de corte eh chamada

      } catch (Exception exception) {
        exception.printStackTrace();
      }

    }

  }

  /* ***************************************************************
 * Metodo: cortar
 * Funcao: Determina o comportamento da Thread do barbeiro em relacao a
 * execucao do corte
 * Parametros: Nao ha parametros
 * Retorno: void
 *************************************************************** */
  private void cortar(){

    boolean executado = false;  //Boolean setado como false para que o while execute o corte

    while(!executado){
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
      //Baseado na velocidade selecionada pelo usuario, realiza o processo de corte
      if(controller.getClienteAtendido().isVisible()){

        controller.cortando();
  
        try{
          sleep(controller.getVeloCorte());
        } catch(Exception e){
          e.printStackTrace();
        }
  
      }
  
      controller.corteFinalizado();    //Chama a animacao que corresponde ao fim do processo do cliente
      
      try{
        sleep(300);
      } catch(Exception e){
        e.printStackTrace();
      }

      executado = true;  //Boolean setado como true para sair do while

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
