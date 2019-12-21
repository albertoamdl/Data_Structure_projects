/* CS1101 – Intro to Computer Science 
Instructor: Aguirre OR Akbar OR Villanueva 
Comprehensive Lab 2
By including my name below, I confirm that:
-	I am submitting my original work.
-	If I include code obtained from another source or I received help I am giving attribution to those sources as comments.
-	This submission does not incur in any academic dishonesty practice as described in the course syllabus.
Modified and submitted by: ALBERTO ANTONIO MIRANDA 
*/

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Scanner;

public class AwesomeGreenScreenReplacer {
	public static void main(String[] args) throws Exception {
		Scanner scnr = new Scanner(System.in);

		System.out.println("Would you like to use greenScreenPic.jpg as default image name?");
		System.out.println("a) Would you like to use backgroundPic.jpg as default image name?");
		System.out.println("b) use other backgroundPic image?");
		System.out.println("c) Would you like to utilize multiple images in a folder?");
		String response1 = scnr.nextLine();
		String greenImage;
		String responseFGname = "greenScreenPic.jpg";
		String responseFBname = "backgroundPic.jpg";

		switch (response1) {
		case "a":
			greenImage = "backgroundPic.jpg";
			break;

		case "b":
			System.out.println("Alright, please input the Background file name you'd like to use: ");
			responseFBname = scnr.nextLine();
			greenImage = responseFBname;
			break;
		case "c":

			System.out.println("c) Would you like to utilize multiple images in a folder?");
			response1 = scnr.nextLine();

			if (response1.equals("c")) {
				System.out.println("Please input folder directory name: ");
				String DirectoryName = scnr.nextLine();

				Color[][] backgroundImg = readImage("greenScreenPic.jpg");
				String[] fileNames = getFilesInFolder(DirectoryName);
				replaceGreenPixelsRecursive(backgroundImg, fileNames, 0);

			}
			break;
		}

		Color[][] greenScreenImg = readImage(responseFGname);
		Color[][] backgroundImg = readImage(responseFBname);
		Color[][] NewPixelImg = replaceGreenPixels(greenScreenImg, backgroundImg);
		displayImage(NewPixelImg);

		System.out.println("Alright, would you like to save your image? ");
		String responseWow = scnr.nextLine();

		switch (responseWow) {
		case "Y":
			System.out.println("Ok, what would you like to call it? ");
			String responseName = scnr.nextLine();

			saveImage(NewPixelImg, responseName);

			System.out.println("Thank you for using Awesome Green Screen Replacer!");
			break;
		case "N":
			System.out.println("Alright, thank you for using Awesome Green Screen Replacer! ");
			break;
		}

		/*
		 * if (response1.equals("Y")) { greenImage = "greenScreenPic.jpg"; } else if (
		 * response1.equals("N")) { System.out.println("Enter file name:");
		 * responseFGname = scnr.nextLine(); greenImage = responseFGname; }
		 * 
		 * else if (response1.equals("a")) { greenImage = "backgroundPic.jpg"; } else if
		 * ( response1.equals("N")) { System.out.
		 * println("c) Would you like to utilize multiple images in a folder?");
		 * response1 = scnr.nextLine();
		 * 
		 * if (response1.equals("c")) { greenImage =
		 * "Please input folder directory name: "; String DirectoryName =
		 * scnr.nextLine();
		 * 
		 * Color[][] backgroundImg = readImage("greenScreenPic.jpg"); String[] fileNames
		 * = getFilesInFolder(DirectoryName); replaceGreenPixelsRecursive(backgroundImg,
		 * fileNames, 0);
		 * 
		 * } else if ( response1.equals("N")) { System.out.
		 * println("Alright, please input the Background file name you'd like to use: "
		 * ); responseFBname = scnr.nextLine(); greenImage = responseFBname;
		 * 
		 * 
		 * } }
		 * 
		 * else{ System.out.println("Thank you for using AwesomeGreenScreen Replacer");
		 * }
		 * 
		 * 
		 * Color[][] greenScreenImg = readImage(responseFGname); Color[][] backgroundImg
		 * = readImage(responseFBname); Color[][] NewPixelImg =
		 * replaceGreenPixels(greenScreenImg, backgroundImg); displayImage(NewPixelImg);
		 * 
		 * System.out.println("Alright, would you like to save your image? "); String
		 * responseWow = scnr.nextLine();
		 * 
		 * switch (responseWow){ case "Y":
		 * System.out.println("Ok, what would you like to call it? "); String
		 * responseName = scnr. nextLine();
		 * 
		 * saveImage( NewPixelImg, responseName);
		 * 
		 * System.out.println("Thank you for using Awesome Green Screen Replacer!");
		 * break; case "N": System.out.
		 * println("Alright, thank you for using Awesome Green Screen Replacer! ");
		 * break; }
		 * 
		 * 
		 */
	}

	/**
	 * This method receives the color values associated with a pixel. It returns
	 * true if and only if the given pixel is green
	 * 
	 * @param red   Red value of the pixel
	 * @param green Green value of the pixel
	 * @param blue  Blue value of the pixel
	 * @return true if green; false, otherwise.
	 */
	public static boolean isGreenPixel(int red, int green, int blue) {
		if ((red > 65) && (blue < 120) && (green > 130) && (red < 160)) {
			return true;
		}

		return false;

	}

	/**
	 * This method creates a new image where the the green pixels in greenScreenImg
	 * are replaced with the corresponding background pixels from backgroundImg
	 * 
	 * @param greenScreenImg 2D array that contains the pixels of a green screen
	 *                       image
	 * @param backgroundImg  2D array that contains the pixels of a background image
	 * 
	 * @return Image where the green pixels from greenScreenImg were replace with
	 *         the corresponding background pixels from backgroundImg
	 */
	public static Color[][] replaceGreenPixels(Color[][] greenScreenImg, Color[][] backgroundImg) {

		Color[][] NewPixelImg = new Color[greenScreenImg.length][greenScreenImg[0].length];
		for (int i = 0; i < greenScreenImg.length; i++) {
			// System.out.println(greenScreenImg[0].length);
			// System.out.println(backgroundImg[0].length);

			for (int j = 0; j < greenScreenImg[0].length; j++) {
				// System.out.println(i + " " + j);
				// Color[][] image = readImage("greenScreenPic.jpg");

				int redValue = greenScreenImg[i][j].getRed();
				int greenValue = greenScreenImg[i][j].getGreen();
				int blueValue = greenScreenImg[i][j].getBlue();

				if (isGreenPixel(redValue, greenValue, blueValue)) {
					NewPixelImg[i][j] = backgroundImg[i][j];
				} else {
					NewPixelImg[i][j] = greenScreenImg[i][j];
				}
			}
		}

		return NewPixelImg;
	}

	/**
	 * Recursive method to process multiple background images
	 * 
	 * @param greenScreenImage
	 * @param backgroundImagesPaths
	 * @param currentIndex
	 */
	public static void replaceGreenPixelsRecursive(Color[][] greenScreenImage, String[] backgroundImagesPaths,
			int currentIndex) throws Exception {
		if (currentIndex >= backgroundImagesPaths.length) {
			System.out.println("Thank for using the Awesome green screen replacer");
			return;
		}

		Color[][] backgroundImg = readImage(backgroundImagesPaths[currentIndex]);
		Color[][] newPixelImg = replaceGreenPixels(greenScreenImage, backgroundImg);
		displayImage(newPixelImg);

		replaceGreenPixelsRecursive(greenScreenImage, backgroundImagesPaths, currentIndex + 1);

	}

	/**
	 * This method saves a given image to disk
	 * 
	 * @param imagePixels Image to be saved
	 * @param fileName    Name of the file
	 */
	public static void saveImage(Color[][] imagePixels, String fileName) {
		try {
			// Convert to BufferedImage
			BufferedImage bi = createImageFromPixelArray(imagePixels);

			// Save file
			File outputfile = new File(fileName);
			ImageIO.write(bi, "jpg", outputfile);
		} catch (IOException e) {
			System.out.println("Error: " + e.toString());
		}
	}

	/**
	 * This method displays the imaged passed as input
	 * 
	 * @param colors Image to be displayed
	 * 
	 */
	public static void displayImage(Color[][] imagePixels) {
		BufferedImage image = createImageFromPixelArray(imagePixels);

		ImageIcon icon = new ImageIcon(image);

		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(1280, 720);

		JLabel lbl = new JLabel();
		lbl.setIcon(icon);

		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * This method reads an image given its path
	 * 
	 * @param filePath The path of the image to be read
	 * @return A 2D array of pixels representing the image
	 * @throws Exception Exception is thrown when the file does not exist
	 */
	public static Color[][] readImage(String filePath) throws Exception {
		File imageFile = new File(filePath);
		BufferedImage image = ImageIO.read(imageFile);
		Color[][] colors = new Color[image.getWidth()][image.getHeight()];

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				colors[x][y] = new Color(image.getRGB(x, y));
			}
		}
		return colors;
	}

	/**
	 * Reads all the files in a given folder
	 * 
	 * @param folderPath The path to the folder
	 * 
	 * @return List of all files inside of the input folder
	 */
	public static String[] getFilesInFolder(String folderPath) {

		ArrayList<String> fileArrayList = new ArrayList<String>();
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				fileArrayList.add(file.getAbsolutePath());
			}
		}

		return fileArrayList.toArray(new String[fileArrayList.size()]);
	}

	/**
	 * This method receives a 2D array of pixel colors and returns its equivalent
	 * BufferedImage representation
	 * 
	 * @param imagePixels Pixel values of the image to be converted
	 * 
	 * @return BufferedImage representation of the input
	 */
	public static BufferedImage createImageFromPixelArray(Color[][] imagePixels) {
		BufferedImage image = new BufferedImage(imagePixels.length, imagePixels[0].length, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				image.setRGB(x, y, imagePixels[x][y].getRGB());
			}
		}

		return image;
	}

}