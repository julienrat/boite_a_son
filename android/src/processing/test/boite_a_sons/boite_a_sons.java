package processing.test.boite_a_sons;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import android.media.*; 
import android.content.res.*; 
import apwidgets.*; 
import android.view.inputmethod.EditorInfo; 
import android.text.InputType; 

import apwidgets.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class boite_a_sons extends PApplet {




//Chargement des biblioth\u00e8ques APWIDGET pour les zones de saisie



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
public void setup()
{
 
  orientation(PORTRAIT);
  titre = loadFont("CurseCasualJVE-48.vlw");
  speakerON=loadImage("Speaker.png");
  speakerOFF=loadImage("Speakeroff.png");
  textFont(titre, displayWidth/15);  
  textAlign(CENTER, CENTER);
  la = new Bouton(displayWidth/6, displayHeight/3, 1.9f*displayWidth/6.0f, displayHeight/8.0f, "Sinus 440 Hz", true);
  ultra = new Bouton(displayWidth/6+displayWidth/3, displayHeight/3, 1.9f*displayWidth/6.0f, displayHeight/8.0f, "Sinus 21000 Hz", true);
  custom = new Bouton(displayWidth/6, 2*displayHeight/3, 4*displayWidth/6.0f, displayHeight/8.0f, "Fr\u00e9quence personnalis\u00e9e", true);

  //frequence perssonalis\u00e9e//
  widgetContainer = new APWidgetContainer(this); //create new container for widgets
  freq_input = new APEditText(PApplet.parseInt(displayWidth/6), PApplet.parseInt(1.7f*displayHeight/3.0f), PApplet.parseInt(4*displayWidth/6.0f), PApplet.parseInt(displayHeight/16.0f)); 
  widgetContainer.addWidget(freq_input); //place textField in container
  freq_input.setInputType(InputType.TYPE_CLASS_NUMBER); //Set the input type to text
  freq_input.setText("440");
  freq_input.setImeOptions(EditorInfo.IME_ACTION_DONE);
  freq_input.setCloseImeOnDone(true);
  lastTime = millis();
}

public void draw() {
  background(251, 232, 0);
  textFont(titre, displayWidth/10); 
  fill(0);
  text("La Bo\u00eete \u00e0 sons", displayWidth/2, displayHeight/6);
  textFont(titre, displayWidth/20);
  imageMode(CENTER);

  la.dessine();
  ultra.dessine();
  custom.dessine();
  image(speakerOFF, displayWidth/2, 1.5f*displayHeight/6, displayWidth/7, displayWidth/7);  
  if (custom.click()) {
    if (freq_input.getText()!="") { // si du texte est pr\u00e9sent dans la zone texte url
      frequence=PApplet.parseInt(freq_input.getText()); // reception de l' URL de la zone texte
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
public void mousePressed() {
  image(speakerON, displayWidth/2, 1.5f*displayHeight/6, displayWidth/7, displayWidth/7);



  redraw();
}


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
  public void off() {
    cache=true;
  }
  public void on() {
    cache=false;
  }


  public void init_image(String bouton, String bouton_clicked, int posx, int posy, int larg, int haut, boolean tog) {
    bt = loadImage(bouton);
    bt_clicked=loadImage(bouton_clicked);
    positionx=posx;
    positiony=posy;
    largeur=larg;
    hauteur=haut;
    toggle = tog;
  }
  public void move(int posx, int posy, int larg, int haut) {
    positionx=posx;
    positiony=posy;
    largeur=larg;
    hauteur=haut;
  }
  public boolean click() {

    if (mouseX>positionx && mouseX<positionx+largeur && mouseY>positiony && mouseY<positiony+hauteur && mousePressed && !cache) {

      return true;
    }
    else {

      return false;
    }
  }
  public boolean toggle() {   
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

  public void dessine() {
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



public class PlaySound {
  boolean running= true;
  private final int sampleRate = 48000;
  private final int numSamples = sampleRate;
  private final double samples[] = new double[numSamples];
  private final byte generatedSnd[] = new byte[2*(numSamples)];

  final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 
  sampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO, 
  AudioFormat.ENCODING_PCM_16BIT, numSamples, 
  AudioTrack.MODE_STATIC);

  public void genTone(double freqOfTone) {
    // fill out the array

    for (int i = 0; i < numSamples; ++i) {
      samples[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
    }
    // convert to 16 bit pcm sound array
    // assumes the sample buffer is normalised.
    int idx = 0;
    for (double dVal : samples) {
      // scale to maximum amplitude
      short val = (short) ((dVal * 32767));
      // in 16 bit wav PCM, first byte is the low order byte
      generatedSnd[idx++] = (byte) (val & 0x00ff);
      generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
    }
    audioTrack.write(generatedSnd, 0, numSamples*2);
    audioTrack.setLoopPoints(0, numSamples/2, -1);
  }
  
  public void genSqrTone(double freqOfTone) {
    // fill out the array

    for (int i = 0; i < numSamples; ++i) {
      samples[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
      if (samples[i]<0) {
        samples[i]=-1;
      }
      else {
        samples[i]=1;
      }
    }
    // convert to 16 bit pcm sound array
    // assumes the sample buffer is normalised.
    int idx = 0;
    for (double dVal : samples) {
      // scale to maximum amplitude
      short val = (short) ((dVal * 32767));
      // in 16 bit wav PCM, first byte is the low order byte
      generatedSnd[idx++] = (byte) (val & 0x00ff);
      generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
    }
    audioTrack.write(generatedSnd, 0, numSamples*2);
   // audioTrack.setLoopPoints(0, numSamples/2, -1);
  }

  public void playSound(boolean on) {
    if (on) {

      audioTrack.play();
    }
    else {
      audioTrack.pause();
    }
  }
}



  public int sketchWidth() { return displayWidth; }
  public int sketchHeight() { return displayHeight; }
}
