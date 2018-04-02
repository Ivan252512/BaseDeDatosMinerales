package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de estudiantes.
 */
public class BaseDeDatosEstudiantes extends BaseDeDatos {

    /**
     * Crea un estudiante en blanco.
     * @return un estudiante en blanco.
     */
    @Override public Registro creaRegistro() {
        // Aquí va su código.
        return (Registro)new Estudiante();
    }

    /**
     * Busca estudiantes por un campo específico.
     * @param campo el campo del registro por el cuál buscar.
     * @param texto el texto a buscar.
     * @return una lista con los estudiantes tales que en el campo especificado
     *         contienen el texto recibido, o una lista vacía si el texto es
     *         <code>null</code> o la cadena vacía.
     * @throws IllegalArgumentException si el campo no es instancia de
     *         {@link CampoEstudiante}.
     */
    @Override public Lista buscaRegistros(Enum campo, String texto) {
        if (!(campo instanceof CampoEstudiante))
            throw new IllegalArgumentException("El campo debe ser " +
                                               "CampoEstudiante");
        CampoEstudiante c = (CampoEstudiante)campo;
        // Aquí va su código.
    }
}
