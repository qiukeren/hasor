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
/**
 * 定义如果想要支持扩展的配置策略则需要实现该接口，
 * 可以通过该接口的getDefineConfig来获取有关定义上的一些额外扩展属性设置。
 * @version 2010-9-24
 * @author 赵永春 (zyc@byshell.org)
 */
public interface ExpandDefine {
    /**获取被扩展的目标对象。*/
    public Object getTarget();
}