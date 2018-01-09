package co.ceiba.parqueaderoRest.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parqueaderoRest.repository.ParqueaderoRepository;
import co.ceiba.parqueaderoRest.service.ParqueaderoService;
import co.ceiba.parqueaderoRest.dominio.Parking;
import co.ceiba.parqueaderoRest.dominio.Vehiculo;

@Service
public class ParqueaderoServiceImpl implements ParqueaderoService {

	@Autowired
	ParqueaderoRepository parqueaderoRepository;
	
	@Override
	public boolean ingresarVehiculo(Parking parking) throws Exception {

		try {
		return parqueaderoRepository.ingresarAuto(parking);
		
		}catch(Exception e) {
			System.out.println("Error al guardar en base de datos");
			return false;
		}
		
		
	}



	@Override
	public boolean registrarPagoVehiculo(Parking parking) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Parking retirarVehiculo(Vehiculo vehiculo) throws Exception {
		Parking park=new Parking(vehiculo,new Date());
		park.setFechaRetiro(new Date());
		
		return park;
	}




}
