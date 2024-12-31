package SplashScreen;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyImage extends JPanel
{

    public MyImage()
    {
        super();
        this.setBorder(null);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);
       Image image = new ImageIcon(getClass().getResource("/Images/assetLoading.gif")).getImage();
       g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
}
