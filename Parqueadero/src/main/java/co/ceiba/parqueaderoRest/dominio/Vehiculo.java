package co.ceiba.parqueaderoRest.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Vehiculo")
public class Vehiculo {

	@JsonProperty("placa")
	private String placa;

	public Vehiculo() {
		super();
	}

	public Vehiculo(String placa) {
		super();
		this.placa = placa;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	@Override
	public String toString() {
		return "Vehiculo [placa=" + placa + "]";
	}
	
	

}
