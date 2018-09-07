package org.javabrain.util.structure;

/**
 * @author Fernando García
 * @version 0.0.1
 */
public class DataPersist {

    private Thread refresh;
    private int time;

    public DataPersist() {
        time = 300000;

        refresh = new Thread(new Runnable() {
            public void run() {
                try {
                    while(true){
                        beforePetition();
                        petition();
                        afterPetition();
                        Thread.sleep(time);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        startRefresh();
    }

    public void petition() { }

    public void afterPetition() {}

    public void beforePetition() {}

    public void startRefresh(){
        refresh.start();
    }

    public void stopRefresh(){
        refresh.stop();
    }

    public void setTime(int time){
        this.time = time;
        refresh.stop();
        refresh = new Thread(new Runnable() {
            public void run() {
                try {
                    while(true){
                        petition();
                        Thread.sleep(time);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        refresh.start();
    }
}
