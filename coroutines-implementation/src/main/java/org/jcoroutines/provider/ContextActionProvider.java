package org.jcoroutines.provider;

import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jcoroutines.api.ContextAction;
import org.jcoroutines.api.CoroutinePair;

import java.util.Map;

/**
 * Created by Darek on 2015-12-05.
 */
@RequiredArgsConstructor
public class ContextActionProvider
{
    @NonNull
    private final CoroutinePair coroutinePair;

    public ContextAction provide()
    {
        Map<String, Object> model = Maps.newHashMap();
        val consumerDecorator = new ConsumerDecorator(coroutinePair.getConsumerRoutine(), model);
        val producerDecorator = new ProducerDecorator(coroutinePair.getProducerRoutine(), model);

        consumerDecorator.setContext(producerDecorator);
        producerDecorator.setContext(consumerDecorator);
        return producerDecorator;
    }
}
