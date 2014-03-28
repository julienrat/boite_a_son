
class Bouton {

 float largeur, hauteur, positionx, positiony; // c'est comme des variables globales !
  boolean on, toggle, centre, cache;
  String texte;
  PImage bt, bt_clicked; 

  Bouton(int posx, int posy, float x, float y, String txt, boolean centred) {
    positionx=posx;
    positiony=posy;
    largeur=x;
    hauteur=y;
    texte=txt;
    centre=centred;
  }
  void off() {
    cache=true;
  }
  void on() {
    cache=false;
  }


  void init_image(String bouton, String bouton_clicked, int posx, int posy, int larg, int haut, boolean tog) {
    bt = loadImage(bouton);
    bt_clicked=loadImage(bouton_clicked);
    positionx=posx;
    positiony=posy;
    largeur=larg;
    hauteur=haut;
    toggle = tog;
  }
  void move(int posx, int posy, int larg, int haut) {
    positionx=posx;
    positiony=posy;
    largeur=larg;
    hauteur=haut;
  }
  boolean click() {

    if (mouseX>positionx && mouseX<positionx+largeur && mouseY>positiony && mouseY<positiony+hauteur && mousePressed && !cache) {

      return true;
    }
    else {

      return false;
    }
  }
  boolean toggle() {   
    if (click()) {
      if (on==false) {
        on= true;
        delay(200);
      }
      else {
        on=false;
        delay(200);
      }
    }

    return on;
  }

  void dessine() {
    if (!cache) {
      if (bt!=null) {
        if (toggle==false) {
          image(bt, positionx, positiony, largeur, hauteur);
          if (mouseX>positionx && mouseX<positionx+largeur && mouseY>positiony && mouseY<positiony+hauteur && mousePressed) {
            image(bt_clicked, positionx, positiony, largeur, hauteur);
          }
        }
        else {
          if (on) {
            image(bt, positionx, positiony);
          }
          else {
            image(bt_clicked, positionx, positiony);
          }
        }
      }
      else {
        if (mouseX>positionx && mouseX<positionx+largeur && mouseY>positiony && mouseY<positiony+hauteur) {
           fill(180,20,0);
          if (mousePressed) {
            //strokeWeight(4);
            //stroke(200);
            fill(0, 120, 0);
          }
        }
        else {
          fill(180,20,0);
        }
        noStroke();
        rect(positionx, positiony, largeur, hauteur,largeur/5);


        fill(25);
        if (centre) {
          textAlign(CENTER, CENTER);
          text(texte, positionx + (largeur/2), positiony+(hauteur)/2);
        }
        else {
          textAlign(LEFT, CENTER);
          text(texte, 360, positiony+(hauteur)/2);
        }
      }
    }
  }
}



