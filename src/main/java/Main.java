class Parent {
    int x, y;

    Parent(int x, int y) {
        this.x=x;
        this.y=y;
    }

    int getT() {
        return x*y;
    }
}



class Child extends Parent {
    int x;

    Child (int x) {
        super(x+1, x);
        this.x=x;
    }
    int getT(int n){
        return super.getT()+n;
    }
}



class Main {
    public static void main(String[] args) {
        Parent parent = new Child(3);
        System.out.println(parent.getT());
    }
}