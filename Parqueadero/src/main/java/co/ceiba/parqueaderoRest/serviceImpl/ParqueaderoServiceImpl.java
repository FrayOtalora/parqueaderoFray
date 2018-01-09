package co.ceiba.parqueaderoRest.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import co.ceiba.parqueaderoRest.dominio.Auto;
import co.ceiba.parqueaderoRest.dominio.Moto;
import co.ceiba.parqueaderoRest.service.ParqueaderoService;

@Service
public class ParqueaderoServiceImpl implements ParqueaderoService {

	@Override
	public boolean ingresarAuto(Auto auto) throws Exception {
		
		LocalDateTime ingreso= LocalDateTime.now();
		LocalDateTime salida= LocalDateTime.now().plusHours(7);
		
		if(ingreso.getHour()-salida.getHour()>5) 
		System.out.println("La hora de entrada fue: "+ ingreso.getHour()+":"+ingreso.getMinute()+ "hora de salida: "+salida.getHour()+":"+salida.getMinute());
		
		else {
			System.out.println("es mayor a 5 horas");
		}
		
		return true;
	}

	@Override
	public boolean ingresarMoto(Moto moto) throws Exception {

		if (moto.getCilindraje() > 500) {
			return true;
		}

		return false;
	}

	@Override
	public boolean retirarAuto(Auto auto) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean retirarMoto(Moto moto) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}
