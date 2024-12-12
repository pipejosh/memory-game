
// TODO FIX THE MUSIC BUGS


package Scripts;

import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import Forms.WinOrLose;

public class Scripts extends JFrame 
{
    private int[] buttonImageIndex;
    private JToggleButton[] buttonsArray;
    private int pairsLeft;
    private int currentButtonsActive;
    private Timer gameTimer;
    private WinOrLose result = new WinOrLose();
    private PlayMusic musicPlayer = new PlayMusic();
    private String levelDificulty = ""; 

    public int[] randomPairsImage;
    public Random random = new Random();
    public static int NUMBEROFIMAGES = 12;
    
    public Scripts(JToggleButton[] buttonsArray, int pairsLeft, int currentButtonsActive, String levelDificulty)
    {
        this.buttonsArray = buttonsArray;
        this.pairsLeft = pairsLeft;
        this.currentButtonsActive = currentButtonsActive;
        this.levelDificulty = levelDificulty;
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
       if (gameTimer != null) 
        {
            gameTimer.cancel(); 
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
                    gameTimer.cancel(); 
                    onFinish.run();
                }
            }
        };

        gameTimer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void stopGameTimer()
    {
        if (gameTimer != null) 
        {
            gameTimer.cancel();
            gameTimer = null;
        }
    }

    public void randomImage(int buttons) 
    {
        randomPairsImage = new int[buttons];

        ArrayList<Integer> imagePairs = new ArrayList<Integer>();

        for (int i = 0; i < buttons; i+= 2)
        {
            int randomElement;

            do
            {
                randomElement = random.nextInt(12); 
            }
            
            while (imagePairs.contains(randomElement));

            imagePairs.add(randomElement);
            imagePairs.add(randomElement);
        }

        Collections.shuffle(imagePairs);

        for (int i = 0; i < randomPairsImage.length; i++)
        {
            randomPairsImage[i] = imagePairs.get(i); 
        }
    }

    public void assignImageToButtons()
    {
        buttonImageIndex = new int[buttonsArray.length];

        for (int i = 0; i < buttonsArray.length; i++)
        {
            String imagePath = String.format("/Images/asset%d.png", randomPairsImage[i] + 1 );

            buttonsArray[i].setIcon(new javax.swing.ImageIcon(getClass().getResource(imagePath))); 
             
            buttonImageIndex[i] = randomPairsImage[i];
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

        assignImageToButtons();

        result.setStateLbl("YOU LOSE");

        result.setVisible(true);

        labelToEndGame.setText("YOU LOSE");

        deactivateButtons();

        stopGameTimer();

        musicPlayer.stopSong();
    }

    public void win(JLabel labelToWinGame)
    {
        assignImageToButtons();

        result.setStateLbl("YOU WIN");        

        result.setVisible(true);
        
        labelToWinGame.setText("YOU WON");

        deactivateButtons();

        stopGameTimer();

        musicPlayer.stopSong();
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

    //TODO poner un argumento que pida el nivel y en base a eso poner la cancion DESDE SCRIPTS PARA EVITAR BUG QUE NO HAY CANCION ACTUALEMENTE

    public void gameBegin(JLabel lblPaisLeft)
    {
        lblPaisLeft.setText("PAIRS LEFT " + pairsLeft);
        getLevelSong();
        
        deactivateButtons();
        randomImage(buttonsArray.length);
        assignImageToButtons();
    }

    public void getLevelSong()
    {
        switch (levelDificulty)
        {
            case "easy" -> musicPlayer.startSong("easyLevelTheme", 0);
            case "normal" -> musicPlayer.startSong("normalLevelTheme", 0);
            case "hard" -> musicPlayer.startSong("hardLevelTheme", 0);
            case "impossible" -> musicPlayer.startSong("impossibleLevelTheme", 0);
            default -> System.out.println("NO SONG WITH THAT key");
        }
    }
}
