package test;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class Decode {
	 public static String decodeQr(String filePath) {
	        String retStr = "";
	        if ("".equalsIgnoreCase(filePath) && filePath.length() == 0) {
	            return "图片路径为空!";
	        }
	        try {
	            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(filePath));
	            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
	            Binarizer binarizer = new HybridBinarizer(source);
	            BinaryBitmap bitmap = new BinaryBitmap(binarizer);
	            HashMap<DecodeHintType, Object> hintTypeObjectHashMap = new HashMap<>();
	            hintTypeObjectHashMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
	            Result result = new MultiFormatReader().decode(bitmap, hintTypeObjectHashMap);
	            retStr = result.getText();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return retStr;
	    }
	 
	 
	 public static void main(String[] args) {
		System.out.println(new Decode().decodeQr("D:\\-1998301148.png"));
	}
}
