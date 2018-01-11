package co.ceiba.vigilante.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name="parking")
@Table(name="parking")
public class Parking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 615979173739091224L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String placa;
	private int tipoVehiculo;
	private int cilindraje;
	private Date fechaIngreso;
	private Date fechaSalida;
	private float valorPago;

	public Parking() {

	}
	
	public Parking(String placa, int tipoVehiculo, int cilindraje) {
		this.placa=placa;
		this.tipoVehiculo=tipoVehiculo;
		this.cilindraje=cilindraje;
	}

	public Parking(int id, String placa, int tipoVehiculo, int cilindraje, Date fechaIngreso, Date fechaSalida, float valorPago) {
		super();
		this.id = id;
		this.placa=placa;
		this.tipoVehiculo=tipoVehiculo;
		this.cilindraje=cilindraje;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valorPago = valorPago;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public float getValorPago() {
		return valorPago;
	}

	public void setValorPago(float valorPago) {
		this.valorPago = valorPago;
	}

	
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(int tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	@Override
	public String toString() {
		return "Parking [id=" + id + ", placa=" + placa + ", tipoVehiculo=" + tipoVehiculo + ", cilindraje="
				+ cilindraje + ", fechaIngreso=" + fechaIngreso + ", fechaSalida=" + fechaSalida + ", valorPago="
				+ valorPago + "]";
	}



}
