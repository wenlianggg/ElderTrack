package eldertrack.ui;

import javax.crypto.BadPaddingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import eldertrack.db.Crypto;
import eldertrack.db.LDBConfig;
import eldertrack.db.SQLObject;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DecryptDBFrame extends JFrame {
	private static final long serialVersionUID = -8300553804651155712L;
	private JPasswordField passwordField;

	DecryptDBFrame() {
		setTitle("ElderTrack Decryption");
		setBounds(800, 500, 330, 160);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblEnterDecryptionKey = new JLabel("Enter Decryption Key Password");
		lblEnterDecryptionKey.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		lblEnterDecryptionKey.setBounds(10, 11, 294, 36);
		panel.add(lblEnterDecryptionKey);
		
		JLabel lblpressEnterTo = new JLabel("(Press Enter to Continue)");
		lblpressEnterTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblpressEnterTo.setBounds(8, 95, 294, 14);
		panel.add(lblpressEnterTo);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				authenticate();
			}
		});
		passwordField.setBounds(10, 51, 294, 36);
		panel.add(passwordField);
		// TODO Auto-generated constructor stub
	}
	
	void authenticate() {
		char[] password = passwordField.getPassword();
		String initVector = "ElderTRACK451462";
		byte[] dbpassword;
		byte[] salt = {0x1F, 0x4E, 0x4D, 0x7A, 0x22, 0x31, 0x64, 0x5C, 0x1A};
		try {
			if (LDBConfig.useLocal) {
				dbpassword = Crypto.decrypt(password, salt, initVector, LDBConfig.dbencrypted);
				LDBConfig.setPassword(dbpassword);
			} else {
				dbpassword = Crypto.decrypt(password, salt, initVector, SQLObject.dbencrypted);
				SQLObject.setPassword(dbpassword);
			}
			this.setVisible(false);
			MainFrame.getInstance().setVisible(true);
		} catch (BadPaddingException e) {
			JOptionPane.showMessageDialog(this, "Decryption key rejected.");
		}
	}
	
}
