package org.jcoroutines.api;

/**
 * Created by Darek on 2015-12-05.
 */
public interface Coroutine<T extends Context>
{
    void run(T context);
}
