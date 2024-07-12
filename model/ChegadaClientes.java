/* ***************************************************************
* Autor............: Hercules Sampaio Oliveira
* Matricula........: 202310486
* Inicio...........: 05/06/2024
* Ultima alteracao.: 15/06/2024
* Nome.............: ChegadaClientes.java
* Funcao...........: A classe extende Thread e todos os parametros para instanciar o
sistema de chegada de clientes e organizar a ordem sao criados nela.
*************************************************************** */

package model;

import controller.ControllerAnimacao;

public class ChegadaClientes extends Thread {

  public static boolean threadRun = true;  //Flag com acesso global para controle da Thread
  private ControllerAnimacao controller;   //Declaracao do controller

  /* ***************************************************************
 * Metodo: Construtor
 * Funcao: Define a passagem de parametros para que o objeto seja instanciado e manipulado
 * Parametros: ControllerAnimacao controller
 * Retorno: Nao ha retorno
 *************************************************************** */
  public ChegadaClientes(ControllerAnimacao controller){
    this.controller = controller;
  }

  /* ***************************************************************
 * Metodo: run
 * Funcao: Metodo responsavel pela sequencia de comandos e chamadas de metodos do objeto animado
 * Parametros: Nao ha parametros
 * Retorno: void
 *************************************************************** */
  public void run(){

    while(threadRun){
      //Dado o valor maximo do Slider, o while mantem a thread sem executar nada especifico, simulando um pause
      if(controller.getVeloChegada() == 1400){

        while(controller.getVeloChegada() == 1400){

          try{
            sleep(1);
          } catch(Exception e){
            e.printStackTrace();
          }

        }

      }
      //Instancia e start de objetos Cliente baseado na velocidade selecionada pelo usuario
      Cliente cliente = new Cliente(controller);
      cliente.start();

      try{
        Thread.sleep(controller.getVeloChegada());
      } catch(Exception e){
        e.printStackTrace();
      }

    }

  }

}