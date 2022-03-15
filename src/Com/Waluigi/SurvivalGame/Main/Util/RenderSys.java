package Com.Waluigi.SurvivalGame.Main.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class RenderSys {
    /**
     * Draws the image at the corner instead of the center
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
    /**
     * Used to stack multiple images on top of each other
     * @param Path The path to the Image
     * @param ImageFiles A list of images to stack (note: First image defines size of the image)
     * @return Returns the stacked image
     */
    public Image StackImages(String Path, String[] ImageFiles){
        Image[] Images = new Image[ImageFiles.length];
        int a = 0;
        for(String File : ImageFiles){
            Images[a] = getImage(Path, File);
            a++;
        }
        BufferedImage image = new BufferedImage(Images[0].getWidth(null), Images[0].getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        for (Image value : Images) {
            g.drawImage(value, 0, 0, null);
        }
        return image;
    }
    public static class TileMap{
        public Layer[] Layers;
        public int GridSize;
        public Dimension ScreenSize;
        public TileMap(int LayerAmount, int GridSize, Dimension ScreenSize){
            this.GridSize = GridSize;
            this.ScreenSize = ScreenSize;
            this.Layers = new Layer[LayerAmount];
        }
        public void VisulizeGrid(Graphics g, Color color){
            for(int x = 0; x < ScreenSize.width/GridSize; ++x) {
                g.setColor(color);
                g.drawLine(x * GridSize, 0, x * GridSize, ScreenSize.height);
                g.drawLine(0, x * GridSize, ScreenSize.width, x * GridSize);
            }
        }
        public class Layer{
            public Image[][] Tiles;
            Layer(){
                Tiles = new Image[ScreenSize.width/GridSize][ScreenSize.height /GridSize];
            }
        }
    }
}