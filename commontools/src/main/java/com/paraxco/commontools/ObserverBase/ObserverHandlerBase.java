package com.paraxco.commontools.ObserverBase;


import com.paraxco.commontools.Utils.SmartLogger;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * to add a new observer jus make your class singletone and extend this class
 * use informObserverListInternal fun and customize global informs in your class
 *
 * @param <OBSERVER_TYPE>
 */
abstract public class ObserverHandlerBase<OBSERVER_TYPE> {

    ConcurrentHashMap<OBSERVER_TYPE, Boolean> observerList = new ConcurrentHashMap();
    LinkedList<ObserverListChangeListener<OBSERVER_TYPE>> observerListChangeListeners = new LinkedList<>();

    public int getObserversCount() {
        return observerList.size();
    }

    public void addObserver(OBSERVER_TYPE observer) {
        synchronized (this) {
            observerList.put(observer, true);
            callBackObserverListChangeListeners(true,observer);
            SmartLogger.logDebug("observerList size:" + observerList.size());
        }
    }

    public void removeObserver(OBSERVER_TYPE observer) {
        synchronized (this) {
            observerList.remove(observer);
            callBackObserverListChangeListeners(false,observer);
            SmartLogger.logDebug("observerList size:" + observerList.size());
        }
    }

    protected void informObserverListInternal(List<Object> data) {
        synchronized (this) {
            SmartLogger.logDebug("observerList size:" + observerList.size());

            for (Map.Entry<OBSERVER_TYPE, Boolean> entry : observerList.entrySet()) {
                informObserverInternal(entry.getKey(), data);
            }
        }
    }

    protected void informObserverListInternal(Object data) {
        List<Object> tempList = new LinkedList<>();
        tempList.add(data);
        informObserverListInternal(tempList);
    }

    private void callBackObserverListChangeListeners(boolean added, OBSERVER_TYPE observer) {
        for (ObserverListChangeListener observerListChangeListener : observerListChangeListeners)
            if (added)
                observerListChangeListener.observeAdded(observer, getObserversCount());
            else
                observerListChangeListener.observerRemoved(observer, getObserversCount());
    }

    public void addObserverChangeListener(ObserverListChangeListener<OBSERVER_TYPE> observerListChangeListener) {
        observerListChangeListeners.add(observerListChangeListener);

    }

    public void removeObserverChangeListener(ObserverListChangeListener<OBSERVER_TYPE> observerListChangeListener) {
        observerListChangeListeners.remove(observerListChangeListener);

    }

    /**
     * data is list of parameters needed for calling observer
     *
     * @param observe
     * @param data
     */
    abstract protected void informObserverInternal(OBSERVER_TYPE observe, List<Object> data);


    public interface ObserverListChangeListener<OBSERVER_TYPE> {
        void observeAdded(OBSERVER_TYPE observer, int observersCount);

        void observerRemoved(OBSERVER_TYPE observer, int observersCount);
    }

}
