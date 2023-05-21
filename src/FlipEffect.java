import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class FlipEffect {
    public static void main(String[] args) {
        ImageIcon originalIcon = new ImageIcon("resources/kaktus.png");
        Image originalImage = originalIcon.getImage();

        Image flippedImage = flipImageHorizontal(originalImage);

        ImageIcon flippedIcon = new ImageIcon(flippedImage);
        JLabel imageLabel = new JLabel(flippedIcon);

        JFrame frame = new JFrame("Odwrócony obraz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(imageLabel);
        frame.pack();
        frame.setVisible(true);
    }

    public static Image flipImageHorizontal(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        BufferedImage flippedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = flippedImage.createGraphics();

        AffineTransform transform = new AffineTransform();
        transform.translate(0, height);
        transform.scale(1, -1);

        g2d.drawImage(image, transform, null);
        g2d.dispose();

        return flippedImage;
    }
}
