package mx.unam.ciencias.icc;

/**
 * Enumeración para los campos de un {@link Estudiante}.
 */
public enum CampoEstudiante {

    /** El nombre del estudiante. */
    NOMBRE,
    /** El número de cuenta del estudiante. */
    CUENTA,
    /** El promedio del estudiante. */
    PROMEDIO,
    /** La edad del estudiante. */
    EDAD;

    /**
     * Regresa una representación en cadena del campo para ser usada en
     * interfaces gráficas.
     * @return una representación en cadena del campo.
     */
    @Override public String toString() {
        // Aquí va su código.
        if (this.equals(CampoEstudiante.NOMBRE))
            return "Nombre";
        if (this.equals(CampoEstudiante.CUENTA))
            return "# Cuenta";
        if (this.equals(CampoEstudiante.PROMEDIO))
            return "Promedio";
        return "Edad";
    }
}
