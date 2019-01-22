package com.pgkk.data.api;


import com.pgkk.data.model.CaiPu;

import rx.Observable;

/**
 * Created by tanxueze on 2017/12/7.
 */

public interface CaipuApi {

    Observable<CaiPu> lingdata(String fileName);

}
