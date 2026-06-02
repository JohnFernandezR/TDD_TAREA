package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoServiceTest {

	private ProductoService productoService;

	@BeforeEach
	void setUp() {
		productoService = new ProductoService();
	}

	@Test
	void testProcesarProductoValido() {
		String input = "J1011221226,Jabón Copito,200,990,16.827.524-1,proveedor@copito.com";
		Producto p = productoService.crearProductoDesdeTexto(input);

		assertNotNull(p);
		assertEquals("J1011221226", p.getCodigo());
		assertEquals("Jabón Copito", p.getNombre());
	}

	@Test
	void testNombreSupera30Caracteres() {
		String input = "J1011221226,Este es un Jabón Copito con un nombre extremadamente largo,200,990,16.827.524-1,proveedor@copito.com";

		try {
			productoService.crearProductoDesdeTexto(input);
			fail("Se esperaba una IllegalArgumentException porque el nombre supera los 30 caracteres.");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testRutInvalido() {
		String input = "J1011221226,Jabón Copito,200,990,12.345.678-9,proveedor@copito.com";

		try {
			productoService.crearProductoDesdeTexto(input);
			fail("Se esperaba una IllegalArgumentException porque el RUT es inválido.");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testEmailInvalido() {
		String input = "J1011221226,Jabón Copito,200,990,16.827.524-1,proveedorcopito.com";

		try {
			productoService.crearProductoDesdeTexto(input);
			fail("Se esperaba una IllegalArgumentException porque el correo no tiene un formato válido.");
		} catch (IllegalArgumentException e) {

		}
	}
}