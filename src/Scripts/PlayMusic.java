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
    public int currentVolume;
    public ReadVolumeConfig volumeConfig = new ReadVolumeConfig();
   
    private String mainThemePath = "/Music/mainTheme.wav";
    private String normalLevelTheme = "/Music/normalModetheme.wav";
    private String easyLevelThemePath = "/Music/easyLevelTheme.wav";
    private String hardLevelThemePath = "/Music/hardLevelTheme.wav";
    private String impossibleLevelThemePath = "/Music/impossibleLevelTheme.wav";
    private String configurationThemePath = "/Music/configurationTheme.wav";
    private String buttonPairSoundEffectPath = "/Music/pairMatch.wav";
    private String buttonClickSoundEffectPath = "/Music/buttonSelected.wav";
    private String winThemePath = "/Music/winTheme.wav";
    private String loseThemePath = "";
    
    public PlayMusic() 
    {
        this.currentVolume = volumeConfig.readConfigurationFile();
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
            case "pairEffect" -> buttonPairSoundEffectPath;
            case "clickEffect" -> buttonClickSoundEffectPath;
            case "winTheme" -> winThemePath;
            case "lose" -> loseThemePath;
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
            changeVolume();
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
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

    public void changeVolume()
    {
        if (volumeControler == null)
        {
            return;
        }

        float minimumValue = volumeControler.getMinimum();
        float maximunValue = volumeControler.getMaximum();
        float newVolume = (getVolume() / 100.0f) * (maximunValue - minimumValue) + minimumValue;

        volumeControler.setValue(newVolume);
        volumeConfig.saveFile(this.currentVolume);
    }

    public int getVolume()
    {
        return this.currentVolume;
    }
}
