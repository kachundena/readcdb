package com.kachundena.readcdb;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Image;


import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.*;

import java.io.File;
import java.io.ByteArrayOutputStream;


public class readimagecdb {

    public static void main(String[] args) {
        String szValue = readCdbFromFile(args[0]);
        if (szValue == "") {
            System.out.println("Error!");
        }
        else {
            System.out.println(szValue);
        }
    }
    
    public static String readCdbFromFile(String pszImageFile) {
        String returnvalue = "";
        InputStream barCodeInputStream;
        try {
            String szImagePngFile = "";
            if (getExtensionFile(pszImageFile).toUpperCase().equals("JPG") || getExtensionFile(pszImageFile).toUpperCase().equals("JPEG")) {
                convertJpgToPng(pszImageFile, getPngFile(pszImageFile)); 
                szImagePngFile = getPngFile(pszImageFile);
            }
            else {
                szImagePngFile = pszImageFile;
            }
            barCodeInputStream = new FileInputStream(szImagePngFile);
            BufferedImage barCodeBufferedImage;
            barCodeBufferedImage = ImageIO.read(barCodeInputStream);
            LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Reader reader = new MultiFormatReader();          
            Result result = reader.decode(bitmap);           
            returnvalue = result.getText();    
            return returnvalue;
        } 
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        } 
        catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        } 
        catch (ChecksumException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        } 
        catch (FormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }
    
    public static void convertJpgToPng(String pszInputFile, String pszOutputFile) {
 
        try {
            // read a jpeg from a inputFile
            BufferedImage bufferedImage;
            bufferedImage = ImageIO.read(new File(pszInputFile));
            // write the bufferedImage back to outputFile
            ImageIO.write(bufferedImage, "png", new File(pszOutputFile));
            // this writes the bufferedImage into a byte array called resultingBytes
            // ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            // ImageIO.write(bufferedImage, "png", byteArrayOut);
            // byte[] resultingBytes = byteArrayOut.toByteArray();
            // Bitmap bitmap = BitmapFactory.decodeByteArray(resultingBytes, 0, resultingBytes.length);
        } 
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public static String getExtensionFile(String pszFileName) {
        String returnvalue = "";
        if (pszFileName.lastIndexOf(".") != -1 &&  pszFileName.lastIndexOf(".") < pszFileName.length() - 1) {
            returnvalue = pszFileName.substring(pszFileName.lastIndexOf(".") + 1);
        }
        return returnvalue;
    }
    
    public static String getPngFile (String pszFileName) {
        String returnvalue = "";
        if (pszFileName.lastIndexOf(".") != -1 &&  pszFileName.lastIndexOf(".") < pszFileName.length() - 1) {
            returnvalue = pszFileName.substring(0, pszFileName.lastIndexOf(".")) + "rep.png";
        }        
        return returnvalue;
    }
    
}
