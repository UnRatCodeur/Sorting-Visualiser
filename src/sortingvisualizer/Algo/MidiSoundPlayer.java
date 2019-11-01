package sortingvisualizer.Algo;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;


public class MidiSoundPlayer {
    private final int INSTRU = 0; /*www.midi.org/specifications/item/gm-level-1-sound-set*/
    private Synthesizer synth;
    private final MidiChannel channel;

    
    public  MidiSoundPlayer() {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(MidiSoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        Instrument[] instruments = synth.getAvailableInstruments();
        Instrument piano = instruments[INSTRU];
        synth.loadInstrument(piano);
        channel = synth.getChannels()[0];
    }
    
    public void playSound(int i){
        channel.noteOn(25+i, 60);
    }
    
    
    
}
