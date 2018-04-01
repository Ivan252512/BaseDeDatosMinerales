age mx.unam.ciencias.icc;

/**
 * <p>Clase para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas son iterables utilizando sus nodos.</p>
 */
public class Lista {

    /**
     * Clase para nodos de uso interno a la clase Lista.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private Object elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /**
         * Construye un nodo con un elemento.
         * @param elemento el elemento del nodo.
         */
        public Nodo(Object elemento) {
            // Aquí va su código.
            this.elemento=elemento;
            this.anterior=null;
            this.siguiente=null;
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            // Aquí va su código.
            return this.anterior;
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            // Aquí va su código.
            return this.siguiente;
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public Object get() {
            // Aquí va su código.
            return this.elemento;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        // Aquí va su código.
        return this.longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        // Aquí va su código.
        return cabeza==null;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     */
    public void agregaFinal(Object elemento) {
        Nodo n=new Nodo(elemento);
        if (!esVacia()){
          rabo.siguiente=n;
          rabo.siguiente.anterior=rabo;
          rabo=n;
          longitud++;
        }else{
          cabeza=n;
          rabo=n;
          longitud++;
        }
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     */
    public void agregaInicio(Object elemento) {
        // Aquí va su código.
        Nodo n=new Nodo(elemento);
        if (!esVacia()){
          cabeza.anterior=n;
          cabeza.anterior.siguiente=cabeza;
          cabeza=n;
          longitud++;
        }else{
          cabeza=n;
          rabo=n;
          longitud++;
        }
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     */
    public void inserta(int i, Object elemento) {
        // Aquí va su código.
        Nodo n= new Nodo(elemento);
        if (i<=0){
            agregaInicio(elemento);
        }else if (i>=getLongitud()){
            agregaFinal(elemento);
        }else if(getLongitud()==0){
            cabeza=n;
            rabo=n;
            longitud++;
        }else{
          int cont=0;
          Nodo aux=cabeza;
          while(cont<i){
            aux=aux.siguiente;
            cont++;
          }
          aux.anterior.siguiente=n;
          aux.anterior=n;
          n.siguiente=aux;
          n.anterior=aux.anterior.anterior;
          longitud++;
        }

    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(Object elemento) {
        // Aquí va su código.
        if(!esVacia() && contiene(elemento)){
          if(cabeza.siguiente==null){
            cabeza=null;
            rabo=null;
            longitud--;
          }else{
            Nodo n=cabeza;
            while(n.siguiente!=null && !n.get().equals(elemento)){
              n=n.siguiente;
            }
            if(n.anterior==null){
              eliminaPrimero();
            }else if (n.siguiente==null){
              eliminaUltimo();
            }else{
              n.siguiente.anterior=n.anterior;
              n.anterior.siguiente=n.siguiente;
              n.siguiente=null;
              n.anterior=null;
              longitud--;
            }
          }
        }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista está vacía.
     */
    public Object eliminaPrimero() {
        // Aquí va su código.
        if(!esVacia()){
          Nodo n=cabeza;
          if(cabeza.siguiente==null){
            cabeza=null;
            rabo=null;
          }else{
            cabeza=cabeza.siguiente;
            cabeza.anterior.siguiente=null;
            cabeza.anterior=null;
          }
          longitud--;
          return n.get();
        }
        return null;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista está vacía.
     */
    public Object eliminaUltimo() {
        // Aquí va su código.
        if(!esVacia()){
          Nodo n=rabo;
          if(rabo.anterior==null){
            cabeza=null;
            rabo=null;
          }else{
            rabo=rabo.anterior;
            rabo.siguiente.anterior=null;
            rabo.siguiente=null;
          }
          longitud--;
          return n.get();
        }
        return null;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(Object elemento) {
        // Aquí va su código.
        Nodo aux=cabeza;
        for (int i=1;i<=getLongitud();i++){
          if(aux.get().equals(elemento)){
            return true;
          }
          aux=aux.siguiente;
        }
        return false;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista reversa() {
        // Aquí va su código.
        Lista rev=new Lista();
        Nodo n=rabo;
        for(int i=1;i<=getLongitud();i++){
          rev.agregaFinal(n.get());
          n=n.anterior;
        }
        return rev;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista copia() {
        // Aquí va su código.
        Lista l=new Lista();
        Nodo n=cabeza;
        for(int i=1;i<=getLongitud();i++){
          l.agregaFinal(n.get());
          n=n.siguiente;
        }
        return l;
    }

    /**
     * Limpia la lista de elementos. El llamar este método es equivalente a
     * eliminar todos los elementos de la lista.
     */
    public void limpia() {
        // Aquí va su código.
        Nodo n=cabeza;
        while(getLongitud()!=0){
          eliminaPrimero();
        }
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Object getPrimero() {
        // Aquí va su código.
        if(!esVacia()){
          return cabeza.get();
        }
        return null;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Object getUltimo() {
        // Aquí va su código.
        if(!esVacia()){
          return rabo.get();
        }
        return null;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista. Si el índice es menor
     * que cero o mayor o igual que el número de elementos de la lista, el
     * método regresa <tt>null</tt>.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, si <em>i</em> es mayor
     *         o igual que cero y menor que el número de elementos en la lista;
     *         <tt>null</tt> en otro caso.
     */
    public Object get(int i) {
        // Aquí va su código.
        if(0<=i && i<getLongitud()){
          Nodo n=cabeza;
          int cont=0;
          while(n.siguiente!=null && cont<i){
            n=n.siguiente;
            cont++;
          }
          return n.get();
        }
        return null;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(Object elemento) {
        // Aquí va su código.
        if(contiene(elemento)){
          int contador=0;
          Nodo aux=cabeza;
          while(!aux.get().equals(elemento)){
            aux=aux.siguiente;
            contador++;
          }
          return contador;
        }
        return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        // Aquí va su código.
        if (getLongitud()==0){
          return "[]";
        }
        String s = "[";
        for (int i = 0; i < getLongitud()-1; i++)
            s += String.format("%s, ", (String)get(i));
        s += String.format("%s]", (String)get(getLongitud()-1));
        return s;
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param o el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la lista es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
        if (!(o instanceof Lista))
            return false;
        Lista lista = (Lista)o;
        // Aquí va su código.
        if(getLongitud()!=lista.getLongitud())
            return false;
        Nodo aux1=this.cabeza;
        Nodo aux2=lista.cabeza;
        for(int i=0;i<getLongitud();i++){
            if(!aux1.get().equals(aux2.get()))
                return false;
            aux1=aux1.siguiente;
            aux2=aux2.siguiente;
        }
        return true;
    }

    /**
     * Regresa el nodo cabeza de la lista.
     * @return el nodo cabeza de la lista.
     */
    public Nodo getCabeza() {
        // Aquí va su código.
        return this.cabeza;
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo() {
        // Aquí va su código.
        return this.rabo;
    }
}
