// Proyecto 2
// Vázquez Martínez Josué Abraham 7CM1

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MovimientoPoligonos extends JPanel {
    private Random random = new Random();
    private Polygon[] poligonos;

    public MovimientoPoligonos(int cantidad) {
        poligonos = new Polygon[cantidad];
        Timer timer = new Timer(50, e -> {
            for (Polygon poligono : poligonos) {
                if (poligono != null) {
                    moverPoligono(poligono);
                }
            }
            repaint();
        });
        timer.start();
    }

    private void moverPoligono(Polygon poligono) {
        for (int i = 0; i < poligono.npoints; i++) {
            poligono.xpoints[i] += random.nextInt(3) - 1;
            poligono.ypoints[i] += random.nextInt(3) - 1;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Polygon poligono : poligonos) {
            if (poligono != null) {
                g2d.drawPolygon(poligono);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java MovimientoPoligonos <número de polígonos>");
            return;
        }

        int cantidadPoligonos = Integer.parseInt(args[0]);
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Movimiento de Polígonos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            MovimientoPoligonos movimientoPoligonos = new MovimientoPoligonos(cantidadPoligonos);
            frame.add(movimientoPoligonos);
            frame.setVisible(true);

            // Generar los polígonos
            for (int i = 0; i < cantidadPoligonos; i++) {
                Polygon poligono = generarPoligono(frame.getHeight());
                movimientoPoligonos.poligonos[i] = poligono;
            }
        });
    }

    private static Polygon generarPoligono(int alturaPantalla) {
        Random random = new Random();
        int lados = random.nextInt(5) + 3; // Entre 3 y 7 lados
        double radioMaximo = alturaPantalla / 8.0;
        double radio = random.nextDouble() * radioMaximo;
        int[] xPoints = new int[lados];
        int[] yPoints = new int[lados];
        double anguloInicial = Math.toRadians(random.nextInt(360));
        double anguloPorPunto = 2 * Math.PI / lados;

        for (int i = 0; i < lados; i++) {
            double angulo = anguloInicial + i * anguloPorPunto;
            xPoints[i] = (int) (radio * Math.cos(angulo));
            yPoints[i] = (int) (radio * Math.sin(angulo));
        }

        return new Polygon(xPoints, yPoints, lados);
    }
}

