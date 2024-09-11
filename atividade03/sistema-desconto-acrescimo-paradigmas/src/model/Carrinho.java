package model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private final List<Item> itens = new ArrayList<>();

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public List<Item> getItens() {
        return itens;
    }

    public Item buscarItemPorCodigo(String codigo) {
        for (Item item : itens) {
            if (item.getCodigo().equals(codigo)) {
                return item;
            }
        }
        return null;
    }

    public void imprimirItens() {
        for (Item item : itens) {
            item.imprimirItem();
        }
    }
}
