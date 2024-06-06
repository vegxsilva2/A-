package Aestrella;

public class Tupla<X,Y> implements Comparable<Object> {
  private X x;
  private Y y;
  
  public Tupla(X x, Y y) {
	  this.x=x;
	  this.y=y;
  }


@Override
public int compareTo(Object o) {
	// TODO Auto-generated method stub
	if(o instanceof Tupla) {
		Tupla<?, ?> n=(Tupla<?,?>) o ;
		if(x==n.x) {
			if(n.y==y) {
				return 0;
			}else if((Integer) y > (Integer) n.y) {
				return 1;
			}else {
				return -1;
			}
		}else if((Integer) x > (Integer) n.x) {
			return 1;
		}else {
			return -1;
		}
	}else {
		throw new RuntimeException("Instancia fallida");
	}
	
	
}



public X getX() {
	return x;
}


public void setX(X x) {
	this.x = x;
}


public Y getY() {
	return y;
}


public void setY(Y y) {
	this.y = y;
}


public String toString() {
	return "("+x.toString()+","+y.toString()+") ";
	
}
}