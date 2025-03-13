//Clase App: Se encarga de la interfaz gráfica y la animación de los puntos en las listas circulares
import javax.swing.*;
import java.awt.*;

public class App extends JPanel {

    private ListaCircular lista1, lista2; //Dos listas circulares
    private Nodo actual1, actual2; //Nodos actuales de cada lista
    private Timer timer; //Timer para animación

    //Constructor de la clase App, inicializa las listas y el temporizador
    public App() {
        lista1 = new ListaCircular(); //Primera lista circular
        lista2 = new ListaCircular(); //Segunda lista circular

        //Se generan dos trayectorias circulares con sus respectivos parámetros (centroX, centroY, radio, puntos)
        generarTrayectoriaCircular(lista1, 200, 200, 100, 30);
        generarTrayectoriaCircular(lista2, 400, 200, 100, 30);

        actual1 = lista1.getCabeza(); //Nodo actual de la primera lista
        actual2 = lista2.getCabeza(); //Nodo actual de la segunda lista

        //Configuración del temporizador que mueve los puntos en los círculos
        timer = new Timer(100, e -> {
            actual1 = actual1.siguiente; //Mueve el primer círculo en sentido horario
            actual2 = obtenerAnterior(actual2, lista2); //Mueve el segundo círculo en sentido antihorario
            repaint(); //Redibuja la pantalla
        });
        timer.start(); //Inicia la animación
    }

    //Método para generar una trayectoria circular de puntos en una lista circular
    public void generarTrayectoriaCircular(ListaCircular lista, int centroX, int centroY, int radio, int puntos) {
        double anguloIncremento = 2 * Math.PI / puntos; //Incremento del ángulo entre puntos
        for (int i = 0; i < puntos; i++) {
            int x = (int) (centroX + radio * Math.cos(i * anguloIncremento)); //Calcula la coordenada X
            int y = (int) (centroY + radio * Math.sin(i * anguloIncremento)); //Calcula la coordenada Y
            lista.insertar(x, y); //Inserta el punto en la lista
        }
    }

    //Método para obtener el nodo anterior a un nodo dado en una lista circular
    private Nodo obtenerAnterior(Nodo nodo, ListaCircular lista) {
        Nodo temp = lista.getCabeza();
        while (temp.siguiente != nodo) { //Recorre la lista hasta encontrar el nodo anterior
            temp = temp.siguiente;
        }
        return temp;
    }

    //Método que se ejecuta automáticamente para dibujar los elementos en pantalla
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarLista(g, lista1, Color.BLACK, Color.BLUE, actual1); //Dibuja la primera lista
        dibujarLista(g, lista2, Color.BLACK, Color.RED, actual2); //Dibuja la segunda lista
    }

    //Método para dibujar una lista circular en pantalla
    private void dibujarLista(Graphics g, ListaCircular lista, Color colorPuntos, Color colorActual, Nodo actual) {
        Nodo temp = lista.getCabeza();
        g.setColor(colorPuntos);
        do {
            g.fillOval(temp.x, temp.y, 5, 5); //Dibuja cada punto de la lista
            temp = temp.siguiente;
        } while (temp != lista.getCabeza()); //Se asegura de recorrer la lista completa

        g.setColor(colorActual);
        g.fillOval(actual.x, actual.y, 10, 10); //Dibuja el nodo actual con un tamaño mayor
    }

    //Método principal para ejecutar el programa
    public static void main(String[] args) {
        JFrame frame = new JFrame("Trayectoria Circular"); //Crea la ventana
        App panel = new App(); //Crea una instancia de la clase App
        frame.add(panel);
        frame.setSize(600, 400); //Tamaño de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la aplicación al salir
        frame.setVisible(true); //Muestra la ventana
    }
}