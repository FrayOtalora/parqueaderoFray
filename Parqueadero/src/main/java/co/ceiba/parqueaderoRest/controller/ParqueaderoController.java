package co.ceiba.parqueaderoRest.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parqueaderoRest.consumer.ParqueaderoConsumer;
import co.ceiba.parqueaderoRest.dominio.Auto;
import co.ceiba.parqueaderoRest.dominio.Moto;
import co.ceiba.parqueaderoRest.service.ParqueaderoService;
import co.ceiba.parqueaderoUtilities.dominio.ConfiSys;

@RestController
public class ParqueaderoController {

	@Autowired
	ParqueaderoService parqueaderoService;

	@Autowired
	ParqueaderoConsumer parqueaderoConsumer;

	/*
	 * Metodo encargado del ingreso de los vehiculos al parqueadero
	 * 
	 * @param ingresa objeto de tipo Auto
	 * 
	 * @return mensaje con datos de ingreso
	 */
	@RequestMapping(value = "/ingresarAuto", method = RequestMethod.POST)
	public String ingresarAuto(@RequestBody Auto auto) throws Exception {
		try {

			ConfiSys restriccionLetra = new ConfiSys("restriccionLetras", "A-R");
			ConfiSys limiteAutos = new ConfiSys("cantidadAutos", "20");

			try {
				// Obtiene el valor de la cantidad limite para el parqueo de autos
				// limiteAutos = parqueaderoConsumer.obtenerConfisysNombre(limiteAutos);
			} catch (Exception exc) {
				System.out.println(
						"Ocurrio un error al momento de consultar el confisys de limite de autos" + exc.getMessage());
				return "OCURRIO UN ERROR AL CONSULTAR EL CONFISYS";
			}

			try {
				// Obtiene las letras que tienen restriccion para ingresar lunes y domingo
				// restriccionLetra =
				// parqueaderoConsumer.obtenerConfisysNombre(restriccionLetra);
			} catch (Exception exc) {
				System.out.println("Ocurrio un error al momento de consultar el confisys de restriccion de letras"
						+ exc.getMessage());
				return "OCURRIO UN ERROR AL CONSULTAR EL CONFISYS";
			}

			// RECORRE PARA SABER SI LA PLCA CORRESPONDE A ALGUNA LETRA QUE TIENE
			// RESTRICCION
			if (this.restriccionIngreso(auto, restriccionLetra))
				return "EL VEHICULO TIENE RESTRICCION PARA LOS DIAS LUNES Y DOMINGO";

			// Se crea un properties para mantener la cantidad de autos que hay en el
			// momento parqueados
			Properties p = new Properties();
			p.load(new FileInputStream("parqueadero.properties"));
			int cantidadAutosParqueados = Integer.parseInt(p.getProperty("autos"));
			if (cantidadAutosParqueados < Integer.parseInt(limiteAutos.getValor())) {
				if (parqueaderoService.ingresarAuto(auto)) {
					p.setProperty("autos", String.valueOf(++cantidadAutosParqueados));
					p.store(new FileOutputStream("parqueadero.properties"), null);
					return "BIENVENIDO AL PARQUEADERO";
				} else {
					return "Ocurrio un error al ingresar el vehiculo al parqueadero";
				}
			}

			return "NO HAY MAS CUPO";
		} catch (

		Exception e) {
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

	private boolean restriccionIngreso(Auto auto, ConfiSys confisys) {

		String[] letras = confisys.getValor().split("-");
		if (letras.length > 0) {
			for (String ss : letras) {
				if (auto.getPlaca().charAt(0) == ss.charAt(0)
						&& (LocalDateTime.now().getDayOfWeek().name().equals("MONDAY")
								|| LocalDateTime.now().getDayOfWeek().name().equals("SUNDAY")))
					return true;
			}
		}
		return false;

	}

}
