package mx.unam.ciencias.icc;

/**
 * Enumeración para los campos de un {@link Mineral}.
 */
public enum CampoMineral {

    /** El nombre del mineral. */
    NOMBRE,
    /** La dureza del mineral. */
    DUREZA,
    /** La densidad del mineral. */
    DENSIDAD,
    /** El punto de fusión del mineral. */
    PUNTODEFUSION,
    /** La estructura cristalina del mineral. */
    ESTRUCTURACRISTALINA;

    /**
     * Regresa una representación en cadena del campo para ser usada en
     * interfaces gráficas.
     * @return una representación en cadena del campo.
     */
    @Override public String toString() {
        // Aquí va su código.
        if (this.equals(CampoMineral.NOMBRE))
            return "Nombre";
        if (this.equals(CampoMineral.DUREZA))
            return "Dureza";
        if (this.equals(CampoMineral.DENSIDAD))
            return "Densidad";
        if (this.equals(CampoMineral.PUNTODEFUSION))
            return "Punto de fusión"
        return "Estructura cristalina";
    }
}
