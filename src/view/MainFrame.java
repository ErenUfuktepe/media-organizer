package view;

import controller.MediaController;
import enums.OrganizeType;
import utils.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private final List<String> extensions = List.of("jpg", "png", "gif", "jpeg");
    private final static String TITLE = "Media Organizer";
    private final static int WIDTH = 800;
    private final static int HEIGHT = 600;
    private JPanel panel;
    private JLabel label;
    private JTextField folderPathField;
    private JButton browseButton;
    private JRadioButton monthRadioButton, weekRadioButton, dayRadioButton;
    private ButtonGroup radioGroup;
    private JButton startButton;
    private final MediaController mediaController = new MediaController(extensions);

    public MainFrame() {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        initComponents();  // Initialize UI components
        setupLayout();     // Arrange UI components in the panel
        setupButtonActions(); // Add button event listeners

        setVisible(true);
    }

    private void initComponents() {
        panel = new JPanel(new GridBagLayout());

        // Title Label
        label = new JLabel(TITLE);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        // Folder Path Field
        folderPathField = new JTextField(30);
        folderPathField.setHorizontalAlignment(JTextField.CENTER);
        folderPathField.setFont(new Font("Arial", Font.PLAIN, 20));

        // Browse Button
        browseButton = new JButton("Browse");

        // Radio buttons
        monthRadioButton = new JRadioButton("Month");
        weekRadioButton = new JRadioButton("Week");
        dayRadioButton = new JRadioButton("Day");


        // Group Radio Buttons
        radioGroup = new ButtonGroup();
        radioGroup.add(monthRadioButton);
        radioGroup.add(weekRadioButton);
        radioGroup.add(dayRadioButton);
        // Default radio button
        monthRadioButton.setSelected(true);

        // Start Button
        startButton = new JButton("Start");
        startButton.setEnabled(false);
    }

    private void setupLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 20, 0); // Default spacing

        // Title Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label, gbc);

        // Folder Path Field
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(folderPathField, gbc);

        // Browse Button
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(browseButton, gbc);

        // Radio Button Panel
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        radioPanel.add(monthRadioButton);
        radioPanel.add(weekRadioButton);
        radioPanel.add(dayRadioButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(radioPanel, gbc);

        // Start Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(startButton, gbc);

        add(panel);
    }

    private void setupButtonActions() {
        setupBrowseButtonAction();
        setupStartButtonAction();
    }

    private void setupBrowseButtonAction() {
        browseButton.addActionListener(e -> {
            JFileChooser folderChooser = new JFileChooser();
            folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Only allow folder selection
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Image Files (*.jpg, *.png, *.gif, etc)", String.valueOf(extensions));
            folderChooser.setFileFilter(filter);
            int returnValue = folderChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                folderPathField.setText(folderChooser.getSelectedFile().getAbsolutePath());
                startButton.setEnabled(true);
            }
        });
    }

    private void setupStartButtonAction() {
        startButton.addActionListener(e -> {
            if (!FileUtils.isValidDirectory(folderPathField.getText())) {
                System.err.println("Path not valid: " + folderPathField.getText());
            }

            radioGroup.getElements().asIterator().forEachRemaining(radioButton -> {
                if (radioButton.isSelected()) {
                    mediaController.organizeMedia(folderPathField.getText(), OrganizeType.fromDisplayName(radioButton.getText()));
                    mediaController.shutdown();
                }
            });
        });
    }


}
