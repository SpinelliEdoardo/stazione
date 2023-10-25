public class Stazione extends Thread {
    public volatile boolean interrupt = false;
    public boolean passaggioTreno = false;
    private volatile boolean sborra = false; //false=alzata , true=abbassata
    private volatile boolean semaforo = false; //false=verde , true=rosso

    public boolean isSborra() {
        return sborra;
    }

    public void abbassaSbarra() {
        sborra = true;
        semaforo = true;
        System.out.println("la sbarra si e' abbassata e il semaforo e' diventato rosso");
    }

    public void alzaSbarra() {
        sborra = false;
        semaforo = false;
        System.out.println("la sbarra si e' alzata e il semaforo e' diventato verde e quindi si puo attraversare fiche' non arriva un altro treno");
    }

    @Override
    public void run() {
        while (!interrupt) {
            if (passaggioTreno) {
                try {
                    System.out.println("si sta abbassando la sbarra");
                    Thread.sleep(1000);
                    abbassaSbarra();


                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    //ignored
                }
                alzaSbarra();
                passaggioTreno = false;
            }
        }
    }
}