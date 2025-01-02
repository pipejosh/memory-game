/*
Cristopher Rosas
14/12/2024 - 29/12/2024
Read and writes the configuration file 
*/
 
package Scripts;

// Import all necesary libraries
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
    // Initialize variables
    private String configurationFilePath = "config.txt";
    private int volumeValue; 
    private boolean isMuted;

    // Create the default contructor
    public ReadVolumeConfig() 
    {
        if (!hasConfigFile())
        {
            writeDefaultConfiguration();
        }

        this.volumeValue = readVolumeConfiguration();
        this.isMuted = readIsMutedConfiguration();
    }

    // Creates the default configuration
    public void writeDefaultConfiguration()
    {
        // Gives a message to let the user know a new default config file will be created
        System.out.println("NO CONFIGURATION FILE DETECTED WRITING A DEFAULT ONE");
        writeConfiguration(75, false);
    }
    
    // Saves the comfig file with custom values
    public void saveFile() 
    {
        // call the function writeConfiguration with custom values (our atriburtes)
        writeConfiguration(volumeValue, isMuted);
    }

    // The funcion that saves the file 2 parameters volume as an integer and if is muted as a boolean
    public void writeConfiguration(int volume, boolean mute)
    {
        // We try to create an instance of the writer in the path specified in configurationFilePath
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configurationFilePath))) 
        {
            // We write in the file 
            writer.write("volume = " + volume + "\n");
            writer.write("mute = " + mute + "\n");
        } 
        // If theres a exception 
        catch (IOException e) 
        {
            // We print what happended + a message to let the user know that a problem happened
            e.printStackTrace();
            System.out.println("A PROBLEM WHILE WRITING THE CONFIGURATION HAPPENED");
        }
    }

    // Small funciont that returns if the config file already exists
    public boolean hasConfigFile()
    {
        // Create an instance of File (with the route of configurationFilePath)
        File configurationFile = new File(configurationFilePath);
        // Return if the config file exists
        return configurationFile.exists();
   }

    // Funcion that reads the configuration, in base of a regex and if theres no match return a default value
    public String readConfigurationFile(String regex, String defaultValue)
    {
        // Create an instance of patter with the regex asked in the parameters
        Pattern pattern = Pattern.compile(regex);
        // Try to create an instance of the reader in the path specified in configurationFilePath
        try (BufferedReader reader = new BufferedReader(new FileReader(configurationFilePath))) 
        {
            String line;

            // While the line (the current line) is not equal to null
            while ((line = reader.readLine()) != null) 
            {
                // We ask if the line match with the regex
                Matcher matcher = pattern.matcher(line);

                if (matcher.matches()) 
                {
                    // If the line matches with the regex we return the first capturing group 
                    return matcher.group(1);
                }
            }
        } 
        // If theres an exception we print that theres was a problem
        catch (IOException e) 
        {
            System.out.println("A PROBLEM HAPPENED WHILE READING THE FILE");
        }
        // If there are no matches we return the default values
        return defaultValue;
    }
    
    // Read the value of isMuted from the config file
    public boolean readIsMutedConfiguration()
    {
        return Boolean.parseBoolean(readConfigurationFile("^mute\\s*=\\s*(true|false)$", "false"));
    }

    // Read the value of volume from the config file
    public int readVolumeConfiguration() 
    {
        return Integer.parseInt(readConfigurationFile("^volume\\s*=\\s*(\\d+)$", "75"));
    }

    // Set muted to a new state and save the file
    public void setIsMuted(boolean newValue)
    {
        isMuted = newValue;
        saveFile();
    }

    // Set volme to a new state and save the file
    public void setVolume(int newVolume)
    {
        volumeValue = newVolume;
        saveFile();
    }

    // Get the value from isMuted
    public boolean getIsMuted()
    {
        return isMuted;
    }

    // Get the value from volume 
    public int getVolumeValue()
    {
        return volumeValue;
    }
}
