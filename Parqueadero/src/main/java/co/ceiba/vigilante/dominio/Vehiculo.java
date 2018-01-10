package co.ceiba.vigilante.dominio;

public class Vehiculo {

	private String placa;
	private int tipo;
	private int cilindraje;

	public Vehiculo() {
		super();
	}

	public Vehiculo(String placa, int tipo) {
		this.placa = placa;
		this.tipo = tipo;
	}

	public Vehiculo(String placa, int tipo, int cilindraje) {
		this.placa = placa;
		this.tipo = tipo;
		this.cilindraje=cilindraje;
	}
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	@Override
	public String toString() {
		return "Vehiculo [placa=" + placa + ", tipo=" + tipo + ", cilindraje=" + cilindraje + "]";
	}

}
