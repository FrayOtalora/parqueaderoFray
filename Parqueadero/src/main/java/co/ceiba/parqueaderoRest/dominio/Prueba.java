package co.ceiba.parqueaderoRest.dominio;

import java.util.Calendar;
import java.util.Date;

public class Prueba {

	public static void main(String[]args) {
		
	   long milis1, milis2, diff;
		Calendar calFechaIngreso=Calendar.getInstance();
		Calendar calFechaRetiro=Calendar.getInstance();
		
		calFechaIngreso.setTime(new Date());
		calFechaRetiro.setTime(new Date());
		
		calFechaRetiro.set(2018, 0, 10,18,30,15);
		
		
		System.out.println(calFechaIngreso.getTime());
		System.out.println(calFechaRetiro.getTime());
		

        milis1 = calFechaIngreso.getTimeInMillis();

        milis2 = calFechaRetiro.getTimeInMillis();


        diff = milis2-milis1;


        // calcular la diferencia en segundos

    long diffSegundos =  Math.abs (diff / 1000);


    // calcular la diferencia en minutos

    long diffMinutos =  Math.abs (diff / (60 * 1000));

    
    long restominutos = diffMinutos%60;



    // calcular la diferencia en horas

    long diffHoras =   (diff / (60 * 60 * 1000));



    // calcular la diferencia en dias

    long diffdias = Math.abs ( diff / (24 * 60 * 60 * 1000) );


    
    System.out.println("En segundos: " + diffSegundos + " segundos.");

    System.out.println("En minutos: " + diffMinutos + " minutos.");

    System.out.println("En horas: " + diffHoras + " horas.");

    System.out.println("En dias: " + diffdias + " dias.");
    

    
System.out.println(String.valueOf(diffHoras + "H " + restominutos + "m "));

if(restominutos>0) {
	++diffHoras;
}

boolean hayHoras = true;
float valor = 0;
int p=0;
System.out.println("hora total="+diffHoras);
while (hayHoras) {

	if (diffHoras <= 9) {
			valor += diffHoras * 1000;
			hayHoras = false;
	}
	
	else if (diffHoras>9 && diffHoras <= 24) {
				valor += 8000;
				hayHoras = false;
	} else {
			valor += 8000;
			diffHoras -= 24;
	}

         
   }

System.out.println("valor a pagar"+valor);
	
	}
}
