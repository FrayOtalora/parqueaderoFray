package co.ceiba.parqueaderoRest.consumerImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.ceiba.parqueaderoRest.consumer.ParqueaderoConsumer;
import co.ceiba.parqueaderoUtilities.dominio.ConfiSys;

@Service
public class ParqueaderoConsumerImpl implements ParqueaderoConsumer{

	@Override
	public ConfiSys obtenerConfisysNombre(ConfiSys confisys) {
		 RestTemplate restTemplate = new RestTemplate();
	     confisys = restTemplate.postForObject("http://:localhost:8090/obtenerConfisys", confisys, ConfiSys.class);
	     return confisys;
	}

	
	
}
