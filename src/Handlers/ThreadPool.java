package Handlers;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadPool extends ThreadGroup{
   
    private static IDAssigner poolID = new IDAssigner(1);
    private boolean running;
    private static LinkedList<Runnable> taskQueue;
    private int id;
    
    // Crear los Threads 
    public ThreadPool(int numThreads){
        super("ThreadPool- " + poolID.next());
        this.id = poolID.getCurrentId();
        // Mientras que el main siga activo, los demas hilos siguen activos
        setDaemon(true);
        taskQueue = new LinkedList();
        running = true;
        for(int i = 0; i < numThreads; i++){
            new PooledThread(this).start();
        }
    }
    
    // Se encarga de la jerarquia de las Tasks 
    public synchronized void runTask(Runnable task){
        if(!running) throw new IllegalStateException("ThreadPoo-" + id + " is dead");
        if(task != null){
            taskQueue.add(task);
            // Encuentra el Thread que este activo y le pasa este task
            notify();
        }
    }
    
    // Cerrar el Thread de manera abrupta
    public synchronized void close(){
        if(!running) return;
        running = false;
        taskQueue.clear();
        interrupt();
    }
    
    // Cerrrar pero dejando que cada Task termine su trabajo
    public void join(){
        synchronized(this){
            running = false;
            notifyAll();
        }
        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);
        
        for(int i = 0; i < count; i++){
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    protected synchronized Runnable getTask() throws InterruptedException{
        while(taskQueue.size() == 0){
            if(!running) return null;
            wait();
        }
        return taskQueue.remove(0);
    }
}
