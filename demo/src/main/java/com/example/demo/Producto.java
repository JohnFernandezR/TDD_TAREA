package com.example.demo;

public class Producto {
    private String codigo;
    private String nombre;
    private int stock;
    private double precio;
    private String rutProveedor;
    private String mailProveedor;

    public Producto(String codigo, String nombre, int stock, double precio, String rutProveedor, String mailProveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.rutProveedor = rutProveedor;
        this.mailProveedor = mailProveedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    public String getRutProveedor() {
        return rutProveedor;
    }

    public String getMailProveedor() {
        return mailProveedor;
    }
}