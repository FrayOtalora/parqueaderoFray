package co.ceiba.vigilante.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.vigilante.dominio.Confisys;
import co.ceiba.vigilante.dominio.Parking;
import co.ceiba.vigilante.excepcion.VigilanteExcepcion;
import co.ceiba.vigilante.repository.ConfisysRepository;
import co.ceiba.vigilante.repository.VigilanteRepository;
import co.ceiba.vigilante.service.VigilanteService;
import co.ceiba.vigilante.testdatabuilder.ParkingTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceImplTest {

	Parking parkingIngreso, parkingRetiro, parkingPago;
	Confisys confisys;

	@Autowired
	VigilanteService vigilanteService;

	@Autowired
	ConfisysRepository confisysRepository;

	@Autowired
	VigilanteRepository vigilanteRepository;

	@Before
	public void inicializar() {
		parkingIngreso = new ParkingTestDataBuilder().withPlaca("R12525").build();
		parkingRetiro = new ParkingTestDataBuilder().withPlaca("123456").withFechaIngreso(new Date()).build();
		parkingPago = new ParkingTestDataBuilder().withPlaca("PAGO").withCilindraje(700).withTipoVehiculo(1).withFechaIngreso(new Date())
				.withFechaSalida(new Date()).withValorPago(10000).build();
		
		
	}

	@Test
	public void inicia() {
		
		vigilanteRepository.deleteAll();
		confisysRepository.deleteAll();
		confisys = new Confisys("motos", "10");
			confisysRepository.save(confisys);
		confisys = new Confisys("autos", "20");
			confisysRepository.save(confisys);		
		confisys = new Confisys("restriccionPlaca", "A");
			confisysRepository.save(confisys);
			
		vigilanteRepository.save(parkingRetiro);
		vigilanteRepository.save(parkingPago);

	}

	@Test(expected = VigilanteExcepcion.class)
	public void ingresarVehiculoNull() {
		vigilanteService.ingresarVehiculo(null);
	}

	@Test
	public void ingresarVehiculoAuto() {
		vigilanteService.ingresarVehiculo(parkingIngreso);
	}

	@Test
	public void ingresarVehiculoMoto() {

		parkingIngreso = new ParkingTestDataBuilder().withPlaca("55555").withTipoVehiculo(1).build();
		vigilanteService.ingresarVehiculo(parkingIngreso);
	}

	@Test (expected= VigilanteExcepcion.class)
	public void ingresarVehiculDiferente() {
		parkingIngreso = new ParkingTestDataBuilder().withPlaca("55555").withTipoVehiculo(11111).build();
		vigilanteService.ingresarVehiculo(parkingIngreso);
	}
	
	@Test
	public void ingresarVehiculoMotoMayor500() {
		parkingIngreso = new ParkingTestDataBuilder().withPlaca("7777").withTipoVehiculo(1).withCilindraje(700).build();
		vigilanteService.ingresarVehiculo(parkingIngreso);
	}

	@Test
	public void retirarVehiculoOK() {
		assertNotNull(vigilanteService.retirarVehiculo(parkingRetiro.getPlaca()));
	}

	@Test(expected = VigilanteExcepcion.class)
	public void retirarVehiculoNoExiste() {
		parkingRetiro.setPlaca("xxxx");
		vigilanteService.retirarVehiculo(parkingRetiro.getPlaca());
	}

	@Test(expected = VigilanteExcepcion.class)
	public void registrarPagoVehiculoNull() {
		vigilanteService.registrarPagoVehiculo(null);
	}

	@Test
	public void registrarPagoVehiculoOK() {
		vigilanteService.registrarPagoVehiculo(parkingPago);
	}

}
