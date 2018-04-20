package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase para representar minerales. Un mineral tiene nombre, dureza en la
 * escala de Mohs, punto de fusión, densidad y estructura cristalina. La clase
 * implementa {@link Registro}, por lo que puede cargarse y guardarse utilizando
 * objetos de las clases {@link BufferedReader} y {@link BufferedWriter} como
 * entrada y salida respectivamente.
 */
public class Mineral implements Registro {

    /* Nombre del mineral. */
    private String nombre;
    /* Dureza en la escala de Mohs (Valores del 1 al 10). */
    private int dureza;
    /* Densidad del mineral. */
    private double densidad;
    /* Punto de fusión del mineral*/
    private double puntoDeFusion;
    /* Estructura cristalina */
    private String estructuraCristalina;

    /**
     * Construye un mineral con todas sus propiedades.
     * @param nombre el nombre del mineral.
     * @param dureza la dureza del mineral.
     * @param densidad la densidad del mineral.
     * @param puntoDeFusion el punto de fución del mineral.
     * @param estructuraCristalina la estructura cristalina del mineral.
     */
    public Mineral(String nombre,
                   int    dureza,
                   double densidad,
                   double puntoDeFusion
                   String estructuraCristalina) {
        // Aquí va su código.
        this.nombre=nombre;
        this.dureza=dureza;
        this.densidad=densidad;
        this.puntoDeFusion=puntoDeFusion;
        this.edad=edad;
    }

    /**
     * Regresa el nombre del mineral.
     * @return el nombre del mineral.
     */
    public String getNombre() {
        // Aquí va su código.
        return this.nombre;
    }

    /**
     * Define el nombre del mineral.
     * @param nombre el nuevo nombre del mineral.
     */
    public void setNombre(String nombre) {
        // Aquí va su código.
        this.nombre=nombre;
    }

    /**
     * Regresa la estructura cristalina del mineral.
     * @return la estructura cristalina del mineral.
     */
    public String getEstructuraCristalina() {
        // Aquí va su código.
        return this.estructuraCristalina;
    }

    /**
     * Define la estructura cristalina del mineral.
     * @param estructuraCristalina la estructura cristalina del mineral.
     */
    public void setEstructuraCristalina(String estructuraCristalina) {
        // Aquí va su código.
        this.estructuraCristalina=estructuraCristalina;
    }

    /**
     * Regresa la dureza del mineral.
     * @return la dureza del mineral.
     */
    public int getDureza() {
        // Aquí va su código.
        return this.dureza;
    }

    /**
     * Define la dureza del mineral.
     * @param dureza la dureza del mineral.
     */
    public void setDureza(int dureza) {
        // Aquí va su código.
        this.dureza=dureza;
    }

    /**
     * Regresa el punto de fusión del mineral.
     * @return el punto de fusión del mineral.
     */
    public double getPuntoDeFusion() {
        // Aquí va su código.
        return this.puntoDeFusion;
    }

    /**
     * Define el punto de fusión del mineral.
     * @param puntoDeFusion el nuevo punto de fusión del mineral.
     */
    public void setPuntoDeFusion(double puntoDeFusion) {
        // Aquí va su código.
        this.puntoDeFusion=puntoDeFusion;
    }

    /**
     * Regresa la densidad del mineral.
     * @return la densidad del mineral.
     */
    public int getDensidad() {
        // Aquí va su código.
        return this.densidad;
    }

    /**
     * Define la edad del estudiante.
     * @param edad la nueva edad del estudiante.
     */
    public void setDensidad(double densidad) {
        // Aquí va su código.
        this.densidad=densidad;
    }

    /**
     * Nos dice si el objeto recibido es un estudiante igual al que manda llamar
     * el método.
     * @param o el objeto con el que el estudiante se comparará.
     * @return <tt>true</tt> si el objeto o es un estudiante con las mismas
     *         propiedades que el objeto que manda llamar al método,
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
        if (!(o instanceof Estudiante))
            return false;
        Estudiante e = (Estudiante)o;
        // Aquí va su código.
        if(e.nombre.length()==this.nombre.length()){
            for(int i=0;i<nombre.length();i++){
              if(e.nombre.charAt(i)!=this.nombre.charAt(i))
                  return false;
            }
        }
        if(e.nombre.length()!=this.nombre.length())
            return false;
        return (this.cuenta==e.cuenta && this.promedio==e.promedio
                && this.edad==e.edad);
    }

    /**
     * Regresa una representación en cadena del estudiante.
     * @return una representación en cadena del estudiante.
     */
    @Override public String toString() {
        // Aquí va su código.
        String cadena = String.format("Nombre   : %s\n" +
                                      "Cuenta   : %d\n" +
                                      "Promedio : %2.2f\n" +
                                      "Edad     : %d",
                                      nombre, cuenta, promedio, edad);
        return cadena;
    }

    /**
     * Guarda al estudiante en la salida recibida.
     * @param out la salida dónde hay que guardar al estudiante.
     * @throws IOException si un error de entrada/salida ocurre.
     */
    @Override public void guarda(BufferedWriter out) throws IOException {
        // Aquí va su código.
        out.write(String.format("%s\t%d\t%2.2f\t%d\n",
                                nombre,
                                cuenta,
                                promedio,
                                edad));
    }

    /**
     * Carga al estudiante de la entrada recibida.
     * @param in la entrada de dónde hay que cargar al estudiante.
     * @return <tt>true</tt> si el método carga un estudiante válido,
     *         <tt>false</tt> en otro caso.
     * @throws IOException si un error de entrada/salida ocurre, o si la entrada
     *         recibida no contiene a un estudiante.
     */
    @Override public boolean carga(BufferedReader in) throws IOException {
        // Aquí va su código.
        String linea=in.readLine();
        if(linea==null)
          return false;
        linea=linea.trim();
        if(linea.equals(""))
          return false;
        String [] p = linea.split("\t");
        if(p.length!=4)
          throw new IOException("El archivo es inválido");
        if(p[1].equals("a") || p[2].equals("a") || p[3].equals("a")){
          throw new IOException("El archivo es inválido");
        }
        nombre=p[0];
        cuenta=Integer.parseInt(p[1]);
        promedio=Double.parseDouble(p[2]);
        edad=Integer.parseInt(p[3]);
        return (nombre==getNombre() && cuenta==getCuenta() &&
                promedio==getPromedio() && edad==getEdad());
    }
}
