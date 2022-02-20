package Com.Waluigi.SurvivalGame.Main.Util;

import Com.Waluigi.SurvivalGame.Main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class RenderingSystem {
    /**
     * A class containing methods for Drawing images onto the screen
     */
    public static class DrawSystem {
        /**
         * Draws the image at the corner instead of the centerR
         * @param g The graphics
         * @param ImageFile The image you want to draw
         * @param Scale The scale of the image
         * @param X X location
         * @param Y Y Location
         * */
        public void DrawImage(Graphics g, int X, int Y, Image ImageFile, int Scale){
            g.drawImage(ImageFile, X, Y, Scale, Scale, null);
        }
        /**
         * Draws the image in the center instead of the corner
         * @param g The graphics
         * @param ImageFile The image you want to draw
         * @param Scale The scale of the image
         * @param X X location
         * @param Y Y location
         * */
        public void DrawCenteredImage(Graphics g, int X, int Y, Image ImageFile, int Scale){
            g.drawImage(ImageFile, X + ImageFile.getWidth(null) / 2,
                    Y + ImageFile.getHeight(null) / 2, Scale, Scale, null);
        }

        public Image StackImages(String[] ImageFiles){
            ImageGetter getter = new ImageGetter();
            Image[] Images = new Image[ImageFiles.length];
            int a = 0;
            for(String File : ImageFiles){
                Images[a] = getter.getImage(GamePanel.IMAGE_PATH, File);
                a++;
            }
            BufferedImage image = new BufferedImage(Images[0].getWidth(null), Images[0].getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.getGraphics();
            for (Image value : Images) {
                g.drawImage(value, 0, 0, null);
            }
            return image;
        }
    }

    /**
     * A class containing the method to get an image from location
     */
    public static class ImageGetter {
        /**
         * Used to get an image from a specified place
         * @param Filename This is the path to the folder the image is in
         * @param Path This is the image file
         * @return Returns the Image received as a java.awt.Image
         * */
        public Image getImage(String Path, String Filename){
            URL url = this.getClass().getClassLoader().getResource(Path + Filename);
            if (url != null) {
                return new ImageIcon(url).getImage();
            }else{
                System.out.println("ERROR: (" + Path + Filename + ")");
                return null;
            }
        }
    }
}