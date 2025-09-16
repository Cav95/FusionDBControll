package descriptionupdate.view.factory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Factory class for creating GUI components with consistent styling.
 * Provides methods to create buttons, text fields, and combo boxes.
 */
public class GuiFactory {
    public static final String FONT = "Roboto";

    /**
     * Creates a JButton with the specified properties.
     *
     * @param name       the text to display on the button
     * @param backgroud  the background color of the button
     * @param foreGround the foreground color of the button
     * @param font       the font of the button text
     * @param action     the action listener to be added to the button
     * @return a JButton with the specified properties
     */
    public static JButton getButton(final String name, final Color backgroud, final Color foreGround,
            final Font font, final ActionListener action) {

        final JButton button = new JButton(name);
        button.setBackground(backgroud);
        button.setForeground(foreGround);
        button.setFont(font);
        button.addActionListener(action);
        button.setEnabled(true);
        button.setFocusPainted(false);
        return button;

    }

    /**
     * Creates a JTextField with the specified number of columns.
     * 
     * @param i the number of columns for the text field
     * @return a JTextField with the specified number of columns
     */
    public static JTextField getTextField(final Integer i) {
        JTextField textField = new JTextField(i);
        return textField;
    }

    /**
     * Creates a JComboBox with the specified list of strings.
     * 
     * @param allGroupTypeString the list of strings to populate the combo box
     * @return a JComboBox with the specified list of strings
     */
    public static JComboBox<String> getComboBox(final List<String> allGroupTypeString) {
        return new JComboBox<>(allGroupTypeString.toArray(new String[0]));
    }

    /**
     * Creates a JLabel with the specified properties.
     * 
     * @param text  the text to display on the label
     * @param font  the font of the label text
     * @param color the color of the label text
     * @return a JLabel with the specified properties
     */
    public static JLabel getLabel(final String text, final Font font, final Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    /**
     * Creates a Font with the specified style and size.
     * 
     * @param style the style of the font
     * @param size  the size of the font
     * @return a Font with the specified style and size
     */
    public static Font getFont(final String style, final int size) {
        return Font.getFont(style, new Font(FONT, Font.PLAIN, size));
    }

}
