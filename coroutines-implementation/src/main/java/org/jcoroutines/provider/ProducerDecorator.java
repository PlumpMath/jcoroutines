package org.jcoroutines.provider;

import lombok.*;
import org.jcoroutines.api.Consumer;
import org.jcoroutines.api.ContextAction;
import org.jcoroutines.api.Coroutine;
import org.jcoroutines.api.Producer;

import java.util.Map;

/**
 * Created by Darek on 2015-12-05.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ProducerDecorator implements Producer, ContextAction
{
    @NonNull
    private final Coroutine<Consumer> coroutine;
    @NonNull
    @Getter
    private final Map<String, Object> model;
    @Setter
    private ConsumerDecorator context;
    @Getter
    private boolean produce;

    @Override
    public void consumed()
    {
        produce = true;
    }

    @Override
    public boolean execute()
    {
        coroutine.run(context);
        produce = false;
        if (context.isConsume())
        {
            return context.execute();
        }
        return false;
    }
}
