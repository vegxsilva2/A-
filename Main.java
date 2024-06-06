package Aestrella;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		AlgoritmoAestrella a=new AlgoritmoAestrella();
	    a.rellenarSol(a.Algoritmo());
	    
	    
		a.lab.mostrar();
		
		
	}

}
