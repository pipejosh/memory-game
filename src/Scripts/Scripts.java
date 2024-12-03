package Scripts;


import java.util.*;
import javax.swing.JLabel;

public class Scripts 
{
    public Random random = new Random();
    
    public Scripts()
    {

    }
    public void main(String[] args) 
    {
        int[] a = randomImage(4);

        System.out.println(Arrays.toString(a));

    }


    public void stopwatch(int seconds, JLabel labelToChange ) 
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
                    labelToChange.setText("SE ACABO EL TIMEPO");
                    time.cancel();
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
}
