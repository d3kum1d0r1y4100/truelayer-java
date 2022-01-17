package truelayer.java.http.interceptors;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.UUID;

import static truelayer.java.Constants.HeaderNames.IDEMPOTENCY_KEY;

public class IdempotencyKeyInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        var request = chain.request();
        var newRequest = request.newBuilder()
                .header(IDEMPOTENCY_KEY, UUID.randomUUID().toString())
                .build();

        return chain.proceed(newRequest);
    }
}