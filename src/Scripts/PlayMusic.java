/*
Cristopher Rosas
9/12/2024 - 30/12/2024
This class is responsible for the game music
*/
package Scripts;

// Imort all the necesary stuff
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PlayMusic 
{
    // Initialize all the necesary variables
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
    
    // Create the defaul constructor
    public PlayMusic() 
    {
        this.isMuted = volumeConfig.getIsMuted();
    }

    // This method starts to play the song
    public void startSong(String soundKey, int loopTimes) 
    {
        // The variable sound path is gonna equal some music path
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

        // If its null
        if (soundPath == null) 
        {
            // We let the programmer know that he need to double check
            System.out.println("NO SOUNDS WITH THAT SPECIFIC KEY DOUBLE CHECK");  
            return;
        }

        // Try to exceture the action 
        try 
        {
            // Create the sound url
            URL soundUrl = getClass().getResource(soundPath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundUrl);

            // Start the song
            clip = AudioSystem.getClip();
            clip.open(audioInput);

            // Create the volume controler
            volumeControler = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            
            // Loop the song the timer it wants to loop
            clip.loop(loopTimes);
            // Set the song to a certain volume
            setVolumeControl();
        }

        // If theres a exception
        catch (Exception e)
        {
            // Print the error + message
            e.printStackTrace();
            System.out.println("AN ERROR OCCUR WHILE PLAYING THE SONG");
        }
    }

    // This method stops the song
    public void stopSong() 
    {
        // If the clip is different from null
        if (clip != null)
        {
            // We stop
            clip.stop();
        }
        // If the clip is null
        else
        {
            // We display that there are no songs currently playing
            System.out.println("NO SONGS CURRENTLY PLAYING");
        }
    }
    
    // This method changes the volume
    public void setVolumeControl()
    {
        // If the volume controler is null we return
        if (volumeControler == null)
        {
            return;
        }
        
        // If its muted 
        if (volumeConfig.getIsMuted())
        {
            // We set the volume to cero
            volumeControler.setValue(CEROVOLUME);
        }
        
        // If is not muted
        else
        {
            // Calculate the max and min value
            float minimumValue = volumeControler.getMinimum();
            float maximunValue = volumeControler.getMaximum();
            // In base of that values calculate the new value
            float newVolume = (volumeConfig.getVolumeValue() / 100.0f) * (maximunValue - minimumValue) + minimumValue;

            // Set the new volume + save it to the config.txt file
            volumeControler.setValue(newVolume);
            volumeConfig.saveFile();
        }
    }
}
