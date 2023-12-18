/* ***************************************************************
* Autor............: Joao Vitor Cangussu Bernardes Oliveira
* Matricula........: 202210559
* Inicio...........: 21/08/2023
* Ultima alteracao.: 01/09/2023
* Nome.............: Classe Trem
* Funcao...........: Classe que descreve um trem da simulacao, 
com seus respectivos atributos, construtores, métodos, getters e 
setters
*************************************************************** */
package model;

import javafx.scene.image.ImageView;

public class Train {
  //Atributos de um Train
  private ImageView imageView;//imageView eh uma imagem associada ao trem na interface
  private int initialPosition;//initialPosition eh a posicao inicial do trem escolhida pelo usuario
  private double speed;//speed eh a velocidade do trem atualizada manipulada pelo usuario  

  //Construtor da classe recebendo a posicao inicial e a imagem associada ao Train
  public Train(int initialPosition, ImageView imageView){
    this.initialPosition = initialPosition;
    this.imageView = imageView;
    //Switch para definir a posicao da imagem na interface analisando a posicao inicial do Train
    switch(this.initialPosition){
      case 1:{//Posicao superior esquerda
        //Define as posicoes X Y e a rotacao da imagem
        this.imageView.setLayoutX(290);
        this.imageView.setLayoutY(-135);
        this.imageView.setRotate(0);
        break;
      }//fim case 1
      case 2:{//Posicao superior direita
        //Define as posicoes X Y e a rotacao da imagem
        this.imageView.setLayoutX(449);
        this.imageView.setLayoutY(-135);
        this.imageView.setRotate(0);
        break;
      }//fim case 2
      case 3:{//Posicao inferior esquerda
        //Define as posicoes X Y e a rotacao da imagem
        this.imageView.setLayoutX(294);
        this.imageView.setLayoutY(624);
        this.imageView.setRotate(180);
        break;
      }//fim case 3
      case 4:{//Posicao inferior direita
        //Define as posicoes X Y e a rotacao da imagem
        this.imageView.setLayoutX(451);
        this.imageView.setLayoutY(624);
        this.imageView.setRotate(180);
        break;
      }//fim case 4
    }//fim do switch
  }//fim do construtor

  //Getters e Setters dos atributos
  public int getInitialPosition(){
    return this.initialPosition;
  }
  public double getSpeed(){
    return this.speed;
  }
  public void setSpeed(double speed){
    this.speed = speed;
  }
  public ImageView getImageView(){
    return this.imageView;
  }
  public void setImageView(ImageView imageView){
    this.imageView = imageView;
  }
}//fim classe Train
