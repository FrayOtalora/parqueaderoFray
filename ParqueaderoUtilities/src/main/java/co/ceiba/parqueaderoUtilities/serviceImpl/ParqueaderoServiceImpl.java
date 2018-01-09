package co.ceiba.parqueaderoUtilities.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import co.ceiba.parqueaderoUtilities.dominio.ConfiSys;
import co.ceiba.parqueaderoUtilities.service.ParqueaderoService;

@Service
public class ParqueaderoServiceImpl implements ParqueaderoService {

	@Override
	public boolean obtenerConfisys(ConfiSys confisys) throws Exception{
		
		LocalDateTime ingreso= LocalDateTime.now();
		LocalDateTime salida= LocalDateTime.now().plusHours(7);
		
		if(ingreso.getHour()-salida.getHour()>5) 
		System.out.println("La hora de entrada fue: "+ ingreso.getHour()+":"+ingreso.getMinute()+ "hora de salida: "+salida.getHour()+":"+salida.getMinute());
		
		else {
			System.out.println("es mayor a 5 horas");
		}
		
		return true;
	}
}