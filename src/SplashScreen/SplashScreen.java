package SplashScreen;

import javax.swing.JWindow;

public class SplashScreen extends JWindow
{

    private MyImage image;

    public SplashScreen()
    {
        setOptions();
        addImage();
        this.setVisible(true);
        close();
    }

    private void setOptions()
    {
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
    }

    private void addImage()
    {
        this.image = new MyImage();
        this.add(this.image);
    }

    private void close()
    {
        try
        {
            Thread.sleep(2000);
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        }

        this.dispose();
    }
    
}
