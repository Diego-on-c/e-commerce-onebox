package e_commerce.services.deleteTaskSchedulerServices;

import e_commerce.domain.contracts.CartRepository;
import e_commerce.domain.models.CartId;

public class DeleteCartTask implements Runnable {
    private final long start;
    private final CartId cartId;
    private long execStart;

    private final CartRepository cartRepository;

    public DeleteCartTask(CartId cartId, CartRepository cartRepository) {
        this.start = System.currentTimeMillis();
        this.cartId = cartId;
        this.cartRepository = cartRepository;
    }

    @Override
    public void run() {
        execStart = System.currentTimeMillis();
        cartRepository.deleteCartById(cartId);
        printTaskInfo();
    }

    private void printTaskInfo() {
        String info = "Key -> " + cartId.value + " - deleted after: " + (execStart - start) / 1000 + " seg.";
        System.out.println(info);
    }
}
