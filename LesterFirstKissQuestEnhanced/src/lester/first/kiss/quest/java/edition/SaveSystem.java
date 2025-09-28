package lester.first.kiss.quest.java.edition;

import java.io.*;
import java.util.HashMap;

public class SaveSystem {
    private static final String SAVE_PATH = "save.dat";
    
    public static void saveGame(int dias, Personaje lester, Ligue ligueActual, 
                              boolean tener_ligue, boolean primer_beso, boolean fin_juego) {
        HashMap<String, Object> gameState = new HashMap<>();
        gameState.put("dias", dias);
        gameState.put("lester", lester);
        gameState.put("ligueActual", ligueActual);
        gameState.put("tener_ligue", tener_ligue);
        gameState.put("primer_beso", primer_beso);
        gameState.put("fin_juego", fin_juego);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH))) {
            oos.writeObject(gameState);
            System.out.println("\nJuego guardado exitosamente.\n");
        } catch (IOException e) {
            System.out.println("\nError al guardar la partida: " + e.getMessage() + "\n");
        }
    }
    
    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_PATH))) {
            System.out.println("\nJuego cargado exitosamente.\n");
            return (HashMap<String, Object>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\nNo hay un archivo de guardado existente. Inicia un nuevo juego.\n");
            return null;
        }
    }
}