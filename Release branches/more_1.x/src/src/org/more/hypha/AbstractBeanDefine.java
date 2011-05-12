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
package org.more.hypha;
import java.util.Collection;
import org.more.util.attribute.IAttribute;
/**
 * 该接口用于定义一个beans组建中的bean声明。
 * @version 2010-9-15
 * @author 赵永春 (zyc@byshell.org)
 */
public interface AbstractBeanDefine extends IAttribute {
    /**返回bean的唯一编号，如果没有指定id属性则id值将是fullName属性值。*/
    public String getID();
    /**返回bean的名称，如果指定了package属性那么name的值可以出现重复。*/
    public String getName();
    /**获取Bean的逻辑包定义，这个包定义与类的实际所处包不同。它表现为一个外在的逻辑管理形式。*/
    public String getPackage();
    /**获取包含package和bean名的虚拟类完整限定名。*/
    public String getFullName();
    /**获取bean被定义的bean的类型该类型与class类型是有区分的，class类型可以表述一个具体类型但是无法表述大分类。*/
    public String getBeanType();
    /**生成Bean所使用的生成器名，Fact，Ioc，User*/
    public String getBuildFactory();
    /**返回一个boolean值，表示类是否为一个抽象类。*/
    public boolean isAbstract();
    /**返回一个boolean值，表示类是否为一个接口。*/
    public boolean isInterface();
    /**返回一个boolean值，表示这个bean是否为单态的。*/
    public boolean isSingleton();
    /**返回一个boolean值，表示这个bean是否为延迟装载的。*/
    public boolean isLazyInit();
    /**返回bean的描述信息。*/
    public String getDescription();
    /**获取该类型bean的工厂方法。*/
    public AbstractMethodDefine factoryMethod();
    /**获取初始化方法名。*/
    public String getInitMethod();
    /**获取销毁方法名。*/
    public String getDestroyMethod();
    /**该属性是用来定义在bean上的一些方法，返回的集合是一个只读集合。*/
    public Collection<? extends AbstractMethodDefine> getMethods();
    /**
     * 该属性定义了当创建这个bean时候需要的启动参数。
     * 启动参数通常是指构造方法参数，对于工厂形式创建启动参数代表了工厂方法的参数列表。
     * 返回的集合是一个只读集合。
     */
    public Collection<? extends AbstractPropertyDefine> getInitParams();
    /**返回bean的定义属性集合，返回的集合是一个只读集合。*/
    public Collection<? extends AbstractPropertyDefine> getPropertys();
    /**返回具有特征的字符串。*/
    public String toString();
}