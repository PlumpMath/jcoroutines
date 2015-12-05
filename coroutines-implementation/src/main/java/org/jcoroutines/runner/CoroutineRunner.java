package org.jcoroutines.runner;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jcoroutines.api.ContextAction;
import org.jcoroutines.provider.ContextActionProvider;

/**
 * Created by Darek on 2015-12-05.
 */

@AllArgsConstructor
public class CoroutineRunner
{
    @NonNull
    private ContextActionProvider contextActionProvider;

    public void execute()
    {
        ContextAction contextAction = contextActionProvider.provide();
        while (contextAction.execute())
        {
        }
    }
}
