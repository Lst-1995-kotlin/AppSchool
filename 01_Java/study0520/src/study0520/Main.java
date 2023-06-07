package study0520;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Child obj = new Child();
	     System.out.println(obj.getX());
	}

}


class Parent {
    int x = 100;
 
    Parent() {
        this(500);
    }
 
    Parent(int x) {
        this.x = x;
    }
 
    int getX() {
        return x;
    }
}
 
class Child extends Parent {
    int x = 4000;
    
    Child() {
        this(5000);
    }
 
    Child(int x) {
        this.x = x;
    }
}