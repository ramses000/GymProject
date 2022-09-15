package demo4;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.*;
import java.io.InputStream;
import java.io.OutputStream;
public class demo5 {

	public static void main(String[] args)throws IOException {
		new File("C:/GymProject").mkdirs();
		new File("C:/GymProject/Resources").mkdirs();
		try {
			image(1);
			XWPFDocument document = new XWPFDocument();
			FileOutputStream out = new FileOutputStream(new File("C:/GymProject/gym.docx"));
//Title Creation
			XWPFParagraph title1 = document.createParagraph();
			title1.setAlignment(ParagraphAlignment.CENTER);  
			XWPFRun title = title1.createRun();
            title.setBold(true);
        
            title.setFontSize(36);
            title.setFontFamily("Times");
			title.setText("Workout Plan");
//Chest Creation
			XWPFParagraph p1 = document.createParagraph();
			p1.setAlignment(ParagraphAlignment.LEFT);  
			XWPFRun chest = p1.createRun();
            chest.setBold(true);
            
            chest.setFontSize(22);
            chest.setFontFamily("Times");
			chest.setText("Chest");
			
//Flat Bench Press
			XWPFParagraph p1flat = document.createParagraph();
			p1flat.setAlignment(ParagraphAlignment.LEFT);  
			XWPFRun flatchest = p1flat.createRun();
            flatchest.setBold(true);
            
			flatchest.setFontSize(12);
			flatchest.setFontFamily("Times");
			flatchest.setText("Flat Bench Press");
//Image Add
			String imgFile = image(0);
            flatchest.addBreak();
            try (FileInputStream is = new FileInputStream(imgFile)) {
                flatchest.addPicture(is,
                        Document.PICTURE_TYPE_JPEG,    
                        imgFile,
                        Units.toEMU(400),
                        Units.toEMU(200));            
            }
//Incline Bench
            flatchest.addBreak();
			flatchest.setText("Incline Bench Press");
			imgFile = image(1);
            flatchest.addBreak();
            try (FileInputStream is = new FileInputStream(imgFile)) {
                flatchest.addPicture(is,
                        Document.PICTURE_TYPE_JPEG,    
                        imgFile,
                        Units.toEMU(400),
                        Units.toEMU(200));            
            }
//Decline Bench 
            flatchest.addBreak();
            flatchest.setText("Decline Bench Press");
			imgFile = image(2);
            flatchest.addBreak();
            try (FileInputStream is = new FileInputStream(imgFile)) {
                flatchest.addPicture(is,
                        Document.PICTURE_TYPE_JPEG,    
                        imgFile,
                        Units.toEMU(400),
                        Units.toEMU(200));            
            }
            
            
            
//Close doc
			document.write(out);
			out.close();		
		}
		catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Workout Plan Created");
	}
	public static String image(int j) throws IOException{
		ArrayList<String> chestURL = new ArrayList<String>();
        ArrayList<String> chestFile = new ArrayList<String>();
        chestURL.add("https://image.shutterstock.com/image-illustration/closegrip-barbell-bench-press-3d-260nw-430936051.jpg"); // Flat benchpress URL
        chestFile.add("C:/GymProject/Resources/benchpress.jpg");
        chestURL.add("https://static.strengthlevel.com/images/illustrations/incline-bench-press-1000x1000.jpg");
        chestFile.add("C:/GymProject/Resources/inclinebenchpress.jpg");
        chestURL.add("https://images.squarespace-cdn.com/content/v1/55e406fbe4b0b03c5e7543ae/1492945457901-Z6JGIW7OSYVY7OSUMCOO/Decline+Barbell+Chest+Press.jpg");
        chestFile.add("C:/GymProject/Resources/declinebenchpress.jpg");
        for(int i = 0; i <chestURL.size();i++) {
            saveImage(chestURL.get(i), chestFile.get(i));
            //Put url and names in an arraylist so that you can just use the arraylist name instead for easily changing the output with a loop
            
        }
        return chestFile.get(j);
	}
	public static void saveImage(String flatBenchPressURL, String flatBenchPress) throws IOException {
        URL url = new URL(flatBenchPressURL);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(flatBenchPress);
        byte[] b = new byte[2048];
        int length;
    
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        is.close();
        os.close();
    }
}
