package eldertrack.ui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTableExample {
  public static void main(String[] args) {
    Object[] column = {"One", "Two"};
    Object[][] data = {{1, 2}, {3, 4}, {5, 6}};

    JTable toDoTable = new JTable(data, column);
    JScrollPane jpane = new JScrollPane(toDoTable);
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    panel.add(jpane);
    frame.getContentPane().add(new JScrollPane(panel));
    frame.setVisible(true);
  }
}