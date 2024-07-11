/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qlangtech.tis.util;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qlangtech.tis.datax.IDataXNameAware;
import com.qlangtech.tis.extension.Descriptor;
import com.qlangtech.tis.extension.impl.PropValRewrite;
import com.qlangtech.tis.extension.impl.SuFormProperties.SuFormGetterContext;
import com.qlangtech.tis.plugin.ds.DataSourceFactory;
import com.qlangtech.tis.runtime.module.misc.IFieldErrorHandler.BizLogic;
import com.qlangtech.tis.runtime.module.misc.IMessageHandler;
import com.qlangtech.tis.runtime.module.misc.IPostContent;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.List;


/**
 * @author 百岁（baisui@qlangtech.com）
 * @date 2020/04/13
 */
public interface IPluginContext extends IMessageHandler, IDataXNameAware, IPostContent {


    public static IPluginContext namedContext(String collectionName) {
        if (StringUtils.isEmpty(collectionName)) {
            throw new IllegalArgumentException("param collectionName can not be empty");
        }
        return new IPluginContext() {
            @Override
            public JSONObject getJSONPostContent() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void executeBizLogic(BizLogic logicType, Context context, Object param) throws Exception {
                throw new UnsupportedOperationException();
            }

            @Override
            public List<IUploadPluginMeta> parsePluginMeta(String[] plugins, boolean useCache) {
                return Collections.emptyList();
            }

            @Override
            public Pair<Boolean, IPluginItemsProcessor> getPluginItems(
                    IUploadPluginMeta pluginMeta, Context context, int pluginIndex, JSONArray itemsArray, boolean verify, PropValRewrite propValRewrite) {
                throw new UnsupportedOperationException();
            }

            @Override
            public String getExecId() {
                return null;
            }

            @Override
            public boolean isCollectionAware() {
                return true;
            }

            @Override
            public boolean isDataSourceAware() {
                return false;
            }

            @Override
            public void addDb(Descriptor.ParseDescribable<DataSourceFactory> dbDesc, String dbName, Context context,
                              boolean shallUpdateDB) {
            }

            @Override
            public String getRequestHeader(String key) {
                return null;
            }

            @Override
            public String getCollectionName() {
                return collectionName;
            }

            @Override
            public void errorsPageShow(Context context) {

            }

            @Override
            public void addActionMessage(Context context, String msg) {

            }

            @Override
            public void setBizResult(Context context, Object result, boolean overwriteable) {

            }

            @Override
            public void addErrorMessage(Context context, String msg) {

            }
        };
    }

    /**
     * 执行更新流程客户端会保存一个ExecId的UUID
     *
     * @return
     */
    String getExecId();


    public void executeBizLogic(BizLogic logicType, Context context, Object param) throws Exception;

    /**
     * 是否和数据源相关
     *
     * @return
     */
    boolean isDataSourceAware();


    /**
     * TIS default implements: PluginAction.addDb()
     * 向数据库中新添加一条db的记录
     *
     * @param dbName
     * @param context
     */
    void addDb(Descriptor.ParseDescribable<DataSourceFactory> dbDesc, String dbName, Context context, boolean shallUpdateDB);


}
