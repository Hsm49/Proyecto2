// Proyecto 2
// Vázquez Martínez Josué Abraham 7CM1

import java.util.Random;

public class PoligonoReg {
    private Coordenada[] vertices;
    private double radio;

    // Constructor que recibe el número de vértices y el radio
    public PoligonoReg(int numVertices, double radio) {
        this.vertices = new Coordenada[numVertices];
        this.radio = radio;
        Random rand = new Random();
        for (int i = 0; i < numVertices; i++) {
            double angle = Math.toRadians((360 / numVertices) * i);
            double x = radio * Math.cos(angle);
            double y = radio * Math.sin(angle);
            vertices[i] = new Coordenada(x, y);
        }
    }

    // Método para modificar el vertice n
    public void modificaVertice(int indice, Coordenada nuevaCoordenada) {
        if (indice < 0 || indice >= vertices.length) {
            throw new IllegalArgumentException("El índice del vértice está fuera de rango.");
        }
        vertices[indice] = nuevaCoordenada;
    }

    // Método para imprimir el conjunto de vértices del polígono
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Poligono Regular:\n");
        for (int i = 0; i < vertices.length; i++) {
            sb.append("Vértice ").append(i + 1).append(": [")
                .append(String.format("%.1f", vertices[i].abcisa())).append(", ")
                .append(String.format("%.1f", vertices[i].ordenada())).append("]\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java PoligonoReg <número de vértices> <radio>");
            return;
        }

        int numVertices = Integer.parseInt(args[0]);
        double radio = Double.parseDouble(args[1]);

        // Número de polígonos a generar (por defecto 1)
        int n = 1;
        if (args.length > 2) {
            n = Integer.parseInt(args[2]);
        }

        // Generar n polígonos
        for (int i = 0; i < n; i++) {
            PoligonoReg poligono = new PoligonoReg(numVertices, radio);
            System.out.println(poligono);
        }
    }
}
