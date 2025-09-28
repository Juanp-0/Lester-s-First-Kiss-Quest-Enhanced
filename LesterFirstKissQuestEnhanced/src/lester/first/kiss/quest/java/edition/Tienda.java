package lester.first.kiss.quest.java.edition;

public class Tienda{
    private Item[] itemsDisponibles;

    public Tienda(Item[] itemsDisponibles){
        this.itemsDisponibles = itemsDisponibles;
    }

    public Item[] getItemsDisponibles(){
        return itemsDisponibles;
    }

    public void mostrarItems(){
        System.out.println("Productos disponibles:");
        for (int i = 0; i < itemsDisponibles.length; i++) {
            Item item = itemsDisponibles[i];
            System.out.print((i+1) + ".- " + item.getNombre() + " (-" + item.getPrecio() + " de Dinero");
            
            if (item instanceof EnergItem) {
                System.out.println(", +" + ((EnergItem)item).getEnergia_recuperada()+ " de Energía)");
            } 
            else if (item instanceof CarismaItem) {
                System.out.println(", +" + ((CarismaItem)item).getCarisma_subido()+ " Nv. de Carisma)");
            }
        }
    }

    public void irTienda(Personaje personaje) {
        System.out.println("\nCajero: Bienvenido ¿En que te puedo servir?\"\n");
        int compra_item;

        do{
            mostrarItems();
            System.out.println((getItemsDisponibles().length + 1) + ".- Salir de la Tienda");
            System.out.println("\nSelecciona el item que deseas comprar o presiona "+ (getItemsDisponibles().length + 1) +" para salir:");
            compra_item = LesterFirstKissQuestJavaEdition.solicitarEntrada();

            if (compra_item < 1 || compra_item > getItemsDisponibles().length + 1) {
                System.out.println("\nSelecciona una opción valida\n");
            } 
            else if (compra_item == getItemsDisponibles().length + 1) {
                System.out.println("\nSaliendo de la Tienda...\n");
                break;
            } 
            else {
                Item itemSeleccionado = getItemsDisponibles()[compra_item - 1];
                if (personaje.getDinero() >= itemSeleccionado.getPrecio()) {
                    personaje.resDinero(itemSeleccionado.getPrecio());
                    itemSeleccionado.usarItem(personaje);
                    System.out.println("\nHas comprado: " + itemSeleccionado.getNombre() + "\n");
                }
                else if(personaje.getEnergia() == 100 && itemSeleccionado instanceof EnergItem){
                    System.out.println("\nTienes toda la energía recargada\n");
                }
                else if(personaje.getNv_carisma() == 10 && itemSeleccionado instanceof CarismaItem){
                    System.out.println("\nHas llegado al maximo nivel de carisma\n");

                } 
                else {
                    System.out.println("\nNo te alcanza\n");
                }
            }
        } while (compra_item != getItemsDisponibles().length + 1);
    }
}    