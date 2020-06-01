package com.cisyapp.rest.controlador;
//Clase que contiene metodos para diferentes usos:
public class Biblioteca {
	//Metodo que recibe dos Strings y ve cuantas letras en orden hay de uno en el otro, ejemplo 'Getafe','Geta', en el caso de que se supere la mitad de letras que contiene se dara como valido, y se devuelve un boolean:
	public static boolean compruebaSiContieneLocalidad(String a, String b){
		boolean prueba=false;	//Variable boolean que controlara el estado de la comprobacion.
		//Convertimos los Strings recibidos en mayusculas para tratarlos mas facil:
		a=a.toUpperCase();
		b=b.toUpperCase();
		//Comprobamos si el String 'a' contiene al String 'b':
		if(!prueba&&a.contains(b)) {
			//Si lo contiene, solo daremos como true la comprobacion siempre que el numero de letras sea mayor a la mitad, es decir que las letras que contiene el String 'a' de 'b' sean mas de la mitad que su propio tamanio:
			if(b.length()>a.length()/2) {
				prueba=true;
			}
		}//Si 'a' no contiene a 'b' lo comprobamos a la inversa:
		else if(!prueba&&b.contains(a)) {
			//Si lo contiene, solo daremos como true la comprobacion siempre que el numero de letras sea mayor a la mitad, es decir que las letras que contiene el String 'b' de 'a' sean mas de la mitad que su propio tamanio:
			if(a.length()>b.length()/2) {
				prueba=true;
			}
		}
		//Si el String no se contiene de ningun lado, comprobaremos cuantas letras tienen iguales y cuantas en orden e iguales:
		if(!prueba) {
			int contLetrasIguales=0;					//Contador que representa el numero de letras que tienen iguales las dos cadenas.
			int contLetrasIgualesBienPosicionadas=0;	//Contador que representa el numero de letras que tienen iguales y que comparten orden.
			//Segun la cadena mas larga calculamos con ella el numero de letras iguales que hay entre ellas:
			if(a.length()>b.length()) {
				char [] a_array = a.toCharArray();
				char [] b_array = b.toCharArray();
				for (int i = 0; i < a_array.length; i++) {
					for(int j = 0; j < b_array.length; j++) {
						if(a_array[i]==b_array[j]) {
							contLetrasIguales++;
							b_array[j]='\0';
						}
					}
				}
				//Una vez tenemos el numero de letras iguales entre cadenas, pasaremos a calcular el numero de letras que comparte un orden:
				String aux="";
				for(int i = 0 ; i < b.length() ; i++) {
					aux+=b.charAt(i)+"";
					if(a.contains(aux)) {
						if(aux.length()==2) {
							contLetrasIgualesBienPosicionadas+=2;
						}
						else if(aux.length()>2) {contLetrasIgualesBienPosicionadas++;}
					}else {
						if(aux.length()>1) {
							i--;
							a=a.replaceFirst(aux.substring(0, aux.length()-1), "");
						}
						aux="";
					}
				}
				//Pasemos a deducir si hacen match o no las cadenas una vez hemos rellenado los contadores:
				if(contLetrasIguales>=b.length()/2&&contLetrasIgualesBienPosicionadas>=b.length()/2&&contLetrasIgualesBienPosicionadas>=contLetrasIguales/2&&b.length()>=a.length()/2) {
					prueba=true;
				}
			}
			else {
				char [] b_array = b.toCharArray();
				char [] a_array = a.toCharArray();
				for (int i = 0; i < b_array.length; i++) {
					for(int j = 0; j < a_array.length; j++) {
						if(b_array[i]==a_array[j]) {
							contLetrasIguales++;
							a_array[j]='\0';
						}
					}
				}
				//Una vez tenemos el numero de letras iguales entre cadenas, pasaremos a calcular el numero de letras que comparte un orden:
				String aux="";
				for(int i = 0 ; i < a.length() ; i++) {
					aux+=a.charAt(i)+"";
					if(b.contains(aux)) {
						if(aux.length()==2) {
							contLetrasIgualesBienPosicionadas+=2;
						}
						else if(aux.length()>2) {contLetrasIgualesBienPosicionadas++;}
					}else {
						if(aux.length()>1) {
							i--;
							b=b.replaceFirst(aux.substring(0, aux.length()-1), "");
						}
						aux="";
					}
				}
				//Pasemos a deducir si hacen match o no las cadenas una vez hemos rellenado los contadores:
				if(contLetrasIguales>=a.length()/2&&contLetrasIgualesBienPosicionadas>=a.length()/2&&contLetrasIgualesBienPosicionadas>=contLetrasIguales/2&&a.length()>=b.length()/2) {
					prueba=true;
				}
			}
		}
		return prueba;	//Retornamos el resultado de la comprobacion.
	}
}
