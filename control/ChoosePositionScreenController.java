/* ***************************************************************
* Autor............: Joao Vitor Cangussu Bernardes Oliveira
* Matricula........: 202210559
* Inicio...........: 24/08/2023
* Ultima alteracao.: 01/09/2023
* Nome.............: Controlador da Tela PopUp de Escolha de 
Posicoes dos Trens
* Funcao...........: Controla e gerencia o JavaFX e as interacoes
do usuario com os elementos presentes na tela, como a definicao da
posicao inicial dos trens por meio de uma caixa de escolha
*************************************************************** */
package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChoosePositionScreenController implements Initializable {
  //atributos do FXML
  @FXML
  private ImageView startButton;
  @FXML
  private Label chooseStatusLabel;
  @FXML
  private ChoiceBox<String> choosePositionChoiceBox;
  
  //Atributos da classe
  private String[] options = {"Superior esquerdo e Superior direito", "Inferior esquerdo e Inferior direito", "Superior esquerdo e Inferior direito", "Superior direito e Inferior esquerdo"};//vetor de opcoes que estarao disponiveis na caixa de escolha
  private Stage dialogStage;//recebe o stage com o FXML da cena carregado
  private static int initialPosition1;//posicao inicial do trem 1
  private static int initialPosition2;//posicao inicial do trem 2
  
  //Getters e setters
  public int getInitialPosition1(){
    return initialPosition1;
  }
  public int getInitialPosition2(){
    return initialPosition2;
  }
  public Stage getDialogStage(){
    return dialogStage;
  }
  public void setDialogStage(Stage dialogStage){
    this.dialogStage = dialogStage;
  }

  /* ***************************************************************
  * Metodo: startTrack
  * Funcao: Configura o botao de iniciar para comecar a siimulacao dos
  * trens
  * Parametros: MouseEvent event - evento de quando o botao eh pressionado
  * Retorno: void
  *************************************************************** */
  @FXML
  public void startTrack(MouseEvent event){
    try{
      //compara o valor selecionado pelo usuario com o vetor de opcoes que podem ser escolhidas
      if(choosePositionChoiceBox.getValue().equals(options[0])){//define as posicoes iniciais do primeiro caso
        initialPosition1 = 1;
        initialPosition2 = 2;
      } else if(choosePositionChoiceBox.getValue().equals(options[1])){//define as posicoes iniciais do segundo caso
        initialPosition1 = 3;
        initialPosition2 = 4;
      } else if(choosePositionChoiceBox.getValue().equals(options[2])){//define as posicoes iniciais do terceiro caso
        initialPosition1 = 1;
        initialPosition2 = 4;
      } else if(choosePositionChoiceBox.getValue().equals(options[3])){//define as posicoes iniciais do quarto caso
        initialPosition1 = 2;
        initialPosition2 = 3;
      }
      //encerra o dialogStage e retorna para a tela primaria
      this.dialogStage.close();
    }catch(Exception exception){//captura a excecao para quando o usuario nao selecionou nenhuma opcao
      //trata a excecao mostrando o erro para o usuario
      chooseStatusLabel.setStyle("-fx-background-color: #F78181; -fx-background-radius: 30");
      chooseStatusLabel.setText("Erro: Escolha uma opcao");
      choosePositionChoiceBox.requestFocus();
    }//fim try catch
  }//fim metodo startTrack
 
  //metodo initialize para definir configuracoes iniciais da tela
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //inicia a caixa de escolhas com as opcoes que o usuario podera escolher  
    choosePositionChoiceBox.getItems().addAll(options);
  }

}
