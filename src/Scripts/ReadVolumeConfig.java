package Scripts;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadVolumeConfig 
{
    private String configurationFilePath = "config.txt";
    private int volumeValue; 
    private boolean isMuted;

    public ReadVolumeConfig() 
    {
        if (!hasConfigFile())
        {
            writeDefaultConfiguration();
        }

        this.volumeValue = readVolumeConfiguration();
        this.isMuted = readIsMutedConfiguration();
    }

    public void writeDefaultConfiguration()
    {
        System.out.println("NO CONFIGURATION FILE DETECTED WRITING A DEFAULT ONE");
        writeConfiguration(75, false);
    }
    
    public void saveFile() 
    {
        writeConfiguration(volumeValue, isMuted);
    }

    public void writeConfiguration(int volume, boolean mute)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configurationFilePath))) 
        {
            writer.write("volume = " + volume + "\n");
            writer.write("mute = " + mute + "\n");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("A PROBLEM WHILE WRITING THE CONFIGURATION HAPPENED");
        }
    }

    public boolean hasConfigFile()
    {
        File configurationFile = new File(configurationFilePath);

        return configurationFile.exists();
   }

   public String readConfigurationFile(String regex, String defaultValue)
   {
       Pattern pattern = Pattern.compile(regex);

       try (BufferedReader reader = new BufferedReader(new FileReader(configurationFilePath))) 
       {
            String line;

            while ((line = reader.readLine()) != null) 
            {
                Matcher matcher = pattern.matcher(line);

                if (matcher.matches()) 
                {
                    return matcher.group(1);
                }
            }
        } 

        catch (IOException e) 
        {
            System.out.println("A PROBLEM HAPPENED WHILE READING THE FILE");
        }

        return defaultValue;
   }

    public boolean readIsMutedConfiguration()
    {
        return Boolean.parseBoolean(readConfigurationFile("^mute\\s*=\\s*(true|false)$", "false"));
    }

    public int readVolumeConfiguration() 
    {
        return Integer.parseInt(readConfigurationFile("^volume\\s*=\\s*(\\d+)$", "75"));
    }

    public void setIsMuted(boolean newValue)
    {
        isMuted = newValue;
        saveFile();
    }

    public void setVolume(int newVolume)
    {
        volumeValue = newVolume;
        saveFile();
    }

    public boolean getIsMuted()
    {
        return isMuted;
    }

    public int getVolumeValue()
    {
        return volumeValue;
    }
}
