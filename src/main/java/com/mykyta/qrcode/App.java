package com.mykyta.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your link:");
        String prefix = "https://";
        String input = scanner.nextLine();
        String text = prefix + input;
        int width = 300;
        int height = 300;
        String filePath = "qrcode.png";

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            Path path = FileSystems.getDefault().getPath(filePath);

            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            System.out.println("QR-code successfully created: " + filePath);
        } catch (WriterException | IOException e) {
            System.out.println("Error generating QR-code: " + e.getMessage());
        }
    }
}