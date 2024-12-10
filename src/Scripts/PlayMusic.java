 
// TODO FIX THE MUSIC BUGS
 
package Scripts;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class PlayMusic 
{
    private Clip clip;

    private final String mainThemePath = "/Music/mainTheme.wav";
    private final String gameThemePath = "/Music/gameTheme.wav";
    private final String buttonPairSoundEffectPath = "/Music/pairMatch.wav";
    private final String buttonClickSoundEffectPath = "/Music/buttonSelected.wav";
    private final String winThemePath = "";
    private final String loseThemePath = "";

    public PlayMusic() 
    {
    }

    public void startSong(String soundKey) 
    {
        String soundPath = switch (soundKey) 
        {
            case "mainTheme" -> mainThemePath;
            case "gameTheme" -> gameThemePath;
            case "pairEffect" -> buttonPairSoundEffectPath;
            case "clickEffect" -> buttonClickSoundEffectPath;
            case "win" -> winThemePath;
            case "lose" -> loseThemePath;
            default -> null;
        };

        if (soundPath == null) 
        {
            
            return;
        }

URL soundUrl = new URL();

 soundUrl = getClass().getResource(soundPath);

        System.out.println(soundUrl.toString());

        try 
        {

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundUrl);

            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void stopSong() 
    {
        if (clip != null && clip.isRunning())
        {
            clip.stop();
        }

        else
        {
            System.out.println("NO HAY CANCIONES");
        }
    }
}
