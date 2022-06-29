import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Filter {
	
	public static BufferedImage makeGrayscale(BufferedImage input, String name, String fileType, String path) {
		int width = input.getWidth(), height = input.getHeight();
		for(int y = 0; y<height; y++) {
			for(int x = 0; x<width; x++) {
				Color currentPixel = new Color(input.getRGB(x, y)), newPixel = null;
				int avg = getAverage(currentPixel);
				newPixel = new Color(avg,avg,avg);
				input.setRGB(x, y, newPixel.getRGB());
			}
		}
		saveImage(input, "Gray-"+name, fileType, path);
		return input;
	}
	
	public static BufferedImage makeRed(BufferedImage input, String name, String fileType, String path) {
		int width = input.getWidth(), height = input.getHeight();
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				Color currentPixel = new Color(input.getRGB(x, y)), newPixel = null; 
				int avg = getAverage(currentPixel);
				int[] colors = getColorValues(currentPixel);
				if(avg < 128) {
					int red = colors[0]*2;
					if(red > 255) {
						newPixel = new Color(255,0,0);
					}
					else {
						newPixel = new Color(colors[0]*2,0,0);
					}
				}
				else {
					newPixel = new Color(255,(avg*2)-255,(avg*2)-255);
				}
				input.setRGB(x, y, newPixel.getRGB());
			}
		}
		saveImage(input, "Red-"+name, fileType, path);
		return input;
	}
	
	public static int getAverage(Color pixel) {
		int result = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
		return result;
	}
	
	public static int[] getColorValues(Color pixel) {
		int[] colors = {pixel.getRed(), pixel.getGreen(), pixel.getBlue()};
		return colors;
	}
	
	public static void saveImage(BufferedImage image, String name, String fileType, String path) {
		try {
			File f = new File(path + name);
			ImageIO.write(image, fileType, f);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
}
