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

  void genTone(double freqOfTone) {
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
  
  void genSqrTone(double freqOfTone) {
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

  void playSound(boolean on) {
    if (on) {

      audioTrack.play();
    }
    else {
      audioTrack.pause();
    }
  }
}


