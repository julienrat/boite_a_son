
import android.media.*;
import android.content.res.*;
//Chargement des bibliothèques APWIDGET pour les zones de saisie
import apwidgets.*;
import android.view.inputmethod.EditorInfo;
import android.text.InputType;
//Construction des zones de saisie
APWidgetContainer widgetContainer; 
APEditText freq_input;


PFont titre;
PlaySound sineWave = new PlaySound();
Bouton la ;
Bouton ultra ;
Bouton custom;
PImage speakerON;
PImage speakerOFF;
int frequence=440;
boolean sound=false;
final int DISPLAY_TIME = 2000; // 2000 ms = 2 seconds
int lastTime; // When the current image was first displayed
void setup()
{
  size(displayWidth, displayHeight);
  orientation(PORTRAIT);
  titre = loadFont("CurseCasualJVE-48.vlw");
  speakerON=loadImage("Speaker.png");
  speakerOFF=loadImage("Speakeroff.png");
  textFont(titre, displayWidth/15);  
  textAlign(CENTER, CENTER);
  la = new Bouton(displayWidth/6, displayHeight/3, 1.9*displayWidth/6.0, displayHeight/8.0, "Sinus 440 Hz", true);
  ultra = new Bouton(displayWidth/6+displayWidth/3, displayHeight/3, 1.9*displayWidth/6.0, displayHeight/8.0, "Sinus 21000 Hz", true);
  custom = new Bouton(displayWidth/6, 2*displayHeight/3, 4*displayWidth/6.0, displayHeight/8.0, "Fréquence personnalisée", true);

  //frequence perssonalisée//
  widgetContainer = new APWidgetContainer(this); //create new container for widgets
  freq_input = new APEditText(int(displayWidth/6), int(1.7*displayHeight/3.0), int(4*displayWidth/6.0), int(displayHeight/16.0)); 
  widgetContainer.addWidget(freq_input); //place textField in container
  freq_input.setInputType(InputType.TYPE_CLASS_NUMBER); //Set the input type to text
  freq_input.setText("440");
  freq_input.setImeOptions(EditorInfo.IME_ACTION_DONE);
  freq_input.setCloseImeOnDone(true);
  lastTime = millis();
}

void draw() {
  background(251, 232, 0);
  textFont(titre, displayWidth/10); 
  fill(0);
  text("La Boîte à sons", displayWidth/2, displayHeight/6);
  textFont(titre, displayWidth/20);
  imageMode(CENTER);

  la.dessine();
  ultra.dessine();
  custom.dessine();
  image(speakerOFF, displayWidth/2, 1.5*displayHeight/6, displayWidth/7, displayWidth/7);  
  if (custom.click()) {
    if (freq_input.getText()!="") { // si du texte est présent dans la zone texte url
      frequence=int(freq_input.getText()); // reception de l' URL de la zone texte
    } 
    sineWave.genTone(frequence); 
    delay(15);
    sineWave.playSound(true);
    delay(10000);
    sineWave.playSound(false);
  }


  if (la.click()) {  
    sineWave.genTone(440); 
    delay(15);
    sineWave.playSound(true);
    delay(10000);
    sineWave.playSound(false);
  }
  if (ultra.click()) { 
    sineWave.genTone(21000); 
    delay(15);
    sineWave.playSound(true);
    delay(10000);
    sineWave.playSound(false);
  }
}
void mousePressed() {
  image(speakerON, displayWidth/2, 1.5*displayHeight/6, displayWidth/7, displayWidth/7);



  redraw();
}

