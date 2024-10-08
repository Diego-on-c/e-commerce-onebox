package e_commerce.services.deleteTaskSchedulerServices;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DeleteCartAfterTTLService {

    private final ScheduledExecutorService scheduler;
    private final Long ttl; //10 minutes in milliseconds

    public DeleteCartAfterTTLService(Long ttl) {
        this.scheduler = new ScheduledThreadPoolExecutor(1);
        this.ttl = ttl;
    }

    public void scheduleTask(DeleteCartTask task) {
        scheduler.schedule(task, ttl, TimeUnit.MILLISECONDS);
    }
}
