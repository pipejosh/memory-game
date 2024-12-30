package Scripts;

import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import Forms.MainMenu;
import Forms.WinOrLose;

public class Scripts extends JFrame 
{
    private int[] buttonPairImageIndex;
    private JToggleButton[] buttonsArray;
    private int pairsLeft;
    private int buttonsAmount = 0;
    private int currentButtonsActive;
    private Timer gameTimer;
    private WinOrLose result;
    private String levelDificulty = ""; 
    private boolean hasLost = false;
    
    public PlayMusic musicPlayer = new PlayMusic();
    public Random random = new Random();
    public static int NUMBEROFIMAGES = 12;
    
    public Scripts(JToggleButton[] buttonsArray, int pairsLeft, int currentButtonsActive, String levelDificulty)
    {
        this.buttonsArray = buttonsArray;
        this.pairsLeft = pairsLeft;
        this.currentButtonsActive = currentButtonsActive;
        this.levelDificulty = levelDificulty;
        this.buttonsAmount = buttonsArray.length;
        getLevelSong();
    }

    public Scripts()
    {
        this.buttonsArray = null;
        this.pairsLeft = 0;
        this.currentButtonsActive = 0;
        this.levelDificulty = "";

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

    public int[] randomPairImage() 
    {

        ArrayList<Integer> imagePairs = new ArrayList<Integer>();

        for (int i = 0; i < buttonsAmount; i+= 2)
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

        int[] randomPairsImage = imagePairs.stream().mapToInt(Integer::intValue).toArray();

        return randomPairsImage;
    }

    public int[] randomImpairImage()
    {
        ArrayList<Integer> imagePairs = new ArrayList<Integer>();

        for (int i = 0; i < buttonsAmount; i ++)
        {
            int randomElement;

            do
            {
                randomElement = random.nextInt(12);
            }

            while (!imagePairs.contains(randomElement));

            imagePairs.add(randomElement);
        }

        int[] randomImpairImage = imagePairs.stream().mapToInt(Integer::intValue).toArray();

        return randomImpairImage;
    }

    public void assignPairImageToButtons()
    {
        int[] randomImages = randomPairImage();
        buttonPairImageIndex = new int[buttonsArray.length];

        for (int i = 0; i < buttonsAmount; i++)
        {
            String imagePath = String.format("/Images/asset%d.png", randomImages[i] + 1 );

            buttonsArray[i].setIcon(new javax.swing.ImageIcon(getClass().getResource(imagePath))); 
             
            buttonPairImageIndex[i] = randomImages[i];
        }
    }

    public void assignImpairImageToButtons()
    {
        int[] randomImages = randomPairImage();

        for (int i = 0; i < buttonsAmount; i++)
        {
            String imagePath = String.format("/Images/asset%d.png", randomImages[i] + 1 );

            buttonsArray[i].setIcon(new javax.swing.ImageIcon(getClass().getResource(imagePath))); 
             
            buttonPairImageIndex[i] = randomImages[i];
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

        int targetImageIndex = buttonPairImageIndex[originalImageIndex];

        for (int i = 0; i < buttonsArray.length; i++)
        {
            if (i != originalImageIndex && buttonPairImageIndex[i] == targetImageIndex)
            {
                return buttonsArray[i]; 
            }
        }

        return null;
    }

    public boolean isButtonPairSelected(JToggleButton currentButton)
    {
        JToggleButton buttonPair = findPartner(currentButton);

        return currentButton.isSelected() && buttonPair.isSelected();
    }

    public void checkAndUpdate(JToggleButton currentButton, JLabel pairsLabel, JLabel lblGameState)
    {
        JToggleButton buttonPair = findPartner(currentButton);

        if (isButtonPairSelected(currentButton))
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
            hasLost = true;
        }

        if (hasLost)
        {
            lose(lblGameState);
        }
    }

    public void checkAndUpdate(JToggleButton currentButton, Runnable onFinishAction)
    {
        JToggleButton buttonsPair = findPartner(currentButton);

        if (isButtonPairSelected(currentButton))
        {
            buttonsPair.setSelected(false);
            currentButton.setSelected(false);

            buttonsPair.setEnabled(false);
            currentButton.setEnabled(false);

            onFinishAction.run();
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
        result = new WinOrLose("lose");
        result.setVisible(true);
        assignPairImageToButtons();

        labelToEndGame.setText("YOU LOSE");

        deactivateButtons();
        stopGameTimer();

        musicPlayer.stopSong();
    }

    public void win(JLabel labelToWinGame)
    {
        result = new WinOrLose("win");
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

    public void playAgain()
    {
        MainMenu menu = new MainMenu();
        
        menu.setVisible(true);

        musicPlayer.stopSong();
    }

    public void gameBegin(JLabel lblPaisLeft)
    {
        lblPaisLeft.setText("PAIRS LEFT " + pairsLeft);
        
        deactivateButtons();
        assignPairImageToButtons();
    }

    public void getLevelSong()
    {
        switch (levelDificulty)
        {
            case "tutorial" -> musicPlayer.startSong("tutorialTheme", 1000);
            case "easy" -> musicPlayer.startSong("easyLevelTheme", 0);
            case "normal" -> musicPlayer.startSong("normalLevelTheme", 0);
            case "hard" -> musicPlayer.startSong("hardLevelTheme", 0);
            case "impossible" -> musicPlayer.startSong("impossibleLevelTheme", 0);
            default -> System.out.println("NO SONG WITH THAT key");
        }
    }

    public void exit()
    {
        musicPlayer.stopSong();
    }
}
