package eldertrack.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MarqueePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 8319487979316580272L;
	private static final int RATE = 16;
    private Timer timer = new Timer(1000 / RATE, this);
    private JLabel label = new JLabel();
    private String s;
    private int n;
    private int index;

    public MarqueePanel(String s, int n) {
        if (s == null || n < 1) {
            throw new IllegalArgumentException("Null string or n < 1");
        }
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(' ');
        }
        this.s = sb + s + sb;
        this.n = n;
        label.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        label.setForeground(Color.WHITE);
        label.setText(sb.toString());
        this.add(label);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
    
    public void setText(String s) {
    	this.s = s;
    	index = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        index++;
        if (index > s.length() - n) {
            index = 0;
        }
        label.setText(s.substring(index, index + n));
    }
}