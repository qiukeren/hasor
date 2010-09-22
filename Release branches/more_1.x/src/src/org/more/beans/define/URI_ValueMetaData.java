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
package org.more.beans.define;
import java.net.URI;
import org.more.beans.ValueMetaData;
import org.more.beans.ValueMetaData.PropertyMetaTypeEnum;
/**
 * ��ʾһ���������ݣ���Ӧ��PropertyMetaTypeEnum����Ϊ{@link PropertyMetaTypeEnum#URI}��
 * @version 2010-9-17
 * @author ������ (zyc@byshell.org)
 */
public class URI_ValueMetaData extends ValueMetaData {
    private URI uriObject = null; //��ʾ���ӵ�url�ַ�����
    /**�÷������᷵��{@link PropertyMetaTypeEnum#URI}��*/
    public PropertyMetaTypeEnum getPropertyType() {
        return PropertyMetaTypeEnum.URI;
    }
    public URI getUriObject() {
        return this.uriObject;
    }
    /**���ñ�ʾ���ӵ�uri����*/
    public void setUriObject(URI uriObject) {
        this.uriObject = uriObject;
    }
}