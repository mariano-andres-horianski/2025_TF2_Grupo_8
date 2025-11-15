package vista.RendererAsociados;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JPanel implements TableCellRenderer {

    private JButton btn = new JButton();

    public ButtonRenderer(String text) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        btn.setPreferredSize(new Dimension(60, 15));

        btn.setText(text);
        add(btn);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
