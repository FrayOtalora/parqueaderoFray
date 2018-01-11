package co.ceiba.vigilante.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.vigilante.businesslogic.BusinessLogic;
import co.ceiba.vigilante.dominio.Parking;
import co.ceiba.vigilante.excepcion.VigilanteExcepcion;
import co.ceiba.vigilante.testdatabuilder.ParkingTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BusinessLogicTest {

	@Autowired
	BusinessLogic businessLogic;

	static final String AUTOS="autos";
	static final String MOTOS="motos";
	
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaSalida;
	private Parking parking;

	@Before
	public void setUp() {
		fechaIngreso = LocalDateTime.now();
		fechaSalida = LocalDateTime.now();
		parking = new ParkingTestDataBuilder().build();
	}

	// Pruebas para metodo restriccionIngreso

	@Test (expected = VigilanteExcepcion.class)
	public void placaNula() {
		businessLogic.restriccionIngreso("");
	}

	@Test
	public void placaNoNulaConRestriccion() {

		parking = new ParkingTestDataBuilder().withPlaca("A123456").build();

		assertTrue(businessLogic.restriccionIngreso(parking.getPlaca()));
	}

	@Test
	public void placaNoNulaSinRestriction() {
		assertFalse(businessLogic.restriccionIngreso(parking.getPlaca()));
	}

	// Pruebas obtener diferencia horas

	@Test
	public void obtenerDiferenciaHorasMenor9() {

		Date ingreso = this.aumentarHorayMinutos(fechaIngreso, 0, 0);
		Date salida = this.aumentarHorayMinutos(fechaSalida, 5, 0);

		assertEquals(5, businessLogic.obtenerDiferenciaHoras(ingreso, salida));

	}

	@Test
	public void obtenerDiferenciaHorasEntre9y24() {

		Date ingreso = this.aumentarHorayMinutos(fechaIngreso, 0, 0);
		Date salida = this.aumentarHorayMinutos(fechaSalida, 15, 0);

		assertEquals(15, businessLogic.obtenerDiferenciaHoras(ingreso, salida));

	}

	@Test
	public void obtenerDiferenciaHorasMayor24() {

		Date ingreso = this.aumentarHorayMinutos(fechaIngreso, 0, 0);
		Date salida = this.aumentarHorayMinutos(fechaSalida, 27, 0);

		assertEquals(27, businessLogic.obtenerDiferenciaHoras(ingreso, salida));
	}

	@Test
	public void obtenerDiferenciaHorasMenor9MinutosAdicionales15() {

		Date ingreso = this.aumentarHorayMinutos(fechaIngreso, 0, 0);
		Date salida = this.aumentarHorayMinutos(fechaSalida, 7, 15);

		assertEquals(8, businessLogic.obtenerDiferenciaHoras(ingreso, salida));
	}

	@Test
	public void obtenerDiferenciaHorasMayor9Menor24MinutosAdicionales15() {

		Date ingreso = this.aumentarHorayMinutos(fechaIngreso, 0, 0);
		Date salida = this.aumentarHorayMinutos(fechaSalida, 15, 15);

		assertEquals(16, businessLogic.obtenerDiferenciaHoras(ingreso, salida));
	}

	@Test
	public void obtenerDiferenciaHorasMayor24MinutosAdicionales15() {

		Date ingreso = this.aumentarHorayMinutos(fechaIngreso, 0, 0);
		Date salida = this.aumentarHorayMinutos(fechaSalida, 30, 15);

		assertEquals(31, businessLogic.obtenerDiferenciaHoras(ingreso, salida));
	}

	public Date aumentarHorayMinutos(LocalDateTime fechaI, long horas, long minutos) {

		LocalDateTime fecha = fechaI.plusHours(horas).plusMinutes(minutos);
		Instant instant2 = fecha.toInstant(ZoneOffset.UTC);
		return Date.from(instant2);

	}

	// Test metodo obtener valor a pagar

	@Test
	public void valorPagarAuto7Horas() {

		assertEquals(7000.0f, businessLogic.obtenerValorPagar(7, parking.getTipoVehiculo(), parking.getCilindraje()), 0.0f);
	}

	@Test
	public void valorPagarAuto13Horas() {

		assertEquals(8000.0f, businessLogic.obtenerValorPagar(13, parking.getTipoVehiculo(), parking.getCilindraje()), 0.0f);
	}

	@Test
	public void valorPagarAuto28Horas() {

		assertEquals(12000.0f, businessLogic.obtenerValorPagar(28, parking.getTipoVehiculo(), parking.getCilindraje()), 0.0f);
	}

	@Test
	public void valorPagarMoto7HorasCilindrajeMenos500() {

		parking = new ParkingTestDataBuilder().withTipoVehiculo(1).build();

		assertEquals(3500.0f, businessLogic.obtenerValorPagar(7, parking.getTipoVehiculo(), parking.getCilindraje()), 0.0f);
	}

	@Test
	public void valorPagarMoto13HorasCilindrajeMenos500() {

		parking = new ParkingTestDataBuilder().withTipoVehiculo(1).build();

		assertEquals(4000.0f, businessLogic.obtenerValorPagar(13, parking.getTipoVehiculo(), parking.getCilindraje()), 0.0f);
	}

	@Test
	public void valorPagarMoto28HorasCilindrajeMenos500() {

		parking = new ParkingTestDataBuilder().withTipoVehiculo(1).build();

		assertEquals(6000.0f, businessLogic.obtenerValorPagar(28, parking.getTipoVehiculo(), parking.getCilindraje()), 0.0f);
	}

	@Test
	public void valorPagarMoto7HorasCilindrajeMayor500() {

		parking = new ParkingTestDataBuilder().withTipoVehiculo(1).withCilindraje(600).build();

		assertEquals(5500.0f, businessLogic.obtenerValorPagar(7, parking.getTipoVehiculo(), parking.getCilindraje()), 0.0f);
	}

	@Test
	public void valorPagarMoto13HorasCilindrajeMayor500() {

		parking = new ParkingTestDataBuilder().withTipoVehiculo(1).withCilindraje(600).build();

		assertEquals(6000.0f, businessLogic.obtenerValorPagar(13, parking.getTipoVehiculo(), parking.getCilindraje()), 0.0f);
	}

	@Test
	public void valorPagarMoto28HorasCilindrajeMayor500() {

		parking = new ParkingTestDataBuilder().withTipoVehiculo(1).withCilindraje(600).build();

		assertEquals(8000.0f, businessLogic.obtenerValorPagar(28, parking.getTipoVehiculo(), parking.getCilindraje()), 0.0f);
	}

	// Test obtenerPropertiesByName

	@Test (expected = VigilanteExcepcion.class)
	public void obtenerPropertiesByNameParametroNull() {
		businessLogic.obtenerPropertiesByName("");

	}
	
	@Test (expected = VigilanteExcepcion.class)
	public void obtenerPropertiesByNameNoExiste() {
		businessLogic.obtenerPropertiesByName("xxxx");
	}
	
	@Test
	public void obtenerPropertiesByNameAutos() {
		assertNotNull(businessLogic.obtenerPropertiesByName(AUTOS));
	}
	
	@Test
	public void obtenerPropertiesByNameMotos() {
		assertNotNull(businessLogic.obtenerPropertiesByName(MOTOS));
	}
	
	
	@Test (expected = VigilanteExcepcion.class)
	public void actualizarPropertiesByNameVacioValorOK() {
		businessLogic.actualizarPropertiesByName("", "5" );
	}
	

	@Test (expected = VigilanteExcepcion.class)
	public void actualizarPropertiesByNameOKValorVacio() {
		businessLogic.actualizarPropertiesByName(AUTOS, "" );
	}
	
	@Test
	public void actualizarPropertiesByNameOKValorOK() {
		businessLogic.actualizarPropertiesByName(MOTOS, "7" );
	}
	
	@Test (expected = VigilanteExcepcion.class)
	public void actualizarPropertiesByNameNoexisteValorOK() {
		businessLogic.actualizarPropertiesByName("xxxx", "7" );
	}
	
	

	

}
