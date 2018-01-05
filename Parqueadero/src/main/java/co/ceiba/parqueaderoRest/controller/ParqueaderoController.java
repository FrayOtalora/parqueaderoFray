package co.ceiba.parqueaderoRest.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parqueaderoRest.dominio.Auto;
import co.ceiba.parqueaderoRest.dominio.Moto;
import co.ceiba.parqueaderoRest.service.ParqueaderoService;

@RestController
public class ParqueaderoController {

	@Autowired
	ParqueaderoService parqueaderoService;
	
/*Metodo encargado del ingreso de los vehiculos al parqueadero
 * @param  ingresa objeto de tipo Auto 
 * @return mensaje con datos de ingreso
 */
	@RequestMapping(value = "/ingresarAuto", method = RequestMethod.POST)
	public String ingresarAuto(@RequestBody Auto auto) throws Exception {
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("parqueadero.properties"));
			int cantidadAutos = Integer.parseInt(p.getProperty("autos"));
			if (cantidadAutos < 21) {
				if (parqueaderoService.ingresarAuto(auto)){
					p.setProperty("autos",String.valueOf(++cantidadAutos));
					p.store(new FileOutputStream("parqueadero.properties"), null);
					return auto.toString();
					}
				else {
					return "Ocurrio un error al ingresar el vehiculo al parqueadero";
				}
			}

			return "NO HAY MAS CUPO";
		} catch (Exception e) {
			return "ocurrio una error. Excepcion: " + e.getMessage();
		}

	}

	@RequestMapping(value = "/ingresarMoto", method = RequestMethod.POST)
	public String ingresarMoto(@RequestBody Moto moto) throws Exception {
		if (parqueaderoService.ingresarMoto(moto)) {
			return "La moto es superor a 500 cc";
		}
		return "La moto es menor a 500 cc";
	}

	@RequestMapping(value = "/sacarAuto", method = RequestMethod.POST)
	public String retirarAuto(@RequestBody Auto auto) {
		return "Greetings from Spring Boot vvvvvvv!";
	}

	@RequestMapping(value = "/sacarMoto", method = RequestMethod.POST)
	public String retirarMoto(@RequestBody Moto moto) {
		return "Greetings from Spring Boot vvvvvvv!";
	}

}
