package org.wsif.wsdl.extensions.spring;

public class SpringBinding extends org.apache.wsif.wsdl.extensions.java.JavaBinding
{

    public SpringBinding()
    {
        fieldElementType = SpringBindingConstants.Q_ELEM_SPRING_BINDING;
        fieldRequired = null;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(super.toString());
        stringbuffer.append("\nSpringBinding (" + fieldElementType + "):");
        stringbuffer.append("\nrequired=" + fieldRequired);
        return stringbuffer.toString();
    }

    private static final long serialVersionUID = 1L;
}