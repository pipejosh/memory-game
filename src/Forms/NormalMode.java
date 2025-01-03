/*
Cristopher Rosas
2/12/2024 - 12/12/2024
The normal mode of my game with 3 pairs of images
*/

package Forms;

// Import all necesary stuff
import javax.swing.JToggleButton;
import Scripts.Scripts;

public class NormalMode extends javax.swing.JFrame 
{
    // Inicialize all necesary variables
    private JToggleButton[] buttonsArray;
    private Scripts scripts = null;
    private int pairsLeft = 3;
    private int buttonsCurrentlyActive = 0;

    // Create the default constructor
    public NormalMode() 
    {
        initComponents();
        runProgram();
    }

    // Net Beans autogenerated stuff
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn1 = new javax.swing.JToggleButton();
        btn2 = new javax.swing.JToggleButton();
        btn3 = new javax.swing.JToggleButton();
        btn4 = new javax.swing.JToggleButton();
        btn5 = new javax.swing.JToggleButton();
        btn6 = new javax.swing.JToggleButton();
        lblModeDisplay = new javax.swing.JLabel();
        lblTimeLeft = new javax.swing.JLabel();
        lblPairsLeft = new javax.swing.JLabel();
        lblCurrentState = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/asset0.png"))); // NOI18N
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/asset0.png"))); // NOI18N
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/asset0.png"))); // NOI18N
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/asset0.png"))); // NOI18N
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/asset0.png"))); // NOI18N
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/asset0.png"))); // NOI18N
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        lblModeDisplay.setText("NORMAL MODE");

        lblTimeLeft.setText("TIME LEFT");

        lblPairsLeft.setText("PAIRS LEFT");

        lblCurrentState.setText("CURRENT STATE: MEMORIZING");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblModeDisplay)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btn3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn4)
                        .addGap(18, 18, 18)
                        .addComponent(btn6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn1)
                        .addGap(18, 18, 18)
                        .addComponent(btn2)
                        .addGap(18, 18, 18)
                        .addComponent(btn5)))
                .addContainerGap(87, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblTimeLeft)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPairsLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(lblCurrentState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblModeDisplay)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPairsLeft, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTimeLeft))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn4)
                            .addComponent(btn6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(lblCurrentState)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Buttons actions (for every action we call the buttonsAction function from Scripts.java more in that in Scripts.java)
    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        scripts.buttonsAction(btn1, lblPairsLeft, lblCurrentState);
    }

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        scripts.buttonsAction(btn2, lblPairsLeft, lblCurrentState);
    }

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        scripts.buttonsAction(btn3, lblPairsLeft, lblCurrentState);
    }

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        scripts.buttonsAction(btn4, lblPairsLeft, lblCurrentState);
    }

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        scripts.buttonsAction(btn5, lblPairsLeft, lblCurrentState);
    }

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        scripts.buttonsAction(btn6, lblPairsLeft, lblCurrentState);
    }

    // Net Beans autogenerated stuff
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NormalMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NormalMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NormalMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NormalMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NormalMode().setVisible(true);
            }
        });
    }

    // My custom initialization process for each form
    private void runProgram()
    {
        // Create the buttons Array (very usefull) + create the instance of scripts (the whole brain of the game)
        buttonsArray = new JToggleButton[] {btn1, btn2, btn3, btn4, btn5, btn6,};
        scripts = new Scripts(buttonsArray, pairsLeft, buttonsCurrentlyActive, "normal");

        // Call the gunction gameBegin (more of than is Scripts.java)
        scripts.gameBegin(lblPairsLeft);

        // We give 3 seconds to memorize the stuff and 7 seconds to find all the pairs
        scripts.gameTime(3, 7, lblTimeLeft, lblCurrentState);


    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn1;
    private javax.swing.JToggleButton btn2;
    private javax.swing.JToggleButton btn3;
    private javax.swing.JToggleButton btn4;
    private javax.swing.JToggleButton btn5;
    private javax.swing.JToggleButton btn6;
    private javax.swing.JLabel lblCurrentState;
    private javax.swing.JLabel lblModeDisplay;
    private javax.swing.JLabel lblPairsLeft;
    private javax.swing.JLabel lblTimeLeft;
    // End of variables declaration//GEN-END:variables
}
