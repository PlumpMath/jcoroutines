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
class ConsumerDecorator implements Consumer, ContextAction
{
    @NonNull
    private final Coroutine<Producer> coroutineConsuming;

    @NonNull
    @Getter
    private final Map<String, Object> model;
    @Setter
    private ProducerDecorator context;

    @Getter
    private boolean consume;

    @Override
    public void produced()
    {
        consume = true;
    }

    @Override
    public boolean execute()
    {
        coroutineConsuming.run(context);
        consume = false;
        return context.isProduce();
    }
}
