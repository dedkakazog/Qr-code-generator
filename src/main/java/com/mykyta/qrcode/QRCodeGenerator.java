package com.mykyta.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

public class QRCodeGenerator {
    private static final int width = 300;
    private static final int height = 300;
    private static final String filePath = "qrcode.png";
    private static final String prefix = "https://";

    private static String text;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*linkParser(scanner);*/
    }

    public BufferedImage generateQRCode(String text){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            /*Path path = FileSystems.getDefault().getPath(filePath);

            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);*/

            return MatrixToImageWriter.toBufferedImage(bitMatrix);

        } catch (WriterException /*| IOException*/ e) {
            System.out.println("Error generating QR-code: " + e.getMessage());
        }
        return null;
    }

    /*private static void linkParser(Scanner scanner) {
        System.out.println("Enter your link:");
        String input = scanner.nextLine();
        if(input.startsWith(prefix)){
            text = input;
        }
        text = prefix + input;
    }*/
}