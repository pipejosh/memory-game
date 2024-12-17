package Scripts;

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

    public ReadVolumeConfig() 
    {
    }
    
    public void saveFile(int volumeConfiguration) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configurationFilePath))) 
        {
            writer.write("volume = " + volumeConfiguration);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("A PROBLEM HAPPENED");
        }
    }

    public int readConfigurationFile() 
    {
        String line;
        int volumeValue = 75; 
        Pattern pattern = Pattern.compile("^volume\\s*=\\s*(\\d+)$");

        try (BufferedReader reader = new BufferedReader(new FileReader(configurationFilePath))) 
        {
            while ((line = reader.readLine()) != null) 
            {
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) 
                {
                    volumeValue = Integer.parseInt(matcher.group(1));  
                    break;
                }
            }
            if (volumeValue == 75) 
            {
                saveFile(volumeValue);
            }

        } 
        catch (IOException e) 
        {
            System.out.println("NO CONFIGURATION FILE DETECTED, CREATING DEFAULT ONE");
        }

        return volumeValue;
    }
}
