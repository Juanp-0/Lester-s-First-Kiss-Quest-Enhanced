package lester.first.kiss.quest.java.edition;

public class EnergItem extends Item{
    private int energia_recuperada;

    public EnergItem(String nombre, int precio, int energia_recuperada){
        super(nombre, precio);
        this.energia_recuperada = energia_recuperada;
    }
    public int getEnergia_recuperada(){
        return energia_recuperada;
    }
    public void setEnergia_recuperada(int energia_recuperada){
        this.energia_recuperada = energia_recuperada;
    }

    @Override
    public void usarItem(Personaje personaje){
        personaje.sumEnergia(energia_recuperada);
    }
}
