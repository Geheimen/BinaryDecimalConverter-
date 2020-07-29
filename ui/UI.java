package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

import converter.Converter;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Label;
import javax.swing.ButtonGroup;
import java.awt.Color;

public class UI {

	private JFrame frame;
	private JTextField input;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 146);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{190, 215, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 92, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("Convert to Binary");
		buttonGroup.add(rdbtnNewRadioButton);
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.anchor = GridBagConstraints.NORTH;
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 0;
		gbc_rdbtnNewRadioButton.gridy = 0;
		frame.getContentPane().add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		
		final JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Convert to Decimal");
		buttonGroup.add(rdbtnNewRadioButton_1);
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_1.fill = GridBagConstraints.VERTICAL;
		gbc_rdbtnNewRadioButton_1.gridx = 1;
		gbc_rdbtnNewRadioButton_1.gridy = 0;
		frame.getContentPane().add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		final JTextPane output = new JTextPane();
		output.setSize(new Dimension(100, 200));
		output.setEditable(false);
		GridBagConstraints gbc_output = new GridBagConstraints();
		gbc_output.anchor = GridBagConstraints.NORTH;
		gbc_output.fill = GridBagConstraints.HORIZONTAL;
		gbc_output.insets = new Insets(0, 0, 5, 0);
		gbc_output.gridx = 1;
		gbc_output.gridy = 1;
		frame.getContentPane().add(output, gbc_output);
		
		JButton btnNewButton = new JButton("Convert");
		btnNewButton.setDisplayedMnemonicIndex(1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNewRadioButton.isSelected()) {
					String bin = Converter.convertNumber(Double.parseDouble(input.getText()), 2);
					output.setText(String.valueOf(bin));
				} else if(rdbtnNewRadioButton_1.isSelected()) {
					String dec = Converter.convertNumber(Double.parseDouble(input.getText()), 10);
					output.setText(String.valueOf(dec));
				} else {
					JOptionPane.showConfirmDialog(frame, null, "Please select which base to convert to", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		final Label validationLabel = new Label("                                     ");
		GridBagConstraints gbc_validationLabel = new GridBagConstraints();
		gbc_validationLabel.insets = new Insets(0, 0, 0, 5);
		gbc_validationLabel.gridx = 0;
		gbc_validationLabel.gridy = 3;
		frame.getContentPane().add(validationLabel, gbc_validationLabel);
		
		input = new JTextField();
		
		input.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				try {
					char key = e.getKeyChar();

					if ((key != '\b' && key != KeyEvent.VK_ESCAPE && key != KeyEvent.VK_ENTER) && (key < '0' || key > '9')) {
						validationLabel.setText("Invalid Character");
						validationLabel.setForeground(Color.RED);
						e.consume();
					} else {
						validationLabel.setText("Valid Input");
						validationLabel.setForeground(Color.GREEN);
					}
				} catch (NumberFormatException e1) {
					return;
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		GridBagConstraints gbc_input = new GridBagConstraints();
		gbc_input.insets = new Insets(0, 0, 5, 5);
		gbc_input.fill = GridBagConstraints.HORIZONTAL;
		gbc_input.gridx = 0;
		gbc_input.gridy = 1;
		frame.getContentPane().add(input, gbc_input);
		input.setColumns(10);
		
		Label label = new Label("Input");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		frame.getContentPane().add(label, gbc_label);
		
		Label label_1 = new Label("Output");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 2;
		frame.getContentPane().add(label_1, gbc_label_1);
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 3;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
	}

}
