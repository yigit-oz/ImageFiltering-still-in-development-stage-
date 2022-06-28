import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App extends Filter{
	
	public static void Application() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String filename = "", fileType = "", path = "";
		BufferedImage img = null;
		while(true) {
			System.out.println("Please select an image...");
			JFileChooser fileChooser = new JFileChooser("C:");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes());
			fileChooser.setFileFilter(filter);
			int result = fileChooser.showOpenDialog(fileChooser);	
			if(result == JFileChooser.APPROVE_OPTION) {
				File image = fileChooser.getSelectedFile();
				try {
					img = ImageIO.read(image);
					filename = image.getName();
					int extensionPlace = filename.lastIndexOf(".") + 1;
					fileType = filename.substring(extensionPlace);
					path = image.getAbsolutePath();
					int lastPlace = path.lastIndexOf("\\");
					path = path.substring(0,lastPlace) + "\\";
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			else {
				System.out.println("You didn't select a file.");
				break;
			}
			
			int filterNumber = -1;
			while(true) {	
				String[] filters = {"Grayscale", "Red", "Tan", "Rainbow", "Invert"};
				for(int i=0; i<filters.length; i++) {
					System.out.println(i+1 + "--" +filters[i]);
				}
				System.out.println("Please choose the number of filter to apply: (Enter -1 to exit, Enter -2 to select another image)");
				filterNumber = input.nextInt();
				if(filterNumber == -1) {
					return;
				}
				if(filterNumber == -2) {
					break;
				}
				if(filterNumber > filters.length || filterNumber < 1) {
					System.out.println("Please choose an appropriate number.");
					continue;
				}
				switch(filterNumber) {
				case 1:
					makeGrayscale(img, filename, fileType, path);
					break;
				case 2:
					makeRed(img, filename, fileType, path);
					break;
				case 3:
					System.out.println(filterNumber + " is chosen");
					break;
				case 4: 
					System.out.println(filterNumber + " is chosen");
					break;
				case 5:
					System.out.println(filterNumber + " is chosen");
					break;
				}
				System.out.println("Your image is saved to the path: " + path);
			}
		}
		input.close();
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to the image filtering app!");
		Application();
		System.out.println("Done");
	}
	
}










