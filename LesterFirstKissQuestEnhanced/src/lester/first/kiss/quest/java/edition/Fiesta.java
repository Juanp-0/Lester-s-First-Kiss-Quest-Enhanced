package lester.first.kiss.quest.java.edition;

public class Fiesta{
    private Ligue[] chicas;

    static String[] nombresChicas = {
        "Romina", "Ariana", "Julieta", "María", "Maya", "Erika", "Sofía", "Carla", "Debanhi", "Deborah", "Katia", "Serena", "Ramona", "Ana", "Alejandra", "Tondelaya", "Victoria", "Nereida", "Violeta", "Fernanda", "Catalina"
    };
    static int[] nivelesCarisma = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    static String[] nv_carisma_mayor = {"Si", "No", "No", "No", "Flechazo"};
    static String[] nv_carisma_igual = {"Si", "Si", "No", "No", "Flechazo"};
    static String[] nv_carisma_menor = {"Si", "Si", "Si", "No", "Flechazo"};

    static Cinematicas cinematicas = new Cinematicas();

    public Fiesta(Ligue[] chicas){
        this.chicas = chicas;
    }

    public Ligue[] getChicas(){
        return chicas;
    }

    public void generarChicas(int numeroChicas) {
        chicas = new Ligue[numeroChicas];
    
        for (int i = 0; i < numeroChicas; i++) {
            String nombre = nombresChicas[(int) (Math.random() * nombresChicas.length)];
            int nv_carisma = nivelesCarisma[(int) (Math.random() * nivelesCarisma.length)];
            chicas[i] = new Ligue(nombre, nv_carisma, new String[]{"Nulo", "Interesada", "Amigos", "Quedantes", "Novios"}, 5);
        }   
    }

    public void mostrarChicas(){
        for (int i = 0; i < chicas.length; i++) {
            Ligue chica = chicas[i];
            System.out.println((i + 1) + ". " + chica.getNombre() + "\nNv. Carisma: " + chica.getNv_carisma() + "\n");
        }
    }

    public void ligueExito(Ligue chica, Personaje personaje) {
        String[] opciones;

        if (personaje.getNv_carisma() > chica.getNv_carisma()) {
            opciones = nv_carisma_mayor;
        } 
        else if (personaje.getNv_carisma() == chica.getNv_carisma()) {
            opciones = nv_carisma_igual;
        } 
        else {
            opciones = nv_carisma_menor;
        }

        String eleccion = opciones[(int) (Math.random() * opciones.length)];

        switch (eleccion) {
            case "Si":
                System.out.println("Parece que tus habilidades de carisma fueron efectivas\n");
                LesterFirstKissQuestJavaEdition.tener_ligue = true;
                chica.setEstado_relacion_xp(5);
                break;
            case "No":
                System.out.println("Parece que tus habilidades de carisma no fueron efectivas\n");
                LesterFirstKissQuestJavaEdition.tener_ligue = false;
                break;
            case "Flechazo":
                System.out.println("Parece que tus habilidades de carisma fueron superefectivas, tanto que acabas de dar tu primer beso\n");
                LesterFirstKissQuestJavaEdition.tener_ligue = true;
                LesterFirstKissQuestJavaEdition.primer_beso = true;
                chica.setEstado_relacion_xp(21);
                break;
        }

    }

        public Ligue chicas(Personaje personaje){  
        cinematicas.fiestaSalir();

        generarChicas(3);
        mostrarChicas();

        System.out.println("Ingresa el numero de la chica con la que quieras hablar:\n");
        int chicasmenu = LesterFirstKissQuestJavaEdition.solicitarEntrada();
        
        if (chicasmenu < 1 || chicasmenu > chicas.length) {
            System.out.println("\nSelecciona una opción valida\n");
            return null;
        } 
        else {
            Ligue chicaSeleccionada = chicas[chicasmenu - 1];
            ligueExito(chicaSeleccionada, personaje);
            return chicaSeleccionada;
        }
    }

}    