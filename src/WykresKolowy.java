import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WykresKolowy {
    private int ileUsuszonych;
    private int ileWszystkich;

    public WykresKolowy(int ileUsuszonych, int ileWszystkich) {
        this.ileUsuszonych = ileUsuszonych;
        this.ileWszystkich = ileWszystkich;

        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Wykres Kołowy");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPieChart(g);
            }
        };

        frame.add(panel);
        frame.setVisible(true);
        panel.setBackground(Color.pink);
    }

    private void drawPieChart(Graphics g) {
        int width = 300;
        int height = 300;
        int centerX = 250;
        int centerY = 250;
        int radius = Math.min(width, height) / 2;

        // Obliczanie procentowego udziału ususzonych i pozostałych kwiatków
        double ususzonychProcent = (double) ileUsuszonych / ileWszystkich * 100;
        double pozostalychProcent = 100 - ususzonychProcent;

        // Obliczanie kątów sektorów
        double startAngle = 0;
        double arcAngleUsuszonych = 360 * (ususzonychProcent / 100);
        double arcAnglePozostalych = 360 * (pozostalychProcent / 100);

        // Rysowanie sektora ususzonych kwiatków
        g.setColor(Color.yellow);
        g.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius,
                (int) startAngle, (int) arcAngleUsuszonych);

        // Rysowanie sektora pozostałych kwiatków
        g.setColor(Color.GREEN);
        g.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius,
                (int) (startAngle + arcAngleUsuszonych), (int) arcAnglePozostalych);

        // Obliczanie położenia etykiety dla sektora ususzonych kwiatków
        double midAngleUsuszonych = startAngle + arcAngleUsuszonych / 2;
        int labelXUsuszonych = (int) (centerX + 0.5 * radius * Math.cos(Math.toRadians(midAngleUsuszonych)));
        int labelYUsuszonych = (int) (centerY - 0.5 * radius * Math.sin(Math.toRadians(midAngleUsuszonych)));

        // Obliczanie położenia etykiety dla sektora pozostałych kwiatków
        double midAnglePozostalych = startAngle + arcAngleUsuszonych + arcAnglePozostalych / 2;
        int labelXPozostalych = (int) (centerX + 0.5 * radius * Math.cos(Math.toRadians(midAnglePozostalych)));
        int labelYPozostalych = (int) (centerY - 0.5 * radius * Math.sin(Math.toRadians(midAnglePozostalych)));

        // Rysowanie etykiet
        g.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 12); // Tworzenie nowej czcionki z pogrubieniem i większym rozmiarem
        g.setFont(font);
        g.drawString("ususzonych: " + ileUsuszonych, labelXUsuszonych, labelYUsuszonych);
        g.drawString("żywych: " + (ileWszystkich - ileUsuszonych), labelXPozostalych, labelYPozostalych);

        font = new Font("Arial", Font.BOLD, 16); // Tworzenie nowej czcionki z pogrubieniem i większym rozmiarem
        g.setFont(font);
        g.drawString("Wykres ususzonych roślin", 140, 50);
    }

    public static void main(String[] args) {
        int ileUsuszonych = 15;
        int ileWszystkich = 50;

        new WykresKolowy(ileUsuszonych, ileWszystkich);
    }
}
