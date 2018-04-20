package mx.unam.ciencias.icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.BaseDeDatosEstudiantes;
import mx.unam.ciencias.icc.CampoEstudiante;
import mx.unam.ciencias.icc.Estudiante;
import mx.unam.ciencias.icc.Lista;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link BaseDeDatosMinerales}.
 */
public class TestBaseDeDatosMinerales {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /* Generador de números aleatorios. */
    private Random random;
    /* Base de datos de minerales. */
    private BaseDeDatosMinerales bdd;
    /* Número total de minerales. */
    private int total;

    /* Enumeración espuria. */
    private enum X {
        /* Campo espurio. */
        A;
    }

    /**
     * Crea un generador de números aleatorios para cada prueba y una base de
     * datos de minerales.
     */
    public TestBaseDeDatosMinerales() {
        random = new Random();
        bdd = new BaseDeDatosMinerales();
        total = 1 + random.nextInt(100);
    }

    /**
     * Prueba unitaria para {@link
     * BaseDeDatosMinerales#BaseDeDatosMinerales}.
     */
    @Test public void testConstructor() {
        Lista minerales = bdd.getRegistros();
        Assert.assertFalse(estudiantes == null);
        Assert.assertTrue(minerales.getLongitud() == 0);
        Assert.assertTrue(bdd.getNumRegistros() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getNumRegistros}.
     */
    @Test public void testGetNumRegistros() {
        Assert.assertTrue(bdd.getNumRegistros() == 0);
        for (int i = 0; i < total; i++) {
            Mineral m = TestMineral.mineralAleatorio();
            bdd.agregaRegistro(m);
            Assert.assertTrue(bdd.getNumRegistros() == i+1);
        }
        Assert.assertTrue(bdd.getNumRegistros() == total);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getRegistros}.
     */
    @Test public void testGetRegistros() {
        Lista l = bdd.getRegistros();
        Lista r = bdd.getRegistros();
        Assert.assertTrue(l.equals(r));
        Assert.assertFalse(l == r);
        Mineral[] minerales = new Mineral[total];
        for (int i = 0; i < total; i++) {
            minerales[i] = TestMineral.mineralAleatorio();
            bdd.agregaRegistro(minerales[i]);
        }
        l = bdd.getRegistros();
        int c = 0;
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Assert.assertTrue(minerales[c++].equals(nodo.get()));
            nodo = nodo.getSiguiente();
        }
        l.elimina(minerales[0]);
        Assert.assertFalse(l.equals(bdd.getRegistros()));
        Assert.assertFalse(l.getLongitud() == bdd.getNumRegistros());
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#agregaRegistro}.
     */
    @Test public void testAgregaRegistro() {
        for (int i = 0; i < total; i++) {
            Mineral m = TestMineral.mineralAleatorio();
            Assert.assertFalse(bdd.getRegistros().contiene(m));
            bdd.agregaRegistro(m);
            Assert.assertTrue(bdd.getRegistros().contiene(m));
            Lista l = bdd.getRegistros();
            Assert.assertTrue(l.get(l.getLongitud() - 1).equals(m));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#eliminaRegistro}.
     */
    @Test public void testEliminaRegistro() {
        int ini = random.nextInt(1000000);
        for (int i = 0; i < total; i++) {
            Mineral m = TestMineral.mineralAleatorio(ini + i);
            bdd.agregaRegistro(m);
        }
        while (bdd.getNumRegistros() > 0) {
            int i = random.nextInt(bdd.getNumRegistros());
            Mineral m = (Mineral)bdd.getRegistros().get(i);
            Assert.assertTrue(bdd.getRegistros().contiene(m));
            bdd.eliminaRegistro(m);
            Assert.assertFalse(bdd.getRegistros().contiene(m));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#guarda}.
     */
    @Test public void testGuarda() {
        for (int i = 0; i < total; i++) {
            Mineral m = TestMineral.mineralAleatorio();
            bdd.agregaRegistro(m);
        }
        String guardado = "";
        try {
            StringWriter swOut = new StringWriter();
            BufferedWriter out = new BufferedWriter(swOut);
            bdd.guarda(out);
            out.close();
            guardado = swOut.toString();
        } catch (IOException ioe) {
            Assert.fail();
        }
        String[] lineas = guardado.split("\n");
        Assert.assertTrue(lineas.length == total);
        Lista l = bdd.getRegistros();
        int c = 0;
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Mineral m = (Mineral)nodo.get();
            String el = String.format("%s\t%d\t%2.2f\t%2.2f\t%s",
                                      m.getNombre(),
                                      m.getDureza(),
                                      m.getDensidad(),
                                      m.getPuntoDeFusion(),
                                      m.getEstructuraCristalina();
            Assert.assertTrue(lineas[c++].equals(el));
            nodo = nodo.getSiguiente();
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#carga}.
     */
    @Test public void testCarga() {
        int ini = random.nextInt(1000000);
        String entrada = "";
        Mineral[] minerales = new Mineral[total];
        for (int i = 0; i < total; i++) {
            minerales[i] = TestMineral.mineralAleatorio(ini + i);
            entrada += String.format("%s\t%d\t%2.2f\t%2.2f\t%s\n",
                                      m.getNombre(),
                                      m.getDureza(),
                                      m.getDensidad(),
                                      m.getPuntoDeFusion(),
                                      m.getEstructuraCristalina();
            bdd.agregaRegistro(minerales[i]);
        }
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Lista l = bdd.getRegistros();
        Assert.assertTrue(l.getLongitud() == total);
        int c = 0;
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Assert.assertTrue(minerales[c++].equals(nodo.get()));
            nodo = nodo.getSiguiente();
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatosMinerales#creaRegistro}.
     */
    @Test public void testCreaRegistro() {
        Mineral m = (Mineral)bdd.creaRegistro();
        Assert.assertTrue(m.getNombre() == null);
        Assert.assertTrue(m.getDureza() == 0);
        Assert.assertTrue(m.getDensidad() == 0.0);
        Assert.assertTrue(m.getPuntoDeFusion() == 0.0);
        Assert.assertTrue(m.getEstructuraCristalina() == null);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatosMinerales#buscaRegistros}.
     */
    @Test public void testBuscaRegistros() {
        Mineral[] minerales = new Mineral[total];
        int ini = 1000000 + random.nextInt(999999);
        for (int i = 0; i < total; i++) {
            Mineral m =  new Mineral(String.valueOf(ini+i),ini+i, (i * 10.0),
                                     (i * 10.0),String.valueOf(ini+i));
            minerales[i] = m;
            bdd.agregaRegistro(m);
        }

        Mineral mineral;
        String busqueda;
        Lista l;
        Lista.Nodo nodo;
        int i, n;

        for (int k = 0; k < total/10 + 3; k++) {
            i = random.nextInt(total);
            mineral = minerales[i];

            String nombre = mineral.getNombre();
            l = bdd.buscaRegistros(CampoMineral.NOMBRE, nombre);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Mineral m = (Mineral)nodo.get();
                Assert.assertTrue(m.getNombre().indexOf(nombre) > -1);
                nodo = nodo.getSiguiente();
            }
            n = nombre.length();
            busqueda = nombre.substring(random.nextInt(2),
                                        2 + random.nextInt(n-2));
            l = bdd.buscaRegistros(CampoMineral.NOMBRE, busqueda);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(mineral));
            nodo = l.getCabeza();
            while (nodo != null) {
                Mineral m = (Mineral)nodo.get();
                Assert.assertTrue(m.getNombre().indexOf(busqueda) > -1);
                nodo = nodo.getSiguiente();
            }

            String cuenta = String.valueOf(estudiante.getCuenta());
            l = bdd.buscaRegistros(CampoEstudiante.CUENTA, cuenta);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Estudiante e = (Estudiante)nodo.get();
                String s = String.valueOf(e.getCuenta());
                Assert.assertTrue(s.indexOf(cuenta) > -1);
                nodo = nodo.getSiguiente();
            }
            n = cuenta.length();
            busqueda = cuenta.substring(random.nextInt(2),
                                        2 + random.nextInt(n-2));
            l = bdd.buscaRegistros(CampoEstudiante.CUENTA, busqueda);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Estudiante e = (Estudiante)nodo.get();
                String s = String.valueOf(e.getCuenta());
                Assert.assertTrue(s.indexOf(busqueda) > -1);
                nodo = nodo.getSiguiente();
            }

            String promedio = String.format("%2.2f", estudiante.getPromedio());
            l = bdd.buscaRegistros(CampoEstudiante.PROMEDIO, promedio);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Estudiante e = (Estudiante)nodo.get();
                String s = String.format("%2.2f", e.getPromedio());
                Assert.assertTrue(s.indexOf(promedio) > -1);
                nodo = nodo.getSiguiente();
            }
            n = promedio.length();
            busqueda = promedio.substring(random.nextInt(2),
                                          2 + random.nextInt(n-2));
            l = bdd.buscaRegistros(CampoEstudiante.PROMEDIO, busqueda);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Estudiante e = (Estudiante)nodo.get();
                String s = String.format("%2.2f", e.getPromedio());
                Assert.assertTrue(s.indexOf(busqueda) > -1);
                nodo = nodo.getSiguiente();
            }

            String edad = String.valueOf(estudiante.getEdad());
            l = bdd.buscaRegistros(CampoEstudiante.EDAD, edad);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Estudiante e = (Estudiante)nodo.get();
                String s = String.valueOf(e.getEdad());
                Assert.assertTrue(s.indexOf(edad) > -1);
                nodo = nodo.getSiguiente();
            }
            n = edad.length();
            busqueda = String.valueOf(edad.charAt(random.nextInt(n)));
            l = bdd.buscaRegistros(CampoEstudiante.EDAD, busqueda);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Estudiante e = (Estudiante)nodo.get();
                String s = String.valueOf(e.getEdad());
                Assert.assertTrue(s.indexOf(busqueda) > -1);
                nodo = nodo.getSiguiente();
            }
        }

        l = bdd.buscaRegistros(CampoEstudiante.NOMBRE, "xxx-nombre");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.CUENTA, "9123456");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.PROMEDIO, "97.12");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.EDAD, "127");
        Assert.assertTrue(l.esVacia());

        l = bdd.buscaRegistros(CampoEstudiante.NOMBRE, "");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.CUENTA, "");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.PROMEDIO, "");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.EDAD, "");
        Assert.assertTrue(l.esVacia());

        l = bdd.buscaRegistros(CampoEstudiante.NOMBRE, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.CUENTA, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.PROMEDIO, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.EDAD, null);
        Assert.assertTrue(l.esVacia());

        try {
            l = bdd.buscaRegistros(X.A, "");
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
    }
}
