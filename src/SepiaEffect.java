import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SepiaEffect {

    public static void main(String[] args) {
        // Wczytanie obrazka
        ImageIcon originalIcon = new ImageIcon("path/to/your/image.jpg");
        Image originalImage = originalIcon.getImage();

        // Przekształcenie obrazka na efekt sepii
        Image sepiaImage = applySepiaEffect(originalImage);

        // Wyświetlenie obrazka
        JFrame frame = new JFrame("Sepia Effect");
        JPanel panel = new JPanel();
        panel.add(new JLabel(new ImageIcon(sepiaImage)));
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static Image applySepiaEffect(Image image) {
        BufferedImage bufferedImage = toBufferedImage(image);

        // Przekształcenie obrazka w efekt sepii
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                Color color = new Color(bufferedImage.getRGB(x, y));

                if (!color.toString().equals("java.awt.Color[r=0,g=0,b=0]")) {

                    int inputRed = color.getRed();
                    int inputGreen = color.getGreen();
                    int inputBlue = color.getBlue();

                    int outputRed = (int) (inputRed * 0.393 + inputGreen * 0.769 + inputBlue * 0.189);
                    int outputGreen = (int) (inputRed * 0.349 + inputGreen * 0.686 + inputBlue * 0.168);
                    int outputBlue = (int) (inputRed * 0.272 + inputGreen * 0.534 + inputBlue * 0.131);

                    // Zastosowanie ograniczeń wartości kolorów do zakresu 0-255
                    outputRed = Math.min(outputRed, 255);
                    outputGreen = Math.min(outputGreen, 255);
                    outputBlue = Math.min(outputBlue, 255);

                    Color outputColor = new Color(outputRed, outputGreen, outputBlue);
                    bufferedImage.setRGB(x, y, outputColor.getRGB());
                }
            }
        }

        return bufferedImage;
    }

    private static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        // Konwersja obrazu na BufferedImage
        BufferedImage bufferedImage = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();

        return bufferedImage;
    }
}
