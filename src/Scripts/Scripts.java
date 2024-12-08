package Scripts;


import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import Forms.WinOrLose;

public class Scripts extends JFrame 
{
    public Random random = new Random();
    public static final int NUMBEROFIMAGES = 12;
    private int[] buttonImageIndex;
    private JToggleButton[] buttonsArray;
    private int pairsLeft;
    private int currentButtonsActive;
    private Timer gameTimer;
    private WinOrLose result = new WinOrLose();
    
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

    public void gameTime(int memorizeTime, int gameTime, JLabel lblTimeChange, JLabel lblGameState)
    {
        startTimer(memorizeTime, lblTimeChange, new Runnable() 
        {
            @Override
            public void run()
            {
                setDefaultImageToButtons();
                activateButtons();
                lblGameState.setText("CURRENT STATE: IN GAME");

                // Segundo temporizador: Tiempo para jugar
                startTimer(gameTime, lblTimeChange, new Runnable() 
                {
                    @Override
                    public void run()
                    {
                        lose(lblGameState);
                    }
                });
            }
        });
    }

    private void startTimer(int seconds, JLabel labelToChange, Runnable onFinish)
    {
        if (gameTimer != null) {
            gameTimer.cancel(); // Detener cualquier timer en curso
        }

        gameTimer = new Timer();
        TimerTask task = new TimerTask() 
        {
            int counter = seconds;

            @Override
            public void run()
            {
                if (counter > 0)
                {
                    labelToChange.setText("COUNTER " + counter);
                    counter--;
                }
                else
                {
                    labelToChange.setText("Your time has ended!");
                    gameTimer.cancel(); // Detener el timer actual
                    onFinish.run();
                }
            }
        };

        gameTimer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void stopGameTimer()
    {
        if (gameTimer != null) {
            gameTimer.cancel();
            gameTimer = null;
        }
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

    public void checkAndUpdate(JToggleButton currentButton, JLabel pairsLabel, JLabel lblGameState)
    {
        JToggleButton buttonPair = findPartner(currentButton);

        System.out.println(currentButtonsActive);

        if (currentButton.isSelected() && buttonPair.isSelected())
        {
            pairsLeft --;

            pairsLabel.setText("PAIRS LEFT " + pairsLeft);

            currentButton.setSelected(false);
            buttonPair.setSelected(false);

            currentButton.setEnabled(false);
            buttonPair.setEnabled(false);
        }

        else if (currentButtonsActive == 2 && pairsLeft > 0)
        {
            lose(lblGameState);
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

    public void checkButtonsCurrentlyActive()
    {
        for (int i = 0; i < buttonsArray.length; i++)
        {
            if (buttonsArray[i].isSelected())
            {
                currentButtonsActive ++;
            }

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

    public void lose(JLabel labelToEndGame)
    {

        result.setStateLbl("YOU LOSE");

        result.setVisible(true);

        labelToEndGame.setText("YOU LOSE");

        deactivateButtons();

        stopGameTimer();

    }

    public void win(JLabel labelToWinGame)
    {
        result.setStateLbl("YOU WIN");        

        result.setVisible(true);
        
        labelToWinGame.setText("YOU WON");

        deactivateButtons();

        stopGameTimer();
    }
     
    public boolean checkIfWin()
    {
        if (pairsLeft == 0)
        {
            return true;
        }

        return false;
    }

    public void buttonsAction(JToggleButton button, JLabel lblPairsleftDisplay, JLabel lblGameState)
    {
        currentButtonsActive = 0;
        checkButtonsCurrentlyActive();
        checkAndUpdate(button, lblPairsleftDisplay, lblGameState);
        boolean hasWon = checkIfWin();

        if (hasWon)
        {
            win(lblGameState);
        }
    }
}
