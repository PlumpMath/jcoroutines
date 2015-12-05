package org.jcoroutines.runner;

import lombok.extern.java.Log;
import lombok.val;
import org.jcoroutines.api.Consumer;
import org.jcoroutines.api.Coroutine;
import org.jcoroutines.api.CoroutinePair;
import org.jcoroutines.api.Producer;
import org.jcoroutines.provider.ContextActionProvider;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Darek on 2015-12-05.
 */
@Log
public class CoroutineRunnerTest
{
    private CoroutineRunner coroutineRunner;

    private final Coroutine<Consumer> producerRoutine = (context) -> {
        log.info("produced");
        context.produced();
    };

    @Before
    public void setUp() throws Exception
    {
        val pair = CoroutinePair.builder().consumerRoutine(new ConsumerRoutine()).producerRoutine(producerRoutine).build();
        coroutineRunner = new CoroutineRunner(new ContextActionProvider(pair));
    }

    @Test
    public void testExecute() throws Exception
    {
        coroutineRunner.execute();
    }

    private static class ConsumerRoutine implements Coroutine<Producer>
    {
        @Override
        public void run(Producer context)
        {
            Map<String, Object> model = context.getModel();

            if (!model.containsKey("case"))
            {
                model.put("case", false);
            } else
            {
                return;
            }

            log.info("Consumed");
            context.consumed();
        }
    }

}