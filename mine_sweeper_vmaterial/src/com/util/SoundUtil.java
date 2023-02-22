package com.util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundUtil {
	/*＊
	 *  自作コード
	private static void loadAndPlayAudio(String soundFilePath) {
		
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(soundFilePath).getAbsoluteFile());
			AudioFormat af = stream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, af);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
			if(!clip.isActive()) {
				stream.close();
			}
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	*/
	
	private static void loadAndPlayAudio(String path) {
		Clip clip = null;
		AudioInputStream audioInputStream;
		try {
			File soundFile = new File(path);
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioInputStream);
			clip.start();
		} catch (UnsupportedAudioFileException uafe) {
			uafe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (LineUnavailableException lue) {
			lue.printStackTrace();
		}
	}

	public static void playSoundButtonFace() {
		loadAndPlayAudio("./sound/click1.wav");
	}

	public static void playSoundButtonFlag() {
		loadAndPlayAudio("./sound/click2.wav");
	}
	
	public static void playSoundSquare() {
		loadAndPlayAudio("./sound/button1.wav");
	}

	public static void playSoundMine() {
		loadAndPlayAudio("./sound/mine1.wav");
	}

	public static void playSoundClear() {
		loadAndPlayAudio("./sound/clear4.wav");
	}
}
