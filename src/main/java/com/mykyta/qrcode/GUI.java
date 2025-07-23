package com.mykyta.qrcode;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GUI  extends JFrame {
    private JTextField textField;
    private JButton generateButton;
    /*private JButton saveButton;       // Кнопка сохранения*/
    private JButton clearButton;
    private JLabel imageLabel;

    public GUI(){
        super("QR Code Generator");
        setupWindow();
        createComponents();
        layoutComponents();
        setupEventHandlers();
        setVisible(true);

    }
    private void setupWindow() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void createComponents() {
        textField = new JTextField(30); // 30 символов ширина
        textField.setFont(new Font("Arial", Font.PLAIN, 14));

        generateButton = new JButton("Create QR-код");
        clearButton = new JButton("Clear");
        /*saveButton = new JButton("Сохранить");*/

        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(300, 300));
        imageLabel.setBorder(BorderFactory.createEtchedBorder());
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setText("QR-code will appear here");
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Enter your text or link:"));
        topPanel.add(textField);
        topPanel.add(generateButton);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(imageLabel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(clearButton);
        //bottomPanel.add(saveButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
    }

    private void setupEventHandlers() {
        generateButton.addActionListener(e -> generateQRCode());

        /*// Обработчик для кнопки сохранения
        saveButton.addActionListener(e -> saveQRCode());*/

        clearButton.addActionListener(e -> clearQRCode());

        textField.addActionListener(e -> generateQRCode());
    }

    private void generateQRCode() {
        String text = textField.getText().trim();

        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter text to generate QR code",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            QRCodeGenerator generator = new QRCodeGenerator();
            BufferedImage currentQRImage = generator.generateQRCode(text);

            ImageIcon icon = new ImageIcon(currentQRImage);
            imageLabel.setIcon(icon);
            imageLabel.setText(""); // Убираем текст

            /*// Активируем кнопку сохранения
            saveButton.setEnabled(true);*/

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error creating QR code: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearQRCode() {
        imageLabel.setIcon(null);
        imageLabel.setText("QR-code will appear here");

        /*// Очищаем сохраненное изображение
        currentQRImage = null;

        // Делаем кнопки неактивными
        saveButton.setEnabled(false);*/
        /*clearButton.setEnabled(false);*/

        textField.setText("");

        System.out.println("Qr-code cleared");
    }

}