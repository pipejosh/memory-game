package Scripts;


import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class Scripts extends JFrame 
{
    public Random random = new Random();
    public static final int NUMBEROFIMAGES = 12;
    private JToggleButton[] buttonsArray;
    
    public Scripts(JToggleButton[] buttonsArray)
    {
        this.buttonsArray = buttonsArray;
    }
    public void main(String[] args) 
    {
        int[] a = randomImage(4);

        System.out.println(Arrays.toString(a));

    }


    public void stopwatch(int seconds, JLabel labelToChange, Runnable onFinish) 
    {
        Timer time = new Timer();
        
        TimerTask task = new TimerTask() 
        {
            int counter = seconds;

            @Override
            public void run()
            {
                if (counter > 0)
                {
                    labelToChange.setText("COUNTER " + counter);
                    counter --;
                }
                else
                {
                    labelToChange.setText("Your time has ended!");
                    time.cancel();

                    onFinish.run();
                }
            }
        };
        time.scheduleAtFixedRate(task, 0, 1000);
    }

    public int[] randomImage(int buttons) 
    {
        // escojer una imagen aleatoria

        int[] randomIndexes = new int[buttons];

        ArrayList<Integer> imagePairs = new ArrayList<Integer>();

        for (int i = 0; i < buttons; i+= 2)
        {
            int randomElemet = random.nextInt(12);

            imagePairs.add(randomElemet);
            imagePairs.add(randomElemet);
        }

        Collections.shuffle(imagePairs);

        for (int i = 0; i < randomIndexes.length; i++)
        {
            randomIndexes[i] = imagePairs.get(i); 
        }

        return randomIndexes;
    }

    public void assignImageToButtons()
    {
        int[] randomButtonsImageAndPairs = randomImage(buttonsArray.length);

        for (int i = 0; i < buttonsArray.length; i++)
        {
            String imagePath = String.format("/Images/asset%d.png", randomButtonsImageAndPairs[i] + 1 );

            buttonsArray[i].setIcon(new javax.swing.ImageIcon(getClass().getResource(imagePath))); 
        }
    }

    public JToggleButton findPartner(JToggleButton originalButton) 
    {

        ImageIcon targetIcon = (ImageIcon) originalButton.getIcon();
        String targetDescription = targetIcon.getDescription();

        for (int i = 0; i < buttonsArray.length; i++)
        {
            
            if (buttonsArray[i] == originalButton) 
            {
                continue; 
            }

            ImageIcon currentIcon = (ImageIcon) buttonsArray[i].getIcon();
            String currentDescription = currentIcon.getDescription();

            if (currentDescription.equals(targetDescription))
            {
                return buttonsArray[i];
            }
        }
        return null; 
    }

    public void checkAndUpdate(JToggleButton currentButton, JLabel pairsLabel, int pairsLeft)
    {
        JToggleButton buttonPair = findPartner(currentButton);

        if (currentButton.isSelected() && buttonPair.isSelected())
        {
            pairsLeft -= 1;


            pairsLabel.setText("PAIRS LEFT " + pairsLeft);

            currentButton.setEnabled(false);
            buttonPair.setEnabled(false);

        }
    }
}
