package co.ceiba.vigilante.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.vigilante.dominio.Vehiculo;
import co.ceiba.vigilante.service.VigilanteService;
import co.ceiba.vigilante.testdatabuilder.VehiculoTestDataBuilder;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceImplTest {

	private Vehiculo vehiculo;
	
	@Autowired
	VigilanteService vigilanteService;
	
	@Before
	public void inicializar() {
		vehiculo=new VehiculoTestDataBuilder().withPlaca("R12525").build();
	}
	
	
@Test
public void ingresarVehiculo() {
	vigilanteService.ingresarVehiculo(vehiculo);
}

@Test
public void retirarVehiculo() {
	assertNotNull(vigilanteService.retirarVehiculo(vehiculo.getPlaca()));
}

}
