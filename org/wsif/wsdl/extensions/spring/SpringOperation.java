package org.wsif.wsdl.extensions.spring;

public class SpringOperation extends org.apache.wsif.wsdl.extensions.java.JavaOperation
{

    public SpringOperation()
    {
        fieldElementType = SpringBindingConstants.Q_ELEM_SPRING_OPERATION;
        fieldRequired = null;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(super.toString());
        stringbuffer.append("\nSpringOperation (" + fieldElementType + "):");
        stringbuffer.append("\nrequired=" + fieldRequired);
        stringbuffer.append("\nmethodName=" + fieldMethodName);
        stringbuffer.append("\nmethodType=" + fieldMethodType);
        stringbuffer.append("\nparameterOrder=" + fieldParameterOrder);
        stringbuffer.append("\nreturnPart=" + fieldReturnPart);
        return stringbuffer.toString();
    }

    private static final long serialVersionUID = 1L;
}