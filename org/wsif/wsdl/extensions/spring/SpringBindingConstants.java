package org.wsif.wsdl.extensions.spring;

import com.ibm.wsdl.Constants;
import javax.xml.namespace.QName;

public class SpringBindingConstants
{

    public SpringBindingConstants()
    {
    }

    public static final String NS_URI_SPRING = "http://xyz.com/wsdl/spring/";
    public static final String ELEM_ADDRESS = "address";
    public static final QName Q_ELEM_SPRING_BINDING = new QName("http://xyz.com/wsdl/spring/", "binding");
    public static final QName Q_ELEM_SPRING_OPERATION = new QName("http://xyz.com/wsdl/spring/", "operation");
    public static final QName Q_ELEM_SPRING_ADDRESS = new QName("http://xyz.com/wsdl/spring/", "address");

}