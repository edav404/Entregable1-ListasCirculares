//Clase ListaCircular: Implementa la estructura de datos de una lista enlazada circular
class ListaCircular {
    private Nodo cabeza = null; //Primer nodo de la lista
    private Nodo cola = null; //Último nodo

    //Método para insertar un nodo en la lista circular
    public void insertar(int x, int y) {
        Nodo nuevo = new Nodo(x, y); //Crea un nuevo nodo con las coordenadas dadas
        if (cabeza == null) { //Si la lista está vacía
            cabeza = nuevo;
            cola = nuevo;
            cabeza.siguiente = cabeza; //Apunta a sí mismo (hace que la lista sea circulae)
        } else {
            cola.siguiente = nuevo; //Conecta el último nodo con el nuevo
            nuevo.siguiente = cabeza; //El nuevo nodo apunta al primero
            cola = nuevo; //Actualiza la cola
        }
    }

    //Método para obtener la cabeza de la lista
    public Nodo getCabeza() {
        return cabeza;
    }
}