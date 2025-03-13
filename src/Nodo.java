//Clase Nodo: Representa cada punto de la lista circular
class Nodo {
    int x, y; //Coordenadas del nodo
    Nodo siguiente; //Referencia al siguiente nodo en la lista

    //Constructor de la clase Nodo
    public Nodo(int x, int y) {
        this.x = x;
        this.y = y;
        this.siguiente = null; //Inicialmente no tiene siguiente
    }
}
