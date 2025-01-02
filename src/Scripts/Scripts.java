/*
Cristopher Rosas
2/12/2024 - 30/12/2024
This is the brain of the game, all the functions from each game mode is in here
*/
package Scripts;

// Import all the necesary stuff
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import Forms.WinOrLose;

public class Scripts extends JFrame 
{
    // Initialize all the variables we will need
    private int[] randomImages;
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
    
    // Create the scripts with custom values
    public Scripts(JToggleButton[] buttonsArray, int pairsLeft, int currentButtonsActive, String levelDificulty)
    {
        // Assign each parameter from the constructor to the atributes
        this.buttonsArray = buttonsArray;
        this.pairsLeft = pairsLeft;
        this.currentButtonsActive = currentButtonsActive;
        this.levelDificulty = levelDificulty;
        this.buttonsAmount = buttonsArray.length;

        // Call this function to get the level song, using the level dificulty (String)
        getLevelSong();
    }

    // Create a default constructor
    public Scripts()
    {
        this.buttonsArray = null;
        this.pairsLeft = 0;
        this.currentButtonsActive = 0;
        this.levelDificulty = "";
    }

    // This is a timer, takes as parameters the amount of time to count (int seconds) a label in which we display and an runnable (an action)
    private void startTimer(int seconds, JLabel labelToChange, Runnable onFinish)
    {
        // Guard clause 
       if (gameTimer != null) 
        {
            gameTimer.cancel(); 
        }

        // Create an instance of Timer and an instance of TimerTask
        gameTimer = new Timer();
        TimerTask task = new TimerTask() 
        {
            // Create the counter 
            int counter = seconds;

            // Override the run method to give it a custom beheivor
            @Override
            public void run()
            {
                // If the counter is more than 0 we change the label and decrease the counter (the timer)
                if (counter > 0)
                {
                    labelToChange.setText("COUNTER " + counter);
                    counter--;
                }

                // If the counter is less or equal to 0 we change the label cancel the timer, and execute the final action (runnable)
                else
                {
                    labelToChange.setText("Your time has ended!");
                    gameTimer.cancel(); 
                    onFinish.run();
                }
            }
        };

        // Do the timer once every second
        gameTimer.scheduleAtFixedRate(task, 0, 1000);
    }

    // This is game time, takes as parameters the amount of time to memorize, an int that the game time label in which we display and an a label that displays the game state 
    public void gameTime(int memorizeTime, int gameTime, JLabel lblTimeChange, JLabel lblGameState)
    {
        // Start the first timer with memorize time, the label to change and for the action at the end
        startTimer(memorizeTime, lblTimeChange, new Runnable() 
        {
            // Override the run method to give it a custom beheivor
            @Override
            public void run()
            {
                // Start the game so we set the default images + activate the buttons + change the game state
                setDefaultImageToButtons();
                activateButtons();
                lblGameState.setText("CURRENT STATE: IN GAME");

                // Here at the action on end we create another timer with the game time and the label to change the time
                startTimer(gameTime, lblTimeChange, new Runnable() 
                {

                    // Override the run method to give it a custom beheivor
                    @Override
                    public void run()
                    {
                        // If we reach here it means that the time has ended, therefore the user lose the game
                        lose(lblGameState);
                    }
                });
            }
        });
    }

    // This method stop the game time
    public void stopGameTimer()
    {
        // If the game timer is not null we cancel it and make it null
        if (gameTimer != null) 
        {
            gameTimer.cancel();
            gameTimer = null;
        }
    }

    // This method creates random images but in pairs (eg [1,2,1,3,2,3]) random numbers but in pairs
    public void randomPairImage() 
    {
        // Create and arraylist of integers (I use an arraylist because is more flexible)
        ArrayList<Integer> imagePairs = new ArrayList<Integer>();

        // Iterate over the amount of buttons and increment the counter (i) by two
        for (int i = 0; i < buttonsAmount; i+= 2)
        {
            //Create the random element
            int randomElement;

            // Use a do while loop because I want to run atleast the loop once 
            do
            {
                // Get a random element in base of the number of images avaliable
                randomElement = random.nextInt(NUMBEROFIMAGES); 
            }

            // The loop is going to keep throwing random values until the random number is not in the arraylist
            while (imagePairs.contains(randomElement));

            // If the number is not in the arraylist we add it twice (eg [1,1,4,4,5,5,2,2])
            imagePairs.add(randomElement);
            imagePairs.add(randomElement);
        }

        // Shuffle the arraylist to be random (eg [1,2,1,3,2,4,5,4,3,5])
        Collections.shuffle(imagePairs);

        // Convert the arraylist to a regular array of integers in order to be more easy to handle after
        randomImages = imagePairs.stream().mapToInt(Integer::intValue).toArray();

    }

    // This method assign the random images to the buttons
    public void assignPairImageToButtons()
    {
        // Create a new array to track the partners buttons 
        buttonPairImageIndex = new int[buttonsAmount];

        // Loop over the amount of buttons
        for (int i = 0; i < buttonsAmount; i++)
        {
            // This is going to be the image path (eg /Images/asset5.png) depends because the random images + 1
            String imagePath = String.format("/Images/asset%d.png", randomImages[i] + 1 );

            // For each button we assign it a new image
            buttonsArray[i].setIcon(new javax.swing.ImageIcon(getClass().getResource(imagePath))); 
             
            // And the buttons index is gonna be the random position
            buttonPairImageIndex[i] = randomImages[i];
        }
    }

    // This method return the partner of a certain button (the one that has the same image)
    public JToggleButton findPartner(JToggleButton originalButton) 
    {
        // Initialize the original button to -1
        int originalImageIndex = -1;

        // Loop over the hole button
        for (int i = 0; i < buttonsAmount; i++)
        {
            // If the button in the array in index i is equal to the orignal button
            if (buttonsArray[i] == originalButton) 
            {
                // The originla image index is gonna equal i + break out of this loop
                originalImageIndex = i;
                break;
            }
        }

        // Create the target index to the index of original image in button pair image index array
        int targetImageIndex = buttonPairImageIndex[originalImageIndex];

        // Loop over the buttons again
        for (int i = 0; i < buttonsAmount; i++)
        {
            // if i is different from orignla image index and button pair image index in index i in equal to our target image index 
            if (i != originalImageIndex && buttonPairImageIndex[i] == targetImageIndex)
            {
                // Return the button in index i
                return buttonsArray[i]; 
            }
        }

        // If theres no partner we return null
        return null;
    }

    // This method return if the button pair of a certain button "currentButton"  is selected 
    public boolean isButtonPairSelected(JToggleButton currentButton)
    {
        // Create the button pair with help of the find partner function
        JToggleButton buttonPair = findPartner(currentButton);

        // Return is the current button and its pair is selected
        return currentButton.isSelected() && buttonPair.isSelected();
    }

    // This method check and updates the game taking as parameters the current button pairs left and the game state
    public void checkAndUpdate(JToggleButton currentButton, JLabel pairsLabel, JLabel lblGameState)
    {
        // Create the button pair for the current button
        JToggleButton buttonPair = findPartner(currentButton);

        // If the button pair is selecter
        if (isButtonPairSelected(currentButton))
        {
            // Rest the pairs left
            pairsLeft --;

            // Change the pairs left label to the new value
            pairsLabel.setText("PAIRS LEFT " + pairsLeft);

            // Deselect and de enable the current button and its partner
            currentButton.setSelected(false);
            buttonPair.setSelected(false);

            currentButton.setEnabled(false);
            buttonPair.setEnabled(false);

        }

        // Else if the amount of curren buttons active is 2 and there are more than 0 pairs left 
        else if (currentButtonsActive == 2 && pairsLeft > 0)
        {
            // The variable has lost is gonna equal true
            hasLost = true;
        }

        // And if the use lost we call the function lost
        if (hasLost)
        {
            lose(lblGameState);
        }
    }

    // Here we use method overloading, a type of polymorphism because with the same name but with different paramenter we get 2 totally different outputs
    public void checkAndUpdate(JToggleButton currentButton, Runnable onFinishAction)
    {
        // Get the button pair of the original button
        JToggleButton buttonsPair = findPartner(currentButton);

        // If its pair is selected
        if (isButtonPairSelected(currentButton))
        {
            // Just deactivate the buttons 
            buttonsPair.setSelected(false);
            currentButton.setSelected(false);

            buttonsPair.setEnabled(false);
            currentButton.setEnabled(false);

            // And run an action at the end
            onFinishAction.run();
        }
    }

    // This method deactivates the buttons
    public void deactivateButtons()
    {
        // Use a for each loop (because is more easy to iterate)
        for (JToggleButton button : buttonsArray)
        {
            // For each button in the buttons array we deactivate it
            button.setEnabled(false); 
        }
    }

    // This method activates the buttons
    public void activateButtons()
    {
        // Use a for each loop (because is more easy to iterate)
        for (JToggleButton button : buttonsArray)
        {
            // For each button in the buttons array we activate it
            button.setEnabled(true); 
        }
    }

    // This method checks how many buttons are currently active
    public void checkButtonsCurrentlyActive()
    {
        // Use a for each loop (because is more easy to iterate)
        for (JToggleButton button : buttonsArray)
        {
            // If the current button is selected
            if (button.isSelected()) 
            {
                // Increase the current buttons active
                currentButtonsActive ++;
            }
        }
    }

    // This method set the default image to the buttons
    public void setDefaultImageToButtons()
    {
        String defaultImagePath = "/Images/asset0.png";

        // Use a for each loop (because is more easy to iterate)
        for (JToggleButton button: buttonsArray)
        {
            // Change the image to the default asset (asset0.png)
            button.setIcon(new javax.swing.ImageIcon(getClass().getResource(defaultImagePath))); 
        }
    }

    // This method is used when the user lose the game
    public void lose(JLabel labelToEndGame)
    {
        // Create an instance of the win or lose form + set it visible
        result = new WinOrLose("lose");
        result.setVisible(true);

        // Re assign the images so the user can see the correct answers
        assignPairImageToButtons();

        // Set a label to indicate that the user lost
        labelToEndGame.setText("YOU LOSE");

        // Deactivate the buttons + stop the timer 
        deactivateButtons();
        stopGameTimer();

        // Stop the song
        musicPlayer.stopSong();
    }

    // This method is used when the user wins the game
    public void win(JLabel labelToWinGame)
    {
        // Create an instance of the win or lose form + set it visible
        result = new WinOrLose("win");
        result.setVisible(true);

        // Set a label to indicate that the user wins
        labelToWinGame.setText("YOU WON");

        // Deactivate the buttons + stop the timer 
        deactivateButtons();
        stopGameTimer();
        
        // Stop the song
        musicPlayer.stopSong();
    }
     
    // This method returns true if the user wins
    public boolean checkIfWin()
    {
        // If the pairs left are 0
        if (pairsLeft == 0)
        {
            // The user wins so it returns true
            return true;
        }

        // The user lose so it returns false
        return false;
    }

    // This is the big method is the actions of the buttons
    public void buttonsAction(JToggleButton button, JLabel lblPairsleftDisplay, JLabel lblGameState)
    {
        // IDK why but this line makes prevents the game from breaking, this line makes the game to be able to lose (dont touch it)
        currentButtonsActive = 0;
        // Checks how many buttons are currently active + runs the check and update method
        checkButtonsCurrentlyActive();
        checkAndUpdate(button, lblPairsleftDisplay, lblGameState);

        // Create the wariale has won and assign it to checkIfWin 
        boolean hasWon = checkIfWin();

        // If the user has won
        if (hasWon)
        {
            // We run the win function
            win(lblGameState);
        }
    }

    // This method runs when the game starts
    public void gameBegin(JLabel lblPaisLeft)
    {
        // Set the label to the pairs left
        lblPaisLeft.setText("PAIRS LEFT " + pairsLeft);
        
        // Deactivate the buttons + generate the random images + assign the images to the buttons
        randomPairImage();
        deactivateButtons();
        assignPairImageToButtons();
    }

    // This method plays the music in base of the level
    public void getLevelSong()
    {
        switch (levelDificulty)
        {
            // Assign each string to the key of a song + starst that song
            case "tutorial" -> musicPlayer.startSong("tutorialTheme", 1000);
            case "easy" -> musicPlayer.startSong("easyLevelTheme", 0);
            case "normal" -> musicPlayer.startSong("normalLevelTheme", 0);
            case "hard" -> musicPlayer.startSong("hardLevelTheme", 0);
            case "impossible" -> musicPlayer.startSong("impossibleLevelTheme", 0);
            default -> System.out.println("NO SONG WITH THAT key");
        }
    }
}
