package mx.unam.ciencias.icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import mx.unam.ciencias.icc.CampoEstudiante;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la enumeración {@link CampoEstudiante}.
 */
public class TestCampoMineral {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /**
     * Prueba unitaria para {@link CampoEstudiante#toString}.
     */
    @Test public void testToString() {
        String s = CampoMineral.NOMBRE.toString();
        Assert.assertTrue(s.equals("Nombre"));
        s = CampoMineral.DENSIDAD.toString();
        Assert.assertTrue(s.equals("Densidad"));
        s = CampoEstudiante.DUREZA.toString();
        Assert.assertTrue(s.equals("Dureza"));
        s = CampoEstudiante.PUNTODEFUSION.toString();
        Assert.assertTrue(s.equals("Punto de fusión"));
        s = CampoEstudiante.ESTRUCTURACRISTALINA.toString();
        Assert.assertTrue(s.equals("Estructura cristalina"));
    }
}
