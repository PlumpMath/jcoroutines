package org.jcoroutines.api;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * Created by Darek on 2015-12-05.
 */
@Builder
public class CoroutinePair
{
    @NonNull
    @Getter
    private final Coroutine<Consumer> producerRoutine;

    @NonNull
    @Getter
    private final Coroutine<Producer> consumerRoutine;
}
