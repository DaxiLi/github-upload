package studentGUI;

public class MyDate {
        int y;
        int m;
        int d;
        MyDate(){
            this(1970,1,1);
        }
        MyDate(int y,int m,int d){
            this.y = y;
            this.m = m;
            this.d = d;
        }

    @Override
    public String toString() {
        return "MyDate{" +
                "y=" + y +
                ", m=" + m +
                ", d=" + d +
                '}';
    }

    public int getD() {
        return d;
    }

    public int getM() {
        return m;
    }

    public int getY() {
        return y;

    }

    public void setD(int d) {
        this.d = d;
        System.out.println(d);
    }

    public void setM(int m) {
        this.m = m;
        System.out.println(m);
    }

    public void setY(int y) {
        this.y = y;
        System.out.println(y);
    }
}
