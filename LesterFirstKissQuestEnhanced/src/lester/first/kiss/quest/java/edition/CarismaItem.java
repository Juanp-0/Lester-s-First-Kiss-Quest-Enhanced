package lester.first.kiss.quest.java.edition;

public class CarismaItem extends Item{
    private int carisma_subido;

    public CarismaItem(String nombre, int precio, int carisma_subido){
        super(nombre, precio);
        this.carisma_subido = carisma_subido;
    }
    public int getCarisma_subido(){
        return carisma_subido;
    }
    public void setCarisma_subido(int carisma_subido){
        this.carisma_subido = carisma_subido;
    }

    @Override
    public void usarItem(Personaje personaje){
        personaje.sumCarisma(carisma_subido);
    }
}