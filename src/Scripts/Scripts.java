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
    private int[] buttonImageIndex;
    private JToggleButton[] buttonsArray;
    private int pairsLeft;
    private int currentButtonsActive;
    
    public Scripts(JToggleButton[] buttonsArray, int pairsLeft, int currentButtonsActive)
    {
        this.buttonsArray = buttonsArray;
        this.pairsLeft = pairsLeft;
        this.currentButtonsActive = currentButtonsActive;
    }
    public void main(String[] args) 
    {
        int[] a = randomImage(4);

        System.out.println(Arrays.toString(a));

    }


    private void stopwatch(int seconds, JLabel labelToChange, Runnable onFinish) 
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

    public void gameTime(int memorizeTime, int gameTime, JLabel lblTimeChange, JLabel endGameLabel)
    {
        stopwatch(memorizeTime, lblTimeChange, new Runnable() 
        {
            @Override

            public void run()
            {
                setDefaultImageToButtons();
                activateButtons();

                stopwatch(gameTime, lblTimeChange, new Runnable() 
                {
                    @Override

                    public void run()
                    {
                        endGame(endGameLabel);
                    }
                    
                });
            }


            
        });

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
        buttonImageIndex = new int[buttonsArray.length];

        for (int i = 0; i < buttonsArray.length; i++)
        {
            String imagePath = String.format("/Images/asset%d.png", randomButtonsImageAndPairs[i] + 1 );

            buttonsArray[i].setIcon(new javax.swing.ImageIcon(getClass().getResource(imagePath))); 
             
            buttonImageIndex[i] = randomButtonsImageAndPairs[i];
        }
    }

    public JToggleButton findPartner(JToggleButton originalButton) 
    {
        int originalImageIndex = -1;

        for (int i = 0; i < buttonsArray.length; i++)
        {
            if (buttonsArray[i] == originalButton) 
            {
                originalImageIndex = i;
                break;
            }
        }

        int targetImageIndex = buttonImageIndex[originalImageIndex];

        for (int i = 0; i < buttonsArray.length; i++)
        {
            if (i != originalImageIndex && buttonImageIndex[i] == targetImageIndex)
            {
                return buttonsArray[i]; 
            }
        }

        return null;
    }

    public void checkAndUpdate(JToggleButton currentButton, JLabel pairsLabel)
    {
        JToggleButton buttonPair = findPartner(currentButton);

        if (currentButton.isSelected() && buttonPair.isSelected())
        {
            pairsLeft -= 1;

            pairsLabel.setText("PAIRS LEFT " + pairsLeft);

            currentButton.setEnabled(false);
            buttonPair.setEnabled(false);
            deselectButtons();
        }
    }

    public void deactivateButtons()
    {
        for (int i = 0; i < buttonsArray.length; i++)
        {
            buttonsArray[i].setEnabled(false);    
        }
    }

    public void activateButtons()
    {
        for (int i = 0; i < buttonsArray.length; i++)
        {
            buttonsArray[i].setEnabled(true);    
        }
    }

    public void deselectButtons()
    {
        for (int i = 0; i < buttonsArray.length; i++)
        {
            buttonsArray[i].setSelected(false);    
        }
    }

    public void checkButtonsCurrentlyActive()
    {
        currentButtonsActive += 1;
        
        if (currentButtonsActive == 2)
        {
            currentButtonsActive = 0; 
            
            deselectButtons();
        }
    }

    public void setDefaultImageToButtons()
    {
        String defaultImagePath = "/Images/asset0.png";

        for (int i = 0; i < buttonsArray.length; i++)
        {
            buttonsArray[i].setIcon(new javax.swing.ImageIcon(getClass().getResource(defaultImagePath))); 
        }
    }

    public void endGame(JLabel labelToEndGame)
    {
        labelToEndGame.setText("YOU LOST HAHAH");

        deactivateButtons();

    }


}
