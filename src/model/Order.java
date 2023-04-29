package model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Order {

    private String nombreComprador;
    private ArrayList<Product> listaProductos;
    private double precioTotal;
    private LocalTime fechaCompra;

    public Order(String nombreComprador, ArrayList<Product> listaProductos, double precioTotal, LocalTime fechaCompra) {
        this.nombreComprador = nombreComprador;
        this.listaProductos = listaProductos;
        this.precioTotal = precioTotal;
        this.fechaCompra = fechaCompra;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public ArrayList<Product> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Product> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public LocalTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    

    

}