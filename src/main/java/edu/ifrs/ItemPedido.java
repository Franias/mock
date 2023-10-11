package edu.ifrs;

public class ItemPedido {
    private double subtotal;

    public ItemPedido(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
