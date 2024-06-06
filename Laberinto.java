package Aestrella;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class Laberinto {
	String sFichero = "salida.txt";
	File fichero = new File(sFichero);
    
	public static int FILAS=60;
    public static int COLUMNAS=80;
	public char[][] matriz= new char[FILAS][COLUMNAS];
	private static int nElementos=FILAS*COLUMNAS;
	private static int nObstaculos=(int) (0.3*nElementos); 
	private static Random r=new Random();
	public int fInicial, cInicial, fObj,cObj;

	
	public Laberinto() {
		rellenarObstaculos();
		rellenarEstadoInicial();
		rellenarEstadoObjetivo();
	}
		

	private void rellenarEstadoObjetivo() {
		// TODO Auto-generated method stub
		boolean res=false;
		while(!res){
			int f=r.nextInt(FILAS);
			int c=r.nextInt(COLUMNAS);
			if(matriz[f][c]!='*' && matriz[f][c]!='I') {
				matriz[f][c]='O';
				res=true;
				fObj=f;
				cObj=c;
			}
			}
	}
		



	private void rellenarEstadoInicial() {
		// TODO Auto-generated method stub
		boolean res=false;
		while(!res){
			int f=r.nextInt(FILAS);
			int c=r.nextInt(COLUMNAS);
			if(matriz[f][c]!='*') {
				matriz[f][c]='I';
				res=true;
				fInicial=f;
				cInicial=c;
			}
			}
		
		
	}

	private void rellenarObstaculos() {
		//Random rF=new Random();
		//Random rC=new Random();
		boolean res=false;
		// TODO Auto-generated method stub
		for(int i=0;i<nObstaculos;i++) {
			res=false;
			while(!res){
			int f=r.nextInt(FILAS);
			int c=r.nextInt(COLUMNAS);
			if(matriz[f][c]!='*') {
				matriz[f][c]='*';
				res=true;
			}
			}
		}
		
		
	}


	public boolean esObstaculo(int f, int c) {
		// TODO Auto-generated method stub
		return matriz[f][c]=='*';
	}
	
	public void mostrar() throws IOException {
		BufferedWriter bw=new BufferedWriter(new FileWriter(sFichero));
		for (int i = 0; i < FILAS; i++) {
	        for (int j = 0; j < COLUMNAS; j++) {
	        	bw.write(matriz[i][j]+" ");
	            System.out.print(matriz[i][j]+" ");
	        }
	        bw.write("\n");
	        System.out.print("\n");
	    }
		bw.close();
	}

}
