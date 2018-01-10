package co.ceiba.vigilante.businesslogic.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.vigilante.businesslogic.BusinessLogic;
import co.ceiba.vigilante.excepcion.VigilanteExcepcion;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BusinessLogicTest {

	
	@Autowired
	BusinessLogic businessLogic;
	
	
	//Pruebas para metodo restriccionIngreso
	
	@Test (expected = VigilanteExcepcion.class)
	public void placaNula() {
	  businessLogic.restriccionIngreso(null);	
	}
	
		
	@Test
	public void placaNoNulaConRestriccion() {
	  assertTrue(businessLogic.restriccionIngreso("A125236"));	
	}
	
	@Test
	public void placaNoNulaSinRestriction() {
	  assertFalse(businessLogic.restriccionIngreso("125236"));	
	}

}
