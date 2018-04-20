package mx.unam.ciencias.icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import mx.unam.ciencias.icc.Estudiante;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link Mineral}.
 */
public class TestEstudiante {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /* Nombres. */
    private static String[] NOMBRES = {
        "Apatito", "Calcita", "Grafito", "Diamante", "Pirita",
        "Cuarzo", "Granate", "Hematita", "Magnetita", "Serpentinita"
    };

    /* Estructuras cristalinas */
    private static String[] ESTRUCTURASCRISTALINAS = {
        "Cúbica", "Tetragonal", "Hexagonal", "Trigonal", "Ortorrómbica",
        "Monoclínica", "Triclínica"
    }

    /* Generador de números aleatorios. */
    private static Random random;

    /**
     * Genera un nombre aleatorio.
     * @return un nombre aleatorio.
     */
    public static String nombreAleatorio() {
        int n = random.nextInt(NOMBRES.length);
        return NOMBRES[n];
    }

    /**
     * Genera un nombre de sistema cristalino aleatorio.
     * @return un sistema cristalino aleaorio.
     */
    public static String estructuraCristalinaAleatoria() {
        int n = random.nextInt(ESTRUCTURASCRISTALINAS.length);
        return ESTRUCTURASCRISTALINAS[n];
    }

    /**
     * Genera una dureza aleatoria entre 1 y 10.
     * @return una dureza aleatoria.
     */
    public static int durezaAleatoria() {
        return 1+random.nextInt(10);
    }

    /**
     * Genera un punto de fusión aleatorio.
     * @return un punto de fusión aleatorio.
     */
    public static double puntoDeFusionAleatorio() {
        /* Estúpida precisión. */
        return 100.0+random.nextDouble() * 1000.0;
    }

    /**
     * Genera una densidad aleatoria.
     * @return una densidad aleatoria.
     */
    public static int densidadAleatoria() {
        return 0.01+random.nextDouble() * 10.0;
    }

    /**
     * Genera un mineral aleatorio.
     * @return un mineral aleatorio.
     */
    public static Mineral mineralAleatorio() {
        return new Mineral(nombreAleatorio(),
                           durezaAleatoria(),
                           densidadAleatorio(),
                           puntoDeFusionAleatorio(),
                           estructuraCristalinaAleatoria());
    }

    /**
     * Genera un mineral aleatorio con una dureza dada.
     * @param dureza la dureza del mineral.
     * @return un mineral aleatorio.
     */
    public static Mineral mineralAleatorio(int dureza) {
        return new Mineral(nombreAleatorio(),
                           dureza,
                           densidadAleatorio(),
                           puntoDeFusionAleatorio(),
                           estructuraCristalinaAleatoria());
    }

    private Mineral mineral;

    /**
     * Prueba unitaria para {@link
     * Mineral#Mineral(String,int,double,double,String)}.
     */
    @Test public void testConstructor() {
        String nombre = nombreAleatorio();
        int dureza = durezaAleatoria();
        double densidad = densidadAleatoria();
        double puntoDeFusion = puntoDeFusionAleatorio();
        String estructuraCristalina = estructuraCristalinaAleatoria();
        mineral = new Mineral(nombre, dureza, densidad, puntoDeFusion
                              estructuraCristalina);
        Assert.assertTrue(mineral.getNombre().equals(nombre));
        Assert.assertTrue(mineral.getDureza() == dureza);
        Assert.assertTrue(mineral.getDensidad() == densidad);
        Assert.assertTrue(mineral.getPuntoDeFusion() == puntoDeFusion);
        Assert.assertTrue(mineral.getEstructuraCristalina() ==
                          estructuraCristalina);
    }

    /**
     * Prueba unitaria para {@link Mineral#setNombre} y {@link
     * Mineral#getNombre}.
     */
    @Test public void testSetGetNombre() {
        String nombre = nombreAleatorio();
        int dureza = durezaAleatoria();
        double densidad = densidadAleatoria();
        double puntoDeFusion = puntoDeFusionAleatorio();
        String estructuraCristalina = estructuraCristalinaAleatoria();
        mineral = new Mineral(nombre, dureza, densidad, puntoDeFusion
                              estructuraCristalina);
        Assert.assertTrue(mineral.getNombre().equals(nombre));
        String nuevoNombre = nombre + " Repollo ";
        mineral.setNombre(nuevoNombre);
        Assert.assertTrue(mineral.getNombre().equals(nuevoNombre));
        Assert.assertFalse(mineral.getNombre().equals(nombre));
    }

    /**
     * Prueba unitaria para {@link Mineral#setDureza} y {@link
     * Estudiante#getDureza}.
     */
    @Test public void testSetGetDureza() {
        String nombre = nombreAleatorio();
        int dureza = durezaAleatoria();
        double densidad = densidadAleatoria();
        double puntoDeFusion = puntoDeFusionAleatorio();
        String estructuraCristalina = estructuraCristalinaAleatoria();
        mineral = new Mineral(nombre, dureza, densidad, puntoDeFusion
                              estructuraCristalina);
        Assert.assertTrue(mineral.getDureza() == dureza);
        int nuevaDureza;
        if (dureza<10)
          nuevaDureza = dureza + 1;
        if (dureza==10)
          nuevaDureza = 9;
        mineral.setDureza(nuevaDureza);
        Assert.assertTrue(mineral.getDureza() == nuevaDureza);
        Assert.assertFalse(mineral.getDureza() == dureza);
    }

    /**
     * Prueba unitaria para {@link Mineral#setDensidad} y {@link
     * Mineral#getDensidad}.
     */
    @Test public void testSetGetDensidad() {
        String nombre = nombreAleatorio();
        int dureza = durezaAleatoria();
        double densidad = densidadAleatoria();
        double puntoDeFusion = puntoDeFusionAleatorio();
        String estructuraCristalina = estructuraCristalinaAleatoria();
        mineral = new Mineral(nombre, dureza, densidad, puntoDeFusion
                              estructuraCristalina);
        Assert.assertTrue(mineral.getDensidad() == densidad);
        int nuevaDensidad=densidad+1.0;
        mineral.setDensidad(nuevaDensidad);
        Assert.assertTrue(mineral.getDensidad() == nuevaDensidad);
        Assert.assertFalse(mineral.getDensidad() == densidad);
    }

    /**
     * Prueba unitaria para {@link Mineral#setPuntoDeFusion} y {@link
     * Mineral#getPuntoDeFusion}.
     */
    @Test public void testSetGetPuntoDeFusion() {
        String nombre = nombreAleatorio();
        int dureza = durezaAleatoria();
        double densidad = densidadAleatoria();
        double puntoDeFusion = puntoDeFusionAleatorio();
        String estructuraCristalina = estructuraCristalinaAleatoria();
        mineral = new Mineral(nombre, dureza, densidad, puntoDeFusion
                              estructuraCristalina);
        Assert.assertTrue(mineral.getPuntoDeFusion() == puntoDeFusion);
        int nuevoPuntoDeFusion=puntoDeFusion+1.0;
        mineral.setPuntoDeFusion(nuevoPuntoDeFusion);
        Assert.assertTrue(mineral.getPuntoDeFusion() == nuevoPuntoDeFusion);
        Assert.assertFalse(mineral.getPuntoDeFusion() == puntoDeFusion);
    }

    /**
     * Prueba unitaria para {@link Mineral#setEstructuraCristalina} y {@link
     * Mineral#getEstructuraCristalina}.
     */
    @Test public void testSetGetEstructuraCristalina() {
        String nombre = nombreAleatorio();
        int dureza = durezaAleatoria();
        double densidad = densidadAleatoria();
        double puntoDeFusion = puntoDeFusionAleatorio();
        String estructuraCristalina = estructuraCristalinaAleatoria();
        mineral = new Mineral(nombre, dureza, densidad, puntoDeFusion
                              estructuraCristalina);
        Assert.assertTrue(mineral.getEstructuraCristalina().equals(
                          estructuraCristalina));
        String nuevaEstructuraCristalina = estructuraCristalina + " Repollo ";
        mineral.setEstructuraCristalina(nuevaEstructuraCristalina);
        Assert.assertTrue(mineral.getEstructuraCristalina().equals(
                          nuevaEstructuraCristalina));
        Assert.assertFalse(mineral.getEstructuraCristalina().equals(
                          estructuraCristalina));
    }

    /**
     * Prueba unitaria para {@link Mineral#equals}.
     */
    @Test public void testEquals() {
        String nombre = nombreAleatorio();
        int dureza = durezaAleatoria();
        double densidad = densidadAleatoria();
        double puntoDeFusion = puntoDeFusionAleatorio();
        String estructuraCristalina = estructuraCristalinaAleatoria();
        mineral = new Mineral(nombre, dureza, densidad, puntoDeFusion
                              estructuraCristalina);
        Mineral igual = new Mineral(new String(nombre),
                                          dureza, densidad,
                                          puntoDeFusion,
                                          new String(estructuraCristalina));
        Assert.assertTrue(mineral.equals(igual));
        String otroNombre = nombre + " Segundo";
        int otraDureza = dureza + 1;
        double otraDensidad = densidad +1;
        double otroPuntoDeFusion = puntoDeFusion + 1;
        String otraEstructuraCristalina= estructuraCristalina + "Segundo"
        Mineral distinto =
            new Mineral(otroNombre, dureza, densidad, puntoDeFusion,
                        estructuraCristalina);
        Assert.assertFalse(mineral.equals(distinto));
        distinto = new Mineral(nombre, otraDureza, densidad, puntoDeFusion,
                               estructuraCristalina);
        Assert.assertFalse(mineral.equals(distinto));
        distinto = new Mineral(nombre, dureza, otraDensidad, puntoDeFusion,
                               estructuraCristalina);
        Assert.assertFalse(mineral.equals(distinto));
        distinto = new Mineral(nombre, dureza, densidad, otroPuntoDeFusion,
                               estructuraCristalina);
        Assert.assertFalse(mineral.equals(distinto));
        distinto = new Mineral(nombre, dureza, densidad, puntoDeFusion,
                               otraEstructuraCristalina);
        Assert.assertFalse(mineral.equals(distinto));
        Assert.assertFalse(mineral.equals("Una cadena"));
        Assert.assertFalse(mineral.equals(null));
    }

    /**
     * Prueba unitaria para {@link Mineral#toString}.
     */
    @Test public void testToString() {
        String nombre = nombreAleatorio();
        int dureza = durezaAleatoria();
        double densidad = densidadAleatoria();
        double puntoDeFusion = puntoDeFusionAleatorio();
        String estructuraCristalina = estructuraCristalinaAleatoria();
        mineral = new Mineral(nombre, dureza, densidad, puntoDeFusion
                              estructuraCristalina);
        String cadena = String.format("Nombre                : %s\n" +
                                      "Dureza                : %d\n" +
                                      "Densidad              : %2.2f\n" +
                                      "Punto de fusión       : %2.2f\n" +
                                      "Estructura cristalina : %s",
                                      nombre, dureza, densidad,
                                      puntoDeFusion, estructuraCristalina);
        Assert.assertTrue(mineral.toString().equals(cadena));
    }

    /**
     * Prueba unitaria para {@link Estudiante#guarda}.
     */
    @Test public void testGuarda() {
        String nombre = nombreAleatorio();
        int dureza = durezaAleatoria();
        double densidad = densidadAleatoria();
        double puntoDeFusion = puntoDeFusionAleatorio();
        String estructuraCristalina = estructuraCristalinaAleatoria();
        mineral = new Mineral(nombre, dureza, densidad, puntoDeFusion
                              estructuraCristalina);

        try {
            StringWriter swOut = new StringWriter();
            BufferedWriter out = new BufferedWriter(swOut);
            mineral.guarda(out);
            out.close();
            String guardado = swOut.toString();
            String s = String.format("%s\t%d\t%2.2f\t%2.2f\t%s\n",
                                     nombre, dureza, densidad, puntoDeFusion
                                     estructuraCristalina);
                Assert.assertTrue(guardado.equals(s));
        } catch (IOException ioe) {
            Assert.fail();
        }
    }

    /**
     * Prueba unitaria para {@link Estudiante#carga}.
     */
    @Test public void testCarga() {
        mineral = new Mineral(null, 0, 0.0, 0.0,null);

        String nombre = nombreAleatorio();
        int dureza = durezaAleatoria();
        double densidad = densidadAleatoria();
        double puntoDeFusion = puntoDeFusionAleatorio();
        String estructuraCristalina = estructuraCristalinaAleatoria();

        String entrada = String.format("%s\t%d\t%2.2f\t%2.2f\t%s\n",
                                       nombre, dureza, densidad, puntoDeFusion
                                       estructuraCristalina);

        try {
            StringReader srIn = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srIn);
            Assert.assertTrue(mineral.carga(in));
            in.close();
            Assert.assertTrue(mineral.getNombre().equals(nombre));
            Assert.assertTrue(mineral.getDureza() == dureza);
            Assert.assertTrue(mineral.getDensidad() == densidad);
            Assert.assertTrue(mineral.getPuntoDeFusion() == puntoDeFusion);
            Assert.assertTrue(mineral.getEstructuraCristalina.equals(
                              estructuraCristalina);
        } catch (IOException ioe) {
            Assert.fail();
        }

        entrada = "";
        try {
            StringReader srIn = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srIn);
            Assert.assertFalse(mineral.carga(in));
            in.close();
            Assert.assertTrue(mineral.getNombre().equals(nombre));
            Assert.assertTrue(mineral.getDureza() == dureza);
            Assert.assertTrue(mineral.getDensidad() == densidad);
            Assert.assertTrue(mineral.getPuntoDeFusion() == puntoDeFusion);
            Assert.assertTrue(mineral.getEstructuraCristalina.equals(
                              estructuraCristalina);
        } catch (IOException ioe) {
            Assert.fail();
        }

        entrada = "a\ta\ta\ta\ta";
        try {
            StringReader srIn = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srIn);
            mineral.carga(in);
            Assert.fail();
        } catch (IOException ioe) {}

        entrada = "a\ta";
        try {
            StringReader srIn = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srIn);
            mineral.carga(in);
            Assert.fail();
        } catch (IOException ioe) {}
    }

    /* Inicializa el generador de números aleatorios. */
    static {
        random = new Random();
    }
}
