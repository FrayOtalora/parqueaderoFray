package co.ceiba.parqueaderoUtilities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parqueaderoUtilities.dominio.ConfiSys;
import co.ceiba.parqueaderoUtilities.service.ParqueaderoService;


@RestController
public class ParqueaderoController {

	@Autowired
	ParqueaderoService parqueaderoService;
	
/*Metodo encargado del ingreso de los vehiculos al parqueadero
 * @param  ingresa objeto de tipo Auto 
 * @return mensaje con datos de ingreso
 */
	@RequestMapping(value = "/obtenerConfiSys", method = RequestMethod.POST)
	public ConfiSys obtenerConfiSysNombre(@RequestBody ConfiSys confisys) throws Exception {
		try {
			confisys.setValor("20");
			return confisys;
		} catch (Exception e) {
			System.out.println("ocurrio una error. Excepcion: " + e.getMessage());
			return null;
		}

	}
}
