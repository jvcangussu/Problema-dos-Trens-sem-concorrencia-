/* ***************************************************************
* Autor............: Joao Vitor Cangussu Bernardes Oliveira
* Matricula........: 202210559
* Inicio...........: 19/08/2023
* Ultima alteracao.: 01/09/2023
* Nome.............: Controlador da Tela de Percurso dos Trens
* Funcao...........: Controla os elementos gráficos do JavaFX
na tela de percursos do trem, como os controles de velocidade
dos trens, as imagens utilizadas, entre outros
*************************************************************** */
package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Train;

public class TrackScreenController implements Initializable{
  //atributos do FXML
  @FXML
  private ImageView resetButton;  
  @FXML
  private ImageView train1;
  @FXML
  private Slider train1SpeedSlider;
  @FXML
  private ImageView train2;  
  @FXML
  private Slider train2SpeedSlider;

  //instancia dos trens ja definidos como objetos da classe Train
  private static Train brownTrain;
  private static Train blueTrain;

  //Getters e setters
  double getTrain1Speed(){//obtem o valor do slider da velocidade do trem 1
    return train1SpeedSlider.getValue();
  }  
  double getTrain2Speed(){//obtem o valor do slider da velocidade do trem 2
    return train2SpeedSlider.getValue();
  }
  public Train getBrownTrain(){
    return brownTrain;
  }
  public Train getBlueTrain(){
    return blueTrain;
  }
  
  /* ***************************************************************
  * Metodo: setInitialPositions
  * Funcao: Carrega e abre a janela para que o usuario possa escolher
  * a posicao inicial dos trens
  * Parametros: Nao recebe parâmetros
  * Retorno: void
  *************************************************************** */
  public void setInitialPositions() throws IOException{
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(ChoosePositionScreenController.class.getResource("/view/choosePositionScreen.fxml"));//carrega o FXML da tela de escolha de posicao
    AnchorPane page = (AnchorPane) loader.load();//cria uma AnchorPane para receber os atributos FXML carregados
    Stage dialogStage = new Stage();//inicia o stage
    dialogStage.setTitle("Escolha a Posicao Inicial");//define o titulo da pagina
    dialogStage.initStyle(StageStyle.UNDECORATED);//retira a decoracao da pagina para que o usuario nao feche a janela sem escolher uma opcao
    dialogStage.initModality(Modality.APPLICATION_MODAL);//nao deixa que o usuario interaja com outra tela enquanto essa estiver aberta
    Scene scene = new Scene(page);//cria uma cena com o FXML atribuido a AnchorPane
    dialogStage.setScene(scene);//atribui a cena ao stage
    ChoosePositionScreenController controller = loader.getController();//carrega o controller do FXML carregado
    controller.setDialogStage(dialogStage);//seta o stage no controller da tela
    dialogStage.showAndWait();//mostra o stage
  }//fim do metodo setInitialPositions 

  private class MyTimer extends AnimationTimer {
    //handle eh o metodo da classe AnimationTimer que eh executado a cada frame
    @Override
    public void handle(long a) {
      //atualiza a velocidade atual dos trens
      brownTrain.setSpeed(train1SpeedSlider.getValue());
      blueTrain.setSpeed(train2SpeedSlider.getValue());
      //move os trens
      moveTrain(brownTrain);
      moveTrain(blueTrain);
    }//fim do metodo handle

    /* ***************************************************************
    * Metodo: moveTrain
    * Funcao: Move um trem pelo trilho de acordo com sua posicao inicial
    * Parametros: Train train - objeto da classe Train que será o trem movido
    * Retorno: void
    *************************************************************** */
    private void moveTrain(Train train){
      final double SPEED_RATE = 0.035;//SPEED_RATE eh a constante de taxa de velocidade
      double inc = SPEED_RATE * train.getSpeed();//inc eh a quantidade de pixels que sera incrementado nas posicoes X e Y das imagens dos trens a cada frame do timer de movimento proporcional a velocidade do trem definida pelo usuario
      //switch para definir o movimento do trem de acordo com a sua posicao inicial
      switch(train.getInitialPosition()){
        case 1:{//Descreve os movimentos do trem na posicao superior esquerda
          if(train.getImageView().getLayoutY() <= 8){
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
          }
          if(train.getImageView().getLayoutY() > 8 && train.getImageView().getLayoutY() <= 86){
            train.getImageView().setRotate(315);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() + inc);
          }
          if(train.getImageView().getLayoutY() > 86 && train.getImageView().getLayoutY() <= 120){
            train.getImageView().setRotate(0);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
          }
          if(train.getImageView().getLayoutY() > 120 && train.getImageView().getLayoutY() <= 192){
            train.getImageView().setRotate(315);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() + inc);
          }
          if(train.getImageView().getLayoutY() > 192 && train.getImageView().getLayoutY() <= 279){
            train.getImageView().setRotate(0);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
          }
          if(train.getImageView().getLayoutY() > 279 && train.getImageView().getLayoutY() <= 343){
            train.getImageView().setRotate(45);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() - inc);
          }
          if(train.getImageView().getLayoutY() > 343 && train.getImageView().getLayoutY() <= 402){
            train.getImageView().setRotate(0);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
          }
          if(train.getImageView().getLayoutY() > 402 && train.getImageView().getLayoutY() <= 486){
            train.getImageView().setRotate(45);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() - inc);
          }
          if(train.getImageView().getLayoutY() > 486 && train.getImageView().getLayoutY() <= 624){
            train.getImageView().setRotate(0);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
          }
          if(train.getImageView().getLayoutY() > 624){
            train.getImageView().setLayoutX(290);
            train.getImageView().setLayoutY(-135);
          }
          break;
        }//fim case 1
        case 2:{//Descreve os movimentos do trem na posicao superior direita
          if(train.getImageView().getLayoutY() <= 6){
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
          }
          if(train.getImageView().getLayoutY() > 6 && train.getImageView().getLayoutY() <= 85){
            train.getImageView().setRotate(45);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() - inc);
          }
          if(train.getImageView().getLayoutY() > 85 && train.getImageView().getLayoutY() <= 120){
            train.getImageView().setRotate(0);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
          }
          if(train.getImageView().getLayoutY() > 120 && train.getImageView().getLayoutY() <= 187){
            train.getImageView().setRotate(45);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() - inc);
          }
          if(train.getImageView().getLayoutY() > 187 && train.getImageView().getLayoutY() <= 273){
            train.getImageView().setRotate(0);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
          }
          if(train.getImageView().getLayoutY() > 273 && train.getImageView().getLayoutY() <= 345){
            train.getImageView().setRotate(315);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() + inc);
          }
          if(train.getImageView().getLayoutY() > 345 && train.getImageView().getLayoutY() <= 400){
            train.getImageView().setRotate(0);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
          }
          if(train.getImageView().getLayoutY() > 400 && train.getImageView().getLayoutY() <= 477){
            train.getImageView().setRotate(315);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() + inc);
          }
          if(train.getImageView().getLayoutY() > 477 && train.getImageView().getLayoutY() <= 624){
            train.getImageView().setRotate(0);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() + inc);
          }
          if(train.getImageView().getLayoutY() > 624){
            train.getImageView().setLayoutX(449);
            train.getImageView().setLayoutY(-135);
          }
          break;
        }//fim case 2
        case 3:{//Descreve os movimentos do trem na posicao inferior esquerda
          if(train.getImageView().getLayoutY() >= 482){
            train.getImageView().setRotate(180);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
          }
          if(train.getImageView().getLayoutY() < 482 && train.getImageView().getLayoutY() >= 399){
            train.getImageView().setRotate(225);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() + inc);
          }
          if(train.getImageView().getLayoutY() < 399 && train.getImageView().getLayoutY() >= 342){
            train.getImageView().setRotate(180);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
          }          
          if(train.getImageView().getLayoutY() < 342 && train.getImageView().getLayoutY() >= 278){
            train.getImageView().setRotate(225);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() + inc);
          }
          if(train.getImageView().getLayoutY() < 278 && train.getImageView().getLayoutY() >= 191){
            train.getImageView().setRotate(180);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
          }
          if(train.getImageView().getLayoutY() < 191 && train.getImageView().getLayoutY() >= 118){
            train.getImageView().setRotate(135);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() - inc);
          }
          if(train.getImageView().getLayoutY() < 118 && train.getImageView().getLayoutY() >= 85){
            train.getImageView().setRotate(180);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
          }
          if(train.getImageView().getLayoutY() < 85 && train.getImageView().getLayoutY() >= 8){
            train.getImageView().setRotate(135);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() - inc);
          }
          if(train.getImageView().getLayoutY() < 8 && train.getImageView().getLayoutY() >= -135){
            train.getImageView().setRotate(180);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
          }
          if(train.getImageView().getLayoutY() < -135){
            train.getImageView().setLayoutX(294);
            train.getImageView().setLayoutY(624);
          }
          break;
        }//fim case 3
        case 4:{//Descreve os movimentos do trem na posicao inferior direita
          if(train.getImageView().getLayoutY() >= 479){
            train.getImageView().setRotate(180);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
          }
          if(train.getImageView().getLayoutY() < 479 && train.getImageView().getLayoutY() >= 403){
            train.getImageView().setRotate(135);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() - inc);
          }
          if(train.getImageView().getLayoutY() < 403 && train.getImageView().getLayoutY() >= 342){
            train.getImageView().setRotate(180);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
          }          
          if(train.getImageView().getLayoutY() < 342 && train.getImageView().getLayoutY() >= 269){
            train.getImageView().setRotate(135);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() - inc);
          }
          if(train.getImageView().getLayoutY() < 269 && train.getImageView().getLayoutY() >= 191){
            train.getImageView().setRotate(180);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
          }
          if(train.getImageView().getLayoutY() < 191 && train.getImageView().getLayoutY() >= 120){
            train.getImageView().setRotate(225);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() + inc);
          }
          if(train.getImageView().getLayoutY() < 120 && train.getImageView().getLayoutY() >= 81){
            train.getImageView().setRotate(180);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
          }
          if(train.getImageView().getLayoutY() < 81 && train.getImageView().getLayoutY() >= 6){
            train.getImageView().setRotate(225);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
            train.getImageView().setLayoutX(train.getImageView().getLayoutX() + inc);
          }
          if(train.getImageView().getLayoutY() < 6 && train.getImageView().getLayoutY() >= -135){
            train.getImageView().setRotate(180);
            train.getImageView().setLayoutY(train.getImageView().getLayoutY() - inc);
          }
          if(train.getImageView().getLayoutY() < -135){
            train.getImageView().setLayoutX(451);
            train.getImageView().setLayoutY(624);
          }
          break;
        }//fim do case 4
      }//fim do switch
    }//fim do metodo moveTrain
  }//fim da classe MyTimer

  AnimationTimer movimentationTimer = new MyTimer();//instancia da classe MyTimer que controla o timer de animacao

  /* ***************************************************************
  * Metodo: resetTrains
  * Funcao: Configura o botao de resetar para escolher uma nova
  * posicao dos trens quando acionado
  * Parametros: MouseEvent event - evento de quando o botao eh pressionado
  * Retorno: void
  *************************************************************** */
  @FXML
  void resetTrains(MouseEvent event) {
    movimentationTimer.stop();//para o timer da animacao
    try{
      setInitialPositions();//funcao para ir para a tela de escolha de posicao inicial
    } catch(Exception e){
      System.out.println(e.getMessage());
    }
    ChoosePositionScreenController cPSC = new ChoosePositionScreenController();//cPSC eh uma instancia do controller da tela de escolha de posicao
    //instancia os trens com as novas posicoes escolhidas pelo usuario
    brownTrain = new Train(cPSC.getInitialPosition1(), train1);
    blueTrain = new Train(cPSC.getInitialPosition2(), train2);
    //retorna a velocidade dos trens para 20 por cento do slider
    train1SpeedSlider.setValue(20);
    train2SpeedSlider.setValue(20);
    movimentationTimer.start();//inicia o timer da animacao
  }//fim do metodo resetTrains

  //metodo initialize para definir configuracoes iniciais da tela
  @Override
  public void initialize(URL location, ResourceBundle resources){
    try{
      setInitialPositions();//funcao para ir para a tela de escolha de posicao inicial
    } catch(Exception e){
      System.out.println("Erro");
    }
    ChoosePositionScreenController cPSC = new ChoosePositionScreenController();//cPSC eh uma instancia do controller da tela de escolha de posicao
    //instancia os trens passando a posicao inicial e a imagem de cada um
    brownTrain = new Train(cPSC.getInitialPosition1(), train1);
    blueTrain = new Train(cPSC.getInitialPosition2(), train2);
    //define uma velocidade inicial de 20 por cento do slider
    train1SpeedSlider.setValue(20);
    train2SpeedSlider.setValue(20);
    movimentationTimer.start();//inicia o timer da animacao
  }//fim metodo initialize
}//fim da classe TrackScreenController