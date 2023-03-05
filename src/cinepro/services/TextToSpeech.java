/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.services;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


/**
 *
 * @author kortb
 */
public class TextToSpeech {
  private static final String VOICE_NAME = "kevin16"; // Name of the voice you want to use

  public static void speak(String text) {
    VoiceManager voiceManager = VoiceManager.getInstance();
    Voice voice = voiceManager.getVoice(VOICE_NAME);

    if (voice != null) {
      voice.allocate(); // Allocates the resources for the voice

      voice.speak(text); // Converts the text to speech

      voice.deallocate(); // Deallocates the resources for the voice
    } else {
      throw new IllegalStateException("Cannot find voice: " + VOICE_NAME);
    }
  }
}
