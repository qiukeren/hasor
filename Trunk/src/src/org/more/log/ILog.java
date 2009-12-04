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
package org.more.log;
/**
 * ��־Դ�ӿڡ�
 * Date : 2009-5-13
 * @author ������
 */
public interface ILog {
    /** ���Լ��� */
    public static final String LogLevel_Debug   = "Debug";
    /** ��Ϣ���� */
    public static final String LogLevel_Info    = "Info";
    /** ���󼶱� */
    public static final String LogLevel_Error   = "Error";
    /** ���漶�� */
    public static final String LogLevel_Warning = "Warning";
    /** ���м��� */
    public static final String LogLevel_ALL     = "ALL";
    /**
     * �Ե��Լ��������־��
     * @param msg �������־��
     */
    public void debug(String msg);
    /**
     * ����Ϣ���������־��
     * @param msg �������־��
     */
    public void info(String msg);
    /**
     * �Դ��󼶱������־��
     * @param msg �������־��
     */
    public void error(String msg);
    /**
     * �Ծ��漶�������־��
     * @param msg �������־��
     */
    public void warning(String msg);
    /**
     * ������־����������־��Ϣ��ʽ���齨��
     * @param formater ��־��Ϣ��ʽ���齨��
     */
    public void setFormater(ILogFormater formater);
    /**
     * ����־Դ�齨����һ����־�������
     * @param logwrite ��־�������
     * @param level Ҫ���õ���־����
     */
    public void addWrite(ILogWrite logwrite, String level);
}