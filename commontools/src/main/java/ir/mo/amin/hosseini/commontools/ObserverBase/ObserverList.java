package ir.mo.amin.hosseini.commontools.ObserverBase;

import ir.mo.amin.hosseini.commontools.Observers.NetworkObserverHandler;

/**
 * Created by Amin on 03/12/2017.
 */

public class ObserverList {
    public static NetworkObserverHandler getNetworkObserverHandler(){
        return NetworkObserverHandler.getInstance();
    }
}
