/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.more.bizcommon.log;
import org.more.bizcommon.Message;
import org.more.bizcommon.json.JSON;
import org.more.builder.ToStringBuilder;
import org.more.builder.ToStringStyle;
import org.more.util.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
/**
 * @version : 2016年1月10日
 * @author 赵永春(zyc@hasor.net)
 */
public class LogUtils {
    private static Logger     logger    = LoggerFactory.getLogger(LogUtils.class);
    private static Properties errorProp = new Properties();

    static {
        InputStream in = null;
        try {
            in = LogUtils.class.getResourceAsStream("/logger_messages.properties");
            errorProp.load(in);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    //
    //
    //
    private String errorCode;
    private Map<String, Object> map = new TreeMap<String, Object>();
    private LogUtils() {
    }
    private LogUtils(String errorCode) {
        if (errorCode != null) {
            this.errorCode = errorCode;
            put("errorCode", errorCode);
            Object value = errorProp.get(errorCode);
            put("errorCodeMsg", value);
        }
    }
    //
    //
    public static LogUtils create() {
        return new LogUtils();
    }
    public static LogUtils create(String errorCode) {
        if (errorCode != null && !errorProp.containsKey(errorCode)) {
            logger.error("not found errorCode = " + errorCode);
        }
        return new LogUtils(errorCode);
    }
    public static LogUtils createSMS() {
        return createSMS(null);
    }
    public static LogUtils createSMS(String errorCode) {
        return create(errorCode).addLog("NEED_SMS", "NEED_SMS");
    }
    //
    private void put(String key, Object value) {
        if (key != null && map.get(key) != null) {
            logger.error("duplicat key = " + key);
        }
        map.put(key, value);
        if (map.keySet().size() > 20) {
            logger.error("NEED_SMS_NOTIFY map.key.size = " + map.keySet().size());
        }
    }
    public LogUtils addString(String message) {
        if (message == null) {
            message = "valueIsNull";
        }
        put("message", message);
        return this;
    }
    public LogUtils addLog(String key, Object value) {
        if (value == null) {
            value = "valueIsNull";
        }
        put(key, value);
        return this;
    }
    public LogUtils addMessage(Message errorMsg) {
        if (errorMsg == null) {
            put("messageCode", 0);
            put("messageDesc", "errorMsg is null.");
        } else {
            put("messageCode", errorMsg.getType());
            put("messageDesc", errorMsg.getMessage());
        }
        return this;
    }
    public LogUtils logException(Throwable ex) {
        if (ex != null) {
            put("exceptionMsg", ex.getMessage());
        }
        return this;
    }
    /**
     * 注意, 仅是为了方便日志输出及查看, 本方法未进行html转义, 所以切不可输出到客户页面.
     */
    public String toJson() {
        return JSON.toString(map);
    }
    /**
     * 对于Json转换出的异常, 需要使用此方法. 以避免死循环
     */
    public String toStringBuilder() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> kv : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            String s = null;
            if (kv.getValue() == null) {
                s = "null";
            } else {
                if (kv.getValue() instanceof String || kv.getValue() instanceof Long || kv.getValue() instanceof Integer || kv.getValue() instanceof Double) {
                    s = String.valueOf(kv.getValue());
                } else {
                    s = ToStringBuilder.reflectionToString(kv.getValue(), ToStringStyle.SHORT_PREFIX_STYLE);
                }
            }
            sb.append("[" + kv.getKey() + "]=[" + s + "]");
        }
        return sb.toString();
    }
}