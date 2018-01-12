package co.ceiba.vigilante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.vigilante.dominio.Parking;
import co.ceiba.vigilante.service.VigilanteService;

@RestController
public class VigilanteController {

	@Autowired
	private VigilanteService vigilanteService;

	/*
	 * Metodo encargado del ingreso de los vehiculos al Vigilante
	 * @param ingresa objeto de tipo Auto
	 * @return mensaje con datos de ingreso
	 */
	@RequestMapping(value = "/ingresarVehiculo", method = RequestMethod.POST)
	public void ingresarVehiculo(@RequestBody Parking parking){
		vigilanteService.ingresarVehiculo(parking);
	}

	@RequestMapping(value = "/retirarVehiculo", method = RequestMethod.POST)
	public Parking retirarVehiculo(@RequestBody String placa){ 
		return vigilanteService.retirarVehiculo(placa); 
	}

	@RequestMapping(value = "/registrarPagoVehiculo", method = RequestMethod.POST)
	public void registrarPagoVehiculo(@RequestBody Parking parking) {
		vigilanteService.registrarPagoVehiculo(parking);
	}

}
