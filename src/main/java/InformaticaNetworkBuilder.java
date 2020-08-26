public class InformaticaNetworkBuilder {
    private static final String TELECOMUNICACIONES = "Telecomunicaciones";
    private static final String INFORMATICA = "INFORMATICA";
    private static final String MATEMATICA = "Matematica";
    private static final String ANTENAS = "Antenas";
    private static final String REDES = "Redes";
    private static final String CALCULO = "Calculo";
    private static final String FISICA = "Fisica";
    private static final String TRANSMISION = "Transmision";
    private static final String OPERACIONES = "Operaciones";
    private static final String INTELIGENCIA = "Inteligencia Artificial";
    private static final String MODELOS = "Modelos";
    private static final String ELECTRONICA = "Electronica";
    private static final String CIENCIASINFO = "Ciencias / Informacion";
    private static final String INGENIERIAS = "Ingenierias";


    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        GraphBuilder graphBuilder = new GraphBuilder("Informatica Network");//
        graphBuilder
                .addEdge(INFORMATICA, TELECOMUNICACIONES, "Apoya")
                .addSubEdge(TELECOMUNICACIONES, ANTENAS, "Usa")
                .addSubEdge(TELECOMUNICACIONES, REDES, "Usa")
                .addSubEdge(TELECOMUNICACIONES, TRANSMISION, "Requiere de automatizar")
                .addSubEdge(INFORMATICA, REDES, "Automatiza comunicaciones")
                .addEdge(INFORMATICA, MATEMATICA, "Benificia en el calculo")
                .addSubEdge(MATEMATICA, CALCULO, "Genera metodos")
                .addSubEdge(INFORMATICA, CALCULO, "Almacenamiento y repeticion de datos")
                .addEdge(MATEMATICA, FISICA, "Soporta")
                .addSubEdge(CALCULO, OPERACIONES, "Requiere")
                .addSubEdge(CALCULO, FISICA, "Requiere")
                .addSubEdge(FISICA, MODELOS, "Usa")
                .addEdge(INFORMATICA, INTELIGENCIA, "Procesos")
                .addSubEdge(INTELIGENCIA, "Deep Learning", "Metodo")
                .addSubEdge(INTELIGENCIA, "NLP", "Metodo")
                .addSubEdge(INTELIGENCIA, "Machine Learning", "Metodo")
                .addEdge(INFORMATICA, ELECTRONICA,"Complementa")
                .addSubEdge(ELECTRONICA, "Domotica","Apoya")
                .addEdge(INFORMATICA, CIENCIASINFO,"Procesa")
                .addEdge(INFORMATICA, INGENIERIAS,"Apoya")
                .addingNodeName();

        graphBuilder.generatePanel();
        graphBuilder.addGraphIntoPanel();
        graphBuilder.mouseListener();
    }
}