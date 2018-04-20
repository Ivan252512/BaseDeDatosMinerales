package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de Minerales.
 */
public class BaseDeDatosMinerales extends BaseDeDatos {

    /**
     * Crea un mineral en blanco.
     * @return un mineral en blanco.
     */
    @Override public Registro creaRegistro() {
        // Aquí va su código.
        return (Registro)new Mineral(null,0,0.0,0.0,null);
    }

    /**
     * Busca minerales por un campo específico.
     * @param campo el campo del registro por el cuál buscar.
     * @param texto el texto a buscar.
     * @return una lista con los minerales tales que en el campo especificado
     *         contienen el texto recibido, o una lista vacía si el texto es
     *         <code>null</code> o la cadena vacía.
     * @throws IllegalArgumentException si el campo no es instancia de
     *         {@link CampoMineral}.
     */
    @Override public Lista buscaRegistros(Enum campo, String texto) {
        // Aquí va su código.
        if (!(campo instanceof CampoEstudiante))
          throw new IllegalArgumentException("El campo debe ser " +
                                             "CampoMineral");
        CampoEstudiante c = (CampoEstudiante)campo;
        // Aquí va su código.
        Lista r=new Lista();
        if(texto==null || texto.equals(""))
            return r;
        Lista.Nodo n= registros.getCabeza();
        while(n!=null){
            Mineral e=(Mineral)n.get();
            String s= cadenaCampo(e,c);
            if(s.indexOf(texto)>-1){
              r.agregaFinal(e);
            }
            n=n.getSiguiente();
        }
        return r;
    }

    private String cadenaCampo(Mineral m, CampoMineral c){
      switch(c){
        case NOMBRE:return m.getNombre();
        case DUREZA:return String.valueOf(m.getDureza());
        case PUNTODEFUSION:return String.format("%2.2f", m.getPuntoDeFusion());
        case DENSIDAD:return String.format("%2.2f", m.getDensidad());
        case ESTRUCTURACRISTALINA:return m.getEstructuraCristalina();
        default:return "";
      }
    }
}
