package Scripts;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PlayMusic 
{
    public Clip clip;
    public FloatControl volumeControler;
    public boolean isMuted;
    public ReadVolumeConfig volumeConfig = new ReadVolumeConfig();
   
    private final String  mainThemePath = "/Music/mainTheme.wav";
    private final String  normalLevelTheme = "/Music/normalModetheme.wav";
    private final String  easyLevelThemePath = "/Music/easyLevelTheme.wav";
    private final String  hardLevelThemePath = "/Music/hardLevelTheme.wav";
    private final String  impossibleLevelThemePath = "/Music/impossibleLevelTheme.wav";
    private final String  configurationThemePath = "/Music/configurationTheme.wav";
    private final String  tutorialThemePath = "/Music/tutorialTheme.wav";
    private final String  buttonPairSoundEffectPath = "/Music/pairMatch.wav";
    private final String  buttonClickSoundEffectPath = "/Music/buttonSelected.wav";
    private final String  winThemePath = "/Music/winTheme.wav";
    private final String  loseThemePath = "/Music/loseTheme.wav";

    private final float  CEROVOLUME = -80.0f;
    
    public PlayMusic() 
    {
        this.isMuted = volumeConfig.getIsMuted();
    }

    public void startSong(String soundKey, int loopTimes) 
    {
        String soundPath = switch (soundKey) 
        {
            case "mainTheme" -> mainThemePath;
            case "easyLevelTheme" -> easyLevelThemePath;
            case "normalLevelTheme" -> normalLevelTheme;
            case "hardLevelTheme" -> hardLevelThemePath;
            case "impossibleLevelTheme" -> impossibleLevelThemePath;
            case "configurationTheme" -> configurationThemePath;
            case "tutorialTheme" -> tutorialThemePath;
            case "pairEffect" -> buttonPairSoundEffectPath;
            case "clickEffect" -> buttonClickSoundEffectPath;
            case "winTheme" -> winThemePath;
            case "loseTheme" -> loseThemePath;
            default -> null;
        };

        if (soundPath == null) 
        {
            System.out.println("ASI NO ERA CARNAL JAJA");  
            return;
        }

        try 
        {
            URL soundUrl = getClass().getResource(soundPath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundUrl);

            clip = AudioSystem.getClip();
            clip.open(audioInput);

            volumeControler = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            
            clip.loop(loopTimes);
            setVolumeControl();


        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("SOMETHING WENT SO WRONG");
        }
    }

    public void stopSong() 
    {
        if (clip != null)
        {
            clip.stop();
        }
        else
        {
            System.out.println("NO SONGS CURRENTLY PLAYING");
        }
    }
    
    public void setVolumeControl()
    {
        if (volumeControler == null)
        {
            return;
        }
        
        if (volumeConfig.getIsMuted())
        {
            volumeControler.setValue(CEROVOLUME);
        }
        
        else
        {
            float minimumValue = volumeControler.getMinimum();
            float maximunValue = volumeControler.getMaximum();
            float newVolume = (volumeConfig.getVolumeValue() / 100.0f) * (maximunValue - minimumValue) + minimumValue;

            volumeControler.setValue(newVolume);
            volumeConfig.saveFile();
        }
    }
}
