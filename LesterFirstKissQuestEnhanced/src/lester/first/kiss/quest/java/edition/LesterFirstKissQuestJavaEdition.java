package lester.first.kiss.quest.java.edition;

import java.util.*;

public class LesterFirstKissQuestJavaEdition {
    
    static Scanner sc = new Scanner(System.in);
    static Personaje lester = new Personaje(100,0,0);
    static Random random = new Random();
    static Tienda tienda = new Tienda(new Item[]{
        new EnergItem("Bebida Energetica", 20, 20), 
        new EnergItem("Cafe Embotellado", 15, 10), 
        new CarismaItem("Perfume", 150, 1),});
    static Fiesta fiesta = new Fiesta(new Ligue[3]);
    static Ligue ligueActual = null;
    static Cinematicas cinematicas = new Cinematicas(); 
    public static int dias = 1;
    public static boolean[] dax_chance = {true, false};
    public static boolean hablar_uso = false;
    public static boolean choice;
    public static boolean tener_ligue = false;
    public static boolean primer_beso = false;
    public static boolean fin_juego = false;
    
    public static void main(String[] args) {
        int mainmenu;
        
        do{
            System.out.println("Lester First Kiss Quest: Enhanced");
            System.out.println("\nSelecciona una opción:\n1.- Juego Nuevo\n2.- Continuar\n3.- Salir del Juego\n");
            
            mainmenu = solicitarEntrada();
          
            switch(mainmenu){
                case 1:
                    newgame();
                    cinematicas.intro();     
                    game();
                    break;
                case 2:
                    SaveSystem.loadGame();
                    if (primer_beso){
                        System.out.println("\nAl parecer ya diste tu primer beso ¡Felicidades! Ganaste el Juego\n");
                        break;
                    }
                    else if (dias == 49 && lester.getDinero() < 2000){
                        System.out.println("\nNo tienes el dinero suficiente para ejecutar el Plan De Lester, Perdiste el Juego\n");
                        break;
                    } 
                    else{
                        game();
                    }
                    break;
                case 3: 
                    break;
            }
        } while(mainmenu != 3);
    }
    
    public static int solicitarEntrada() {
        int numero = -1;
        boolean inputValido = false;
        while (!inputValido) {
            try {
                numero = sc.nextInt();
                sc.nextLine(); 
                inputValido = true;
            } catch (InputMismatchException e) {
            System.out.println("Selecciona una opción valida");
            sc.nextLine(); 
            }
        }
        return numero;
    }

    public static void newgame(){
        dias = 1;
        lester = new Personaje(100,2000,0);
        ligueActual = new Ligue("Tilina", 5, new String[] {"Nulo", "Interesada", "Amigos", "Quedantes", "Novios"}, 5);
        hablar_uso = false;
        choice = false;
        tener_ligue = true;
        primer_beso = false;
        fin_juego = false;

        SaveSystem.saveGame(dias, lester, ligueActual, tener_ligue, primer_beso, fin_juego);
    }

    public static void game(){
        int gamemenu;
        
        do{
            System.out.println("Dia "+dias);
            System.out.println("Stats:\nEnergia: "+ lester.getEnergia()+"\nDinero: $"+lester.getDinero()+"\nNv. de Carisma: "+lester.getNv_carisma());
            System.out.println("\nSelecciona lo que quieres realizar:\n1.- Trabajar (-50 de Energia, +50 de Dinero)\n2.- Hablar con Dax (+1 Nv. de Carsima (Solo una vez por dia))\n3.- Salir de Fiesta (-30 de Energia, -10 de Dinero)\n4.- Ir a la Tienda\n5.- Ligue\n6.- Dormir (Pasa al Sig. Dia y Restablece Toda la Energia)\n7.- Guardar Partida\n8.- Volver al Menu\n");
            
            gamemenu = solicitarEntrada();
          
            switch(gamemenu){
                case 1: 
                    trabajar();
                    break;
                case 2:
                    if (hablar_uso == true && choice){
                        System.out.println("\nParece ser que ya has hablado con Dax\n");
                    }
                    else if(hablar_uso == true && !choice){
                        System.out.println("\nParece ser que Dax no esta disponible\n");
                    }
                    else if(lester.getNv_carisma() == 10){
                        System.out.println("\nHas llegado al maximo nivel de carisma\n");
                    }
                    else{
                        hablar();
                    }
                    break;
                case 3:
                    if (tener_ligue == true) {
                        System.out.println("\nMejor trata de salir con " + ligueActual.getNombre() + "\n");
                    }
                    else{
                        salirFiesta();
                    }  
                    break;
                case 4:
                    tienda.irTienda(lester);
                    break;
                case 5:
                    if (tener_ligue) {
                        ligueActual.ligueMenu(lester);
                    } 
                    else {
                        System.out.println("\nAún no tienes un ligue\n");
                    }
                    break;
                case 6:
                    if (dias == 49 && lester.getDinero() < 2000 && !primer_beso){
                        cinematicas.badEnding();
                        System.out.println("\nNo tienes el dinero suficiente para ejecutar el Plan De Lester, Perdiste el Juego\n");
                        SaveSystem.saveGame(dias, lester, ligueActual, tener_ligue, primer_beso, fin_juego);
                        break;
                    } 
                    else if(dias == 49  && lester.getDinero() >= 2000 && !primer_beso){
                        System.out.println("\nNo diste tu primer beso, tendras que ejecutar el Plan De Lester\n");
                        cinematicas.delay(1);
                        cinematicas.normEnding(lester);
                        SaveSystem.saveGame(dias, lester, ligueActual, tener_ligue, primer_beso, fin_juego);
                        break;
                    }
                    else if (primer_beso){
                        cinematicas.goodEnding(ligueActual);
                        SaveSystem.saveGame(dias, lester, ligueActual, tener_ligue, primer_beso, fin_juego);
                        break;
                    }
                    else{
                        dormir();
                    }
                    break;
                case 7:
                    SaveSystem.saveGame(dias, lester, ligueActual, tener_ligue, primer_beso, fin_juego);
                    break;
                case 8:
                    break;
            }
        } while(gamemenu != 8 && !fin_juego);
    }
    
    public static void trabajar(){
        if (lester.getEnergia() < 50){
            System.out.println("\nNo tienes ganas de ir a Trabajar\n");
        }
        else{
            lester.resEnergia(50);
            lester.sumDinero(50);
            System.out.println("\nRegresas a Casa, despues de un arduo dia de trabajo\n");
        }
    }
    
    public static void salirFiesta(){
        if (lester.getEnergia() < 30){
            System.out.println("\nNo tienes ganas de Salir de fiesta\n");
        }
        else{
            if(lester.getDinero() < 10){
                System.out.println("\nNo tienes dinero para Salir de fiesta\n");
            }
            else{
                lester.resEnergia(30);
                lester.resDinero(10);
                Ligue ligueSeleccionado = fiesta.chicas(lester);
                if (ligueSeleccionado != null) {
                    ligueActual = ligueSeleccionado;
                }   
                System.out.println("\nRegresas a Casa de manera silenciosa, despues de una noche de locura\n");
            }
        }
    }
    
    public static void dormir(){
        dias = dias + 1;
        lester.setEnergia(100);
        hablar_uso = false;
        Ligue.hablar_uso = false;
    }
    
    public static void hablar(){
        int randomIndex = random.nextInt(dax_chance.length);
        choice = dax_chance[randomIndex];
        
        if (choice == true){
            lester.sumCarisma(1);
            hablar_uso = true;
            System.out.println("\nDax te da unos buenos consejos para aumentar tu carisma\n");
        }
        else{
            hablar_uso = true;
            System.out.println("\nParece ser que Dax no esta disponible\n");
        }
    }
}
