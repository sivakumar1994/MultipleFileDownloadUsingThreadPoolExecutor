package com.filesdownloadthreadpoolexecutor;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

public class BaseViewModel<I> extends AndroidViewModel {

    private I iView;
    /**
     * Constructor for {@link BaseViewModel}.
     *
     * @param application Instance of application.
     */
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Method to show toast from extended view model.
     *
     * @param stringResourceId Id of {@link StringRes}
     */

    public void showToast(@StringRes int stringResourceId) {
        String toastMessage = ResourceHelper.getStringResource(stringResourceId);

        if (!TextUtils.isEmpty(toastMessage)) {
            Toast.makeText(MyApplication.getMyAppInstance(), toastMessage,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
