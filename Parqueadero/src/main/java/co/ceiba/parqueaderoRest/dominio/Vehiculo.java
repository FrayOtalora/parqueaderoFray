package co.ceiba.parqueaderoRest.dominio;

public class Vehiculo {

	private String placa;
	private int tipo;
	private float cilindraje;

	public Vehiculo() {
		super();
	}

	public Vehiculo(String placa, int tipo) {
		this.placa = placa;
		this.tipo = tipo;
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

	public float getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(float cilindraje) {
		this.cilindraje = cilindraje;
	}

	@Override
	public String toString() {
		return "Vehiculo [placa=" + placa + ", tipo=" + tipo + ", cilindraje=" + cilindraje + "]";
	}

}
