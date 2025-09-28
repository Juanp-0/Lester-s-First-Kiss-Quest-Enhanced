package lester.first.kiss.quest.java.edition;

import java.util.Random;

public class Ligue extends Personaje  {
    private String nombre;
    private String[] estado_relacion = {"Nulo", "Interesada", "Amigos", "Quedantes", "Novios"};
    private int estado_relacion_xp;
    private static final long serialVersionUID = 1L;

    static Cinematicas cinematicas = new Cinematicas();
    static Random random = new Random();
    static boolean[] ligue_chance = {true, false};
    static boolean hablar_uso = false;
    static boolean choice;
    static String[] ligue_aprobacion = {"Like", "Dislike", "Excelente"};
    static String choice_aprobacion;
    static boolean[] burger_evento_trigger = {true, false, false, false, false};
    static boolean burger_choice;
    
    
    public Ligue(String nombre, int nv_carisma, String[] estado_relacion, int estado_relacion_xp){
        super(0,0, nv_carisma);
        this.nombre = nombre;
        this.estado_relacion = estado_relacion;
        this.estado_relacion_xp = estado_relacion_xp;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String[] getEstado_relacion(){
        return estado_relacion;
    }

    public int getEstado_relacion_xp(){
        return estado_relacion_xp;
    }

    public void setEstado_relacion_xp(int estado_relacion_xp){
        this.estado_relacion_xp = estado_relacion_xp;
    }

    public void sumEstado_relacion_xp(int num){
        int sum_estado_relacion_xp = estado_relacion_xp + num;

        if (sum_estado_relacion_xp > 20){
            setEstado_relacion_xp(20);
        }
        else{
            setEstado_relacion_xp(sum_estado_relacion_xp);
        }
    }

    public void resEstado_relacion_xp(int num){
        if (estado_relacion_xp - num < 0){
            setEstado_relacion_xp(0);
        }
        else{
            int res_estado_relacion_xp = estado_relacion_xp - num;
            setEstado_relacion_xp(res_estado_relacion_xp);
        }

    }

    public String mostrarEstado_relacion(){
        if (estado_relacion_xp == 0 ){
            return estado_relacion[0];
        }
        else if (estado_relacion_xp >= 1 && estado_relacion_xp < 10){
            return estado_relacion[1];
        }
        else if (estado_relacion_xp >= 10 && estado_relacion_xp < 15){
            return estado_relacion[2];
        }
        else if (estado_relacion_xp >= 15 && estado_relacion_xp < 20){
            return estado_relacion[3];
        }
        else{
            return estado_relacion[4];
        }
    }

    public void datosLigue(){
        System.out.println("\nNombre: " + getNombre() +
                           "\nNivel de Carisma: " + getNv_carisma() +
                           "\nEstado de Relacion: " + mostrarEstado_relacion());
    }

    public boolean fullRelacion(Ligue ligue){
        if(getEstado_relacion_xp() >= 20){
            System.out.println("\nCitas a "+ getNombre()+ " al lugar donde se conocieron\n");
            cinematicas.noviaEscena(Ligue.this);
            return true;
        } 
        else{
            return false;
        }
    }

    public boolean negRelacion(Personaje personaje){
        if(getEstado_relacion_xp() <= 0){
            System.out.println("\nHiciste que tu ligue perdiera todo interes en tí\n");
            personaje.resCarisma(2);
            LesterFirstKissQuestJavaEdition.tener_ligue = false;
            return true;
        } 
        else{
            return false;
        }
    }

    public boolean aprobacionFunc(Personaje personaje, String contexto) {
    int randomIndex2 = random.nextInt(ligue_aprobacion.length);
    choice_aprobacion = ligue_aprobacion[randomIndex2];

    if (choice_aprobacion == "Like") {
        sumEstado_relacion_xp(1);
        if(!fullRelacion(Ligue.this)) {
            switch(contexto) {
                case "HABLAR":
                    System.out.println("\nParece ser que a "+ getNombre()+ " ha disfrutado de la charla\n");
                    break;
                case "CITA":
                System.out.println("\nParece ser que a "+ getNombre()+ " ha disfrutado la cita\n");
                    break;
                }
            return true;
        }
    }
    else if (choice_aprobacion == "Dislike") {
        resEstado_relacion_xp(1);
        if(!negRelacion(personaje)) {
            switch(contexto) {
                case "HABLAR":
                    System.out.println("\nParece ser que a "+ getNombre()+ " no ha disfrutado de la charla\n");
                    break;
                case "CITA":
                    System.out.println("\nParece ser que a "+ getNombre()+ " no ha disfrutado la cita\n");
                    break;
                }
            return true;
        }
    }
    else {
        sumEstado_relacion_xp(3);
        if(!fullRelacion(Ligue.this)) {
            switch(contexto) {
                case "HABLAR":
                    System.out.println("\nParece ser que a "+ getNombre()+ " ha disfrutado muchisimo la charla\n");
                    break;
                case "CITA":
                    System.out.println("\nParece ser que a "+ getNombre()+ " ha disfrutado muchisimo la cita\n");
                    break;
                }
            return true;
            }
        }
    return false;
    }

    public void hablar(Personaje personaje) {
    int randomIndex = random.nextInt(ligue_chance.length);
    choice = ligue_chance[randomIndex];
    
    if (choice) {
        hablar_uso = true;
        aprobacionFunc(personaje, "HABLAR");
        } 
        else {
            hablar_uso = true;
            System.out.println("\nParece ser que "+ getNombre()+ " no esta disponible\n");
        }
    }

   public void salirCita(Personaje pj, int energia, int dinero){
        if (pj.getEnergia() < energia || pj.getDinero() < dinero){
            System.out.println("\nNo tienes suficiente Energia o Dinero para salir de cita\n");    
        }
        else{
            pj.resEnergia(energia);
            pj.resDinero(dinero);
            aprobacionFunc(pj, "CITA");
        }
    }

    public void burgerEvento(Personaje personaje) {
    int randomIndex = random.nextInt(burger_evento_trigger.length);
    burger_choice = burger_evento_trigger[randomIndex];
    System.out.println("\nHas decidido ir a la Hamburgeseria Estrella\n");
        if (burger_choice) {   
            cinematicas.burgerScene(Ligue.this);
            System.out.println("\n¿Que Piensas Hacer?\n1.- Reclamar al Sujeto Seboso\n2.- Ignorar y Comprar otra\n");
            int decision = LesterFirstKissQuestJavaEdition.solicitarEntrada();
            if (decision == 1){
                cinematicas.reclamarSeboso();
                System.out.println("Parece ser que a "+ getNombre()+ " le ha gustado tu actitud\n");
                sumEstado_relacion_xp(3);
                personaje.sumCarisma(2);
                if(!fullRelacion(Ligue.this)) {
                    System.out.println("Parece ser que a "+ getNombre()+ " le ha gustado mucho la cita\n");
                }
            } 
            else if (decision == 2) {
                System.out.println("\nHas decidido ignorarlo y comprar otra\n");
                System.out.println("Parece ser que a "+ getNombre()+ " no le ha gustado tu actitud\n");
                resEstado_relacion_xp(3);
                personaje.resCarisma(2);
                if(!negRelacion(personaje)) {
                    System.out.println("Parece ser que a "+ getNombre()+ " no le ha gustado la cita\n");
                }
            } 
            else {
                System.out.println("\nSelecciona una opcion valida\n");
            }
        } 
        else {
            salirCita(personaje, 40, 250);
        }
    }

   public void cita(Personaje personaje){
        if(fullRelacionCheck()) {
            return;
        }
        
        System.out.println("\nHas decido salir con " + getNombre() + "\n");
        int menu_cita;
        boolean continuar = true;
        
        do{
            System.out.println("Lugares para tener una cita:\n1.- Sandwichería Local (-30 de Energía, -100 de Dinero)\n2.- Cafetería (-40 de Energía, -200 de Dinero)\n3.- Hamburgeseria Esterella (-40 de Energía, -250 de Dinero)"+
            "\n4.- Buffet Italiano (-40 de Energía, -350 de Dinero)\n5.- Estadio de Fútbol (-50 de Energía, -1000 de Dinero)\n6.- Volver al Menu\n");
            System.out.println("Selecciona un lugar para tener una cita o Escribe 6 para salir:");
            menu_cita = LesterFirstKissQuestJavaEdition.solicitarEntrada();
            switch(menu_cita){
                case 1:
                    System.out.println("\nHas decidido ir a Sandwichería Local\n");
                    salirCita(personaje, 30, 100);
                    if(getEstado_relacion_xp() >= 20) continuar = false;
                    break;
                case 2:
                    System.out.println("\nHas decidido ir a la Cafetería\n");
                    salirCita(personaje, 40, 200);
                    if(getEstado_relacion_xp() >= 20) continuar = false;
                    break;
                case 3:
                    burgerEvento(personaje);
                    if(getEstado_relacion_xp() >= 20) continuar = false;
                    break;
                case 4:
                    System.out.println("\nHas decidido ir al Buffet Italiano\n");
                    salirCita(personaje, 40, 350);
                    if(getEstado_relacion_xp() >= 20) continuar = false;
                    break;
                case 5:
                    System.out.println("\nHas decidido ir al Estadio de Fútbol\n");
                    salirCita(personaje, 50, 1000);
                    if(getEstado_relacion_xp() >= 20) continuar = false;
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("\nSelecciona una opcion valida\n");
                    break;
            }
        } while(menu_cita != 5 && continuar);
    }

    public boolean fullRelacionCheck(){
        if(getEstado_relacion_xp() >= 20){
            System.out.println("\nYa eres novio de " + getNombre() + ", ya la conoces bien\n");
            return true;
        }
        else{
            return false;
        }
    }
    

    public void ligueMenu(Personaje personaje){
        int menu_ligue;
        do{
            datosLigue();
            System.out.println("");
            System.out.println("Selecciona lo que quieres hacer con "+ getNombre()+":"+ "\n1.- Hablar con "+ getNombre()+ " (Solo una vez por Día)\n2.- Tener una cita con "+ getNombre()  +"\n3.- Volver al Menu\n");
            menu_ligue = LesterFirstKissQuestJavaEdition.solicitarEntrada();
            switch(menu_ligue){
                 case 1:
                    if (hablar_uso && choice) {
                        System.out.println("\nParece ser que ya has hablado con "+ getNombre()+ "\n");
                    }
                    else if (hablar_uso && !choice) {
                        System.out.println("\nParece ser que "+ getNombre()+ " no esta disponible\n");
                    }
                    else if (!fullRelacionCheck()) {
                        hablar(personaje);
                    }
                    break;
                case 2:
                    if(!fullRelacionCheck()) {
                        cita(personaje);
                    }
                    break;
                case 3:
                    break;
                default:
                    System.out.println("\nSelecciona una opcion valida\n");
                    break;
                 
            }
        } while(menu_ligue != 3);
    }
}