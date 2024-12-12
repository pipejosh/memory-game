 
 
package Scripts;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class PlayMusic 
{
    private Clip clip;

    private String mainThemePath = "/Music/mainTheme.wav";
    private String normalLevelTheme = "/Music/normalModetheme.wav";
    private String easyLevelThemePath = "/Music/easyLevelTheme.wav";
    private String hardLevelThemePath = "/Music/hardLevelTheme.wav";
    private String impossibleLevelThemePath = "/Music/impossibleLevelTheme.wav";
    private String buttonPairSoundEffectPath = "/Music/pairMatch.wav";
    private String buttonClickSoundEffectPath = "/Music/buttonSelected.wav";
    private String winThemePath = "";
    private String loseThemePath = "";

    public PlayMusic() 
    {
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
            case "pairEffect" -> buttonPairSoundEffectPath;
            case "clickEffect" -> buttonClickSoundEffectPath;
            case "win" -> winThemePath;
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
            clip.loop(loopTimes);
        }
        
        catch (Exception e)
        {
            e.printStackTrace();

            System.out.println("WUO");
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
            System.out.println("NO SONGS");
        }
    }
}
