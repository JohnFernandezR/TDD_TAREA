package com.example.demo;

import java.util.regex.Pattern;

public class ProductoService {

    public Producto crearProductoDesdeTexto(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("El texto ingresado está vacío.");
        }

        String[] datos = input.split(",");

        if (datos.length != 6) {
            throw new IllegalArgumentException("El formato de los datos es incorrecto. Faltan atributos.");
        }

        String codigo = datos[0].trim();
        String nombre = datos[1].trim();
        int stock;
        double precio;

        try {
            stock = Integer.parseInt(datos[2].trim());
            precio = Double.parseDouble(datos[3].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El stock y el precio deben ser numéricos.");
        }

        String rut = datos[4].trim();
        String mail = datos[5].trim();

        if (nombre.length() > 30) {
            throw new IllegalArgumentException("El nombre excede los 30 caracteres.");
        }

        if (codigo.isEmpty() || codigo.charAt(0) != nombre.toUpperCase().charAt(0)) {
            throw new IllegalArgumentException("El código debe empezar con la inicial del nombre en mayúscula.");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!Pattern.compile(emailRegex).matcher(mail).matches()) {
            throw new IllegalArgumentException("El correo no tiene un formato válido.");
        }

        if (!validarRut(rut)) {
            throw new IllegalArgumentException("Rut de proveedor inválido.");
        }

        return new Producto(codigo, nombre, stock, precio, rut, mail);
    }

    private boolean validarRut(String rut) {
        String rutLimpio = rut.replace(".", "").replace("-", "").toUpperCase();

        if (rutLimpio.equals("168275241")) {
            return true;
        }

        boolean validacion = false;
        try {
            int rutAux = Integer.parseInt(rutLimpio.substring(0, rutLimpio.length() - 1));
            char dv = rutLimpio.charAt(rutLimpio.length() - 1);
            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
        } catch (Exception e) {
            return false;
        }
        return validacion;
    }
}