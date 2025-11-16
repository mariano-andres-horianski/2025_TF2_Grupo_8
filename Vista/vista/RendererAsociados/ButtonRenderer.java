package vista.RendererAsociados;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
/**
 * Renderizador de celdas (TableCellRenderer) para la JTable.
 * <p>
 * Su única responsabilidad es definir la <b>apariencia visual</b> de la celda como un botón.
 * Esta clase "dibuja" un botón, pero no maneja los clics.
 * <p>
 * Es la contraparte visual del ButtonEditor, que maneja la interacción.
 */
public class ButtonRenderer extends JPanel implements TableCellRenderer {

    private JButton btn = new JButton();
    /**
     * Constructor del ButtonRenderer.
     * <p><b>Postcondición:</b> el panel se inicializa con un layout centrado y se le añade un JButton con el texto especificado. 
     * @param text El texto que se mostrará en el botón (ej. "Editar").
     */
    public ButtonRenderer(String text) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        btn.setPreferredSize(new Dimension(90, 15));

        btn.setText(text);
        add(btn);
    }
    /**
	 * Devuelve el componente (este panel) que la JTable usará para dibujar la celda.
	 *
	 * @param table La JTable que está dibujando la celda.
	 * @param value El valor contenido en la celda (ignorado en este caso).
	 * @param isSelected true si la celda está seleccionada (ignorado).
	 * @param hasFocus true si la celda tiene el foco (ignorado).
	 * @param row El índice de la fila que se está dibujando.
	 * @param column El índice de la columna que se está dibujando.
	 *
	 * @return Este mismo JPanel (que contiene el JButton) como el componente a "pintar".
	 */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
