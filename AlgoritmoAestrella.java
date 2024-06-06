package Aestrella;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class AlgoritmoAestrella {
	Laberinto lab;
	List<Tupla<Integer,Integer>> cerrados;
	Set<Tupla<Integer,Integer>> abiertos;
	Map<Tupla<Integer,Integer>,Tupla<Integer,Integer>> parent;
	Tupla<Integer,Integer> actual;
	int[][] g=new int[60][80];
	int[][] f=new int [60][80];
	Set<Tupla<Integer,Integer>> vecinos;
	


	public AlgoritmoAestrella() {
		incializarMatriz(g);
		incializarMatriz(f);
		lab=new Laberinto();
		g[lab.fInicial][lab.cInicial]=0;
		f[lab.fInicial][lab.cInicial]=g[lab.fInicial][lab.cInicial]+heuristica(lab.fInicial,lab.cInicial);
		cerrados=new ArrayList<>();
		abiertos=new TreeSet<>();
		parent = new TreeMap<>();
		actual=new Tupla<>(lab.fInicial,lab.cInicial);
		vecinos=new TreeSet<>();
		
	}
	
	public void incializarMatriz(int [][] matriz) {
		for(int i = 0;i<10;i++) {
			for(int j = 0;j<20;j++) {
				matriz[i][j]=0;
			}
		}
	}
	
	public void rellenarSol(List<Tupla<Integer,Integer>> l) {
		if(l!=null) {
		for(Tupla<Integer,Integer> n: l) {
			if(lab.matriz[n.getX()][n.getY()]!='I' || lab.matriz[n.getX()][n.getY()]!='O' ) {
			lab.matriz[n.getX()][n.getY()]='+';
		}
	}
	}else {
		System.out.println("Camino no posible");
	}
	}
	

	public List<Tupla<Integer,Integer>> Algoritmo() {
		abiertos.add(actual);
		while(!abiertos.isEmpty()) {
			actual=mejorAbiertos(f,abiertos);
			if(actual.getX()==lab.fObj && actual.getY()==lab.cObj) {
				return reconstruirCamino(parent,actual);
			}
			abiertos.remove(actual);
			cerrados.add(actual);
			sucesores(lab,vecinos,actual);
			for(Tupla<Integer,Integer> n : vecinos) {
				if(!cerrados.contains(n)) {
					int tent=g[actual.getX()][actual.getY()]+(Math.abs(actual.getX()-n.getX())+Math.abs(actual.getY()-n.getY()));
					if(!abiertos.contains(n) || tent<g[n.getX()][n.getY()]) {
						parent.put(n, actual);
						g[n.getX()][n.getY()]=tent;
						f[n.getX()][n.getY()]=g[n.getX()][n.getY()]+heuristica(n.getX(),n.getY());
						if(!abiertos.contains(n)) {
							abiertos.add(n);
						}
					}
				}
			}
		
		}
		return null;
		
	}
	

	private Tupla<Integer, Integer> mejorAbiertos(int[][] f2, Set<Tupla<Integer, Integer>> abiertos2) {
		// TODO Auto-generated method stub
		int min=2147483647;
		Tupla<Integer,Integer> res = null;
		for(Tupla<Integer,Integer> t : abiertos2) {
			if(f[t.getX()][t.getY()]<min) {
				min=f[t.getX()][t.getY()];
				res=t;
			}
		}
		return res;
	}

	private List<Tupla<Integer,Integer>> reconstruirCamino(Map<Tupla<Integer, Integer>, Tupla<Integer, Integer>> parent2, Tupla<Integer,Integer> actual2) {
		// TODO Auto-generated method stub
		List<Tupla<Integer,Integer>> sol=new ArrayList<>();
		//sol.add(actual2);
		boolean encontrado=false;
		while(!encontrado) {
			
			actual2=parent2.get(actual2);
			if(actual2.getX()==lab.fInicial &&actual2.getY()==lab.cInicial) {
				encontrado=true;
			}else {
				sol.add(actual2);
			}
		}
		return sol;
	}


	private void sucesores(Laberinto lab2, Set<Tupla<Integer,Integer>> vecinos2,Tupla<Integer,Integer> actual2) {
		// TODO Auto-generated method stub
		int f,c;
		f=actual2.getX();
		c=actual2.getY();
		
		for(int i=f-1;i<=f+1;i=i+2) {
			if(valido(lab2,i,c)) {
				vecinos2.add(new Tupla<>(i,c));
			}
		}
		for(int j=c-1;j<=c+1;j=j+2) {
			if(valido(lab2,f,j)) {
				vecinos2.add(new Tupla<>(f,j));
			}
		}
		
	}

	private int heuristica(int i,int j) {
		return Math.abs(lab.fObj-i)-Math.abs(lab.cObj-j);
	}
	
	private boolean valido(Laberinto l,int f,int c) {
		return (f>0 && f<Laberinto.FILAS)&&(c>0 && c<Laberinto.COLUMNAS) && (!l.esObstaculo(f,c)) ;
	}
	
	
	

}
