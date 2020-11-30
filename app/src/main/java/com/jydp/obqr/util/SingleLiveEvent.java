package com.jydp.obqr.util;


import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.jydp.obqr.App;
import com.jydp.obqr.dailog.IsoDialog;
import com.jydp.obqr.dailog.ShowIOSDialog;

import java.util.concurrent.atomic.AtomicBoolean;

public class SingleLiveEvent<T> extends MutableLiveData<T> {
    private static final String TAG = SingleLiveEvent.class.getSimpleName();
    private AtomicBoolean pending = new AtomicBoolean(false);

    @MainThread
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer<? super T> observer) {

        if (hasActiveObservers()) {
            Log.e("TAG", "Multiple observers registered but only one will be notified of changes.");
        }
        // Observe the internal MutableLiveData
        super.observe(owner, t -> {
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t);

            }
        });
    }

    @MainThread
    @Override
    public void setValue(T t) {
        pending.set(true);
        super.setValue(t);
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    public void call() {
        setValue(null);
    }
}
