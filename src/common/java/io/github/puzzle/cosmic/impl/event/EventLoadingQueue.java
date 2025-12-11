package io.github.puzzle.cosmic.impl.event;

import com.badlogic.gdx.utils.Queue;
import net.neoforged.bus.api.Event;

public class EventLoadingQueue extends Event {

    private final Queue<Runnable> loadingQueue;

    public EventLoadingQueue(Queue<Runnable> loadingQueue) {
        this.loadingQueue = loadingQueue;
    }

    public void registerToQueue(Runnable runnable) {
        this.loadingQueue.addLast(runnable);
    }


}
