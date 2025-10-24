package com.example.otodiensale.work;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.example.otodiensale.util.CartManager;
import com.example.otodiensale.util.NotificationHelper;

public class CartBadgeWorker extends Worker {
    public CartBadgeWorker(@NonNull Context context, @NonNull WorkerParameters params) { super(context, params); }

    @NonNull
    @Override
    public Result doWork() {
        int count = CartManager.getInstance().getCount();
        if (count > 0) NotificationHelper.showCartNotification(getApplicationContext(), count);
        return Result.success();
    }
}
