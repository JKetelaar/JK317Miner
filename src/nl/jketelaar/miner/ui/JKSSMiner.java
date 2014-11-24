package nl.jketelaar.miner.ui;

import nl.jketelaar.miner.main.Main;
import nl.jketelaar.miner.main.Ores;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.wrappers.Item;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JKSSMiner {

	private JFrame frame;
    private Main core;
    private Choice choice;
    private JLabel lblDistanceToMine;
    private JSlider slider;

	/**
	 * Create the application.
	 */
	public JKSSMiner(Main core) {
        this.core = core;
		initialize();
        this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 215);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblRock = new JLabel("Rock");
        lblRock.setBounds(6, 46, 61, 16);
        frame.getContentPane().add(lblRock);

        choice = new Choice();
        choice.setBounds(156, 43, 233, 27);
        frame.getContentPane().add(choice);

        JButton btnStart = new JButton("Start");
        btnStart.setBounds(327, 138, 117, 29);
        frame.getContentPane().add(btnStart);

        lblDistanceToMine = new JLabel("Distance to rock");
        lblDistanceToMine.setBounds(6, 89, 135, 20);
        frame.getContentPane().add(lblDistanceToMine);

        slider = new JSlider(0, 15, 5);
        slider.setBounds(156, 76, 233, 50);
        frame.getContentPane().add(slider);

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem drop = new JMenuItem("Drop inventory");
        menu.add(drop);
        bar.add(menu);
        frame.setJMenuBar(bar);

        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);

        for (Ores r : Ores.values()){
            choice.add(r.getRockName());
        }

        drop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Item i : Inventory.getItems()) {
                    final int inventoryCount = Inventory.getItems().length;
                    i.drop();
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Inventory.getItems().length > inventoryCount;
                        }
                    }, 500);
                }
            }
        });

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lblDistanceToMine.setText("Distance to rock (" + slider.getValue() + ")");
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Ores r : Ores.values()) {
                    if (r.getRockName().equalsIgnoreCase(choice.getSelectedItem().toLowerCase())){
                        core.setDistance(slider.getValue());
                        core.setRockIDs(r.getRockIDs());
                        frame.setVisible(false);
                    }
                }
            }
        });
	}

    public JFrame getFrame() {
        return frame;
    }
}
