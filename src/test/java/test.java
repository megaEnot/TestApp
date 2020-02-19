public class test {




    public static void main(String[] args) {
        int loc = 0;

        Thread myThready3 = new Thread(new Runnable() {
            public void run() //Этот метод будет выполняться в побочном потоке
            {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("Поток 3, i = " + i);

                }
            }
        });
        myThready3.start();
    }
}
