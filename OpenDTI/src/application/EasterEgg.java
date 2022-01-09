package application;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class EasterEgg {

	public void exec() {

		try {
				
			
			System.out.println(
					  "╔════════════════════════════════════════════════════════════════════╗\n"
					+ "║ Thank you for using Discord Token Info! - You found an easter egg! ║\n"
					+ "╚════════════════════════════════════════════════════════════════════╝\n"
					+ "Turn your speakers on!");
			
			Pattern pattern = MidiFileManager.loadPatternFromMidi(getClass().getResource("/application/RickRoll.mid"));
			
			
			Player player = new Player();
		    player.play(pattern);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
