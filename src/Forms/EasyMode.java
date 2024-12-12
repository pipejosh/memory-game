package Forms;


import javax.swing.JToggleButton;

import Scripts.Scripts;

public class EasyMode extends javax.swing.JFrame
{
    private JToggleButton[] buttonsArray;
    private Scripts scripts = null;
    private int pairsLeft = 2;
    private int buttonsCurrentlyActive = 0;

    public EasyMode() 
    {
        initComponents();
        runProgram();
    }
    
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
        lblModeDisplay = new javax.swing.JLabel();
        lblTimeDisplay = new javax.swing.JLabel();
        lblPairsLeftDisplay = new javax.swing.JLabel();
        lblCurrentGameState = new javax.swing.JLabel();

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

        lblModeDisplay.setText("EASY MODE");

        lblTimeDisplay.setText("TIME LEFT");

        lblPairsLeftDisplay.setText("Pairs left");

        lblCurrentGameState.setText("CURRENT STATE: MEMORIZING");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTimeDisplay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPairsLeftDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(lblModeDisplay))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn1)
                                    .addComponent(btn3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn4)
                                    .addComponent(btn2)))
                            .addComponent(lblCurrentGameState, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblModeDisplay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimeDisplay)
                    .addComponent(lblPairsLeftDisplay))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn1)
                    .addComponent(btn2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn3)
                    .addComponent(btn4))
                .addGap(18, 18, 18)
                .addComponent(lblCurrentGameState)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        scripts.buttonsAction(btn1, lblPairsLeftDisplay, lblCurrentGameState);
    }

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        scripts.buttonsAction(btn2, lblPairsLeftDisplay, lblCurrentGameState);
    }

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        scripts.buttonsAction(btn3, lblPairsLeftDisplay, lblCurrentGameState);
    }
    
    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        scripts.buttonsAction(btn4, lblPairsLeftDisplay, lblCurrentGameState);
    }

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EasyMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EasyMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EasyMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EasyMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EasyMode().setVisible(true);
            }
        });
    }

    public void runProgram()
    {
        buttonsArray = new JToggleButton[] {btn1, btn2, btn3, btn4};
        scripts = new Scripts(buttonsArray, pairsLeft, buttonsCurrentlyActive, "easy");

        scripts.gameBegin(lblCurrentGameState);

        // Give the user 5 seconds to memorize

        scripts.gameTime(2, 5, lblTimeDisplay, lblCurrentGameState);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn1;
    private javax.swing.JToggleButton btn2;
    private javax.swing.JToggleButton btn3;
    private javax.swing.JToggleButton btn4;
    private javax.swing.JLabel lblCurrentGameState;
    private javax.swing.JLabel lblModeDisplay;
    private javax.swing.JLabel lblPairsLeftDisplay;
    private javax.swing.JLabel lblTimeDisplay;
    // End of variables declaration//GEN-END:variables

    
    
    
}
