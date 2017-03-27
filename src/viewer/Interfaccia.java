package viewer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Modifier;
import eccezioni.ModifierException;

public class Interfaccia extends JFrame {

	private JPanel contentPane;
	private JTextField textField_workstation;
	private JTextField textField_jobstream;
	private JTextPane txtpnInviareLogA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaccia frame = new Interfaccia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interfaccia() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		JLabel lblWorkstation = new JLabel("Workstation");
		lblWorkstation.setFont(new Font("Arial", Font.BOLD, 14));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblWorkstation, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWorkstation, 74, SpringLayout.WEST, contentPane);
		contentPane.add(lblWorkstation);

		JLabel lblJobstream = new JLabel("Jobstream");
		lblJobstream.setFont(new Font("Arial", Font.BOLD, 14));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblJobstream, 0, SpringLayout.NORTH, lblWorkstation);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblJobstream, -86, SpringLayout.EAST, contentPane);
		contentPane.add(lblJobstream);

		textField_workstation = new JTextField();
		textField_workstation.setHorizontalAlignment(SwingConstants.LEFT);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_workstation, 6, SpringLayout.SOUTH, lblWorkstation);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_workstation, 48, SpringLayout.WEST, contentPane);
		contentPane.add(textField_workstation);
		textField_workstation.setColumns(10);

		textField_jobstream = new JTextField();
		textField_jobstream.setHorizontalAlignment(SwingConstants.LEFT);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_workstation, -39, SpringLayout.WEST,
				textField_jobstream);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_jobstream, 0, SpringLayout.NORTH,
				textField_workstation);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_jobstream, -192, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_jobstream, -34, SpringLayout.EAST, contentPane);
		textField_jobstream.setColumns(10);
		contentPane.add(textField_jobstream);

		JLabel lblNote = new JLabel("Note:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNote, 0, SpringLayout.WEST, textField_workstation);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNote, -157, SpringLayout.SOUTH, contentPane);
		lblNote.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(lblNote);

		txtpnInviareLogA = new JTextPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtpnInviareLogA, 10, SpringLayout.SOUTH, lblNote);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtpnInviareLogA, 2, SpringLayout.WEST, textField_workstation);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtpnInviareLogA, -50, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtpnInviareLogA, -29, SpringLayout.EAST, contentPane);
		txtpnInviareLogA.setToolTipText("Completare con indirizzi e-mail o DL");
		txtpnInviareLogA.setText("Inviare log a: \r\n\r\n");
		contentPane.add(txtpnInviareLogA);

		JButton btnNewButton = new JButton("MODIFICA");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, 21, SpringLayout.SOUTH, txtpnInviareLogA);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 151, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton, -4, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, -128, SpringLayout.EAST, contentPane);
		
		//bottone che costruisce la logica di scrittura/modifica file
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modifier modificatore;

				modificatore = new Modifier(textField_jobstream.getText(), textField_workstation.getText(),
						txtpnInviareLogA.getText());
				
				// se non manca nessun dato (jobstream o workstation), procedi a creare/modificare file; altrimenti spara avviso warning
				if (!(modificatore.getJobstream().equals("")) && !(modificatore.getWorkstation().equals("")))
					modificatore.modificaFile();
				else {
					JOptionPane.showMessageDialog(Interfaccia.this, "Manca un dato vincolante", "Avviso", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		contentPane.add(btnNewButton);
	}
}
